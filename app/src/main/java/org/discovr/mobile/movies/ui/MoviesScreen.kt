package org.discovr.mobile.movies.ui

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.bumptech.glide.integration.compose.CrossFade
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import org.discovr.mobile.core.utils.ImageLoader
import org.discovr.mobile.movies.domain.MovieSummary
import org.discovr.mobile.movies.viewmodels.MoviesViewModel
import org.koin.java.KoinJavaComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(
    onClickMovieItem: (movie: MovieSummary) -> Unit
) {
    val viewModel = KoinJavaComponent.get<MoviesViewModel>(MoviesViewModel::class.java)
    val gridState = rememberLazyGridState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Movies") })
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            MovieTabContent(
                movies = viewModel.nowPlayingMovies.collectAsLazyPagingItems(),
                state = gridState,
                onClickMovieItem = onClickMovieItem
            )
        }
    }
}

@Composable
private fun FailureScreen(error: String?) {
    Text(
        text = error ?: "Unknown exception has occurred.",
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
    )
}

@Composable
private fun MovieTabContent(
    movies: LazyPagingItems<MovieSummary>, state: LazyGridState,
    onClickMovieItem: (movie: MovieSummary) -> Unit
) {

    BoxWithConstraints {
        val gridCells = when {
            maxWidth <= 360.dp -> GridCells.Fixed(1)
            maxWidth <= 600.dp -> GridCells.Adaptive(128.dp)
            else -> GridCells.Adaptive(192.dp)
        }

        LazyVerticalGrid(
            columns = gridCells,
            state = state
        ) {

            items(
                count = movies.itemCount,
                key = { movies[it].hashCode() + it }
            ) { index ->
                movies[index]?.let { MovieListItem(it, onClickMovieItem) }
            }

            movies.apply {
                when {

                    loadState.refresh is LoadState.Error -> {
                        item(
                            span = { GridItemSpan(3) }
                        ) {
                            FailureScreen(
                                error = (loadState.refresh as LoadState.Error).error.localizedMessage
                            )
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        item(
                            span = { GridItemSpan(3) }
                        ) {
                            FailureScreen(
                                error = (loadState.append as LoadState.Error).error.localizedMessage
                            )
                        }
                    }

                    loadState.append is LoadState.NotLoading && loadState.refresh is LoadState.NotLoading -> {
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MovieListItem(
    movie: MovieSummary,
    onClickMovieItem: (movie: MovieSummary) -> Unit,
    imageLoader: ImageLoader = KoinJavaComponent.get(
        ImageLoader::class.java
    )
) {
    Card(
        modifier = Modifier.padding(8.dp),
        onClick = { onClickMovieItem(movie) }
    ) {

        GlideImage(
            model = imageLoader.loadOriginal(movie.posterPath),
            loading = placeholder(android.R.color.transparent),
            contentDescription = "",
            transition = CrossFade
        )
    }
}