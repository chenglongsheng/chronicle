package com.buyehou.chronicle.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.buyehou.chronicle.base.BaseDao
import com.buyehou.chronicle.entity.Category

/**
 * @author Rosen
 * @date 2023/9/6 11:29
 */
@Dao
interface CategoryDao : BaseDao<Category> {
    @Query("select * from category")
    override fun getAll(): List<Category>

    @Insert
    override fun insert(value: Category)

    @Delete
    override fun delete(value: Category)

    @Update
    override fun update(value: Category)

}