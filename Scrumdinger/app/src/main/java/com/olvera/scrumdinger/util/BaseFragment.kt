package com.olvera.scrumdinger.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<vBinding: ViewDataBinding, viewModel: ViewModel>: Fragment() {

    lateinit var viewBinding: vBinding
    lateinit var viewModel: ViewModel

    abstract fun getViewBinding(): Int
    abstract fun getViewModel(): Class<viewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[getViewModel()]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater, getViewBinding(), container, false)
        return viewBinding.root
    }

}