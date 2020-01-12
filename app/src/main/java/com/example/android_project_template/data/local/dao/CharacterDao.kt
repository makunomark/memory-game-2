package com.example.android_project_template.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.android_project_template.data.local.entities.Character

@Dao
internal interface CharacterDao : BaseDao<Character> {

    @Query("SELECT * FROM character")
    suspend fun getCharacters(): List<Character>
}
