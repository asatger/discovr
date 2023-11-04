package org.discovr.mobile.movies.domain

import org.discovr.mobile.core.domain.Budget
import org.discovr.mobile.core.domain.Id
import org.discovr.mobile.core.domain.Ids
import org.discovr.mobile.core.domain.Language
import org.discovr.mobile.core.domain.Overview
import org.discovr.mobile.core.domain.Path
import org.discovr.mobile.core.domain.Popularity
import org.discovr.mobile.core.domain.Revenue
import org.discovr.mobile.core.domain.Runtime
import org.discovr.mobile.core.domain.Title
import org.discovr.mobile.core.domain.Vote
import java.net.URL
import java.util.Date

data class Movie(
    val isAdultContent: Boolean,
    val backdropPath: Path?,
    val budget: Budget,
    val genreIds: Ids,
    val homepage: URL?,
    val id: Id,
    val imdbId: Id,
    val originalLanguage: Language,
    val originalTitle: Title,
    val overview: Overview,
    val popularity: Popularity,
    val posterPath: Path?,
    val releaseDate: Date,
    val revenue: Revenue,
    val runtime: Runtime,
    val status: MovieStatus,
    val tagline: Tagline,
    val title: Title,
    val hasVideo: Boolean,
    val vote: Vote
)