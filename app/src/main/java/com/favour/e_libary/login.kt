package com.favour.e_libary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sign = findViewById<Button>(R.id.login)
        sign.setOnClickListener {
            val next1 = Intent(this, bottomnav::class.java)
            startActivity(next1)
        }
    }
}