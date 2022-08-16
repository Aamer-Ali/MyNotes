package com.ali.aamer.mynotes.features.feature_authentication.data.models

data class UserLoginResponseModel(
    val token: String,
    val user: User
)