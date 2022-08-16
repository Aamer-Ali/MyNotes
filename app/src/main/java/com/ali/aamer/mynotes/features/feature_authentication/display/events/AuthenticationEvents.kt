package com.ali.aamer.mynotes.features.feature_authentication.display.events

import com.ali.aamer.mynotes.features.feature_authentication.data.models.UserLoginRequestModel

sealed class AuthenticationEvents {
    data class UserLogin(val loginRequestModel: UserLoginRequestModel) : AuthenticationEvents()
    //RegisterUser
    //Go to User LoginPage
    //Got to User Signup Page

}