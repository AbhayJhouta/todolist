package com.example.noteHP07_AJ.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities =[noteEntity::class], version = 1)
abstract class database:RoomDatabase() {
    abstract fun getdao():noteDao
    companion object
    {
        var instance:database?=null
        fun getdatabase(context:Context):database
        {   synchronized(this)
            {
                if (instance == null) {
                    instance =
                        Room.databaseBuilder(context, database::class.java, "notesdatabse").build()
                }
            }
            return instance!!
        }
    }

}