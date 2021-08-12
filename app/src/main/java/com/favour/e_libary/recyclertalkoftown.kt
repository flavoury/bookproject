package com.favour.e_libary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class recyclertalkoftown (private var bookslist: ArrayList<booksItem>) : RecyclerView.Adapter<recyclertalkoftown.viewHolder >(){
    inner class viewHolder (ItemView: View): RecyclerView.ViewHolder(ItemView) {
    var text11: TextView
    var text33:TextView
    var text44:TextView
    var text55: TextView
        init {
           text11 = itemView.findViewById(R.id.talktitle)
           text33 =itemView.findViewById(R.id.lang1)
            text44 =itemView.findViewById(R.id.desc)
            text55 = itemView.findViewById(R.id.parts1)
    }
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val valu= LayoutInflater.from(parent.context).inflate(R.layout.talkoftown,parent,false)
        return viewHolder(valu)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        var currentItem = bookslist[position]
        holder.text11.text = currentItem.getname()
        holder.text33.text = currentItem.getlanguage()
        holder.text55.text = currentItem.getparts().toString()
        holder.text44.text =  currentItem.getdescription().substring(0, 100)
    }
    override fun getItemCount(): Int {
        return bookslist.size
    }
}