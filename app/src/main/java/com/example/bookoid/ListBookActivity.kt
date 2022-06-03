package com.example.bookoid

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class ListBookActivity : AppCompatActivity() , OnGetDatabase {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listbook)
        val db = Database()
        db.listener=this
        val books = db.getBooks()
        val listBook =findViewById<RecyclerView>(R.id.listBooks)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation=LinearLayoutManager.VERTICAL
        listBook.layoutManager = layoutManager
        val bookAdapter=BookAdapter(books)
        listBook.adapter = bookAdapter

    }

    override fun getBooks() {

    }

}
