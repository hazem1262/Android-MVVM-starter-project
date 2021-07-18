package com.example.mvvmstarterproject.test

import android.util.Log
import com.example.mvvmstarterproject.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {
    @Inject
    lateinit var testString: String
    init {
        Log.d("test", "view model init")
    }
    fun printTestString(){
        Log.d("test", "view model init $testString")
    }
}