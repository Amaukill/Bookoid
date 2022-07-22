package com.example.bookoid


import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore

interface OnGetDataBase {
    fun getBooks(books: List<BookModel>)
    fun getOneBook(id: String, book: BookModel)
    fun updateBookView(book: BookModel)
}

class Database {
    private val db = Firebase.firestore
    var listener: OnGetDataBase? = null
    private fun retrieveBooks(book: List<BookModel>) {
        listener?.getBooks(book)
    }

    private fun retrieveOneBook(id: String, book: BookModel) {
        listener?.getOneBook(id, book)
    }

    private fun updateBook(book: BookModel) {
        listener?.updateBookView(book)
    }

    fun getBooks() {
        db.collection("livres").get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    retrieveBooks(
                        it.result.documents.map { document ->
                            BookModel(
                                ID = document.id,
                                Auteur = document.data?.get("auteur").toString(),
                                Titre = document.data?.get("titre").toString(),
                                Image = document.data?.get("image").toString(),
                                Vue = document.data?.get("vue").toString().toBoolean()
                            )
                        })
                }
            }
    }

    fun getOneBook(id: String) {

        db.collection("livres").document(id).get()
            .addOnCompleteListener {

                if (it.isSuccessful) {
                    retrieveOneBook(id,
                        it.result.run {
                            BookModel(
                                ID = id,
                                Description = this.data?.get("description").toString(),
                                Auteur = this.data?.get("auteur").toString(),
                                Titre = this.data?.get("titre").toString(),
                                Image = this.data?.get("image").toString(),
                                Date = this.data?.get("date").toString(),
                                Vue = this.data?.get("vue").toString().toBoolean()
                            )
                        })
                }
            }

    }

    fun updateBookView(book: BookModel) {

        db.collection("livres").document(book.ID.toString()).update("vue", book.Vue).addOnCompleteListener{
            if(it.isSuccessful){
                updateBook(book)
            }
        }
    }
}
