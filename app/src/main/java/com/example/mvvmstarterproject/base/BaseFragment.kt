package com.example.mvvmstarterproject.base

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.mvvmstarterproject.utils.EventObserver
import com.example.mvvmstarterproject.utils.MessageUtils
import com.example.mvvmstarterproject.utils.network.LoadingHandler
import com.example.mvvmstarterproject.utils.network.Result

abstract class BaseFragment<ViewModel : BaseViewModel> : Fragment() {

    abstract val viewModel:ViewModel
    private lateinit var loadingHandler: LoadingHandler

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingHandler = LoadingHandler.getInstance(requireActivity())
        initLoading()
        initError()
    }

    private fun initError() {
        viewModel.error.observe(viewLifecycleOwner, EventObserver {
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
            MessageUtils.showErrorMessage(requireActivity(), errorMessage)
    }

    private fun initLoading() {
        viewModel.loading.observe(viewLifecycleOwner, EventObserver {
            if (it.loading) showLoading()
            else hideLoading()
        })
    }
    open fun hideLoading() {
        loadingHandler.hideLoading()
    }

    open fun showLoading() {
        hideKeyboard()
        loadingHandler.showLoading()
    }

    fun hideKeyboard() {
        val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

}