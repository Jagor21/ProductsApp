package com.jagor.testproductsapp.domain.usecases

import com.jagor.testproductsapp.domain.repositories.Failure
import kotlinx.coroutines.*
import java.io.IOException

abstract class UseCase<out Type, in Params> {

    private var job: Job? = null

    abstract suspend fun run(params: Params): Either<Failure, Type>

    fun execute(scope: CoroutineScope, params: Params, onResult: (Either<Failure, Type>) -> Unit) {
        job?.cancel()
        job = CoroutineScope(Dispatchers.IO).async {
            run(params)
        }.let {
            scope.launch(Dispatchers.Main) {
                try {
                    onResult(it.await())
                } catch (e: IOException) {
                    e.printStackTrace()
                    onResult(Either.Error(Failure.NetworkFailure(e)))
                }
            }
        }
    }
}

object None
