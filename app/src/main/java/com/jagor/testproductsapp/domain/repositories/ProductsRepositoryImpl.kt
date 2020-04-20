package com.jagor.testproductsapp.domain.repositories

import com.jagor.testproductsapp.EMPTY
import com.jagor.testproductsapp.domain.entities.Product
import com.jagor.testproductsapp.domain.usecases.Either
import com.jagor.testproductsapp.network.ProductsService
import com.jagor.testproductsapp.network.entities.ProductPOJO

class ProductsRepositoryImpl(private val productsService: ProductsService) : ProductsRepository {

    override suspend fun getProductsList(): Either<Failure, List<Product>> {
        val products = productsService.getProductsService().getProductsList().products
        return Either.Success(products.map { mapPOJOToDb(it) })
    }

    override suspend fun getProductById(id: String): Either<Failure, Product> {
        val product = productsService.getProductsService().getProductById(id)
        return Either.Success(mapPOJOToDb(product))
    }

    private fun mapPOJOToDb(product: ProductPOJO): Product {
        return Product(
            id = product.id,
            name = product.name,
            price = product.price,
            imageUrl = product.imageUrl,
            description = product.description ?: String.EMPTY
        )
    }
}