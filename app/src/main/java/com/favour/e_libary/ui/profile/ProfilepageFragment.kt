package com.favour.e_libary.ui.profile

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.favour.e_libary.R
import com.favour.e_libary.editprofile
import com.favour.e_libary.search

class ProfilepageFragment : Fragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        val sign =root.findViewById<ImageView>(R.id.edit)
        sign.setOnClickListener {
            val intent = Intent(getActivity(), editprofile::class.java)
            getActivity()?.startActivity(intent)
        }


        return root
    }

}