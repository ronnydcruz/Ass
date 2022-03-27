package com.ronny.assignment.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Item::class],version = 1,exportSchema = false)

abstract class DatabaseClass :RoomDatabase() {
    abstract fun daointerface():DAOInterface
}