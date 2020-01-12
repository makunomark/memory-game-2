package com.makuno.memory.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.makuno.memory.data.local.entities.Character

@Dao
internal interface CharacterDao : BaseDao<Character> {

    @Query("SELECT * FROM character")
    suspend fun getCharacters(): List<Character>
}
