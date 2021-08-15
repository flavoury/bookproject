package com.favour.e_libary

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class paidstories : AppCompatActivity() {
    lateinit var adapter: RecyclerView.Adapter<recyclercateginside.viewHolder>
    lateinit var layoutManager: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paidstories)


        val status = intent.getStringExtra("type")
        val pgheader = findViewById<TextView>(R.id.pghead)
        var search =  findViewById<SearchView>(R.id.bksearch)
        val categories = findViewById<RecyclerView>(R.id.paidstory)


        if (status == "free") {
            pgheader.text = "Free books"
            var connection = FirebaseDatabase.getInstance().getReference("Books").orderByChild("price").equalTo("0")
            val list: ArrayList<booksItem> = ArrayList()
            val valueEventListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (ds in dataSnapshot.children) {
                        var name = ds.child("name").getValue(String::class.java)
                        var imageurl = ds.child("coverimgurl").getValue(String::class.java)
                        var pdfurl = ds.child("pdfurl").getValue(String::class.java)
                        var description = ds.child("description").getValue(String::class.java)
                        var language = ds.child("language").getValue(String::class.java)
                        var parts = ds.child("parts").getValue(String::class.java)
                        var price = ds.child("price").getValue(String::class.java)
                        var key = ds.key
                        var author = ds.child("author").getValue(String::class.java)
                        var bookobj = booksItem(name.toString(), language.toString(), parts.toString(), price.toString(),
                                description.toString(), imageurl.toString(), pdfurl.toString(), key.toString(), author.toString())
                        Log.i(ContentValues.TAG, "onDataChange: ${bookobj.getkey()}")
                        list.add(bookobj)
                    }

                    layoutManager = LinearLayoutManager(this@paidstories, LinearLayoutManager.VERTICAL, false)
                    categories.layoutManager = layoutManager
                    adapter=recyclercateginside(list)
                    categories.adapter = adapter

                    categories.setHasFixedSize(false)
                    (adapter as recyclercateginside).filter.filter("")

                    search.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            return false
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            (adapter as recyclercateginside).filter.filter(newText)
                            return false
                        }
                    })

                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Toast.makeText(this@paidstories, "${databaseError.message}", Toast.LENGTH_SHORT).show()
                }
            }
            connection.addListenerForSingleValueEvent(valueEventListener)
        }
        else{
            pgheader.text = "Paid books"
            var connection = FirebaseDatabase.getInstance().getReference("Books").orderByChild("price").startAfter("0")
            val list: ArrayList<booksItem> = ArrayList()
            val valueEventListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (ds in dataSnapshot.children) {
                        var name = ds.child("name").getValue(String::class.java)
                        var imageurl = ds.child("coverimgurl").getValue(String::class.java)
                        var pdfurl = ds.child("pdfurl").getValue(String::class.java)
                        var description = ds.child("description").getValue(String::class.java)
                        var language = ds.child("language").getValue(String::class.java)
                        var parts = ds.child("parts").getValue(String::class.java)
                        var price = ds.child("price").getValue(String::class.java)
                        var key = ds.key
                        var author = ds.child("author").getValue(String::class.java)
                        var bookobj = booksItem(name.toString(), language.toString(), parts.toString(), price.toString(),
                                description.toString(), imageurl.toString(), pdfurl.toString(), key.toString(), author.toString())
                        Log.i(ContentValues.TAG, "onDataChange: ${bookobj.getkey()}")
                        list.add(bookobj)
                    }

                    layoutManager = LinearLayoutManager(this@paidstories, LinearLayoutManager.VERTICAL, false)
                    categories.layoutManager = layoutManager
                    adapter=recyclercateginside(list)
                    categories.adapter = adapter

                    categories.setHasFixedSize(false)
                    (adapter as recyclercateginside).filter.filter("")

                    search.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            return false
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            (adapter as recyclercateginside).filter.filter(newText)
                            return false
                        }
                    })

                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Toast.makeText(this@paidstories, "${databaseError.message}", Toast.LENGTH_SHORT).show()
                }
            }
            connection.addListenerForSingleValueEvent(valueEventListener)
        }

    }
}