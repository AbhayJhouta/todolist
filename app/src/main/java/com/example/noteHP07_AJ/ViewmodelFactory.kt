package com.example.noteHP07_AJ

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.noteHP07_AJ.Room.repository

class viewmodelFactory(val repo: repository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainVeiwModel(repo) as T
    }

}