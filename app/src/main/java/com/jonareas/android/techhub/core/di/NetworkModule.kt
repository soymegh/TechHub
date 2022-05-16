package com.jonareas.android.techhub.core.di

import com.jonareas.android.techhub.core.data.api.service.CourseService
import com.jonareas.android.techhub.core.data.api.service.LoginService
import com.jonareas.android.techhub.core.data.api.utils.ApiConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val APPLICATION_JSON : String = "application/json"

    @Provides
    @Singleton
    fun provideLoggingInterceptor() : HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = BODY
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) : OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .build()


    @Provides
    @Singleton
    fun provideRetrofit(client : OkHttpClient, jsonConverter : Converter.Factory) : Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_END_POINT)
            .client(client)
            .addConverterFactory(jsonConverter)
            .build()

    @Provides
    @Singleton
    fun provideLoginService(retrofit : Retrofit) : LoginService =
        retrofit.create(LoginService::class.java)

    @Provides
    @Singleton
    fun provideCourseService(retrofit : Retrofit) : CourseService =
        retrofit.create(CourseService::class.java)





}