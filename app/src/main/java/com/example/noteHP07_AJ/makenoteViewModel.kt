package com.example.noteHP07_AJ

import androidx.lifecycle.ViewModel
import com.example.noteHP07_AJ.Room.noteEntity
import com.example.noteHP07_AJ.Room.repository

class makenoteViewModel(val repo:repository):ViewModel() {
    suspend fun insert( note:noteEntity)
    {
        repo.insert(note)
    }
    suspend fun update(note: noteEntity)
    {
        repo.update(note)
    }


}