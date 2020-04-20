package com.jagor.testproductsapp.domain.repositories

import com.jagor.testproductsapp.domain.entities.Product
import com.jagor.testproductsapp.domain.usecases.Either

interface ProductsRepository {

    suspend fun getProductsList(): Either<Failure, List<Product>>
    suspend fun getProductById(id: String): Either<Failure, Product>
}