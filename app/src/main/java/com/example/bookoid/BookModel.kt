package com.example.bookoid



data class BookModel(
    val Auteur: String? = R.string.vide.toString(),
    val Titre: String? = R.string.vide.toString(),
    val Image: String? = null,
    val ID: String? = null,
    val Description: String? = R.string.vide.toString(),
    val Date : String? =R.string.vide.toString(),
    var Vue: Boolean? = null
)
