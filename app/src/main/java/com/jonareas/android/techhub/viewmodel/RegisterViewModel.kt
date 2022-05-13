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
class RegisterViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dispatchers: DispatcherProvider,
) : ViewModel() {



    fun onUserRegister(user: User) = registerUser(user)


    private fun registerUser(user: User) {
        viewModelScope.launch(dispatchers.io) {
            try {
                userRepository.registerUser(user)
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            }
        }
    }


}