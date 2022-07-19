package com.example.bookoid


import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class BookViewHolder(view: View,listener:BookAdapter.OnItemClickListener) : RecyclerView.ViewHolder(view) {
    private var titre: TextView = view.findViewById(R.id.book_title)
    private var imageView: ImageView = view.findViewById(R.id.book_image)
    private var auteur: TextView = view.findViewById(R.id.book_auteur)
    fun setViewBook(model: BookModel) {
        titre.text = model.Titre
        Picasso.get().load(model.Image).resize(500, 500).into(imageView);
        auteur.text = model.Auteur
    }
    init{
        view.setOnClickListener{
            listener.onItemClick(adapterPosition)
        }
    }

}