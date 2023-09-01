package com.buyehou.chronicle.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import com.buyehou.chronicle.extension.px
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarUtil.isLeapYear
import com.haibin.calendarview.R
import com.haibin.calendarview.YearView

/**
 * @author buyehou
 * @date 2023/9/1 22:18
 */
class CustomYearView(context: Context) : YearView(context) {

    private var mTextPadding = 0

    /**
     * 闰年字体
     */
    private val mLeapYearTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        mTextPadding = 3f.px

        mLeapYearTextPaint.textSize = 12f.px.toFloat()
        mLeapYearTextPaint.color = 0xffd1d1d1.toInt()
        mLeapYearTextPaint.isAntiAlias = true
        mLeapYearTextPaint.isFakeBoldText = true
    }

    override fun onDrawMonth(
        canvas: Canvas, year: Int, month: Int, x: Int, y: Int, width: Int, height: Int
    ) {
        val text = resources.getStringArray(R.array.month_string_array)[month - 1]
        canvas.drawText(
            text,
            (x + mItemWidth / 2 - mTextPadding).toFloat(),
            y + mMonthTextBaseLine,
            mMonthTextPaint
        )
        if (month == 2 && isLeapYear(year)) {
            val w: Float = getTextWidth(mMonthTextPaint, text)

            canvas.drawText(
                "闰年",
                x + mItemWidth / 2 - mTextPadding + w + 6f.px,
                y + mMonthTextBaseLine,
                mLeapYearTextPaint
            )
        }
    }

    override fun onDrawWeek(
        canvas: Canvas, week: Int, x: Int, y: Int, width: Int, height: Int
    ) {
        val text = resources.getStringArray(R.array.year_view_week_string_array)[week]
        canvas.drawText(text, (x + width / 2).toFloat(), y + mWeekTextBaseLine, mWeekTextPaint)
    }

    override fun onDrawSelected(
        canvas: Canvas, calendar: Calendar?, x: Int, y: Int, hasScheme: Boolean
    ): Boolean {
        val cx = x + mItemWidth / 2
        val cy = y + mItemHeight / 2
        val radius = mItemWidth.coerceAtMost(mItemHeight) / 8 * 5
        canvas.drawCircle(cx.toFloat(), cy.toFloat(), radius.toFloat(), mSelectedPaint)
        return true
    }

    override fun onDrawScheme(canvas: Canvas, calendar: Calendar?, x: Int, y: Int) {
        // no code
    }

    override fun onDrawText(
        canvas: Canvas, calendar: Calendar, x: Int, y: Int, hasScheme: Boolean, isSelected: Boolean
    ) {
        val baselineY = mTextBaseLine + y
        val cx = x + mItemWidth / 2

        if (isSelected) {
            canvas.drawText(
                calendar.day.toString(),
                cx.toFloat(),
                baselineY,
                if (hasScheme) mSchemeTextPaint else mSelectTextPaint
            )
        } else if (hasScheme) {
            canvas.drawText(
                calendar.day.toString(),
                cx.toFloat(),
                baselineY,
                if (calendar.isCurrentDay) mCurDayTextPaint else mSchemeTextPaint
            )
        } else {
            canvas.drawText(
                calendar.day.toString(),
                cx.toFloat(),
                baselineY,
                if (calendar.isCurrentDay) mCurDayTextPaint else mCurMonthTextPaint
            )
        }
    }

    private fun getTextWidth(paint: Paint, text: String): Float {
        return paint.measureText(text)
    }

}