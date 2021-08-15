package com.favour.e_libary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class changepassword : AppCompatActivity() {
    private lateinit var signAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_changepassword)

        signAuth = FirebaseAuth.getInstance()
        val userid = signAuth.currentUser?.uid.toString()
        val currpass = findViewById<TextInputEditText>(R.id.curr_pass)
        val newpass = findViewById<TextInputEditText>(R.id.new_pass)
        val confnewpass = findViewById<TextInputEditText>(R.id.new_pass_confirm)
        val btnsave = findViewById<Button>(R.id.save_pass)
        var email : String

        val connection = FirebaseDatabase.getInstance().getReference("Users").child(userid)
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(mydataSnapshot: DataSnapshot) {
                email = mydataSnapshot.child("email").getValue(String::class.java).toString()
                btnsave.setOnClickListener {
                    val currpasstr = currpass.text.toString()
                    val newpasstr = newpass.text.toString()
                    val confpasstr = confnewpass.text.toString()

                    when{
                        currpasstr.trim().isEmpty() -> {
                            currpass.error = "Field is required"
                            currpass.requestFocus()
                            return@setOnClickListener
                        }
                        newpasstr.trim().isEmpty() -> {
                            newpass.error = "Field is required"
                            newpass.requestFocus()
                            return@setOnClickListener
                        }
                        confpasstr.trim().isEmpty() -> {
                            confnewpass.error = "Field is required"
                            confnewpass.requestFocus()
                            return@setOnClickListener
                        }
                        newpasstr != confpasstr -> {
                            Toast.makeText(this@changepassword, "Passwords do not match", Toast.LENGTH_SHORT).show()
                        }
                        revalidateUser(email, currpasstr) == "false" -> {
                            Toast.makeText(this@changepassword, "Incorrect password", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            updatePassword(newpasstr)
                        }
                    }
                }
            }
            override fun onCancelled(myerror: DatabaseError) {
                Toast.makeText(this@changepassword, "${myerror.message}", Toast.LENGTH_SHORT).show()
            }
        }
        connection.addListenerForSingleValueEvent(valueEventListener)
    }
    
    private fun revalidateUser(email: String, password: String) : String {
        var status = ""
        val user = signAuth.currentUser
        val credential = EmailAuthProvider.getCredential(email, password)
        user?.reauthenticate(credential)?.addOnCompleteListener {
            status = when {
                it.isSuccessful -> {
                    "true"
                }
                else -> {
                    "false"
                }
            }
        }
        return status
    }

    private fun updatePassword(password: String){
        val user = signAuth.currentUser
        user!!.updatePassword(password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this@changepassword, "Password updated successfully", Toast.LENGTH_SHORT).show()
                }
            }
    }
}