package com.favour.e_libary.ui.main

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.favour.e_libary.R
import com.favour.e_libary.changepassword
import com.favour.e_libary.paidstories

class CategoriespageFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.categories_fragment, container, false)
        val secur =root.findViewById<LinearLayout>(R.id.paid)
        secur.setOnClickListener {
            val intent = Intent(getActivity(), paidstories::class.java)
            getActivity()?.startActivity(intent)
        }
        val secur1 =root.findViewById<LinearLayout>(R.id.free)
        secur1.setOnClickListener {
            val intent = Intent(getActivity(), paidstories::class.java)
            getActivity()?.startActivity(intent)
        }

        return root
    }

}