package com.buyehou.chronicle

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.buyehou.chronicle.group.GroupRecyclerView
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarLayout
import com.haibin.calendarview.CalendarView

open class MainActivity : AppCompatActivity(), CalendarView.OnCalendarSelectListener,
    CalendarView.OnYearChangeListener, View.OnClickListener {

    var mTextMonthDay: TextView? = null

    var mTextYear: TextView? = null

    var mTextLunar: TextView? = null

    var mTextCurrentDay: TextView? = null

    var mCalendarView: CalendarView? = null

    var mRelativeTool: RelativeLayout? = null
    var mYear = 0
    var mCalendarLayout: CalendarLayout? = null
    var mRecyclerView: GroupRecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initData()
    }

    @SuppressLint("SetTextI18n")
    fun initView() {
//        setStatusBarDarkMode()
        mTextMonthDay = findViewById(R.id.tv_month_day)
        mTextYear = findViewById(R.id.tv_year)
        mTextLunar = findViewById(R.id.tv_lunar)
        mRelativeTool = findViewById(R.id.rl_tool)
        mCalendarView = findViewById(R.id.calendarView)
        mTextCurrentDay = findViewById(R.id.tv_current_day)
        mTextMonthDay!!.setOnClickListener(View.OnClickListener {
            if (!mCalendarLayout!!.isExpand) {
                mCalendarLayout!!.expand()
                return@OnClickListener
            }
            mCalendarView!!.showYearSelectLayout(mYear)
            mTextLunar!!.visibility = View.GONE
            mTextYear!!.visibility = View.GONE
            mTextMonthDay!!.text = mYear.toString()
        })
        findViewById<FrameLayout>(R.id.fl_current).setOnClickListener(View.OnClickListener { mCalendarView!!.scrollToCurrent() })
        mCalendarLayout = findViewById(R.id.calendarLayout)
        mCalendarView!!.setOnCalendarSelectListener(this)
        mCalendarView!!.setOnYearChangeListener(this)
        mTextYear!!.text = mCalendarView!!.curYear.toString()
        mYear = mCalendarView!!.curYear
        mTextMonthDay!!.text =
            mCalendarView!!.curMonth.toString() + "月" + mCalendarView!!.curDay + "日"
        mTextLunar!!.text = "今日"
        mTextCurrentDay!!.text = mCalendarView!!.curDay.toString()
    }

    fun initData() {
        val year = mCalendarView!!.curYear
        val month = mCalendarView!!.curMonth
        val map = HashMap<String, Calendar>()
        map[getSchemeCalendar(year, month, 3, -0xbf24db, "假").toString()] =
            getSchemeCalendar(year, month, 3, -0xbf24db, "假")
        map[getSchemeCalendar(year, month, 6, -0x196ec8, "事").toString()] =
            getSchemeCalendar(year, month, 6, -0x196ec8, "事")
        map[getSchemeCalendar(year, month, 9, -0x20ecaa, "议").toString()] =
            getSchemeCalendar(year, month, 9, -0x20ecaa, "议")
        map[getSchemeCalendar(year, month, 13, -0x123a93, "记").toString()] =
            getSchemeCalendar(year, month, 13, -0x123a93, "记")
        map[getSchemeCalendar(year, month, 14, -0x123a93, "记").toString()] =
            getSchemeCalendar(year, month, 14, -0x123a93, "记")
        map[getSchemeCalendar(year, month, 15, -0x5533bc, "假").toString()] =
            getSchemeCalendar(year, month, 15, -0x5533bc, "假")
        map[getSchemeCalendar(year, month, 18, -0x43ec10, "记").toString()] =
            getSchemeCalendar(year, month, 18, -0x43ec10, "记")
        map[getSchemeCalendar(year, month, 25, -0xec5310, "假").toString()] =
            getSchemeCalendar(year, month, 25, -0xec5310, "假")
        map[getSchemeCalendar(year, month, 27, -0xec5310, "多").toString()] =
            getSchemeCalendar(year, month, 27, -0xec5310, "多")
        //此方法在巨大的数据量上不影响遍历性能，推荐使用
        mCalendarView!!.setSchemeDate(map)
//        mRecyclerView = findViewById(R.id.recyclerView)
//        mRecyclerView.setLayoutManager(LinearLayoutManager(this))
//        mRecyclerView.addItemDecoration(GroupItemDecoration<String, Article>())
//        mRecyclerView.setAdapter(ArticleAdapter(this))
//        mRecyclerView.notifyDataSetChanged()
    }


    override fun onClick(v: View) {
        when (v.id) {
//            R.id.ll_flyme -> MeiZuActivity.show(this)
//            R.id.ll_simple -> SimpleActivity.show(this)
//            R.id.ll_colorful -> com.haibin.calendarviewproject.colorful.ColorfulActivity.show(this)
//            R.id.ll_index -> IndexActivity.show(this)
        }
    }

    open fun getSchemeCalendar(
        year: Int, month: Int, day: Int, color: Int, text: String
    ): Calendar {
        val calendar = Calendar()
        calendar.year = year
        calendar.month = month
        calendar.day = day
        calendar.schemeColor = color //如果单独标记颜色、则会使用这个颜色
        calendar.scheme = text
        return calendar
    }


    override fun onCalendarOutOfRange(calendar: Calendar?) {}

    @SuppressLint("SetTextI18n")
    override fun onCalendarSelect(calendar: Calendar, isClick: Boolean) {
        mTextLunar!!.visibility = View.VISIBLE
        mTextYear!!.visibility = View.VISIBLE
        mTextMonthDay!!.text = calendar.month.toString() + "月" + calendar.day + "日"
        mTextYear!!.text = calendar.year.toString()
        mTextLunar!!.text = calendar.lunar
        mYear = calendar.year
    }

    override fun onYearChange(year: Int) {
        mTextMonthDay!!.text = year.toString()
    }


}