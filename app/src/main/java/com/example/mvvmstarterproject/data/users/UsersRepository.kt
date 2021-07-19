package com.example.mvvmstarterproject.data.users

import com.example.mvvmstarterproject.base.BaseRepository
import com.example.mvvmstarterproject.data.users.remote.User
import com.example.mvvmstarterproject.data.users.remote.UserService
import com.example.mvvmstarterproject.utils.ConnectivityUtils
import com.example.mvvmstarterproject.utils.network.ApplicationException
import com.example.mvvmstarterproject.utils.network.ErrorType
import com.example.mvvmstarterproject.utils.network.Result
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class UsersRepository @Inject constructor(connectivityUtils: ConnectivityUtils, private val userService: UserService): BaseRepository(connectivityUtils) {
    suspend fun getListOfUsers(): Result<List<User>> {
        return safeApiCall {
            userService.getListOfUsers()
        }.let { result ->
            when (result) {
                is Result.Success -> Result.Success(result.data)
                is Result.Error -> result
                else -> Result.Error(ApplicationException(type = ErrorType.Unexpected))
            }
        }
    }
}