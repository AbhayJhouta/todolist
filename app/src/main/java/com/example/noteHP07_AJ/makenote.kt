package com.example.noteHP07_AJ

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.noteHP07_AJ.Room.database
import com.example.noteHP07_AJ.Room.noteEntity
import com.example.noteHP07_AJ.Room.repository
import com.example.noteHP07_AJ.databinding.ActivityMakenoteBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

lateinit var makenoteBind:ActivityMakenoteBinding
class makenote : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makenoteBind= ActivityMakenoteBinding.inflate(layoutInflater)
        setContentView(makenoteBind.root)
        val con=intent.getStringExtra("context")
        if(con=="old") {

            val topic = intent.getStringExtra("topic")
            val desc = intent.getStringExtra("desc")
            makenoteBind.maketopic.setText(topic)
            makenoteBind.makediscrption.setText(desc)
        }
        val bd=database.getdatabase(applicationContext)
        val doaref=bd.getdao()
        val repo= repository(doaref)
        val vmdl=ViewModelProvider(this,makenoteFactory(repo)).get(makenoteViewModel::class.java)
        makenoteBind.savenote.setOnClickListener(View.OnClickListener {
            if(con=="old") {
                val id= intent.getIntExtra("id",0)
                val note = noteEntity(id,
                    makenoteBind.maketopic.text.toString(),
                    makenoteBind.makediscrption.text.toString())
                CoroutineScope(Dispatchers.IO).launch()
                {
                    vmdl.update(note)
                }
            }
            else {
                val shrd = getSharedPreferences("id", MODE_PRIVATE)
                var id = shrd.getInt("no", 1)
                val note = noteEntity(
                    id,
                    makenoteBind.maketopic.text.toString(),
                    makenoteBind.makediscrption.text.toString()
                );
                val editor = shrd.edit()
                id++
                editor.putInt("no", id)
                editor.apply()
                CoroutineScope(Dispatchers.IO).launch()
                {
                    vmdl.insert(note)
                }
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })
    }
}