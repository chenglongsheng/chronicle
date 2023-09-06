package com.buyehou.chronicle.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**
 * 记录的事件
 * @author Rosen
 * @date 2023/9/6 10:30
 */
@Entity
data class Event(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String?,
    val date: Date,
    @ColumnInfo("category_id") val categoryId: Int?,
    @ColumnInfo("content_id") val contentId: Int?
)