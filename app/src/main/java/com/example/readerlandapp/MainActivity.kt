package com.example.readerlandapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth
        createaccount.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }
        loginbutton.setOnClickListener {
            if(emaillogin.text.isNotEmpty() && passwordlogin.text.isNotEmpty()){
                login()
            }else{
                Toast.makeText(this, "გთხოვთ შეავსოთ ყველა ველი", Toast.LENGTH_SHORT).show()
            }
        }
    }
    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            val intent = Intent(this, MainActivity3::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }

    private fun login() {
        auth.signInWithEmailAndPassword(emaillogin.text.toString(), passwordlogin.text.toString())
            .addOnCompleteListener(this) { task ->
                if (!task.isSuccessful) {
                    Toast.makeText(baseContext, "ავტორიზაცია ვერ მოხერხდა",
                        Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "ავტორიზაცია წარმატებულია", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity3::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            }
    }
}