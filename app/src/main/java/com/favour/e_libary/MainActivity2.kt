package com.favour.e_libary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener

class MainActivity2 : AppCompatActivity() {
    var imgarr: ArrayList<Int> = ArrayList()
    var carouselView: CarouselView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        imgarr.add(R.drawable.book)
        imgarr.add(R.drawable.book2)
        val add = imgarr.add(R.drawable.book3)

        carouselView = findViewById(R.id.carouselView)
        carouselView!!.pageCount = imgarr.size

        carouselView!!.setImageListener(imageListener)


        val log = findViewById<LinearLayout>(R.id.btnlog)
        val reg = findViewById<LinearLayout>(R.id.btnsign)

        log.setOnClickListener {
            val intent = Intent(this, createaccount::class.java)
            startActivity(intent)

        }

        reg.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)

        }
    }


   var imageListener = ImageListener { position, imageView -> imageView.setImageResource(imgarr[position]) }    }


