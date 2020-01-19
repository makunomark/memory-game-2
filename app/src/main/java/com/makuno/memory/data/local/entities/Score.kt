package com.makuno.memory.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Score(
    val time: Long,
    val moves: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L
    var date: Date = Date()
}
