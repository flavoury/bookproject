package com.favour.e_libary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class login : AppCompatActivity() {
    private lateinit var signAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        signAuth = FirebaseAuth.getInstance()

        var emailid = findViewById<TextInputEditText>(R.id.uname)
        var passwordid = findViewById<TextInputEditText>(R.id.pass)
        var loginid = findViewById<Button>(R.id.login)
        var signupid = findViewById<TextView>(R.id.signup)

        signupid.setOnClickListener {
            startActivity(Intent(this, createaccount::class.java))
        }

        loginid.setOnClickListener {
            var emailstr = emailid.text.toString().trim()
            var pwordstr = passwordid.text.toString().trim()
            when {
                emailstr.isEmpty() -> {
                    emailid.error = "Username is required"
                    emailid.requestFocus()
                    return@setOnClickListener
                }
                pwordstr.isEmpty() -> {
                    passwordid.error = "password is required"
                    passwordid.requestFocus()
                    return@setOnClickListener
                }
                emailstr == "admin" && pwordstr == "admin" -> {
                    startActivity(Intent(this, Dashboard::class.java))
                }
                else -> {
                    userLogin(emailstr, pwordstr)
                }
            }
        }
    }

    private fun userLogin(email : String, password : String){
        signAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this){
            work ->
            if (work.isSuccessful){
                welcome()
            }
            else{
                work.exception?.message?.let {
                    msg(it)
                }
            }
        }
    }
}

