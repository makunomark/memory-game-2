package com.example.android_project_template.data.local.util

import androidx.room.TypeConverter
import java.util.*

internal class Converters {

    @TypeConverter
    fun dateToTimestamp(date: Date): Long = date.time

    @TypeConverter
    fun timestampToDate(timestamp: Long) = Date(timestamp)
}
