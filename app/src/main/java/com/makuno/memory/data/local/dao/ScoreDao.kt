package com.makuno.memory.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.makuno.memory.data.local.entities.Score

@Dao
internal interface ScoreDao : BaseDao<Score> {

    @Query("SELECT * FROM score ORDER BY date DESC")
    suspend fun getScores(): List<Score>

    @Query("SELECT * FROM score ORDER BY moves DESC, time DESC LIMIT 1")
    suspend fun getHighestScoreCombination(): Score
}
