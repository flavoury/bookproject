package com.favour.e_libary.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import com.favour.e_libary.R
import com.favour.e_libary.changepassword
import com.favour.e_libary.editprofile
import com.favour.e_libary.login
import com.google.firebase.auth.FirebaseAuth

class ProfilepageFragment : Fragment() {


    private lateinit var signAuth: FirebaseAuth
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        signAuth = FirebaseAuth.getInstance()
        val sign =root.findViewById<LinearLayout>(R.id.editprofile)
        sign.setOnClickListener {
            val intent = Intent(getActivity(), editprofile::class.java)
            getActivity()?.startActivity(intent)
        }
        val secur =root.findViewById<LinearLayout>(R.id.security)
        secur.setOnClickListener {
            val intent = Intent(getActivity(), changepassword::class.java)
            getActivity()?.startActivity(intent)
        }
        val logout = root.findViewById<CardView>(R.id.signout)
        logout.setOnClickListener {
            signAuth.signOut()
            startActivity(Intent(requireContext(), login::class.java))
            activity?.finish()
        }


        return root
    }

}