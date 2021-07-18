package com.example.mvvmstarterproject.utils.network

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("data") val data: T?,
    @SerializedName("error") val error: ResponseError?
)


