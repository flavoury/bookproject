package com.favour.e_libary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.favour.e_libary.ui.main.CategoriespageFragment

class categoriespage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.categoriespage_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, CategoriespageFragment.newInstance())
                    .commitNow()
        }
    }
}