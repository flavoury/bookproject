package com.favour.e_libary.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.favour.e_libary.R
import com.favour.e_libary.Recycler
import com.favour.e_libary.recyclertalkoftown
import com.favour.e_libary.search
import com.favour.e_libary.ui.profile.ProfilepageFragment

class HomeFragment : Fragment() {

    lateinit var adapter: RecyclerView.Adapter<Recycler.viewHolder>
    lateinit var adapter1: RecyclerView.Adapter<recyclertalkoftown.viewHolder>
    lateinit var layoutmanager : RecyclerView.LayoutManager


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       val root = inflater.inflate(R.layout.fragment_home, container, false)
        val categories = root.findViewById<RecyclerView>(R.id.recycle)
        layoutmanager = LinearLayoutManager(this.activity, LinearLayoutManager.HORIZONTAL, false)
        adapter = Recycler()
        categories.layoutManager = layoutmanager
        categories.adapter = adapter


        val talfrom = root.findViewById<RecyclerView>(R.id.talkoftown)
        layoutmanager = LinearLayoutManager(this.activity, LinearLayoutManager.HORIZONTAL, false)
        adapter1= recyclertalkoftown()
        talfrom.layoutManager = layoutmanager
        talfrom.adapter=adapter1

        val sign =root.findViewById<ImageView>(R.id.searcicon)
        sign.setOnClickListener {
            val intent = Intent(getActivity(), search::class.java)
            getActivity()?.startActivity(intent)
        }


        return root
    }
}