package com.ronny.assignment.data.database

import androidx.room.*

@Dao
interface DAOInterface {
    @Query("SELECT * FROM Item where page = :page ORDER BY watchers DESC")
    suspend fun getItems(page:Long): List<Item>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(channel: List<Item>)
}