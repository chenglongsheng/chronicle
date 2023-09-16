package com.buyehou.chronicle.base

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Rosen
 * @date 2023/9/11 16:42
 */
abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {

    private val mDataList = arrayListOf<T>()

    private var mOnItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(getResId(), parent, false)
        return BaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        onBindItemHolder(holder, position)
        holder.itemView.setOnClickListener {
            mOnItemClickListener?.onClick(it, position)
        }
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder, position: Int, payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            onBindItemHolder(holder, position)
        } else {
            onBindItemHolder(holder, position, payloads)
        }
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    fun getDataList(): List<T> {
        return mDataList
    }

    fun setDataList(data: List<T>) {
        clear()
        mDataList.addAll(data)
        notifyDataSetChanged()
    }

    fun addAll(data: List<T>) {
        val lastIndex = this.mDataList.size
        if (mDataList.addAll(data)) {
            notifyItemRangeInserted(lastIndex, data.size)
        }
    }

    fun remove(position: Int) {
        mDataList.removeAt(position)
        notifyItemRemoved(position)

        if (position != getDataList().size) {
            notifyItemRangeChanged(position, mDataList.size - position)
        }
    }

    fun clear() {
        mDataList.clear()
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = onItemClickListener
    }

    abstract fun getResId(): Int

    abstract fun onBindItemHolder(holder: BaseViewHolder, position: Int)

    fun onBindItemHolder(holder: BaseViewHolder, position: Int, payloads: List<Any>) {}

    class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var mItemView = itemView

        private val viewSparseArray = SparseArray<View>()

        fun getView(viewId: Int): View {
            var view = viewSparseArray[viewId]
            if (view == null) {
                view = itemView.findViewById(viewId)
                viewSparseArray.put(viewId, view)
            }
            return view
        }

        fun getItemView(): View = mItemView

    }

    interface OnItemClickListener {
        fun onClick(view: View, position: Int)
    }

}