package com.favour.e_libary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class recyclertalkoftown : RecyclerView.Adapter<recyclertalkoftown.viewHolder >(){
    var tx1t:Array<String> = arrayOf("The Dark World","Colours Of Life","Beauty")
    var txt2t:Array<String> = arrayOf("19k","100k","1.97m")
    var txt3t:Array<String> = arrayOf("English","Spanish","Korea")
    var txt4t:Array<String> = arrayOf("Paid Story","Free Story","Paid Story")

    var imgageg1:Array<Int> = arrayOf(R.drawable.thriller1,R.drawable.thriller1,R.drawable.thriller1)
    inner class viewHolder (ItemView: View): RecyclerView.ViewHolder(ItemView) {
    var text11: TextView
    var text22:TextView
    var text33:TextView
    var text44:TextView
     var image12:ImageView
        init {
           text11 = itemView.findViewById(R.id.talktitle)
            text22=itemView.findViewById(R.id.likes1)
           text33 =itemView.findViewById(R.id.lang1)
            text44=itemView.findViewById(R.id.desc)
            image12=itemView.findViewById(R.id.thr)
    }

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val valu= LayoutInflater.from(parent.context).inflate(R.layout.talkoftown,parent,false)
        return viewHolder(valu)

    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        holder.text11.text=tx1t[position]
        holder.text22.text=txt2t[position]
        holder.text33.text=txt3t[position]
        holder.text44.text=txt4t[position]
        holder.image12.setImageResource(imgageg1[position])
    }
    override fun getItemCount(): Int {
        return tx1t.size
    }
}