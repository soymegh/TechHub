package com.jonareas.android.techhub.viewmodel

import androidx.lifecycle.*
import com.jonareas.android.techhub.core.data.cache.model.CachedCourse
import com.jonareas.android.techhub.core.data.repository.CourseRepository
import com.jonareas.android.techhub.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreCoursesViewModel @Inject constructor(
    private val courseRepository: CourseRepository,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {

    private var _courses = MutableLiveData<List<CachedCourse>>()
    val courses = MediatorLiveData<List<CachedCourse>>()

    private var _searchedCourses: LiveData<List<CachedCourse>>
    private var _searchTerm = MutableLiveData<String>()


    init {
        _searchedCourses = Transformations.switchMap(_searchTerm) { courseQuery ->
            fetchCourseByQuery(courseQuery)
        }

        courses.addSource(_searchedCourses) { courses ->
            this.courses.value = courses
        }

        courses.addSource(_courses) { courses ->
            this.courses.value = courses
        }

        getAllCourses()

    }

  fun onRefresh() = fetchAllCourses()

    fun onSearchQuery(query: String) {
        viewModelScope.launch {
            if (query.isEmpty())
                getAllCourses()
            else _searchTerm.value = query
        }
    }

    private fun fetchCourseByQuery(query: String): LiveData<List<CachedCourse>> {
        val liveData = MutableLiveData<List<CachedCourse>>()
        viewModelScope.launch(dispatchers.io) {

            courseRepository.getAllCoursesByNameFlow(query).collectLatest {
                liveData.postValue(it)
            }

        }

        return liveData
    }

    private fun fetchAllCourses() {
        viewModelScope.launch(dispatchers.io) {
            try {
                courseRepository.fetchCourses().collectLatest { listOfCourses ->
                    _courses.postValue(listOfCourses)
                }
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            }
        }
    }

    private fun getAllCourses() {
        viewModelScope.launch(dispatchers.io) {
            courseRepository.getAllFlow().collect { listOfCourses ->
                _courses.postValue(listOfCourses)
            }
        }
    }

}