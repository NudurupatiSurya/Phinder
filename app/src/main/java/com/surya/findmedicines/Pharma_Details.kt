package com.surya.findmedicines

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging

class Pharma_Details : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pharma_details)
        val next = findViewById<Button>(R.id.next)
        val lino = findViewById<EditText>(R.id.LicenseNumber)
        val stname = findViewById<EditText>(R.id.StoreName)
        val link = findViewById<EditText>(R.id.Locationlink)
        next.setOnClickListener {
            val database = Firebase.database
            val myRef = database.getReference("Pharmacists")
            val mysecondref = myRef.child(lino.text.toString())
            mysecondref.child("StoreName").setValue(stname.text.toString())
            mysecondref.child("StoreLink").setValue(link.text.toString())

            StoreFCMToken()
            val intent = Intent(this, Pharma_bottom_navigation::class.java)
            startActivity(intent)
        }
    }
    fun StoreFCMToken(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@addOnCompleteListener
            }
            val token = task.result
            val userID = Firebase.auth.currentUser?.uid.toString()
            val tokenref = Firebase.database.getReference("PharmaUID")
            tokenref.child(token.toString()).setValue(userID)
        }
    }
}