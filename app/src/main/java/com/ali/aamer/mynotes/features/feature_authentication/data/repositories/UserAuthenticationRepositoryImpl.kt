package com.ali.aamer.mynotes.features.feature_authentication.data.repositories

import com.ali.aamer.mynotes.core.utils.Resource
import com.ali.aamer.mynotes.core.utils.Status
import com.ali.aamer.mynotes.core.utils.TokenManager
import com.ali.aamer.mynotes.features.feature_authentication.data.data_sources.remote.AuthenticationAPI
import com.ali.aamer.mynotes.features.feature_authentication.data.models.UserLoginRequestModel
import com.ali.aamer.mynotes.features.feature_authentication.data.models.UserLoginResponseModel
import com.ali.aamer.mynotes.features.feature_authentication.domain.repositories.IUserAuthenticationRepository
import org.json.JSONObject
import retrofit2.Response
import java.lang.Error
import javax.inject.Inject

class UserAuthenticationRepositoryImpl @Inject constructor(private val authenticationAPI: AuthenticationAPI) :
    IUserAuthenticationRepository {

    @Inject
    lateinit var tokenManager: TokenManager

    override suspend fun userLogin(userLoginRequestModel: UserLoginRequestModel): Resource<Boolean> {
        val response = authenticationAPI.loginUser(userLoginRequestModel)
        return handleResponse(response)
    }

    private fun handleResponse(response: Response<UserLoginResponseModel>): Resource<Boolean> {
        return try {
            if (response.isSuccessful && response.body() != null) {
                //Save data to SP
                tokenManager.saveToken(response.body()!!.token)
                Resource.Success(Status.SUCCESS, true)
            } else {
                val errorObject = JSONObject(response.errorBody()!!.charStream().readText())
                Resource.Failure(Status.FAILURE, errorObject.getString("message"))
            }
        } catch (error: Error) {
            Resource.Failure(Status.FAILURE, error.toString())
        }
    }
}