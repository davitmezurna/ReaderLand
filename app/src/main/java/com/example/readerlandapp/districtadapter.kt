package com.example.readerlandapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main4.view.*
import kotlinx.android.synthetic.main.districtexample.view.*

class districtadapter(val distlist : MutableList<district>): RecyclerView.Adapter<districtadapter.viewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.districtexample, parent, false)
        return viewholder(item)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.onbind()
    }

    override fun getItemCount(): Int {
        return distlist.size
    }
    inner class viewholder(itemview: View): RecyclerView.ViewHolder(itemview){
        fun onbind(){
            val distname = distlist[adapterPosition].dist
            val count = distlist[adapterPosition].num
            itemView.buttondistrict.text = distname
            itemView.amountdistrict.text = count
        }
        init {
            itemview.buttondistrict.setOnClickListener {
                Toast.makeText(itemview.context, distlist[adapterPosition].dist, Toast.LENGTH_SHORT).show()
                val intent = Intent(itemview.context, MainActivity5::class.java)
                intent.putExtra("district", distlist[adapterPosition].dist)
                itemview.context.startActivity(intent)
            }
        }
    }

}