package com.favour.e_libary.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.favour.e_libary.R
import com.favour.e_libary.Recyclersearch

class CategoriespageFragment : Fragment() {
    lateinit var adapter: RecyclerView.Adapter<Recyclersearch.viewHolder>
    lateinit var layoutManager: RecyclerView.LayoutManager
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.categories_fragment, container, false)

        val categories = root.findViewById<RecyclerView>(R.id.catrecy)
        layoutManager = GridLayoutManager(this.activity,2 , GridLayoutManager.VERTICAL, false)
        categories.layoutManager = layoutManager
        adapter= Recyclersearch()
        categories.adapter = adapter
        return root
    }

}