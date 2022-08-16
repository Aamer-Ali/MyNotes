package com.ali.aamer.mynotes.features.feature_authentication.domain.usecases

import com.ali.aamer.mynotes.features.feature_authentication.data.models.UserLoginRequestModel
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class UserLoginKtTest {


    @Test
    fun `If the email id is empty return false`() {
        val userData = UserLoginRequestModel("", "1234")
        val response = isUserRequestModelIsValid(userData)
        assertThat(response).isFalse()
    }

//    @Test
//    fun `If the email id is not valid email return false`() {
//        val userData = UserLoginRequestModel("EmailId", "1234")
//        val response = isUserRequestModelIsValid(userData)
//        assertThat(response).isFalse()
//    }

    @Test
    fun `If the password is empty return false`() {
        val userData = UserLoginRequestModel("aamer@aamer.com", "")
        val response = isUserRequestModelIsValid(userData)
        assertThat(response).isFalse()
    }

    @Test
    fun `If the user is valid return true`() {
        val userData = UserLoginRequestModel("aamer@aamer.com", "1234")
        val response = isUserRequestModelIsValid(userData)
        assertThat(response).isTrue()
    }
}