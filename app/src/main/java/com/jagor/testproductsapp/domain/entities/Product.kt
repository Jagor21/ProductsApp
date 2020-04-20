package com.jagor.testproductsapp.domain.entities

data class Product(
    val id: String,
    val name: String,
    val price: Int,
    val imageUrl: String,
    var description: String?
)