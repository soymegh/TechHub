package com.jonareas.android.techhub.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonareas.android.techhub.core.data.cache.model.CachedCourse
import com.jonareas.android.techhub.core.data.repository.CourseRepository
import com.jonareas.android.techhub.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseDetailViewModel @Inject constructor(
    private val courseRepository: CourseRepository,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {

    private var _course = MutableLiveData<CachedCourse>()
    val course: LiveData<CachedCourse> = _course


    private var _relatedCourses = MutableLiveData<List<CachedCourse>>()
    val relatedCourses : LiveData<List<CachedCourse>> = _relatedCourses

    init {
        getRelatedCourses()
    }

    private fun getRelatedCourses() {
        viewModelScope.launch(dispatchers.io) {
            courseRepository.getRelatedCoursesFlow().collectLatest { listOfCourses ->
                _relatedCourses.postValue(listOfCourses)
            }

        }
    }


    internal fun getCourseById(id : Int) {
        viewModelScope.launch(dispatchers.io) {
            courseRepository.getByIdFlow(id).collect { course ->
                _course.postValue(course)
            }
        }
    }

}