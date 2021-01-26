package com.example.readerlandapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main6.*

class MainActivity6 : AppCompatActivity() {
    var mylist = mutableListOf<itemforsale>()
    val ref = FirebaseDatabase.getInstance().getReference("შესრულებული")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)
        ref.addValueEventListener(getdata)
    }
    private var getdata = object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            mylist.clear()
            snapshot.children.forEach {
                val key = it.child("key").value.toString()
                val address = it.child("address").value.toString()
                val date = it.child("time").value.toString()
                val phone = it.child("phone").value.toString()
                val product = it.child("product").value.toString()
                val rest = it.child("rest").value.toString()
                val dst = it.child("distr").value.toString()
                mylist.add(itemforsale(key, address, date, phone, product, rest, dst))
            }
            completedrecycler.adapter = adapter(mylist)
        }

        override fun onCancelled(error: DatabaseError) {
        }

    }

}