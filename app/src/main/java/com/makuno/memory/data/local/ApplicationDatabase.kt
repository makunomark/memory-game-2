package com.makuno.memory.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.makuno.memory.data.local.dao.GameCardDao
import com.makuno.memory.data.local.dao.ScoreDao
import com.makuno.memory.data.local.entities.GameCard
import com.makuno.memory.data.local.entities.Score
import com.makuno.memory.data.local.util.Converters

@Database(entities = [GameCard::class, Score::class], version = 4, exportSchema = false)
@TypeConverters(Converters::class)
internal abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun getProductDao(): GameCardDao

    abstract fun getScoreDao(): ScoreDao
}
