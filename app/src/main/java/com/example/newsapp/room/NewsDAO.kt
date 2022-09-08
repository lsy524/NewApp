package com.example.newsapp.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDAO {

    @Insert
    suspend fun insert(newsEntity: NewsEntity)



    @Query("SELECT * FROM `headlineNews-table`")
    fun fetchAllNews() : Flow<List<NewsEntity>>


}