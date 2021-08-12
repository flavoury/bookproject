package com.favour.e_libary

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.net.URL

class Recycler (private var bookslist: ArrayList<booksItem>) : RecyclerView.Adapter<Recycler.viewHolder>(){

    lateinit var mcontext: Context
    inner class viewHolder (ItemView: View): RecyclerView.ViewHolder(ItemView) {

        var text1: TextView
        var img: ImageView

    init {
        text1=itemView.findViewById(R.id.text)
        img=itemView.findViewById(R.id.image)
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
        val url = URL(curritem.getcoverimgurl())
        val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
        holder.img.setImageBitmap(bitmap)

    }

    override fun getItemCount(): Int {
        return bookslist.size
    }
}