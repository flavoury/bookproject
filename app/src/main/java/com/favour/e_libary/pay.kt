package com.favour.e_libary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class pay : AppCompatActivity() {
    var connection = FirebaseDatabase.getInstance().getReference("Purchased")
    private lateinit var signAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)

        signAuth = FirebaseAuth.getInstance()
        val cnameid = findViewById<TextInputEditText>(R.id.cname)
        val cnumid = findViewById<TextInputEditText>(R.id.cnum)
        val cexpdateid = findViewById<TextInputEditText>(R.id.cexpdate)
        val cvvid = findViewById<TextInputEditText>(R.id.cvv)
        val paybtn = findViewById<Button>(R.id.pay)
        val bookKey = intent.getStringExtra("book_key")
        val userid = signAuth.currentUser?.uid
        var name = intent.getStringExtra("name").toString()
        var imgurl = intent.getStringExtra("imgurl").toString()
        var price = intent.getStringExtra("price").toString()
        var author = intent.getStringExtra("author").toString()
        var description = intent.getStringExtra("description").toString()
        var parts = intent.getStringExtra("parts").toString()
        var lang = intent.getStringExtra("language").toString()
        var pdfurl = intent.getStringExtra("pdfurl").toString()

        var connectioncheck = FirebaseDatabase.getInstance().getReference("Purchased").orderByChild("userid").equalTo(userid.toString())
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                paybtn.setOnClickListener {
                    var cnamestr = cnameid.text.toString()
                    var cnumstr = cnumid.text.toString()
                    var cexpstr = cexpdateid.text.toString()
                    var cvvstr = cvvid.text.toString()

                    when{
                        (cnamestr.trim().isEmpty()) -> {
                            cnameid.error = "Field is required"
                            cnameid.requestFocus()
                            return@setOnClickListener
                        }
                        (cnumstr.trim().isEmpty()) -> {
                            cnumid.error = "Field is required"
                            cnumid.requestFocus()
                            return@setOnClickListener
                        }
                        (cexpstr.trim().isEmpty()) -> {
                            cexpdateid.error = "Field is required"
                            cexpdateid.requestFocus()
                            return@setOnClickListener
                        }
                        (cvvstr.trim().isEmpty()) -> {
                            cvvid.error = "Field is required"
                            cvvid.requestFocus()
                            return@setOnClickListener
                        }
                        else -> {
                            var check = 0
                            for (ds in snapshot.children){
                                var purbook = ds.child("bookid").getValue(String::class.java).toString()
                                if (purbook == bookKey){
                                    check += 1
                                }
                            }
                            if (check > 0){
                                Toast.makeText(this@pay, "You have already purchased this book", Toast.LENGTH_LONG).show()
                            }
                            else{
                                addnew(bookKey.toString(), userid.toString(), name, lang, parts, price, description, imgurl, pdfurl, author)
                            }
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@pay, "${error.message}", Toast.LENGTH_SHORT).show()
            }
        }
        connectioncheck.addListenerForSingleValueEvent(valueEventListener)

    }
    private fun addnew(bookId: String, userId: String, name: String, language: String, parts: String, price: String, description: String, coverimgurl: String, pdfurl: String, author: String){
        val newbook = addpayed(bookId, userId, name, language, parts, price, description, coverimgurl, pdfurl, author)
        connection.push().setValue(newbook)
        startActivity(Intent(this, paymentsuccess::class.java))
    }
}
