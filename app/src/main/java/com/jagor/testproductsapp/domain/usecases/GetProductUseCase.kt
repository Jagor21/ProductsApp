package com.jagor.testproductsapp.domain.usecases

import com.jagor.testproductsapp.domain.entities.Product
import com.jagor.testproductsapp.domain.repositories.Failure
import com.jagor.testproductsapp.domain.repositories.ProductsRepository

class GetProductUseCase(
    private val productsRepository: ProductsRepository
) : UseCase<Product, String>() {

    override suspend fun run(params: String): Either<Failure, Product> {
        return productsRepository.getProductById(params)
    }
}