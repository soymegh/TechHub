package com.jonareas.android.techhub.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonareas.android.techhub.core.data.cache.model.CachedCourse
import com.jonareas.android.techhub.core.data.repository.CourseRepository
import com.jonareas.android.techhub.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyCoursesViewModel @Inject constructor(private val courseRepository: CourseRepository,
                                             private val dispatchers: DispatcherProvider
): ViewModel() {

    private var _courses = MutableLiveData<List<CachedCourse>>()
    val courses: LiveData<List<CachedCourse>> = _courses

    init {
        getAllCourses()
    }

    private fun getAllCourses() {
        viewModelScope.launch(dispatchers.io) {
            courseRepository.getAllFlow().collect { listOfCourses ->
                _courses.postValue(listOfCourses)
            }
        }
    }



}
