package com.jagor.testproductsapp.domain.repositories

import java.io.IOException
import java.lang.Exception

sealed class Failure(val e: Throwable) {

    class UnknownFailure(val t: Exception) : Failure(t)
    class NetworkFailure(val t: IOException) : Failure(t)
}