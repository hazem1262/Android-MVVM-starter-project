package com.example.mvvmstarterproject.ui.main.users

import androidx.lifecycle.MutableLiveData
import com.example.mvvmstarterproject.base.BaseViewModel
import com.example.mvvmstarterproject.data.users.UsersRepository
import com.example.mvvmstarterproject.data.users.remote.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val testRepositoryA: UsersRepository) : BaseViewModel() {
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
