package com.jonareas.android.techhub.core.di

import com.jonareas.android.techhub.core.data.cache.repository.TopicRepository
import com.jonareas.android.techhub.core.data.cache.repository.TopicRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindTopicRepository(topicRepositoryImpl: TopicRepositoryImpl) : TopicRepository

}