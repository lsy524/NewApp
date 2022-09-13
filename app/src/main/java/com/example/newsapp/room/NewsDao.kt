package com.example.newsapp.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert
    suspend fun insert(newsEntity: NewsEntity)

    @Update
    suspend fun update(newsEntity: NewsEntity)

    @Delete
    suspend fun delete(newsEntity: NewsEntity)

    @Query("SELECT * FROM `news-table`")

    fun fetchAllNews() : Flow<List<NewsEntity>>
}