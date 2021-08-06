package com.favour.e_libary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Recyclertoppicks : RecyclerView.Adapter<Recyclertoppicks.viewHolder>(){
    var txtpicks:Array<String> = arrayOf("Culture","Colours Of Life","Beauty")
    var img1picks:Array<Int> = arrayOf(R.drawable.thriller1,R.drawable.volours,R.drawable.beat,R.drawable.culture,R.drawable.volours)
    inner class viewHolder (ItemView: View): RecyclerView.ViewHolder(ItemView) {
    var text1: TextView
    var img:ImageView

    init {
        text1=itemView.findViewById(R.id.PICKStext)
        img=itemView.findViewById(R.id.image1)
    }

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val valu= LayoutInflater.from(parent.context).inflate(R.layout.dashcard,parent,false)
        return viewHolder(valu)

    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        holder.text1.text=txtpicks[position]
        holder.img.setImageResource(img1picks[position])

    }
    override fun getItemCount(): Int {
        return txtpicks.size
    }
}