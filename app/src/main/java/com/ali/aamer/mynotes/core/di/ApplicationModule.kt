package com.ali.aamer.mynotes.core.di

import android.content.Context
import androidx.room.Room
import com.ali.aamer.mynotes.core.data.remote.NotesAuthInterceptor
import com.ali.aamer.mynotes.core.utils.Constants.BASE_URL
import com.ali.aamer.mynotes.features.feature_authentication.data.data_sources.remote.AuthenticationAPI
import com.ali.aamer.mynotes.features.feature_authentication.domain.repositories.IUserAuthenticationRepository
import com.ali.aamer.mynotes.features.feature_authentication.domain.usecases.AuthenticationUseCases
import com.ali.aamer.mynotes.features.feature_authentication.domain.usecases.UserLogin
import com.ali.aamer.mynotes.features.feature_notes.data.data_sources.local.NotesDatabase
import com.ali.aamer.mynotes.features.feature_notes.data.data_sources.remote.NotesAPI
import com.ali.aamer.mynotes.features.feature_notes.domain.repositories.INoteRepository
import com.ali.aamer.mynotes.features.feature_notes.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApplicationModule {

    @Singleton
    @Provides
    fun provideAuthenticationUseCases(iUserAuthenticationRepository: IUserAuthenticationRepository)
            : AuthenticationUseCases {
        return AuthenticationUseCases(
            userLogin = UserLogin(iUserAuthenticationRepository)
        )
    }

    @Singleton
    @Provides
    fun provideNotesUseCases(iNoteRepository: INoteRepository): NotesUseCases {
        return NotesUseCases(
            createNote = CreateNote(iNoteRepository),
            updateNote = UpdateNote(iNoteRepository),
            getNotes = GetNotes(iNoteRepository),
            getNoteById = GetNoteById(iNoteRepository),
            deleteNote = DeleteNote(iNoteRepository),
        )
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(notesAuthInterceptor: NotesAuthInterceptor): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(notesAuthInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthenticationApi(
        retrofitBuilder: Retrofit.Builder,
    ): AuthenticationAPI {
        return retrofitBuilder
            .build()
            .create(AuthenticationAPI::class.java)
    }


    @Singleton
    @Provides
    fun provideNotesApi(
        retrofitBuilder: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ): NotesAPI {
        return retrofitBuilder
            .client(okHttpClient)
            .build()
            .create(NotesAPI::class.java)
    }


    @Singleton
    @Provides
    fun provideNoteDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, NotesDatabase::class.java, "notes_db").build()

    @Singleton
    @Provides
    fun provideNotesDao(notesDatabase: NotesDatabase) = notesDatabase.notesDao()
}