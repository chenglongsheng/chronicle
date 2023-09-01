package com.buyehou.chronicle

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.buyehou.chronicle.databinding.ActivityMainBinding
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView

open class MainActivity : AppCompatActivity(), CalendarView.OnCalendarSelectListener,
    CalendarView.OnYearChangeListener, View.OnClickListener {
    var mYear = 0

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initData()
    }

    @SuppressLint("SetTextI18n")
    fun initView() {
        binding.tvMonthDay.setOnClickListener {
            if (!binding.calendarLayout.isExpand) {
                binding.calendarLayout.expand()
                return@setOnClickListener
            }
            binding.calendarView.showYearSelectLayout(mYear)
            binding.tvLunar.visibility = View.GONE
            binding.tvYear.visibility = View.GONE
            binding.tvMonthDay.text = mYear.toString()
        }
        binding.flCurrent.setOnClickListener {
            binding.calendarView.scrollToCurrent()
        }
        binding.calendarView.setOnCalendarSelectListener(this)
        binding.calendarView.setOnYearChangeListener(this)
        binding.tvYear.text = binding.calendarView.curYear.toString()
        mYear = binding.calendarView.curYear
        binding.tvMonthDay.text =
            binding.calendarView.curMonth.toString() + "月" + binding.calendarView.curDay + "日"
        binding.tvLunar.text = "今日"
        binding.tvCurrentDay.text = binding.calendarView.curDay.toString()
    }

    fun initData() {
        val year = binding.calendarView.curYear
        val month = binding.calendarView.curMonth
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
        binding.calendarView.setSchemeDate(map)
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
        calendar.addScheme(Calendar.Scheme())
        calendar.addScheme(-0xff7800, "假")
        calendar.addScheme(-0xff7800, "节")
        return calendar
    }


    override fun onCalendarOutOfRange(calendar: Calendar?) {}

    @SuppressLint("SetTextI18n")
    override fun onCalendarSelect(calendar: Calendar, isClick: Boolean) {
        binding.tvLunar.visibility = View.VISIBLE
        binding.tvYear.visibility = View.VISIBLE
        binding.tvMonthDay.text = calendar.month.toString() + "月" + calendar.day + "日"
        binding.tvYear.text = calendar.year.toString()
        binding.tvLunar.text = calendar.lunar
        mYear = calendar.year

        Log.e(
            "onDateSelected", "  -- " + calendar.year +
                    "  --  " + calendar.month +
                    "  -- " + calendar.day +
                    "  --  " + isClick + "  --   " + calendar.scheme
        )
    }

    override fun onYearChange(year: Int) {
        binding.tvMonthDay.text = year.toString()
    }

}