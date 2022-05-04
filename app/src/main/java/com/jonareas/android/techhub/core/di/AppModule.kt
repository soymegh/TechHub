package com.jonareas.android.techhub.core.di

import android.content.Context
import android.content.res.Resources
import com.jonareas.android.techhub.utils.DefaultDispatchers
import com.jonareas.android.techhub.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideResources(@ApplicationContext context : Context) : Resources =
        context.resources

    @Provides
    @Singleton
    fun provideDispatchers() : DispatcherProvider =
        DefaultDispatchers

}