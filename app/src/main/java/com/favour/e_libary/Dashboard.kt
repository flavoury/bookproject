package com.favour.e_libary

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.net.toFile
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import org.w3c.dom.Text

class Dashboard : AppCompatActivity() {
    lateinit var path : Uri
    lateinit var pdfpath : Uri
    var connection = FirebaseDatabase.getInstance().getReference("Books")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val btnchooseimg = findViewById<Button>(R.id.addphoto)
        val btnchossepdf = findViewById<Button>(R.id.addpdf)
        val bknameid = findViewById<TextInputEditText>(R.id.bkname)
        val bklangid = findViewById<TextInputEditText>(R.id.lang)
        val bkpartsid = findViewById<TextInputEditText>(R.id.parts)
        val bkpriceid = findViewById<TextInputEditText>(R.id.bkprice)
        val bkdescr = findViewById<TextInputEditText>(R.id.bkdescription)
        val bkauthid = findViewById<TextInputEditText>(R.id.bkauthor)
        val btnuploadid = findViewById<Button>(R.id.upload)

        btnuploadid.setOnClickListener {
            var bknamestr = bknameid.text.toString()
            var bklangstr = bklangid.text.toString()
            var bkpartstr = bkpartsid.text.toString()
            var bkdescstr = bkdescr.text.toString()
            var bkprice = bkpriceid.text.toString()
            var bkauthstr = bkauthid.text.toString()

            when {
                bknamestr.isEmpty() -> {
                    bknameid.error = "Field is required"
                    bknameid.requestFocus()
                    return@setOnClickListener
                }
                bklangstr.isEmpty() -> {
                    bklangid.error = "Field is required"
                    bklangid.requestFocus()
                    return@setOnClickListener
                }
                bkpartstr.isEmpty() -> {
                    bkpartsid.error = "Field is required"
                    bkpartsid.requestFocus()
                    return@setOnClickListener
                }
                bkdescstr.isEmpty() -> {
                    bkdescr.error = "Field is required"
                    bkdescr.requestFocus()
                    return@setOnClickListener
                }
                bkprice.isEmpty() -> {
                    bkpriceid.error = "Field is required"
                    bkpriceid.requestFocus()
                    return@setOnClickListener
                }
                bkauthstr.isEmpty() -> {
                    bkauthid.error = "Field is required"
                    bkauthid.requestFocus()
                    return@setOnClickListener
                }
                else -> {
                    var insertkey = connection.push().key.toString()
                    uploadImgFiles(bknamestr, bklangstr, bkpartstr, bkprice, bkdescstr, insertkey, bkauthstr)
                }
            }
        }


        btnchooseimg.setOnClickListener {
            selectImageFromFile()
        }
        btnchossepdf.setOnClickListener {
            selectPdfFromFile()
        }
    }


    private fun selectImageFromFile(){
        var intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(Intent.createChooser(intent,"select cover image"), 222)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 222 && resultCode == Activity.RESULT_OK && data!=null){
            path = data.data!!
            var bitmap  = MediaStore.Images.Media.getBitmap(contentResolver,path)
            var imgview = findViewById<ImageView>(R.id.imgview)
            imgview.setImageBitmap(bitmap)
        }
        else if(requestCode == 212 && resultCode == Activity.RESULT_OK && data!=null){
            pdfpath = data.data!!
            var fnamtxt = findViewById<TextView>(R.id.bkfname)
            fnamtxt.text = pdfpath.path.toString()
        }
    }

    private fun selectPdfFromFile(){
        var intent = Intent()
        intent.type = "application/pdf"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(Intent.createChooser(intent,"select pdf file"), 212)
    }

    private fun uploadImgFiles(name: String, lang: String, parts: String, price: String, description: String, insertkey: String, author: String){

        if (path != null){

            lateinit var fbimgurl : String
            var imgref = FirebaseStorage.getInstance().reference.child("image/$insertkey.mp3")

            imgref.putFile(path).addOnSuccessListener {
                imgref.downloadUrl.addOnSuccessListener {
                    fbimgurl = it.toString()
                    uploadpdfFiles(name, lang, parts, price, description, fbimgurl, insertkey, author)
                }
                    .addOnFailureListener {
                        Toast.makeText(this, "Unable to get image url", Toast.LENGTH_SHORT).show()
                    }
            }
                .addOnFailureListener{
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
        }
    }

   private fun uploadpdfFiles(name: String, lang: String, parts: String, price: String, description: String, coverimgurl: String, insertkey: String, author: String){
        if (pdfpath != null){

            lateinit var fbpdfurl : String
            var pdfref = FirebaseStorage.getInstance().reference.child("books/$insertkey.pdf")

            pdfref.putFile(pdfpath).addOnSuccessListener {
                pdfref.downloadUrl.addOnSuccessListener {
                    fbpdfurl = it.toString()
                    insertBookToDB(name, lang, parts, price, description, coverimgurl, fbpdfurl, insertkey, author)
                }
                    .addOnFailureListener {
                        Toast.makeText(this, "Unable to get pdf url", Toast.LENGTH_SHORT).show()
                    }
            }
                .addOnFailureListener{
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun insertBookToDB(name: String, lang: String, parts: String, price: String, description: String, coverimgurl: String, pdfurl: String, key: String, author: String){
        val info = addbook(name, lang, parts, price, description, coverimgurl, pdfurl, author)
        connection.child(key).setValue(info)
        Toast.makeText(this, "Book uploaded to list successfully", Toast.LENGTH_SHORT).show()
    }
}