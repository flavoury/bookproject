package com.favour.e_libary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class recyclercateginside : RecyclerView.Adapter<recyclercateginside.viewHolder >(){
    var tx1:Array<String> = arrayOf("The Dark World","Colours Of Life","Beauty")
    var txt2:Array<String> = arrayOf("19k","100k","1.97m")
    var txt3:Array<String> = arrayOf("English","Spanish","Korea")
    var txt4:Array<String> = arrayOf("The Dark World,(ALttP | FSA) also known as the Evil Realm,(OoT) is a recurring location in The Legend of Zelda series","Colours Of Life","Beauty")

    var img1:Array<Int> = arrayOf(R.drawable.paidstories,R.drawable.fre,R.drawable.rom,R.drawable.mys,R.drawable.educa,R.drawable.history)
    inner class viewHolder (ItemView: View): RecyclerView.ViewHolder(ItemView) {
    var text1: TextView
        var text2:TextView
    var text3:TextView
    var text4:TextView
    lateinit var image:ImageView
        init {
           text1 = itemView.findViewById(R.id.showTitle)
            text2=itemView.findViewById(R.id.likes)
           text3 =itemView.findViewById(R.id.lang)
            text4=itemView.findViewById(R.id.description)
            image=itemView.findViewById(R.id.cover)
    }

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val valu= LayoutInflater.from(parent.context).inflate(R.layout.categinside,parent,false)
        return viewHolder(valu)

    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        holder.text1.text=tx1[position]
        holder.text2.text=tx1[position]
        holder.text3.text=tx1[position]
        holder.text4.text=tx1[position]
        holder.image.setImageResource(img1[position])
    }
    override fun getItemCount(): Int {
        return tx1.size
    }
}