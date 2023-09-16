package com.buyehou.chronicle.page.controller.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Rosen
 * @date 2023/9/6 16:55
 */
class CategoryAdapter<T> : RecyclerView.Adapter<CategoryAdapter<T>.ItemViewHolder>() {

    private val data = mutableListOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

    }

    fun addData(data: List<T>) {

    }

    inner class ItemViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}