package com.favour.e_libary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class editprofile : AppCompatActivity() {
    private lateinit var signAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editprofile)

        signAuth = FirebaseAuth.getInstance()
        val emailid = findViewById<TextInputEditText>(R.id.email)
        val fnameid = findViewById<TextInputEditText>(R.id.fname)
        val lnameid = findViewById<TextInputEditText>(R.id.lname)
        val btnsave = findViewById<Button>(R.id.save_changes)
        val userid = signAuth.currentUser?.uid.toString()

        val connection = FirebaseDatabase.getInstance().getReference("Users").child(userid)
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(mydataSnapshot: DataSnapshot) {
                fnameid.setText(mydataSnapshot.child("firstname").getValue(String::class.java).toString())
                lnameid.setText(mydataSnapshot.child("lastname").getValue(String::class.java).toString())
                emailid.setText(mydataSnapshot.child("email").getValue(String::class.java).toString())
            }
            override fun onCancelled(myerror: DatabaseError) {
                Toast.makeText(this@editprofile, "${myerror.message}", Toast.LENGTH_SHORT).show()
            }
        }
        connection.addListenerForSingleValueEvent(valueEventListener)

        btnsave.setOnClickListener {
            var emailstr = emailid.text.toString()
            var fnamestr = fnameid.text.toString()
            var lnamestr = lnameid.text.toString()

            when {
                emailstr.trim().isEmpty() -> {
                    emailid.error = "Field is required"
                    emailid.requestFocus()
                    return@setOnClickListener
                }
                fnamestr.trim().isEmpty() -> {
                    fnameid.error = "Field is required"
                    fnameid.requestFocus()
                    return@setOnClickListener
                }
                lnamestr.trim().isEmpty() -> {
                    lnameid.error="Field is required"
                    lnameid.requestFocus()
                    return@setOnClickListener
                }
                else -> {
                    updateDetails(fnamestr, lnamestr, emailstr)
                }
            }
        }
        }

    private fun updateDetails(firstname : String, lastname : String, email : String){
        val connection = FirebaseDatabase.getInstance().getReference("Users")
        val user = signAuth.currentUser
        if (user != null){
            user!!.updateEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val userinfo = adduser(firstname, lastname, email)
                        connection.child(user.uid).setValue(userinfo)
                        Toast.makeText(this, "Details updated successfully", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(this, "Unable to update details", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    }
