package com.example.newsapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news-table")
data class NewsEntity (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,

    val status : String = "",
    val totalResult : Int = 0,
    val author : String = "",
    val title : String = "",
    val description : String = "",
    val url : String = "",
    val urlToImage : String = "",
    val publishedAt : String = "",
    val content : String = "",
    val articles : List<Articles>
)

data class Articles(
    val author : String = "",
    val title : String= "",
    val description : String = "",
    val url : String = "",
    val urlToImage : String = "",
    val publishedAt : String = "",
    val content : String = ""
)