package com.orange.ods.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : ViewDataBinding>(contentLayoutId: Int) : Fragment(contentLayoutId) {

    protected lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        this.binding = DataBindingUtil.bind<T>(requireNotNull(view))!!.apply {
            lifecycleOwner = viewLifecycleOwner
        }

        setUpView()
        return binding.root
    }

    protected open fun setUpView() {}

}