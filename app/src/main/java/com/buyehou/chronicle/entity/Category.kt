package com.buyehou.chronicle.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 分类标签
 * @author Rosen
 * @date 2023/9/6 10:28
 */
@Entity
data class Category(@PrimaryKey(true) val id: Int, val name: String)