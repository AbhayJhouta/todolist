package com.example.noteHP07_AJ

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.noteHP07_AJ.Room.noteEntity
import com.example.noteHP07_AJ.Room.repository

class MainVeiwModel(val repo:repository):ViewModel() {

    suspend fun deleteall()
    {
        repo.deleteall()
    }
    fun getall(): LiveData<List<noteEntity>>
    {
        return repo.getall()
    }

}