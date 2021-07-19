package com.example.mvvmstarterproject.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.example.mvvmstarterproject.R
import com.example.mvvmstarterproject.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel>() {

    override val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}
