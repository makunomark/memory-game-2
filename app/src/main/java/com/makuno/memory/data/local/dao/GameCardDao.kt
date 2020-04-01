package com.makuno.memory.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.makuno.memory.data.local.entities.GameCard

@Dao
 interface GameCardDao : BaseDao<GameCard> {

    @Query("SELECT * FROM gamecard ORDER BY RANDOM() LIMIT 10")
    suspend fun getProducts(): List<GameCard>?
}
