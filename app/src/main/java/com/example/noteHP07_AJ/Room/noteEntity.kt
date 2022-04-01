package com.example.noteHP07_AJ.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "allnotes")
data class noteEntity(@PrimaryKey val id:Int,val topic:String,val desc:String)
