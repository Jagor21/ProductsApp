package com.jagor.testproductsapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://s3-eu-west-1.amazonaws.com/developer-application-test/cart/"

object ProductsService {

    fun getProductsService(): ProductsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductsApi::class.java)
    }
}