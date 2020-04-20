package com.jagor.testproductsapp.domain.usecases

import com.jagor.testproductsapp.domain.entities.Product
import com.jagor.testproductsapp.domain.repositories.Failure
import com.jagor.testproductsapp.domain.repositories.ProductsRepository

class GetProductsListUseCase (
    private val productsRepository: ProductsRepository
) : UseCase<List<Product>, None>() {

    override suspend fun run(params: None): Either<Failure, List<Product>> {
        return productsRepository.getProductsList()
    }
}