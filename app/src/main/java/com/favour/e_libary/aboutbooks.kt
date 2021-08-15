package com.favour.e_libary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso

class aboutbooks : AppCompatActivity() {
    var connection = FirebaseDatabase.getInstance().getReference("Books")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aboutbooks)

        var key = intent.getStringExtra("key")
        var name = intent.getStringExtra("name")
        var imgurl = intent.getStringExtra("imgurl")
        var price = intent.getStringExtra("price")
        var author = intent.getStringExtra("author")
        var description = intent.getStringExtra("description")
        var parts = intent.getStringExtra("parts")
        var lang = intent.getStringExtra("language")
        var pdfurl = intent.getStringExtra("pdfurl")

        val btnbuyid = findViewById<LinearLayout>(R.id.btnbuy)
        val btnread = findViewById<LinearLayout>(R.id.btnread)
        val bkimgid = findViewById<ImageView>(R.id.bkimg)
        val bknameid = findViewById<TextView>(R.id.bkname)
        val bkpartsid = findViewById<TextView>(R.id.bkparts)
        val bkpriceid = findViewById<TextView>(R.id.bkcat)
        val bkdescriptionid = findViewById<TextView>(R.id.bkdescription)
        val bklangid = findViewById<TextView>(R.id.bklang)
        val bkauthid = findViewById<TextView>(R.id.bkauthor)

        bknameid.text = name.toString()
        Picasso.get().load(imgurl).into(bkimgid)
        bkauthid.text = author.toString()
        bkdescriptionid.text = description.toString()
        bkpartsid.text = parts.toString()+" parts"
        bklangid.text = lang.toString()

        if (price == "0"){
            btnbuyid.visibility = View.GONE
            bkpriceid.text = "Free"
        }
        else{
            btnread.visibility = View.GONE
            bkpriceid.text = "$ "+price.toString()
        }

        btnread.setOnClickListener {
            val intent = Intent(this, viewpdf::class.java)
            intent.putExtra("pdfurl", pdfurl)
            startActivity(intent)
        }
        btnbuyid.setOnClickListener {
            val intent = Intent(this, pay::class.java)
            startActivity(intent)
        }

    }
}