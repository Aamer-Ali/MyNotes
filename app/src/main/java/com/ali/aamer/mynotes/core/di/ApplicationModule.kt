package com.ali.aamer.mynotes.core.di

import com.ali.aamer.mynotes.core.data.remote.NotesAuthInterceptor
import com.ali.aamer.mynotes.core.utils.Constants.BASE_URL
import com.ali.aamer.mynotes.features.feature_authentication.data.data_sources.remote.AuthenticationAPI
import com.ali.aamer.mynotes.features.feature_authentication.domain.repositories.IUserAuthenticationRepository
import com.ali.aamer.mynotes.features.feature_authentication.domain.usecases.AuthenticationUseCases
import com.ali.aamer.mynotes.features.feature_authentication.domain.usecases.UserLogin
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApplicationModule {

    @Provides
    @Singleton
    fun provideAuthenticationUseCases(iUserAuthenticationRepository: IUserAuthenticationRepository)
            : AuthenticationUseCases {
        return AuthenticationUseCases(
            userLogin = UserLogin(iUserAuthenticationRepository)
        )
    }

    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(notesAuthInterceptor: NotesAuthInterceptor): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(notesAuthInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthenticationApi(
        retrofitBuilder: Retrofit.Builder,
    ): AuthenticationAPI {
        return retrofitBuilder
            .build()
            .create(AuthenticationAPI::class.java)

    }


//    @Provides
//    @Singleton
//    fun provideNotesApi(
//        retrofitBuilder: Retrofit.Builder,
//        okHttpClient: OkHttpClient
//    ): AuthenticationAPI {
//        return retrofitBuilder
//            .client(okHttpClient)
//            .build()
//            .create(AuthenticationAPI::class.java)
//
//    }

}