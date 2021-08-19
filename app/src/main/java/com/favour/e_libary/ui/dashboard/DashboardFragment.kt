package com.favour.e_libary.ui.dashboard

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.favour.e_libary.R
import com.favour.e_libary.Recycler
import com.favour.e_libary.booksItem
import com.favour.e_libary.recyclerpurchased
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener

class DashboardFragment : Fragment() {
    lateinit var adapter: RecyclerView.Adapter<recyclerpurchased.viewHolder>
    lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var signAuth: FirebaseAuth


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        signAuth = FirebaseAuth.getInstance()
        val booksrecycler = root.findViewById<RecyclerView>(R.id.paidbooks)
        val prompt = root.findViewById<TextView>(R.id.prompt)
        var search = root.findViewById<SearchView>(R.id.searchb)
        val userid = signAuth.currentUser?.uid
        var connection = FirebaseDatabase.getInstance().getReference("Purchased").orderByChild("userid").equalTo(userid.toString())
        val list: ArrayList<booksItem> = ArrayList()

        val valueEventListener = object : ValueEventListener {
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
                    Log.i(ContentValues.TAG, "onDataChange: ${bookobj.getkey()}")
                    list.add(bookobj)
                }
                if (list.size >= 1) {
                    prompt.visibility = View.GONE
                } else {
                    search.visibility = View.GONE
                }

                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                booksrecycler.layoutManager = layoutManager
                adapter = recyclerpurchased(list)
                booksrecycler.adapter = adapter

                booksrecycler.setHasFixedSize(false)
                (adapter as recyclerpurchased).filter.filter("")

                search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        (adapter as recyclerpurchased).filter.filter(newText)
                        return false
                    }
                })
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(), "${error.message}", Toast.LENGTH_SHORT).show()
            }
        }
        connection.addListenerForSingleValueEvent(valueEventListener)
        return root
    }


}