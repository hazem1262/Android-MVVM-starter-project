package com.example.mvvmstarterproject.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmstarterproject.utils.EventObserver
import com.example.mvvmstarterproject.utils.MessageUtils
import com.example.mvvmstarterproject.utils.network.LoadingHandler
import com.example.mvvmstarterproject.utils.network.Result

abstract class BaseActivity<ViewModel : BaseViewModel> : AppCompatActivity() {

    private lateinit var loadingHandler: LoadingHandler

    abstract val viewModel:ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingHandler = LoadingHandler.getInstance(this)
        initLoading()
        initError()
    }

    private fun initError() {
        viewModel.error.observe(this, EventObserver {
            hideLoading()
            showError(it)
        })
    }
    open fun showError(error: Result.Error) {
        val errorMessage = error.exception.errorMessage
            ?: run {
                return@run if (error.exception.errorMessageRes != null) {
                    getString(error.exception.errorMessageRes)
                } else null
            }
            ?: "unexpected error"
        if (errorMessage.isNotEmpty())
            MessageUtils.showErrorMessage(this, errorMessage)
    }

    private fun initLoading() {
        viewModel.loading.observe(this, EventObserver {
            if (it.loading) showLoading()
            else hideLoading()
        })
    }

    open fun hideLoading() {
        loadingHandler.hideLoading()
    }

    open fun showLoading() {
        loadingHandler.showLoading()
    }
}