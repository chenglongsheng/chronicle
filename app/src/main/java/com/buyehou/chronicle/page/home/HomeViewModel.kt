package com.buyehou.chronicle.page.home

import androidx.lifecycle.MutableLiveData
import com.buyehou.chronicle.base.BaseViewModel
import com.haibin.calendarview.Calendar

/**
 * @author Rosen
 * @date 2023/9/7 12:21
 */
class HomeViewModel : BaseViewModel() {

    val curCalendar = MutableLiveData<Calendar>()

}