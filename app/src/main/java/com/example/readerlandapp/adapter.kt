package com.example.readerlandapp

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.rowexample.view.*

class adapter(val mutableList: MutableList<itemforsale>) :RecyclerView.Adapter<adapter.viewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.rowexample, parent, false)
        return viewholder(item)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.onbind()
    }

    override fun getItemCount(): Int {
        return mutableList.size
    }

    inner class viewholder (itemview : View): RecyclerView.ViewHolder(itemview){
        fun onbind(){
            val address = mutableList[adapterPosition].address
            val time = mutableList[adapterPosition].time
            val phone = mutableList[adapterPosition].phone
            val product = mutableList[adapterPosition].product
            val restriction = mutableList[adapterPosition].rest
            val dist = mutableList[adapterPosition].distr
            itemView.address.text ="მისამართი:\n" + address
            itemView.date.text = "შეკვეთის დრო:\n" + time
            itemView.phone.text = "ტელეფონის ნომერი:\n" + phone
            itemView.product.text = "შეკვეთილი პროდუქტი:\n" + product
            itemView.restriction.text = restriction
            itemView.district.text = "უბანი : \n" + dist
        }
        init {
            itemview.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL)
                val phonenum = mutableList[adapterPosition].phone
                intent.data = Uri.parse("tel:$phonenum")
                itemview.context.startActivity(intent)
            }
            itemview.setOnLongClickListener {
                val ref = FirebaseDatabase.getInstance().getReference("შეკვეთები")
                val refcompleted = FirebaseDatabase.getInstance().getReference("შესრულებული")
                refcompleted.child(mutableList[adapterPosition].key).setValue(mutableList[adapterPosition])
                ref.child(mutableList[adapterPosition].key).removeValue()
                notifyDataSetChanged()
                true
            }
        }
    }
}