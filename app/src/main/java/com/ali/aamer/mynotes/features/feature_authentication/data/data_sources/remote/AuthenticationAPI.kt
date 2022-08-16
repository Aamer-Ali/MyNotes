package com.ali.aamer.mynotes.features.feature_authentication.data.data_sources.remote

import com.ali.aamer.mynotes.core.utils.Resource
import com.ali.aamer.mynotes.features.feature_authentication.data.models.UserLoginRequestModel
import com.ali.aamer.mynotes.features.feature_authentication.data.models.UserLoginResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationAPI {

    @POST("/users/signin")
    suspend fun loginUser(@Body userLoginRequestModel: UserLoginRequestModel): Response<UserLoginResponseModel>

}