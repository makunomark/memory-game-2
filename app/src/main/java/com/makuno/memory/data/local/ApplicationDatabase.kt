package com.makuno.memory.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.makuno.memory.data.local.dao.CharacterDao
import com.makuno.memory.data.local.entities.Character
import com.makuno.memory.data.local.util.Converters

@Database(entities = [Character::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
internal abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun getScoreDao(): CharacterDao
}
