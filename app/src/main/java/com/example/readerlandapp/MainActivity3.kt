package com.example.readerlandapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main3.*
import java.util.*

class MainActivity3 : AppCompatActivity() {
    private var distlist = mutableListOf("შეკვეთები","აეროპორტის დასახლება(ქვემო ალექსეევკა)"
    , "ავლაბარი", "გლდანი(გლდანულა)", "დიდი დიღომი", "დიდუბე(ვაგზალი , წერეთელი , დიდუბე)"
    , "დიღმის მასივი", "დიღომი", "ვაკე(წყნეთი)", "ვარკეთილი-ვაზისუბანი", "ვერა", "ზაჰესი"
    , "თემქა", "ისანი-სამგორი", "კრწანისი", "ლილო", "მთაწმინდა(სოლოლაკი)", "ნაძალადევი(ლოტკინი, ნახალოვკა)"
    , "ნუცუბიძე", "ორხევი", "ოქროყანა ,შინდისი, კიკეთი, ტაბახმელა, კოჯორი", "საბურთალო",
    "სანზონა", "სოფელი დიღომი-ვაშლიჯვარი", "ჩუღურეთი")
    private var districts = mutableListOf<district>()
    private val ref = FirebaseDatabase.getInstance().getReference("შეკვეთები")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        addbutton.setOnClickListener {
            if (FirebaseAuth.getInstance().currentUser!!.uid == "Kbv6ZBe0bdP8Pojqi9BtoqCytIo1"){
                startActivity(Intent(this, MainActivity4::class.java))
            }else{
                Toast.makeText(this, "შეკვეთების დამატება მხოლოდ ადმინის ანგარიშიდანაა შესაძლებელი", Toast.LENGTH_SHORT).show()
            }
        }
        buttonlogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        ref.addValueEventListener(getdata)
        ref.addListenerForSingleValueEvent(getdata)
        completedorders.setOnClickListener {
            startActivity(Intent(this, MainActivity6::class.java))
        }
    }

    private var getdata = object : ValueEventListener{
        override fun onDataChange(snapshot: DataSnapshot) {
            districts.clear()
            distlist.forEach {
                if (it == "შეკვეთები"){
                    districts.add(district(it, snapshot.childrenCount.toString()))
                }else{
                    var n = 0
                    snapshot.children.forEach { itsnap : DataSnapshot ->
                        if (it == itsnap.child("distr").value.toString()){
                            n++
                        }
                    }
                    districts.add(district(it, n.toString()))
                }
            }
            recyclerdistricts.adapter = districtadapter(districts)
        }

        override fun onCancelled(error: DatabaseError) {
        }
    }
}