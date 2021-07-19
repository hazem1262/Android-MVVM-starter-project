package com.example.mvvmstarterproject.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.mvvmstarterproject.R
import com.example.mvvmstarterproject.base.BaseActivity
import com.example.mvvmstarterproject.ui.main.users.UsersFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel>() {

    @Inject
    lateinit var testDagger:String

    override val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.printTestString()
        Log.d("test", viewModel.toString())
        test.text = testDagger
        initFragment()
    }

    private fun initFragment() {
        val testFragmentA = UsersFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainer, testFragmentA).commit()
    }

}
