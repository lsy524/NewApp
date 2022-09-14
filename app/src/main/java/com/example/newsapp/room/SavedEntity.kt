package com.example.newsapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "saved-table")
data class SavedEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val saved: Boolean = false,
    val author : String = "",
    val title : String= "",
    val description : String = "",
    val url : String = "",
    val urlToImage : String = "",
    val publishedAt : String = "",
    val content : String = ""
) : Serializable