package org.discovr.mobile.movies.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import org.discovr.mobile.movies.data.repository.MovieRepository
import org.discovr.mobile.movies.ui.MoviesPagingSource

class MoviesViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    val nowPlayingMovies = Pager(
        config = PagingConfig(
            pageSize = 40
        ),
        pagingSourceFactory = {
            MoviesPagingSource {
                movieRepository.getNowPlaying(it)
            }
        }
    ).flow.cachedIn(viewModelScope)

}