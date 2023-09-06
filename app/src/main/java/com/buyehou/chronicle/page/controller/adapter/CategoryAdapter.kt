package com.buyehou.chronicle.page.controller.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Rosen
 * @date 2023/9/6 16:55
 */
class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(parent)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

    }

    inner class ItemViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}