package com.buyehou.chronicle.page.controller

import android.os.Bundle
import android.util.Log
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.navigation.fragment.findNavController
import com.buyehou.chronicle.R
import com.buyehou.chronicle.base.BaseFragment
import com.buyehou.chronicle.databinding.FragmentAddEventBinding
import com.buyehou.chronicle.page.home.HomeFragment
import com.buyehou.chronicle.util.KeyboardUtils
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

    override fun initView() {
        KeyboardUtils.registerKeyboardHeightListener(requireActivity(),
            object : KeyboardUtils.KeyboardHeightListener {
                override fun onKeyboardHeightChanged(height: Int) {
                    updateToolBarHeight(height)
                }
            })
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

    private fun updateToolBarHeight(height: Int) {
        binding.llToolBar.isVisible = height > 0
        val alpha = (height / 300).toFloat()
        if (alpha >= 1) {
            binding.llToolBar.alpha = 1.0f
        } else {
            binding.llToolBar.alpha = alpha
        }

        binding.llToolBar.updateLayoutParams<CoordinatorLayout.LayoutParams> {
            this.bottomMargin = height
        }
    }

}