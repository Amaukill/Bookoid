package com.example.bookoid


import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class BookViewHolder(view: View, listener: BookAdapter.OnItemClickListener) :
    RecyclerView.ViewHolder(view) {
    private var titre: TextView = view.findViewById(R.id.book_title)
    private var imageView: ImageView = view.findViewById(R.id.book_image)
    private var auteur: TextView = view.findViewById(R.id.book_auteur)
    private var book : BookModel = BookModel()
    fun setViewBook(model: BookModel) {
        book=model
        titre.text = model.Titre
        Picasso.get().load(model.Image).resize(500, 500).into(imageView)
        auteur.text = model.Auteur
        model.Vue?.let { titre.design(it) }
        model.Vue?.let { auteur.design(it) }
        }

       private fun TextView.design(bool: Boolean){
            if(bool){
                setBackgroundColor(resources.getColor(R.color.green))
            }
            else{
                setBackgroundColor(resources.getColor(R.color.red))
            }
        }
    init {
        view.setOnClickListener {
            listener.onItemClick(adapterPosition)
        }
    }

}