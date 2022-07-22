package com.example.bookoid

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ListBookActivity : AppCompatActivity(), OnGetDataBase {
    private val db = Database()
    private lateinit var auth: FirebaseAuth
    private var bookAdapter: BookAdapter? = null
    override fun onStart() {
        super.onStart()
        setContentView(R.layout.activity_listbook)
        auth= Firebase.auth
        db.listener = this
        db.getBooks()
        val listBook = findViewById<RecyclerView>(R.id.listBooks)
        val layoutManager = LinearLayoutManager(this)
        listBook.layoutManager = layoutManager
        bookAdapter = BookAdapter()
        listBook.adapter = bookAdapter
        bookAdapter?.run {
            setOnItemClickListener(object : BookAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {
                    val intent = Intent(this@ListBookActivity, DetailsBookActivity::class.java)
                    intent.putExtra("id", this@run.dataSet[position].ID)
                    startActivity(intent)
                    /*Toast.makeText(
                        this@ListBookActivity,
                        "livre ${this@run.dataSet[position].ID}",Toast.LENGTH_SHORT
                    ).show()*/
                }
            })
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_layout, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_disconnect -> {
            auth.signOut()
            val intent= Intent(this@ListBookActivity, MainActivity::class.java)
            startActivity(intent)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onRestart() {
        super.onRestart()
        this.onStart()
    }

    override fun getBooks(books: List<BookModel>) {
        bookAdapter?.run {
            dataSet = books
            this.notifyDataSetChanged()
        }

    }

    override fun getOneBook(id: String, book: BookModel) {

    }

    override fun updateBookView(book: BookModel) {
    }
}
