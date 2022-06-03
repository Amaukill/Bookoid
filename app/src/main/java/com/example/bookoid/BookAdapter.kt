package com.example.bookoid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(private val dataSet : MutableList<BookModel>) : RecyclerView.Adapter<BookViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BookViewHolder {
        val context=viewGroup.context
        val itemView= LayoutInflater.from(context).inflate(R.layout.viewbook,viewGroup,false)
        return BookViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.setViewBook(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size
}