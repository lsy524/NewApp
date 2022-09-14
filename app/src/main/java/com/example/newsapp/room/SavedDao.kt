package com.example.newsapp.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedDao {

    @Insert
    suspend fun insert(savedEntity: SavedEntity)

    @Delete
    suspend fun delete(savedEntity: SavedEntity)

    @Query("SELECT * FROM `saved-table`")
    fun fetchAllSaved() : Flow<List<SavedEntity>>

}