package com.example.bookoid


import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var textView: TextView = view.findViewById(R.id.book_title)
        private var imageView: ImageView= view.findViewById(R.id.book_image)
        fun setViewBook( model: BookModel){
            textView.text = model.Titre
            imageView.setImageResource(R.drawable.biblio)

        }
}