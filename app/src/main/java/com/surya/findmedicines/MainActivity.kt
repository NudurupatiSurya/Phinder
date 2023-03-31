package com.surya.findmedicines

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var customerbutton = findViewById<Button>(R.id.Customer_Button)
        var pharmacistbutton = findViewById<Button>(R.id.Pharmacist_Button)
        val userID = Firebase.auth.currentUser?.uid.toString()
        val database = Firebase.database
        //customer
        if (FirebaseAuth.getInstance().currentUser != null) {
            val myRef = database.getReference("Customers").child("UID")

            myRef.addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (i in snapshot.children){
                        if (i.key.toString() == userID){
                            val intent = Intent(applicationContext, Customer_Bottom_Navigation::class.java)
                            startActivity(intent)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
            val myRef2 = database.getReference("Pharmacists").child("UID")
            myRef2.addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (i in snapshot.children){
                        if (i.key.toString() == userID){
                            val intent = Intent(applicationContext, Pharma_bottom_navigation::class.java)
                            startActivity(intent)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })

        }

        customerbutton.setOnClickListener {
            val myRef = database.getReference("Customers").child("UID")
            myRef.child(Firebase.auth.currentUser?.uid.toString()).setValue(Firebase.auth.currentUser?.displayName.toString())
            val intent = Intent(this, Customer_Bottom_Navigation::class.java)
            startActivity(intent)
        }
        pharmacistbutton.setOnClickListener {
            val myRef = database.getReference("Pharmacists").child("UID")
            myRef.child(Firebase.auth.currentUser?.uid.toString()).setValue(Firebase.auth.currentUser?.displayName.toString())
            myRef.child(Firebase.auth.currentUser?.uid.toString()).setValue("")
            val intent = Intent(this, Pharma_Details::class.java)
            startActivity(intent)
        }
    }
}