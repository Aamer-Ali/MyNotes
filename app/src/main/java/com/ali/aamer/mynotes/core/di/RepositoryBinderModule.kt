package com.ali.aamer.mynotes.core.di

import com.ali.aamer.mynotes.features.feature_authentication.data.repositories.UserAuthenticationRepositoryImpl
import com.ali.aamer.mynotes.features.feature_authentication.domain.repositories.IUserAuthenticationRepository
import com.ali.aamer.mynotes.features.feature_notes.data.repositories.NoteRepositoryImpl
import com.ali.aamer.mynotes.features.feature_notes.domain.repositories.INoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryBinderModule {
    @Binds
    @Singleton
    abstract fun bindIUserAuthenticationRepository(userAuthenticationRepositoryImpl: UserAuthenticationRepositoryImpl): IUserAuthenticationRepository

    @Binds
    @Singleton
    abstract fun bindINoteRepository(noteRepositoryImpl: NoteRepositoryImpl): INoteRepository
}