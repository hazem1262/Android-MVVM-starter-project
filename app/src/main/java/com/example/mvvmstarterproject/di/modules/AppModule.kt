package com.example.mvvmstarterproject.di.modules

import android.content.Context
import com.example.mvvmstarterproject.BuildConfig
import com.example.mvvmstarterproject.utils.ConnectivityUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideTestString() = "test string"

    @Provides
    fun provideIsDebug() = BuildConfig.DEBUG

    @Provides
    @Singleton
    fun provideConnectivityUtils(@ApplicationContext context: Context): ConnectivityUtils = ConnectivityUtils(context)

}