package com.buyehou.chronicle.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.buyehou.chronicle.entity.Event

/**
 * @author Rosen
 * @date 2023/9/6 10:38
 */
@Dao
interface EventDao {
    @Query("SELECT * FROM event")
    fun getAll(): List<Event>

    @Insert
    fun insert(event: Event)

    @Delete
    fun delete(event: Event)
}