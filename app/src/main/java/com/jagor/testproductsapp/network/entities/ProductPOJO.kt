package com.jagor.testproductsapp.network.entities

import com.google.gson.annotations.SerializedName

data class ProductPOJO(
    @SerializedName("product_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("image")
    val imageUrl: String,
    @SerializedName("description")
    var description: String?
)