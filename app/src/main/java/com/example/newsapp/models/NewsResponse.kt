package com.example.newsapp.models

import java.io.Serializable

data class NewsResponse(

    val articles: List<Articles>

) : Serializable

data class Articles(
    val author : String = "",
    val title : String= "",
    val description : String = "",
    val url : String = "",
    val urlToImage : String = "",
    val publishedAt : String = "",
    val content : String = ""
) : Serializable


