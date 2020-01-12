package com.makuno.memory.data.local.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal data class Character(
    @PrimaryKey @NonNull val id: Int,
    val name: String,
    val url: String
)