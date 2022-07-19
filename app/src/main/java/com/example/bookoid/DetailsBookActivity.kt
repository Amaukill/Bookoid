package com.example.bookoid

import android.media.Image
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.set
import com.squareup.picasso.Picasso

class DetailsBookActivity : AppCompatActivity(), OnGetDataBase {
    private val db = Database()
    private var book = BookModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_book)
        db.listener = this

        val id: String? = intent.getStringExtra("id")
        if (id != null) {
            db.getOneBook(id);
        }
        findViewById<CheckBox>(R.id.book_view).setOnCheckedChangeListener { _, isChecked ->
            book.Vue = isChecked
            db.updateBookView(book)
        }

    }

    override fun getBooks(books: List<BookModel>) {}
    override fun getOneBook(id: String, book: BookModel) {
        findViewById<TextView>(R.id.book_auteur).text =
            "${getString(R.string.Autor)} ${book.Auteur}"
        Picasso.get().load(book.Image).resize(500, 500)
            .into(findViewById<ImageView>(R.id.book_image));
        findViewById<TextView>(R.id.book_description).text =
            "${getString(R.string.Synopsis)} ${book.Description}"
        findViewById<TextView>(R.id.book_title).text = "${getString(R.string.Title)} ${book.Titre}"
        findViewById<CheckBox>(R.id.book_view).isChecked = book.Vue!!
        this.book = book;
    }

    override fun updateBookView(book: BookModel) {
    }
}