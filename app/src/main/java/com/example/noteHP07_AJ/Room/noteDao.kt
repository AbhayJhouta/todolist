package com.example.noteHP07_AJ.Room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface noteDao {
    @Insert
    suspend fun insert(note: noteEntity)
    @Delete
    suspend fun delete(note:noteEntity)
    @Query("Select * from allnotes")
    fun getall():LiveData<List<noteEntity>>
    @Query("Delete  from allnotes")
     suspend fun delall()
     @Update
     suspend fun update(note:noteEntity)

}