package com.example.bookoid

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView

class BookAdapter() : RecyclerView.Adapter<BookViewHolder>() {
    private lateinit var mListener : OnItemClickListener
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: OnItemClickListener){
        mListener=listener
    }
    var dataSet = listOf<BookModel>()
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BookViewHolder {
        val context = viewGroup.context
        val itemView = LayoutInflater.from(context).inflate(R.layout.viewbook, viewGroup, false)
        return BookViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.setViewBook(dataSet[position])
    }

    override fun getItemCount(): Int = dataSet.size
}