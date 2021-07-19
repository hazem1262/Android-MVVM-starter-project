package com.example.mvvmstarterproject.data.users.remote


import retrofit2.Response
import retrofit2.http.GET

interface UserService {

    @GET("users")
    suspend fun getListOfUsers(): Response<List<User>>
}