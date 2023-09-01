package com.buyehou.chronicle.widget

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.widget.TextView
import com.buyehou.chronicle.R
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.WeekBar

/**
 * @author buyehou
 * @date 2023/9/1 23:48
 */
class CustomWeekBar(context: Context):WeekBar(context) {

    private var mPreSelectedIndex = 0

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_week_bar, this, true)
        setBackgroundColor(Color.WHITE)
    }

    override fun onDateSelected(calendar: Calendar, weekStart: Int, isClick: Boolean) {
        getChildAt(mPreSelectedIndex).isSelected = false
        val viewIndex = getViewIndexByCalendar(calendar, weekStart)
        getChildAt(viewIndex).isSelected = true
        mPreSelectedIndex = viewIndex
    }

    override fun onWeekStartChange(weekStart: Int) {
        for (i in 0 until childCount) {
            (getChildAt(i) as TextView).text = getWeekString(i, weekStart)
        }
    }

    /**
     * 或者周文本，这个方法仅供父类使用
     * @param index index
     * @param weekStart weekStart
     * @return 或者周文本
     */
    private fun getWeekString(index: Int, weekStart: Int): String? {
        val weeks = resources.getStringArray(R.array.chinese_week_string_array)
        if (weekStart == 1) {
            return weeks[index]
        }
        return if (weekStart == 2) {
            weeks[if (index == 6) 0 else index + 1]
        } else weeks[if (index == 0) 6 else index - 1]
    }

}