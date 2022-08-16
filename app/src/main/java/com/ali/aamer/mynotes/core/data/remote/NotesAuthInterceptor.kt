package com.ali.aamer.mynotes.core.data.remote

import com.ali.aamer.mynotes.core.utils.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NotesAuthInterceptor @Inject constructor() : Interceptor {
    @Inject
    lateinit var tokenManager: TokenManager
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val toke = tokenManager.getToken()
        request.addHeader("Authorization", "Bearer $toke")
        return chain.proceed(request.build())
    }
}