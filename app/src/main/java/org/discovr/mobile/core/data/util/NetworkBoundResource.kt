package org.discovr.mobile.core.data.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.discovr.mobile.core.domain.Resource

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType?) -> Boolean = { true }
) = flow {

    val resource = if (shouldFetch(query().firstOrNull())) {
        emit(Resource.Loading)
        try {
            val resultType = fetch()
            saveFetchResult(resultType)
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            query().map { Resource.Failure(throwable) }
        }
    } else {
        query().map { Resource.Success(it) }
    }
    emitAll(resource)
}