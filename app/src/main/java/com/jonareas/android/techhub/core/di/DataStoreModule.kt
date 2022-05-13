package com.jonareas.android.techhub.core.di

import android.content.Context
import com.jonareas.android.techhub.core.data.settings.TechHubDataStore
import com.jonareas.android.techhub.core.data.settings.TechHubDataStoreImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

        @Provides
        @Singleton
        fun provideDataStore(@ApplicationContext context : Context) : TechHubDataStore =
            TechHubDataStoreImpl(context)

}