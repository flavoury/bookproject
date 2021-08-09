package com.favour.e_libary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class recyclercateginside : RecyclerView.Adapter<recyclercateginside.viewHolder >(){
    var tx1tt:Array<String> = arrayOf("The Dark World","Colours Of Life","Beauty","Beaut")
    var txt2tt:Array<String> = arrayOf("19k","100k","1.97m","1.99m")
    var txt3tt:Array<String> = arrayOf("English","Spanish","Korea","English")
    var txt4tt:Array<String> = arrayOf("The Dark World,(ALttP | FSA) also known as the Evil Realm,(OoT) is a recurring location in The Legend of Zelda series","Colours Of Life","Beauty","That's always seemed so ridiculous to me, that people want to be around someone because they're pretty.")

    var imgageg1t1:Array<Int> = arrayOf(R.drawable.thriller1,R.drawable.thriller1,R.drawable.thriller1,R.drawable.thriller1)
    inner class viewHolder (ItemView: View): RecyclerView.ViewHolder(ItemView) {
        var text11t: TextView
        var text22t:TextView
        var text33t:TextView
        var text44t:TextView
        var image12t:ImageView
        init {
            text11t = itemView.findViewById(R.id.title1)
            text22t=itemView.findViewById(R.id.likes)
            text33t =itemView.findViewById(R.id.lang)
            text44t=itemView.findViewById(R.id.description)
            image12t=itemView.findViewById(R.id.cover)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val valu= LayoutInflater.from(parent.context).inflate(R.layout.categinside,parent,false)
        return viewHolder(valu)

    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        holder.text11t.text=tx1tt[position]
        holder.text22t.text=txt2tt[position]
        holder.text33t.text=txt3tt[position]
        holder.text44t.text=txt4tt[position]
        holder.image12t.setImageResource(imgageg1t1[position])
    }
    override fun getItemCount(): Int {
        return tx1tt.size
    }
}