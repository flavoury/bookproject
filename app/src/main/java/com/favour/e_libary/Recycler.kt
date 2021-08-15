package com.favour.e_libary

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.net.URL

class Recycler (private var bookslist: ArrayList<booksItem>) : RecyclerView.Adapter<Recycler.viewHolder>(){

    lateinit var mcontext: Context
    inner class viewHolder (ItemView: View): RecyclerView.ViewHolder(ItemView) {

        var text1: TextView
        var img: ImageView
        var cardbody : LinearLayout
    init {
        text1=itemView.findViewById(R.id.text)
        img=itemView.findViewById(R.id.image)
        cardbody = itemView.findViewById(R.id.body)
    }

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val valu= LayoutInflater.from(parent.context).inflate(R.layout.dashcard,parent,false)
        mcontext = parent.context
        return viewHolder(valu)

    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        var curritem = bookslist[position]
        holder.text1.text= curritem.getname()
        Picasso.get().load(curritem.getcoverimgurl()).into(holder.img)
        holder.cardbody.setOnClickListener {
            val intent = Intent(mcontext, aboutbooks::class.java)
            intent.putExtra("key", curritem.getkey())
            intent.putExtra("imgurl", curritem.getcoverimgurl())
            intent.putExtra("description", curritem.getdescription())
            intent.putExtra("parts", curritem.getparts())
            intent.putExtra("price", curritem.getprice())
            intent.putExtra("name", curritem.getname())
            intent.putExtra("pdfurl", curritem.getpdfurl())
            intent.putExtra("author", curritem.getauthor())
            intent.putExtra("language", curritem.getlanguage())
            mcontext.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return bookslist.size
    }
}