package com.buyehou.chronicle.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 事件内容
 * @author Rosen
 * @date 2023/9/6 10:35
 */
@Entity
data class Content(@PrimaryKey(true) val id: Int, val content: String)