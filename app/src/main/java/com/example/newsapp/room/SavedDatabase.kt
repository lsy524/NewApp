package com.example.newsapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [SavedEntity::class])
abstract class SavedDatabase : RoomDatabase() {

    abstract fun savedDao(): SavedDao

    companion object {
        @Volatile
        private var INSTANCE: SavedDatabase? = null

        fun getInstance(context: Context): SavedDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SavedDatabase::class.java,
                        "saved_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}