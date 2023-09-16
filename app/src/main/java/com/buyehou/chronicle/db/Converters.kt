package com.buyehou.chronicle.db

import androidx.room.TypeConverter
import java.util.Date

/**
 * @author Rosen
 * @date 2023/9/11 15:05
 */
class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

}