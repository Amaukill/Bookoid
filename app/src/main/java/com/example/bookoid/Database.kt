package com.example.bookoid

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore
interface OnGetDataBase {
    fun getBooks(books: List<BookModel>)
}
class Database{
    private val db = Firebase.firestore
    var listener: OnGetDataBase?=null
     fun retrieveBooks(book: List<BookModel>) {
        listener?.getBooks(book)
    }
     fun getBooks(){

        db.collection("livres").get()
            .addOnCompleteListener{

                if(it.isSuccessful){
                    retrieveBooks(
                    it.result.documents.map { document -> BookModel(
                        Auteur= document.data?.get("Auteur").toString(),
                        Titre =document.data?.get("Titre").toString(),
                        Image = document.data?.get("Image").toString()
                    )


                    })
                }
            }

    }
}
