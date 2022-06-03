package com.example.bookoid

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

interface OnGetDatabase{
    fun getBooks()
}
class Database{
    private lateinit var db : FirebaseFirestore
    var listener: OnGetDatabase?=null
     fun getBooks(): MutableList<BookModel>{

        val books : MutableList<BookModel> = mutableListOf()
        db.collection("livres").get()
            .addOnCompleteListener{

                if(it.isSuccessful){
                    for (document in it.result!!){
                        Log.v("data",document.data.getValue("Auteur").toString())
                        Log.v("data",document.data.getValue("Titre").toString())
                        Log.v("data",document.data.getValue("Image").toString())
                        books.add(BookModel(document.data.getValue("Auteur").toString(),
                            document.data.getValue("Titre").toString()
                            ,document.data.getValue("Image").toString()
                        ))

                    }
                }
            }
        return books
    }
}