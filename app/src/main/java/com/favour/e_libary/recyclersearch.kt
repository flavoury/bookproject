package com.favour.e_libary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Recyclersearch : RecyclerView.Adapter<Recyclersearch.viewHolder>(){
    var txlt:Array<String> = arrayOf("Paid Stories","Free Stories","Romance","Mystery","Education","History")
    var imga1:Array<Int> = arrayOf(R.drawable.paidstories,R.drawable.fre,R.drawable.rom,R.drawable.mys,R.drawable.educa,R.drawable.history)
    inner class viewHolder (ItemView: View): RecyclerView.ViewHolder(ItemView) {
    var text1: TextView
    var background: LinearLayout

    init {
        text1=itemView.findViewById(R.id.title)
        background=itemView.findViewById(R.id.back)
    }

}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val valu= LayoutInflater.from(parent.context).inflate(R.layout.searchcat,parent,false)
        return viewHolder(valu)

    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        holder.text1.text=txlt[position]
        holder.background.setBackgroundResource(imga1[position])
    }
    override fun getItemCount(): Int {
        return txlt.size
    }
}