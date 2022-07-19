package com.example.bookoid

data class BookModel(
    val Auteur: String? = null,
    val Titre: String? = null,
    val Image: String? = null,
    val ID: String? = null,
    val Description: String? = null,
    var Vue: Boolean? = null
)
