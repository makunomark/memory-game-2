package com.makuno.memory.data.local.util

import androidx.room.TypeConverter
import java.util.*

 class Converters {

    @TypeConverter
    fun dateToTimestamp(date: Date): Long = date.time

    @TypeConverter
    fun timestampToDate(timestamp: Long) = Date(timestamp)
}
