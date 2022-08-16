package com.ali.aamer.mynotes.features.feature_authentication.display.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.aamer.mynotes.features.feature_authentication.display.events.AuthenticationEvents
import com.ali.aamer.mynotes.features.feature_authentication.domain.usecases.AuthenticationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthenticationViewModel @Inject constructor(private val authenticationUseCases: AuthenticationUseCases) :
    ViewModel() {

    val userLoginStatusLiveData = authenticationUseCases.userLogin.userLoginStatus

    fun authenticationEvents(event: AuthenticationEvents) {
        when (event) {
            is AuthenticationEvents.UserLogin -> {
                viewModelScope.launch {
                    authenticationUseCases.userLogin(event.loginRequestModel)
                }
            }
        }
    }
}