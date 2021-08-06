package com.favour.e_libary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class search : AppCompatActivity() {
    lateinit var adapter: RecyclerView.Adapter<Recyclersearch.viewHolder>
    lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val categories = findViewById<RecyclerView>(R.id.catrecy)
        layoutManager = GridLayoutManager(this,2 ,GridLayoutManager.VERTICAL, false)
        categories.layoutManager = layoutManager
        adapter=Recyclersearch()
        categories.adapter = adapter

    }
}