package com.favour.e_libary.ui.home

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.favour.e_libary.R
import com.favour.e_libary.Recycler
import com.favour.e_libary.booksItem
import com.favour.e_libary.recyclertalkoftown
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeFragment : Fragment() {

    lateinit var adapter: RecyclerView.Adapter<Recycler.viewHolder>
    lateinit var adapter1: RecyclerView.Adapter<recyclertalkoftown.viewHolder>
    lateinit var layoutmanager : RecyclerView.LayoutManager
    var connection = FirebaseDatabase.getInstance().getReference("Books")
    private var mybookslist = ArrayList<booksItem>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val popular = root.findViewById<RecyclerView>(R.id.recycle)

        loadData()

        layoutmanager = LinearLayoutManager(this.activity, LinearLayoutManager.HORIZONTAL, false)
        popular.layoutManager = layoutmanager
        adapter = Recycler(mybookslist)
        popular.adapter = adapter


//        val talfrom = root.findViewById<RecyclerView>(R.id.talkoftown)
//        layoutmanager = LinearLayoutManager(this.activity, LinearLayoutManager.HORIZONTAL, false)
//        adapter1= recyclertalkoftown(mybookslist)
//        talfrom.layoutManager = layoutmanager
//        talfrom.adapter=adapter1

        return root
    }

    fun loadData(){
        var templist = ArrayList<booksItem>()
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.children) {
                    var name = ds.child("name").getValue(String::class.java)
                    var imageurl = ds.child("coverimgurl").getValue(String::class.java)
                    var pdfurl = ds.child("pdfurl").getValue(String::class.java)
                    var description = ds.child("description").getValue(String::class.java)
                    var language = ds.child("language").getValue(String::class.java)
                    var parts = ds.child("parts").getValue(Int::class.java)
                    var price = ds.child("price").getValue(Int::class.java)
                    var key = ds.key
                    templist.add(booksItem(name.toString(), language.toString(), parts.toString().toInt(), price.toString().toInt(), description.toString(), imageurl.toString(), pdfurl.toString(), key.toString()))
                    mybookslist = templist
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(requireContext(), "${databaseError.message}", Toast.LENGTH_SHORT).show()
            }
        }
        connection.addListenerForSingleValueEvent(valueEventListener)
    }
}