package com.example.newsapp.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDAO {

    @Insert
    suspend fun insert(newsEntity: NewsEntity)

    @Update
    suspend fun update(newsEntity: NewsEntity)

    @Delete
    suspend fun delete(newsEntity: NewsEntity)


    @Query("SELECT * FROM `news-table`")
    fun fetchAllEmployees() : Flow<List<NewsEntity>>

}