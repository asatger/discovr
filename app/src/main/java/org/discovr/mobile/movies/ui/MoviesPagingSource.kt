package org.discovr.mobile.movies.ui

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.last
import org.discovr.mobile.core.domain.Page
import org.discovr.mobile.core.domain.Resource

class MoviesPagingSource<T : Any>(
    private val nextPageCallback: suspend (nextPageNumber: Int) -> Flow<Resource<Page<T>, Any>>
) : PagingSource<Int, T>() {
    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        try {
            val currentPageNumber = params.key ?: 1

            return when (val resource = this.nextPageCallback(currentPageNumber).last()) {
                is Resource.Failure -> LoadResult.Error(Exception())
                is Resource.Success -> {
                    val nextKey = when {
                        (params.loadSize * (currentPageNumber.plus(1))) < resource.result.totalResults ->
                            currentPageNumber.plus(1)

                        else -> null
                    }
                    LoadResult.Page(
                        data = resource.result.results,
                        prevKey = null,
                        nextKey = nextKey
                    )
                }

                is Resource.Loading -> LoadResult.Page(
                    data = listOf(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

}