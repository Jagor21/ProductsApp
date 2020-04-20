package com.jagor.testproductsapp.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jagor.testproductsapp.domain.entities.Product
import com.jagor.testproductsapp.domain.repositories.Failure
import com.jagor.testproductsapp.domain.repositories.ProductsRepositoryImpl
import com.jagor.testproductsapp.domain.usecases.GetProductsListUseCase
import com.jagor.testproductsapp.domain.usecases.None
import com.jagor.testproductsapp.network.ProductsService

class ProductListViewModel : BaseViewModel() {

    val products: MutableLiveData<List<Product>> = MutableLiveData()
    val loadingProgressBar: MutableLiveData<Boolean> = MutableLiveData()
    private val productsRepository by lazy { ProductsRepositoryImpl(ProductsService) }
    private val getProductsListUseCase by lazy { GetProductsListUseCase(productsRepository) }

    init {
        loadData()
    }

    fun onRefresh() {
        loadData()
    }

    private fun loadData() {
        loadingProgressBar.value = true
        getProductsListUseCase.execute(viewModelScope, None) {
            it.either(::handleFailure, ::handleSuccess)
        }
    }

    private fun handleSuccess(products: List<Product>?) {
        loadingProgressBar.value = false
        this.products.value = products
        failure.value = null
    }

    override fun handleFailure(failure: Failure) {
        super.handleFailure(failure)
        loadingProgressBar.value = false
    }
}