package com.favour.e_libary.ui.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.favour.e_libary.R
import com.favour.e_libary.Recycler
import com.favour.e_libary.booksItem
import com.favour.e_libary.recyclertalkoftown
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.w3c.dom.Text

class HomeFragment : Fragment() {

    lateinit var adapter: RecyclerView.Adapter<Recycler.viewHolder>
    lateinit var adapter1: RecyclerView.Adapter<recyclertalkoftown.viewHolder>
    lateinit var layoutmanager: RecyclerView.LayoutManager
    var connectionpaid = FirebaseDatabase.getInstance().getReference("Books").limitToFirst(3).orderByChild("price").startAfter("0")
    var connectionfree = FirebaseDatabase.getInstance().getReference("Books").limitToFirst(3).orderByChild("price").equalTo("0")
    private lateinit var signAuth: FirebaseAuth

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        signAuth = FirebaseAuth.getInstance()
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val listpaid: ArrayList<booksItem> = ArrayList()
        val listfree: ArrayList<booksItem> = ArrayList()

        //Event listener for selecting paid books from database
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
                    Log.i(TAG, "onDataChange: ${bookobj.getkey()}")
                    listpaid.add(bookobj)
                }
                val talfrom = root.findViewById<RecyclerView>(R.id.talkoftown)
                layoutmanager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter1 = recyclertalkoftown(listpaid)
                talfrom.layoutManager = layoutmanager
                talfrom.adapter = adapter1
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(requireContext(), "${databaseError.message}", Toast.LENGTH_SHORT).show()
            }
        }
        connectionpaid.addListenerForSingleValueEvent(valueEventListener)


        //Event listener for selecting free books from database
        val secondValueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children) {
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
                    Log.i(TAG, "onDataChange: ${bookobj.getkey()}")
                    listfree.add(bookobj)
                }
                val popular = root.findViewById<RecyclerView>(R.id.poprecycle)
                layoutmanager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = Recycler(listfree)
                popular.layoutManager = layoutmanager
                popular.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "${error.message}", Toast.LENGTH_SHORT).show()
            }

        }
        connectionfree.addListenerForSingleValueEvent(secondValueEventListener)

        val userid = signAuth.currentUser?.uid.toString()
        val userconnection = FirebaseDatabase.getInstance().getReference("Users").child(userid)
        val myvalueEventListener = object : ValueEventListener {
            override fun onDataChange(mydataSnapshot: DataSnapshot) {
                val fname = root.findViewById<TextView>(R.id.ufname)
                fname.text = mydataSnapshot.child("firstname").getValue(String::class.java).toString()+"!"
            }
            override fun onCancelled(myerror: DatabaseError) {
                Toast.makeText(requireContext(), "${myerror.message}", Toast.LENGTH_SHORT).show()
            }
        }
        userconnection.addListenerForSingleValueEvent(myvalueEventListener)
        return root
    }

}