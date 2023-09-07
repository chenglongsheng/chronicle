package com.buyehou.chronicle.page.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.buyehou.chronicle.BottomSheetDialogFragment
import com.buyehou.chronicle.R
import com.buyehou.chronicle.base.BaseFragment
import com.buyehou.chronicle.databinding.FragmentHomeBinding
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView

/**
 * @author Rosen
 * @date 2023/9/6 16:54
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>(), CalendarView.OnCalendarSelectListener,
    CalendarView.OnYearChangeListener {

    /**
     * 当前年份
     */
    private var mYear = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        initData()
    }

    @SuppressLint("SetTextI18n")
    fun initView() {
        mYear = binding.calendarView.curYear

        binding.tvYearMonth.text = resources.getString(
            R.string.year_month, binding.calendarView.curYear, binding.calendarView.curMonth
        )
        binding.tvToday.text = "今日"

        binding.tvCurrentDay.text = binding.calendarView.curDay.toString()
    }

    private fun initListener() {
        // 切换年月展开视图
        binding.tvYearMonth.setOnClickListener {
            if (!binding.calendarLayout.isExpand) {
                binding.calendarLayout.expand()
                return@setOnClickListener
            }
            binding.calendarView.showYearSelectLayout(mYear)
            binding.tvToday.visibility = View.GONE

            binding.tvYearMonth.text = mYear.toString()
        }
        // 回到当前日期
        binding.flCurrent.setOnClickListener {
            binding.calendarView.scrollToCurrent()
        }
        // 日历选中
        binding.calendarView.setOnCalendarSelectListener(this)
        // 年份改变
        binding.calendarView.setOnYearChangeListener(this)
        // 记录事件
        binding.floatingActionBtn.setOnClickListener {
            BottomSheetDialogFragment().show(
                parentFragmentManager,
                BottomSheetDialogFragment.TAG
            )
        }
        // 展开标签分类
        binding.flCategory.setOnClickListener {

        }
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

    fun getSchemeCalendar(year: Int, month: Int, day: Int, color: Int, text: String): Calendar {
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

    override fun onCalendarOutOfRange(calendar: Calendar?) {
        // no code
    }

    override fun onCalendarSelect(calendar: Calendar, isClick: Boolean) {
        mYear = calendar.year
    }

    override fun onYearChange(year: Int) {
        binding.tvYearMonth.text = year.toString()
    }
}