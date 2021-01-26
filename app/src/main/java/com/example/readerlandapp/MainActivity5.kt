package com.example.readerlandapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main5.*

class MainActivity5 : AppCompatActivity() {
    var dsrct = ""
    private var mylist = mutableListOf<itemforsale>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)
        val ref = FirebaseDatabase.getInstance().getReference("შეკვეთები")
        ref.addValueEventListener(getdata)
        ref.addListenerForSingleValueEvent(getdata)
        dsrct = intent.getStringExtra("district")!!
    }

    var getdata = object : ValueEventListener {
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
                if (dsrct == "შეკვეთები") {
                    mylist.add(itemforsale(key, address, date, phone, product, rest, dst))
                } else {
                    if (dsrct == dst) {
                        mylist.add(itemforsale(key, address, date, phone, product, rest, dst))
                    }
                }
            }
            recyclerview.adapter = adapter(mylist)
        }

        override fun onCancelled(error: DatabaseError) {
        }

    }


}
