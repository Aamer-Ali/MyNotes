package com.ali.aamer.mynotes.features.feature_authentication.domain.repositories

import com.ali.aamer.mynotes.core.utils.Resource
import com.ali.aamer.mynotes.features.feature_authentication.data.models.UserLoginRequestModel

interface IUserAuthenticationRepository {
    suspend fun userLogin(userLoginRequestModel: UserLoginRequestModel): Resource<Boolean>
    //User Registration
}