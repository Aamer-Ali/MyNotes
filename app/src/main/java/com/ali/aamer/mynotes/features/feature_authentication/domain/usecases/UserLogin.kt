package com.ali.aamer.mynotes.features.feature_authentication.domain.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ali.aamer.mynotes.core.utils.Resource
import com.ali.aamer.mynotes.core.utils.Status
import com.ali.aamer.mynotes.features.feature_authentication.data.models.UserLoginRequestModel
import com.ali.aamer.mynotes.features.feature_authentication.domain.repositories.IUserAuthenticationRepository
import javax.inject.Inject

class UserLogin @Inject constructor(private val iUserAuthenticationRepository: IUserAuthenticationRepository) {

    private var _userLoginStatus = MutableLiveData<Resource<Boolean>>()
    val userLoginStatus: LiveData<Resource<Boolean>> get() = _userLoginStatus

    suspend operator fun invoke(userLoginRequestModel: UserLoginRequestModel) {
        _userLoginStatus.postValue(Resource.Loading(status = Status.LOADING))
        if (!isUserRequestModelIsValid(userLoginRequestModel)) {
            _userLoginStatus.postValue(
                Resource.Failure(
                    status = Status.FAILURE,
                    message = "Please enter valid  details to login."
                )
            )
            return
        }
        val userLoginResponse = iUserAuthenticationRepository.userLogin(userLoginRequestModel)
        _userLoginStatus.postValue(userLoginResponse)
    }
}

fun isUserRequestModelIsValid(userLoginRequestModel: UserLoginRequestModel): Boolean {
    if (userLoginRequestModel.email.isEmpty()) return false
    return userLoginRequestModel.password.isNotEmpty()
}