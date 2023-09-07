package com.buyehou.chronicle.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.buyehou.chronicle.util.createBinding

/**
 * @author Rosen
 * @date 2023/9/7 11:14
 */
abstract class BaseFragment<T : ViewBinding> : Fragment() {

    protected val binding: T
        get() = _binding!!

    protected val nullableBinding: T?
        get() = _binding

    private var _binding: T? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = createBinding(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}