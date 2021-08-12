package com.favour.e_libary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class paidstories : AppCompatActivity() {
    lateinit var adapter: RecyclerView.Adapter<recyclercateginside.viewHolder>
    lateinit var layoutManager: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paidstories)

        val categories = findViewById<RecyclerView>(R.id.paidstory)
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        categories.layoutManager = layoutManager
        adapter=recyclercateginside()
        categories.adapter = adapter

    }
}