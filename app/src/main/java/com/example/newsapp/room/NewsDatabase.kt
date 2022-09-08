package com.example.newsapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [NewsEntity::class])
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao() : NewsDAO

    companion object {
        @Volatile
        private var INSTANCE : NewsDatabase? = null

        fun getInstance(context: Context) : NewsDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NewsDatabase::class.java,
                        "headlineNews-table"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }




}