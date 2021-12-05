package com.yodgorbek.dogapitask.core.di

import com.yodgorbek.dogapitask.BuildConfig
import com.yodgorbek.dogapitask.data.remote.DogsRetrofitAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * This module is responsible for defining the creation of any third parties dependencies used in the
 * application.
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providePokeApi(): DogsRetrofitAPI {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DogsRetrofitAPI::class.java)
    }
}
