package com.buyehou.chronicle.page.controller

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.buyehou.chronicle.R
import com.buyehou.chronicle.base.BaseFragment
import com.buyehou.chronicle.databinding.FragmentAddEventBinding
import com.buyehou.chronicle.db.ChronicleDatabase
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
        binding.btnCancel.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnSure.setOnClickListener {
            saveEvent()
            findNavController().popBackStack()
        }
    }

    private fun saveEvent() {
        val eventDao = ChronicleDatabase.instance.getEventDao()
        val title = binding.etTitle.text
        val content = binding.etContent.text
        Log.d(TAG, "saveEvent: ${title.length} ${content.length}")

    }

}