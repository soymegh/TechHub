package com.jonareas.android.techhub.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonareas.android.techhub.core.data.cache.model.CachedTopic
import com.jonareas.android.techhub.core.data.cache.repository.TopicRepository
import com.jonareas.android.techhub.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchCoursesViewModel @Inject constructor(
    private val topicRepository: TopicRepository,
    private val dispatchers: DispatcherProvider,
) :
    ViewModel() {
    private var _topics = MutableLiveData<List<CachedTopic>>()
    val topics: LiveData<List<CachedTopic>> = _topics

    init {
        getAllTopics()
    }

    private fun getAllTopics() {
        viewModelScope.launch(dispatchers.io) {
            topicRepository.getAllOrderedByNameFlow().collect { listOfTopics ->
                _topics.postValue(listOfTopics)
            }

        }

    }
}