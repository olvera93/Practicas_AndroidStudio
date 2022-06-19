package com.olvera.scrumdinger.util

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseViewModelFragment<viewModel: ViewModel>: Fragment() {

    lateinit var viewModel: viewModel

    abstract fun getViewModel(): Class<viewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[getViewModel()]
    }

}