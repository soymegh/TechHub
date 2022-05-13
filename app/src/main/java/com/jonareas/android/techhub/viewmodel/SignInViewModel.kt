package com.jonareas.android.techhub.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonareas.android.techhub.core.data.repository.UserRepository
import com.jonareas.android.techhub.core.presentation.model.User
import com.jonareas.android.techhub.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: UserRepository,
    private val dispatcher: DispatcherProvider
) : ViewModel() {

    suspend fun authenticateUser(user: User) {
        viewModelScope.launch(dispatcher.io) {
            try {
               repository.authenticateUser(user)
            } catch(throwable : Throwable) {
                throwable.printStackTrace()
            }
        }
    }

}