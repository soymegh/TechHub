package com.jonareas.android.techhub.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonareas.android.techhub.core.data.repository.UserRepository
import com.jonareas.android.techhub.core.data.settings.TechHubDataStore
import com.jonareas.android.techhub.core.presentation.model.User
import com.jonareas.android.techhub.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: UserRepository,
    private val dispatcher: DispatcherProvider,
    private val dataStore : TechHubDataStore
) : ViewModel() {

    private var _loginStatus  = MutableLiveData<Boolean>(false)
    val loginStatus : LiveData<Boolean> = _loginStatus


    fun onUserSignIn(user : User) = authenticateUser(user)

    private fun authenticateUser(user: User) {
        viewModelScope.launch(dispatcher.io) {
            try {
               val response = repository.authenticateUser(user)
                val token = response.token

                if(token.isNotEmpty()) {
                    dataStore.updateToken(token)
                    dataStore.updateLogStatus(true)
                }

                dataStore.isLoggedIn.collectLatest { isLogged -> _loginStatus.postValue(isLogged) }

            } catch(throwable : Throwable) {
                throwable.printStackTrace()
            }
        }
    }

}