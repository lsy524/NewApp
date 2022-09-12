package com.example.newsapp.database

import android.app.Application

class NewsApp : Application(){
    val db by lazy {
        NewsDatabase.getInstance(this)
    }
}