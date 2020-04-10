package com.makuno.memory.data.local.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
 data class GameCard(
    @PrimaryKey @NonNull val id: Long,
    val imageSrc: String
)