package com.buyehou.chronicle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.buyehou.chronicle.databinding.FragmentAddEventBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * @author Rosen
 * @date 2023/9/6 14:37
 */
class BottomSheetDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddEventBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddEventBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        const val TAG = "AddBottomSheetDialogFragment"
    }

}