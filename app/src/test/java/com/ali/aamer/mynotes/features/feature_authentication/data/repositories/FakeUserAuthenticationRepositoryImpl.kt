package com.ali.aamer.mynotes.features.feature_authentication.data.repositories

import com.ali.aamer.mynotes.core.utils.Resource
import com.ali.aamer.mynotes.core.utils.Status
import com.ali.aamer.mynotes.features.feature_authentication.data.models.UserLoginRequestModel
import com.ali.aamer.mynotes.features.feature_authentication.domain.repositories.IUserAuthenticationRepository
import org.junit.Assert.*

class FakeUserAuthenticationRepositoryImpl : IUserAuthenticationRepository {

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override suspend fun userLogin(userLoginRequestModel: UserLoginRequestModel): Resource<Boolean> {
        return if (shouldReturnNetworkError) {
            Resource.Failure(
                status = Status.FAILURE,
                message = "Please enter user details correctly"
            )
        } else {
            Resource.Success(status = Status.SUCCESS, data = true)
        }
    }

}