package com.jagor.testproductsapp.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jagor.testproductsapp.domain.entities.Product
import com.jagor.testproductsapp.domain.repositories.Failure
import com.jagor.testproductsapp.domain.repositories.ProductsRepositoryImpl
import com.jagor.testproductsapp.domain.usecases.GetProductUseCase
import com.jagor.testproductsapp.network.ProductsService

class ProductViewModel : BaseViewModel() {
    val product: MutableLiveData<Product> = MutableLiveData()
    val loadingProgressBar: MutableLiveData<Boolean> = MutableLiveData()
    private val getProductUseCase: GetProductUseCase = GetProductUseCase(ProductsRepositoryImpl(ProductsService))

    override fun handleFailure(failure: Failure) {
        super.handleFailure(failure)
        loadingProgressBar.value = false
    }

    fun getProduct(id: String) {
        loadingProgressBar.value = true
        getProductUseCase.execute(viewModelScope, id) {
            it.either(::handleFailure, ::handleSuccess)
        }
    }

    fun onRefresh(id: String) {
        getProduct(id)
    }

    private fun handleSuccess(product: Product) {
        loadingProgressBar.value = false
        this.product.value = product
    }
}