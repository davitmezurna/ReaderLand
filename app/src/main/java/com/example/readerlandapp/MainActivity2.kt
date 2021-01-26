package com.example.readerlandapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        auth = Firebase.auth
        registerbut.setOnClickListener {
            val name = namesignup.text
            val lastname = lastnamesignup.text
            val email = emailsignup.text
            val password = passwordsignup.text
            val reppass = reppasssignup.text
            val registercode = codesignup.text
            if (name.isNotEmpty() && lastname.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()
                && reppass.toString()==password.toString()){
                    if (registercode.toString()=="test"){
                        register()}else{
                        Toast.makeText(this, "სარეგისტრაციო კოდი არასწორია", Toast.LENGTH_SHORT).show()
                        }

            }else{
                Toast.makeText(this, "გთხოვთ შეავსოთ ყველა ველი", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun register() {
        val email = emailsignup.text
        val password = passwordsignup.text
        auth.createUserWithEmailAndPassword(email.toString(), password.toString())
            .addOnCompleteListener(this) { task ->
                if (!task.isSuccessful) {
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "თქვენ წარმატებულად დარეგისტრირდით", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }
    }
}