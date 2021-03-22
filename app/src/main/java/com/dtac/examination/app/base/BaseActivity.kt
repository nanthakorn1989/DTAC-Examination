package com.dtac.examination.app.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProviders

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel>(private val clazz: Class<VM>) : AppCompatActivity() {

    lateinit var binding: B
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, getLayout())
        viewModel = ViewModelProviders.of(this).get(clazz)

        initialView()
    }

    abstract fun getLayout(): Int

    open fun initialView() {

    }
}