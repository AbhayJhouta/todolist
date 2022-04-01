package com.example.noteHP07_AJ

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.noteHP07_AJ.Room.database
import com.example.noteHP07_AJ.Room.noteEntity
import com.example.noteHP07_AJ.Room.repository
import com.example.noteHP07_AJ.databinding.OnenoteBinding
import kotlinx.coroutines.*


class mainRecyclerAdapter(val dataset:List<noteEntity>,val context:Context,val maincontext:Context) :RecyclerView.Adapter<mainRecyclerAdapter.VeiwHolder>(){
    class VeiwHolder(val veiw: View) :RecyclerView.ViewHolder(veiw)
    {
        val topic:TextView
        val delete:Button
        init {
            topic= veiw.findViewById(R.id.topic)
            delete=veiw.findViewById(R.id.del)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VeiwHolder {
          val veiw=LayoutInflater.from(parent.context).inflate(R.layout.onenote,parent,false);
          return VeiwHolder(veiw)
    }

    override fun onBindViewHolder(holder: VeiwHolder, position: Int) {
        holder.topic.setText(dataset[position].topic)
        holder.delete.setOnClickListener(View.OnClickListener {
            val bd= database.getdatabase(context)
            val doaref=bd.getdao()
            val repo=repository(doaref)
            CoroutineScope(Dispatchers.IO).launch {
                repo.delete(dataset[position])
            }

        })
        holder.topic.setOnClickListener(View.OnClickListener {
            val intent =Intent(maincontext, makenote::class.java)
            intent.putExtra("context","old")
            intent.putExtra("id",dataset[position].id)
            intent.putExtra("topic",dataset[position].topic)
            intent.putExtra("desc",dataset[position].desc)
            maincontext.startActivity(intent)
        })
    }

    override fun getItemCount(): Int {
      return dataset.size
    }

}