package com.jagor.testproductsapp.network.entities

import com.google.gson.annotations.SerializedName

data class ResponseProductsPOJO(
    @SerializedName("products")
    val products: List<ProductPOJO>
)