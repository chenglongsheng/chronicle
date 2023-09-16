package com.buyehou.chronicle.base

/**
 * @author Rosen
 * @date 2023/9/6 11:31
 */
interface BaseDao<T> {
    fun getAll(): List<T>
    fun insert(value: T)
    fun delete(value: T)
    fun update(value: T)
}