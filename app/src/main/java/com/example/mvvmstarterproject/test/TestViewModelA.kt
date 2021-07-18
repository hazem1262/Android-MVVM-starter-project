package com.example.mvvmstarterproject.test

import androidx.lifecycle.MutableLiveData
import com.example.mvvmstarterproject.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestViewModelA @Inject constructor(private val testRepositoryA:TestRepositoryA) : BaseViewModel() {
    @Inject
    lateinit var testString: String
    val userLiveData:MutableLiveData<List<User>> = MutableLiveData()
    fun getUserDetails(){
        wrapBlockingOperation {
            handleResult(testRepositoryA.getListOfUsers()){
                userLiveData.postValue(it.data)
            }
        }
    }
}
