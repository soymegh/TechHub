package com.jonareas.android.techhub.core.di

import com.jonareas.android.techhub.core.data.repository.CourseRepository
import com.jonareas.android.techhub.core.data.repository.TopicRepository
import com.jonareas.android.techhub.core.data.repository.UserRepository
import com.jonareas.android.techhub.core.data.repository.impl.CourseRepositoryImpl
import com.jonareas.android.techhub.core.data.repository.impl.TopicRepositoryImpl
import com.jonareas.android.techhub.core.data.repository.impl.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindTopicRepository(topicRepositoryImpl: TopicRepositoryImpl) :
            TopicRepository

    @Binds
    @ViewModelScoped
    abstract fun bindCourseRepository(courseRepositoryImpl : CourseRepositoryImpl) :
            CourseRepository

    @Binds
    @ViewModelScoped
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl) :
            UserRepository

}