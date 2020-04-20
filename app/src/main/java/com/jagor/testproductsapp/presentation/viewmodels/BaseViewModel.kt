package com.jagor.testproductsapp.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jagor.testproductsapp.domain.repositories.Failure

open class BaseViewModel : ViewModel() {

    var failure: MutableLiveData<Failure?> = MutableLiveData()

    protected open fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }

}