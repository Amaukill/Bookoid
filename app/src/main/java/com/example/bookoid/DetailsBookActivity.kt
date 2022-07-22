package com.example.bookoid


import android.os.Bundle
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class DetailsBookActivity : AppCompatActivity(), OnGetDataBase {
    private val db = Database()
    private var book = BookModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_book)
        db.listener = this
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val id: String? = intent.getStringExtra("id")
        if (id != null) {
            db.getOneBook(id)
        }
        findViewById<CheckBox>(R.id.book_view).setOnCheckedChangeListener { _, isChecked ->
            book.Vue = isChecked
            db.updateBookView(book)
        }

    }

    override fun getBooks(books: List<BookModel>) {}
    override fun getOneBook(id: String, book: BookModel) {
        supportActionBar!!.title = book.Titre
        findViewById<TextView>(R.id.book_auteur).text =
            buildString {
                append(getString(R.string.Autor))
                append(" ")
                append(book.Auteur)
            }
        Picasso.get().load(book.Image).resize(500, 500)
            .into(findViewById<ImageView>(R.id.book_image))
        findViewById<TextView>(R.id.book_description).text =
            buildString {
                append(getString(R.string.Synopsis))
                append(" ")
                append(book.Description)
            }
        findViewById<TextView>(R.id.book_date).text =
            buildString {
                append(getString(R.string.Date))
                append(" ")
                append(book.Date)
            }
        findViewById<TextView>(R.id.book_title).text = buildString {
            append(getString(R.string.Title))
            append(" ")
            append(book.Titre)
        }
        findViewById<CheckBox>(R.id.book_view).isChecked = book.Vue!!
        this.book = book
    }

    override fun updateBookView(book: BookModel) {
    }
}