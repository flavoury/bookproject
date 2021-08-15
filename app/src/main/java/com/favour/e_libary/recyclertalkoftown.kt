package com.favour.e_libary

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class recyclertalkoftown (private var bookslist: ArrayList<booksItem>) : RecyclerView.Adapter<recyclertalkoftown.viewHolder>(){
    lateinit var mcontext: Context
    inner class viewHolder (ItemView: View): RecyclerView.ViewHolder(ItemView) {
    var text11: TextView
    var text33:TextView
    var text44:TextView
    var text55: TextView
    var img: ImageView
    var cardbody : CardView
        init {
            text11 = itemView.findViewById(R.id.talktitle)
            text33 =itemView.findViewById(R.id.lang1)
            text44 =itemView.findViewById(R.id.desc)
            text55 = itemView.findViewById(R.id.parts1)
            img = itemView.findViewById(R.id.bookimg)
            cardbody = itemView.findViewById(R.id.talkbody)
    }
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val valu= LayoutInflater.from(parent.context).inflate(R.layout.talkoftown,parent,false)
        mcontext = parent.context
        return viewHolder(valu)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        var currentItem = bookslist[position]
        holder.text11.text = currentItem.getname()
        holder.text33.text = currentItem.getlanguage()
        holder.text55.text = currentItem.getparts()+" parts"
        Picasso.get().load(currentItem.getcoverimgurl()).into(holder.img)
        if (currentItem.getdescription().length > 100){
            var short : String = currentItem.getdescription().subSequence(0, 100).toString()
            holder.text44.text =  short+"..."
        }
        else{
            holder.text44.text =  currentItem.getdescription()
        }
        holder.cardbody.setOnClickListener {
            val intent = Intent(mcontext, aboutbooks::class.java)
            intent.putExtra("key", currentItem.getkey())
            intent.putExtra("imgurl", currentItem.getcoverimgurl())
            intent.putExtra("description", currentItem.getdescription())
            intent.putExtra("parts", currentItem.getparts())
            intent.putExtra("price", currentItem.getprice())
            intent.putExtra("name", currentItem.getname())
            intent.putExtra("pdfurl", currentItem.getpdfurl())
            intent.putExtra("author", currentItem.getauthor())
            intent.putExtra("language", currentItem.getlanguage())
            mcontext.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return bookslist.size
    }
}