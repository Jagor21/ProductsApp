package com.jagor.testproductsapp.network

import com.jagor.testproductsapp.network.entities.ProductPOJO
import com.jagor.testproductsapp.network.entities.ResponseProductsPOJO
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsApi {

    @GET("list")
    suspend fun getProductsList(): ResponseProductsPOJO

    @GET("{id}/detail")
    suspend fun getProductById(@Path("id") id: String): ProductPOJO
}