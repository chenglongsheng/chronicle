package com.buyehou.chronicle.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.TextUtils
import com.buyehou.chronicle.extension.px
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.MonthView

/**
 * @author buyehou
 * @date 2023/9/1 23:41
 */
class CustomMonthView(context: Context) : MonthView(context) {

    private var mRadius = 0

    /**
     * 自定义魅族标记的文本画笔
     */
    private val mTextPaint = Paint()

    /**
     * 24节气画笔
     */
    private val mSolarTermTextPaint = Paint()

    /**
     * 背景圆点
     */
    private val mPointPaint = Paint()

    /**
     * 今天的背景色
     */
    private val mCurrentDayPaint = Paint()

    /**
     * 圆点半径
     */
    private var mPointRadius = 0f

    private var mPadding = 0

    private var mCircleRadius = 0f

    /**
     * 自定义魅族标记的圆形背景
     */
    private val mSchemeBasicPaint = Paint()

    private var mSchemeBaseLine = 0f

    init {
        mTextPaint.textSize = 8f.px.toFloat()
        mTextPaint.color = -0x1
        mTextPaint.isAntiAlias = true
        mTextPaint.isFakeBoldText = true


        mSolarTermTextPaint.color = -0xb76201
        mSolarTermTextPaint.isAntiAlias = true
        mSolarTermTextPaint.textAlign = Paint.Align.CENTER

        mSchemeBasicPaint.isAntiAlias = true
        mSchemeBasicPaint.style = Paint.Style.FILL
        mSchemeBasicPaint.textAlign = Paint.Align.CENTER
        mSchemeBasicPaint.isFakeBoldText = true
        mSchemeBasicPaint.color = Color.WHITE


        mCurrentDayPaint.isAntiAlias = true
        mCurrentDayPaint.style = Paint.Style.FILL
        mCurrentDayPaint.color = -0x151516

        mPointPaint.isAntiAlias = true
        mPointPaint.style = Paint.Style.FILL
        mPointPaint.textAlign = Paint.Align.CENTER
        mPointPaint.color = Color.RED

        mCircleRadius = 7f.px.toFloat()

        mPadding = 3f.px

        mPointRadius = 2f.px.toFloat()

        val metrics = mSchemeBasicPaint.fontMetrics
        mSchemeBaseLine =
            mCircleRadius - metrics.descent + (metrics.bottom - metrics.top) / 2 + 1f.px
    }

    override fun onPreviewHook() {
        mSolarTermTextPaint.textSize = mCurMonthLunarTextPaint.textSize
        mRadius = mItemWidth.coerceAtMost(mItemHeight) / 11 * 5
    }

    override fun onDrawSelected(
        canvas: Canvas, calendar: Calendar, x: Int, y: Int, hasScheme: Boolean
    ): Boolean {
        val cx = x + mItemWidth / 2
        val cy = y + mItemHeight / 2
        canvas.drawCircle(cx.toFloat(), cy.toFloat(), mRadius.toFloat(), mSelectedPaint)
        return true
    }

    override fun onDrawScheme(canvas: Canvas, calendar: Calendar, x: Int, y: Int) {
        val isSelected = isSelected(calendar)
        if (isSelected) {
            mPointPaint.color = Color.WHITE
        } else {
            mPointPaint.color = Color.GRAY
        }

        canvas.drawCircle(
            (x + mItemWidth / 2).toFloat(),
            (y + mItemHeight - 3 * mPadding).toFloat(),
            mPointRadius,
            mPointPaint
        )
    }

    override fun onDrawText(
        canvas: Canvas, calendar: Calendar, x: Int, y: Int, hasScheme: Boolean, isSelected: Boolean
    ) {
        val cx = x + mItemWidth / 2
        val cy = y + mItemHeight / 2
        val top = y - mItemHeight / 6

        if (calendar.isCurrentDay && !isSelected) {
            canvas.drawCircle(cx.toFloat(), cy.toFloat(), mRadius.toFloat(), mCurrentDayPaint)
        }

        if (hasScheme) {
            canvas.drawCircle(
                x + mItemWidth - mPadding - mCircleRadius / 2,
                y + mPadding + mCircleRadius,
                mCircleRadius,
                mSchemeBasicPaint
            )
            mTextPaint.color = calendar.schemeColor
            canvas.drawText(
                calendar.scheme,
                x + mItemWidth - mPadding - mCircleRadius,
                y + mPadding + mSchemeBaseLine,
                mTextPaint
            )
        }

        //当然可以换成其它对应的画笔就不麻烦，
        if (calendar.isWeekend && calendar.isCurrentMonth) {
            mCurMonthTextPaint.color = -0xb76201
            mCurMonthLunarTextPaint.color = -0xb76201
            mSchemeTextPaint.color = -0xb76201
            mSchemeLunarTextPaint.color = -0xb76201
            mOtherMonthLunarTextPaint.color = -0xb76201
            mOtherMonthTextPaint.color = -0xb76201
        } else {
            mCurMonthTextPaint.color = -0xcccccd
            mCurMonthLunarTextPaint.color = -0x303031
            mSchemeTextPaint.color = -0xcccccd
            mSchemeLunarTextPaint.color = -0x303031
            mOtherMonthTextPaint.color = -0x1e1e1f
            mOtherMonthLunarTextPaint.color = -0x1e1e1f
        }

        if (isSelected) {
            canvas.drawText(
                calendar.day.toString(), cx.toFloat(), mTextBaseLine + top, mSelectTextPaint
            )
            canvas.drawText(
                calendar.lunar,
                cx.toFloat(),
                mTextBaseLine + y + mItemHeight / 10,
                mSelectedLunarTextPaint
            )
        } else if (hasScheme) {
            canvas.drawText(
                calendar.day.toString(),
                cx.toFloat(),
                mTextBaseLine + top,
                if (calendar.isCurrentMonth) mSchemeTextPaint else mOtherMonthTextPaint
            )
            canvas.drawText(
                calendar.lunar,
                cx.toFloat(),
                mTextBaseLine + y + mItemHeight / 10,
                if (!TextUtils.isEmpty(calendar.solarTerm)) mSolarTermTextPaint else mSchemeLunarTextPaint
            )
        } else {
            canvas.drawText(
                calendar.day.toString(),
                cx.toFloat(),
                mTextBaseLine + top,
                if (calendar.isCurrentDay) mCurDayTextPaint
                else if (calendar.isCurrentMonth) mCurMonthTextPaint
                else mOtherMonthTextPaint
            )
            canvas.drawText(
                calendar.lunar,
                cx.toFloat(),
                mTextBaseLine + y + mItemHeight / 10,
                if (calendar.isCurrentDay) mCurDayLunarTextPaint
                else if (calendar.isCurrentMonth)
                    if (!TextUtils.isEmpty(calendar.solarTerm)) mSolarTermTextPaint
                    else mCurMonthLunarTextPaint
                else mOtherMonthLunarTextPaint
            )
        }
    }
}