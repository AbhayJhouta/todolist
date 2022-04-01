package com.example.noteHP07_AJ

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noteHP07_AJ.Room.database
import com.example.noteHP07_AJ.Room.noteEntity
import com.example.noteHP07_AJ.Room.repository
import com.example.noteHP07_AJ.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlin.properties.Delegates

lateinit var recview:RecyclerView
lateinit var recAdapter:mainRecyclerAdapter
lateinit var mainbind:ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainbind= ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainbind.root)
        recview= mainbind.mainrecler
        recview.setHasFixedSize(true)
        val layout=LinearLayoutManager(this)
        recview.layoutManager=layout

        val shrd=getSharedPreferences("id", MODE_PRIVATE)
        if(shrd.getInt("no",-1)==-1) {
            val editor = shrd.edit()
            editor.putInt("no", 0);
            editor.apply()
        }

        val bd=database.getdatabase(applicationContext)
        val doaref=bd.getdao()
        val repo=repository(doaref)
        val vmdl=ViewModelProvider(this, viewmodelFactory(repo)).get(MainVeiwModel::class.java)
        //vmdl.deleteall()
        vmdl.getall().observe(this, Observer {
            val reverse: List<noteEntity> = it.reversed();
            recAdapter= mainRecyclerAdapter(reverse,applicationContext,this@MainActivity)
            recview.adapter= recAdapter
        })

        mainbind.add.setOnClickListener {

            val intent = Intent(this, makenote::class.java)
            startActivity(intent)
        }

    }
}