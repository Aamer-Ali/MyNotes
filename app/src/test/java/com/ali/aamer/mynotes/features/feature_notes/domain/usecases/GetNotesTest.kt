package com.ali.aamer.mynotes.features.feature_notes.domain.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ali.aamer.mynotes.features.feature_notes.data.repositories.FakeNoteRepositoryImpl
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetNotesTest {

    @get:Rule
    var instaExecutorRule = InstantTaskExecutorRule()

    private lateinit var getNotes: GetNotes
    private lateinit var fakeNoteRepositoryImpl: FakeNoteRepositoryImpl

    @Before
    fun setup() {
        fakeNoteRepositoryImpl = FakeNoteRepositoryImpl()
        getNotes = GetNotes(fakeNoteRepositoryImpl)
    }

    @After
    fun tearDown() {
    }

}