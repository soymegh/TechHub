package com.jonareas.android.techhub.core.di

import com.jonareas.android.techhub.utils.DefaultDispatchers
import com.jonareas.android.techhub.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun bindDispatchers(): DispatcherProvider = DefaultDispatchers

}