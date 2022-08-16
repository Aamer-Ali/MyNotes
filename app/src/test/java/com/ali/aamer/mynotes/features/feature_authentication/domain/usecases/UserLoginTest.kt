package com.ali.aamer.mynotes.features.feature_authentication.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ActivityScenario.launch
import com.ali.aamer.mynotes.core.utils.Resource
import com.ali.aamer.mynotes.core.utils.Status
import com.ali.aamer.mynotes.features.feature_authentication.data.models.UserLoginRequestModel
import com.ali.aamer.mynotes.features.feature_authentication.data.repositories.FakeUserAuthenticationRepositoryImpl
import com.ali.aamer.mynotes.getOrAwaitValueTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class UserLoginTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var userLogin: UserLogin
    private lateinit var fakeUserAuthenticationRepositoryImpl: FakeUserAuthenticationRepositoryImpl

    @Before
    fun setup() {
        fakeUserAuthenticationRepositoryImpl = FakeUserAuthenticationRepositoryImpl()
        userLogin = UserLogin(fakeUserAuthenticationRepositoryImpl)
    }

    @After
    fun tearDown() {
    }


    @Test
    fun `If the user not Login successfully because of network fail then the Failure`() {
        runTest {
            val userLoginRequestModel = UserLoginRequestModel("aamer@aamer.com", "1234")
            fakeUserAuthenticationRepositoryImpl.setShouldReturnNetworkError(true)
            userLogin(userLoginRequestModel)
            val response = userLogin.userLoginStatus.getOrAwaitValueTest()
            assertThat(response.status).isEqualTo(Status.FAILURE)
        }
    }

    @Test
    fun `If the user Login successfully then  Success`() {
        runTest {
            val userLoginRequestModel = UserLoginRequestModel("aamer@aamer.com", "1234")
            fakeUserAuthenticationRepositoryImpl.setShouldReturnNetworkError(false)
            userLogin.invoke(userLoginRequestModel)
            val response = userLogin.userLoginStatus.getOrAwaitValueTest()
            assertThat(response.status).isEqualTo(Status.SUCCESS)
        }
    }
}