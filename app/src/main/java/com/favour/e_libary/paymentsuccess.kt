package com.favour.e_libary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class paymentsuccess : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paymentsuccess)

        Handler().postDelayed({
            startActivity(Intent(this@paymentsuccess, bottomnav::class.java))
            finish()
        },2000)
    }
}