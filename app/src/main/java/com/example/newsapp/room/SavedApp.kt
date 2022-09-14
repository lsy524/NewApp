package com.example.newsapp.room

import android.app.Application

class SavedApp : Application() {

    val db by lazy {
        SavedDatabase.getInstance(this)
    }
}