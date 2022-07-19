package com.example.bookoid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class ListBookActivity : AppCompatActivity(), OnGetDataBase {
    private val db = Database()
    private var bookAdapter: BookAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listbook)
        db.listener = this
        db.getBooks()
        var listBook = findViewById<RecyclerView>(R.id.listBooks)
        var layoutManager = LinearLayoutManager(this)
        listBook.layoutManager = layoutManager
        bookAdapter = BookAdapter()
        listBook.adapter = bookAdapter
        bookAdapter?.run {
            setOnItemClickListener(object : BookAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {
                    val intent= Intent(this@ListBookActivity,DetailsBookActivity::class.java)
                    intent.putExtra("id",this@run.dataSet[position].ID)
                    startActivity(intent)
                    /*Toast.makeText(
                        this@ListBookActivity,
                        "livre ${this@run.dataSet[position].ID}",Toast.LENGTH_SHORT
                    ).show()*/
                }
            })
        }

    }

    override fun getBooks(books: List<BookModel>) {
        bookAdapter?.run {
            dataSet = books
            notifyDataSetChanged()
        }

    }

    override fun getOneBook(id: String, book: BookModel) {

    }

    override fun updateBookView(book: BookModel) {
    }
}
