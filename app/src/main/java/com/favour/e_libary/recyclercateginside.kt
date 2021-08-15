package com.favour.e_libary

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class recyclercateginside (private var bookslist : ArrayList<booksItem>) : RecyclerView.Adapter<recyclercateginside.viewHolder>(),
    Filterable {
    lateinit var mcontext: Context
    var booksListfiltered = ArrayList<booksItem>()

    inner class viewHolder (ItemView: View): RecyclerView.ViewHolder(ItemView) {
        var text11t: TextView
        var text33t:TextView
        var text44t:TextView
        var image12t:ImageView
        var cardbody : CardView
        init {
            text11t = itemView.findViewById(R.id.title1)
            text33t =itemView.findViewById(R.id.lang)
            text44t=itemView.findViewById(R.id.description)
            image12t=itemView.findViewById(R.id.cover)
            cardbody = itemView.findViewById(R.id.body)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val valu= LayoutInflater.from(parent.context).inflate(R.layout.categinside,parent,false)
        mcontext = parent.context
        return viewHolder(valu)
    }


    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        var curritem = booksListfiltered[position]
        holder.text11t.text= curritem.getname()
        holder.text33t.text= curritem.getlanguage()
        holder.text44t.text= curritem.getdescription()
        Picasso.get().load(curritem.getcoverimgurl()).into(holder.image12t)
        if (curritem.getdescription().length > 200){
            var short : String = curritem.getdescription().subSequence(0, 200).toString()
            holder.text44t.text =  short+"..."
        }
        else{
            holder.text44t.text =  curritem.getdescription()
        }
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
        return booksListfiltered.size
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty() || charSearch == null) {
                    booksListfiltered = bookslist
                } else {
                    val resultList = ArrayList<booksItem>()
                    for (row in bookslist) {
                        if (row.getname().toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT)) || row.getlanguage().toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    booksListfiltered = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = booksListfiltered
                return filterResults

            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                booksListfiltered = results?.values as ArrayList<booksItem>
                notifyDataSetChanged()
            }
        }
    }
}