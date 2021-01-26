package com.example.readerlandapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import android.widget.Toast
import com.example.readerlandapp.district
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main4.*
import kotlinx.android.synthetic.main.rowexample.*

class MainActivity4 : AppCompatActivity() {
    private val ref = FirebaseDatabase.getInstance().getReference("შეკვეთები")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        placebut.setOnClickListener {
            if (adressadd.text.isNotEmpty() && timeadd.text.isNotEmpty() && phoneadd.text.isNotEmpty() && productadd.text.isNotEmpty()
                && districtbutton.text != "უბანი"){
                placeorder()}else{
                Toast.makeText(this, "გთხოვთ შეავსოთ ყველა ველი", Toast.LENGTH_SHORT).show()}
        }
        districtbutton.setOnClickListener {
            val popupMenu = PopupMenu(this, it)
            popupMenu.setOnMenuItemClickListener {
                districtbutton.text = it.title.toString()
                true
            }
            popupMenu.inflate(R.menu.popupmenu)
            popupMenu.show()
        }
    }

    private fun placeorder() {
        val address = adressadd.text.toString()
        val time = timeadd.text.toString()
        val phone = phoneadd.text.toString()
        val product = productadd.text.toString()
        val restriction = restrictionadd.text.toString()
        val dist = districtbutton.text.toString()
        val key = ref.push().key.toString()
        ref.child(key).setValue(itemforsale(key,address, time, phone, product, restriction, dist))
            .addOnSuccessListener { startActivity(Intent(this, MainActivity3::class.java))
                Toast.makeText(this, "შეკვეთა განთავსდა", Toast.LENGTH_SHORT).show()}
            .addOnFailureListener {
                Toast.makeText(this, "მოხდა შეცდომა : $it", Toast.LENGTH_SHORT).show()
            }
    }
}