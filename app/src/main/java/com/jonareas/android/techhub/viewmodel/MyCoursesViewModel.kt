package com.jonareas.android.techhub.viewmodel

import androidx.lifecycle.ViewModel
import com.jonareas.android.techhub.core.data.cache.repository.TopicRepository
import com.jonareas.android.techhub.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyCoursesViewModel @Inject constructor(private val topicRepository: TopicRepository,
                                             private val dispatchers: DispatcherProvider): ViewModel() {




}
