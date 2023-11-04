package org.discovr.mobile.movies.data.remote.mappers

import org.discovr.mobile.core.domain.Id
import org.discovr.mobile.core.domain.Language
import org.discovr.mobile.core.domain.Overview
import org.discovr.mobile.core.domain.Path
import org.discovr.mobile.core.domain.Popularity
import org.discovr.mobile.core.domain.Title
import org.discovr.mobile.core.domain.Vote
import org.discovr.mobile.movies.data.remote.models.MovieSummaryRemote
import org.discovr.mobile.movies.domain.MovieSummary
import java.sql.Date

fun MovieSummaryRemote.toDomain() = MovieSummary(
    isAdultContent = this.adult,
    backdropPath = this.backdropPath?.let { Path(it) },
    genreIds = this.genreIds.map { Id(it.toString()) },
    id = Id(this.id.toString()),
    originalLanguage = Language(this.originalLanguage),
    originalTitle = Title(this.originalTitle),
    overview = Overview(this.overview),
    popularity = Popularity(this.popularity),
    posterPath = this.posterPath?.let { Path(it) },
    releaseDate = Date.valueOf(this.releaseDate),
    hasVideo = this.video,
    vote = Vote(this.voteAverage, this.voteCount)
)