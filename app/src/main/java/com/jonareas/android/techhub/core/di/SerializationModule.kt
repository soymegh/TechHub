package com.jonareas.android.techhub.core.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.jonareas.android.techhub.core.data.api.utils.SerializationConstants.APPLICATION_JSON
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Converter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SerializationModule {

    @Provides
    @Singleton
    fun provideJson() : Json =
        Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
            isLenient = true
        }

    @ExperimentalSerializationApi
    @Provides
    @Singleton
    fun provideConverter(json : Json) : Converter.Factory =
        json.asConverterFactory(APPLICATION_JSON.toMediaType())

}