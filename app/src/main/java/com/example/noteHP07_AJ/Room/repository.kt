package com.example.noteHP07_AJ.Room

import androidx.lifecycle.LiveData

class repository(val daoref:noteDao) {
    suspend fun insert(note: noteEntity)
    {
        daoref.insert(note)
    }
   suspend fun delete(note: noteEntity)
    {
        daoref.delete(note)
    }
    suspend fun deleteall()
    {
        daoref.delall()
    }
    fun getall():LiveData<List<noteEntity>>
    {
        return daoref.getall()
    }
   suspend fun update(note: noteEntity)
    {
        daoref.update(note)
    }

}