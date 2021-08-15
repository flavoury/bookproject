package com.favour.e_libary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class createaccount : AppCompatActivity() {
    var connection = FirebaseDatabase.getInstance().getReference("Users")
    private lateinit var signAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createaccount)

        signAuth = FirebaseAuth.getInstance()

        //Initialize variables
        var fnameid = findViewById<TextInputEditText>(R.id.fname)
        var lnameid = findViewById<TextInputEditText>(R.id.lname)
        var emailid = findViewById<TextInputEditText>(R.id.email)
        var passid = findViewById<TextInputEditText>(R.id.pass)
        var signupbtn = findViewById<Button>(R.id.signup)
        var loginid = findViewById<TextView>(R.id.login)

        loginid.setOnClickListener {
            startActivity(Intent(this, login::class.java))
        }

        signupbtn.setOnClickListener {
            var fnamestr = fnameid.text.toString().trim()
            var lnamestr = lnameid.text.toString().trim()
            var emailstr = emailid.text.toString().trim()
            var pwordstr = passid.text.toString().trim()

            when {
                fnamestr.isEmpty() -> {
                    fnameid.error = "Field is required"
                    fnameid.requestFocus()
                    return@setOnClickListener
                }
                lnamestr.isEmpty() -> {
                    lnameid.error = "Field is required"
                    lnameid.requestFocus()
                    return@setOnClickListener
                }
                emailstr.isEmpty() -> {
                    emailid.error = "Field is required"
                    emailid.requestFocus()
                    return@setOnClickListener
                }
                pwordstr.isEmpty() -> {
                    passid.error = "Field is required"
                    passid.requestFocus()
                    return@setOnClickListener
                }
                else -> {
                    addnewuser(fnamestr, lnamestr, emailstr, pwordstr)
                }
            }
        }
    }
    private fun addnewuser(firstname : String, lastname : String, email : String, pass : String){
        signAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this) {
            task -> if(task.isSuccessful){
            val userinfo = adduser(firstname, lastname, email)
            val user = signAuth.currentUser
            if (user != null){
                connection.child(user.uid).setValue(userinfo)
                welcome()
            }
        }
        else{
            task.exception?.message?.let {
                msg(it)
            }
        }
        }
    }
}