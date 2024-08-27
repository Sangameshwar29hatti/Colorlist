package com.example.colorlist.repository

import com.example.colorlist.db.ColorDao
import com.example.colorlist.model.Color
import kotlinx.coroutines.flow.Flow

class ColorRepository(private val colorDao: ColorDao) {
    suspend fun insertColor(color: Color) {
        colorDao.insertColor(color)
    }

    fun getAllColors(): Flow<List<Color>> {
        return colorDao.getAllColors()
    }

    suspend fun deleteAllColors() {
        colorDao.deleteAllColors()
    }
}