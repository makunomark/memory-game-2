package com.example.android_project_template.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android_project_template.data.local.dao.CharacterDao
import com.example.android_project_template.data.local.entities.Character
import com.example.android_project_template.data.local.util.Converters

@Database(entities = [Character::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
internal abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun getScoreDao(): CharacterDao
}
