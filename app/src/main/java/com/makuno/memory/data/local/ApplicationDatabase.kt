package com.makuno.memory.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.makuno.memory.data.local.dao.ProductDao
import com.makuno.memory.data.local.entities.Product
import com.makuno.memory.data.local.util.Converters

@Database(entities = [Product::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
internal abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun getScoreDao(): ProductDao
}
