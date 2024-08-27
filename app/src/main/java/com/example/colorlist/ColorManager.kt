package com.example.colorlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.colorlist.db.ColorDao
import com.example.colorlist.db.ColorDatabase
import com.example.colorlist.model.Color
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ColorManager(application: Application) : AndroidViewModel(application) {

    private val colorDao: ColorDao
    private val colorDatabase: ColorDatabase

    private val _colors = MutableLiveData<List<Color>>()
    val colors: LiveData<List<Color>> = _colors

    private val _pendingSyncCount = MutableLiveData<Int>()
    val pendingSyncCount: LiveData<Int> = _pendingSyncCount

    init {
        colorDatabase = ColorDatabase.getDatabase(application)
        colorDao = colorDatabase.colorDao

        // Initialize the list of colors from local storage
        getColorListFromDb()
    }

    private fun getColorListFromDb() {
        CoroutineScope(Dispatchers.IO).launch {
            val colorsFromDb = colorDao.getAllColors()
            _colors.postValue(colorsFromDb)
        }
    }

    fun addColor(color: Color) {
        // Add a new color to the local database
        CoroutineScope(Dispatchers.IO).launch {
            colorDao.insertColor(color)
            getColorListFromDb()
        }
    }

    fun syncColors() {
        // Get the list of pending colors to sync
        CoroutineScope(Dispatchers.IO).launch {
            val pendingColors = colorDao.getPendingColors()
            // Sync the colors with the cloud database (TO DO: implement cloud database sync)
            // Update the local database with the synced colors
            pendingColors.forEach { color ->
                color.synced = true
                colorDao.updateColor(color)
            }
            // Update the pending sync count
            _pendingSyncCount.postValue(colorDao.getPendingSyncCount())
        }
    }

    fun getPendingSyncCount(): Int {
        return _pendingSyncCount.value ?: 0
    }
}