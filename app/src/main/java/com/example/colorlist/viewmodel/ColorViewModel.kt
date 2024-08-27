package com.example.colorlist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.colorlist.repository.ColorRepository
import kotlinx.coroutines.launch

class ColorViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ColorRepository(ColorDatabase.getInstance(application).colorDao())

    fun insertColor(color: Color) {
        viewModelScope.launch {
            repository.insertColor(color)
        }
    }

    fun getAllColors() = repository.getAllColors()

    fun deleteAllColors() {
        viewModelScope.launch {
            repository.deleteAllColors()
        }
    }
}