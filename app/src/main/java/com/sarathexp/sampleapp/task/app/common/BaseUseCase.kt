package com.sarathexp.sampleapp.task.app.common

import kotlinx.coroutines.flow.Flow

interface BaseUseCase<
    in Params,
    out Result,
> {

    suspend fun perform(): Result = throw NotImplementedError()

    suspend fun perform(params: Params): Result? = throw NotImplementedError()

    fun performStreaming(params: Params? = null): Flow<Result> = throw NotImplementedError()
}
