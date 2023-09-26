package com.buyehou.chronicle.page.controller

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.buyehou.chronicle.R
import com.buyehou.chronicle.base.BaseFragment
import com.buyehou.chronicle.databinding.FragmentAddEventBinding
import com.buyehou.chronicle.page.home.HomeFragment
import com.haibin.calendarview.Calendar

/**
 * @author Rosen
 * @date 2023/9/6 16:48
 */
class AddEventFragment : BaseFragment<FragmentAddEventBinding>() {

    private var calendar: Calendar? = null

    companion object {
        private const val TAG = "AddEventFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        calendar = arguments?.getSerializable(HomeFragment.KEY_SELECT_DATE) as? Calendar
        Log.d(TAG, "onCreate: $calendar")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initView() {

    }

    override fun setListener() {
        binding.topAppBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.check -> {
                    true
                }

                else -> false
            }
        }
    }

    private fun saveEvent() {

    }

}