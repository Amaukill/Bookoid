package com.example.bookoid

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class ListBookActivity : AppCompatActivity() , OnGetDataBase  {
    private  val db = Database()
    private var bookAdapter:BookAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listbook)
        db.listener=this
        db.getBooks()
        var listBook =findViewById<RecyclerView>(R.id.listBooks)
        var layoutManager = LinearLayoutManager(this)
        layoutManager.orientation=LinearLayoutManager.VERTICAL
        listBook.layoutManager = layoutManager
        bookAdapter = BookAdapter()


        listBook.adapter = bookAdapter


    }

    override fun getBooks(books: List<BookModel>) {
        bookAdapter?.dataSet = books
        bookAdapter?.notifyDataSetChanged()
    }
}
