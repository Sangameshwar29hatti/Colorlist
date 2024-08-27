package com.example.colorlist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Color::class], version = 1)
abstract class ColorDatabase : RoomDatabase() {
    abstract fun colorDao(): ColorDao

    companion object {
        private var instance: ColorDatabase? = null

        fun getInstance(context: Context): ColorDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    ColorDatabase::class.java,
                    "color_database"
                ).build()
            }
            return instance!!
        }
    }
}