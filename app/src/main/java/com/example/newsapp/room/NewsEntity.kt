package com.example.newsapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "headlineNews-table")
data class NewsEntity (

    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val title : String = "",
    val description : String = "",
    val author : String = "",
    val publishedAt : String = ""
)