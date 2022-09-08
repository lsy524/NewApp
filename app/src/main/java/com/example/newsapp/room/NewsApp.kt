package com.example.newsapp.room

import android.app.Application

class NewsApp : Application() {
    val db by lazy {
        NewsDatabase.getInstance(this)
    }
}