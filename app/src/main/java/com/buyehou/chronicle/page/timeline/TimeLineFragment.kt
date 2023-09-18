package com.buyehou.chronicle.page.timeline

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.buyehou.chronicle.R
import com.buyehou.chronicle.base.BaseFragment
import com.buyehou.chronicle.databinding.FragmentTimeLineBinding
import com.buyehou.chronicle.databinding.ItemTimeLineEventBinding

/**
 * @author Rosen
 * @date 2023/9/16 17:00
 */
class TimeLineFragment : BaseFragment<FragmentTimeLineBinding>() {

    private val mDataList = mutableListOf<Int>()

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun setListener() {
        TODO("Not yet implemented")
    }

    inner class TimeLineEventAdapter : RecyclerView.Adapter<ItemViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_time_line_event, parent)
            return ItemViewHolder(view)
        }

        override fun getItemCount(): Int {
            return mDataList.size
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.binding.tvTime
        }

    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ItemTimeLineEventBinding = ItemTimeLineEventBinding.bind(itemView)
    }

}