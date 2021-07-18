package com.example.mvvmstarterproject.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmstarterproject.utils.Event
import com.example.mvvmstarterproject.utils.network.ApplicationException
import com.example.mvvmstarterproject.utils.network.ErrorType
import com.example.mvvmstarterproject.utils.network.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.UnsupportedOperationException
import javax.inject.Inject

open class BaseViewModel @Inject constructor(): ViewModel() {
    val error = MutableLiveData<Event<Result.Error>>()
    val loading =
        MutableLiveData<Event<Result.Loading>>().apply { value = Event(Result.Loading(false)) }

    inline fun wrapBlockingOperation(
        showLoading: Boolean = true,
        crossinline function: suspend CoroutineScope.() -> Unit
    ): Job {
        loading.value = Event(Result.Loading(showLoading))
        return viewModelScope.launch {
            try {
                function()
            } catch (throwable: Throwable) {
                handelError(throwable)
                Timber.e(throwable)
            } finally {
                loading.value = Event(Result.Loading(false))
            }
        }
    }
    fun <T> handleResult(result: Result<T>, onSuccess: (Result.Success<T>) -> Unit) {
        when (result) {
            is Result.Success<T> -> {
                onSuccess(result)
            }
            is Result.Error -> {
                throw result.exception
            }
            else -> throw UnsupportedOperationException()
        }
    }

    fun handelError(throwable: Throwable) {
        if (throwable is ApplicationException) {
            error.postValue(Event(Result.Error(throwable)))
            // todo handle different types of errors
            when (throwable.type) {
                ErrorType.Network.Unauthorized -> {}
                ErrorType.Network.ResourceNotFound -> {}
                ErrorType.Network.Unexpected -> {}
                ErrorType.Network.NoInternetConnection -> {}
                else -> {}
            }
        }
    }
}