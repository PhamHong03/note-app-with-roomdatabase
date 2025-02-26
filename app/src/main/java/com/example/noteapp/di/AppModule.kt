package com.example.noteapp.di

import androidx.room.Room
import com.example.noteapp.NoteApplication
import com.example.noteapp.data.data_source.NoteDatabase
import com.example.noteapp.data.repository.NoteRepositoryImpl
import com.example.noteapp.domain.repository.NoteRepository
import com.example.noteapp.domain.use_cases.AddNote
import com.example.noteapp.domain.use_cases.DeleteNote
import com.example.noteapp.domain.use_cases.GetNote
import com.example.noteapp.domain.use_cases.GetNotes
import com.example.noteapp.domain.use_cases.NoteUseCases
import com.example.noteapp.domain.use_cases.UpdateNote
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import dagger.Module
import dagger.Provides

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(application: NoteApplication): NoteDatabase = Room.databaseBuilder(
        application,
        NoteDatabase::class.java,
        NoteDatabase.DB_NAME
    ).build()


    @Provides
    @Singleton
    fun provideNoteRepository(noteDatabase: NoteDatabase) : NoteRepository = NoteRepositoryImpl(
        noteDao = noteDatabase.noteDao
    )

    @Provides
    @Singleton
    fun provideNoteUses(noteRepository: NoteRepository) : NoteUseCases = NoteUseCases(
        addNote = AddNote(noteRepository),
        updateNote = UpdateNote(noteRepository),
        deleteNote = DeleteNote(noteRepository),
        getNotes = GetNotes(noteRepository),
        getNote = GetNote(noteRepository)
    )
}