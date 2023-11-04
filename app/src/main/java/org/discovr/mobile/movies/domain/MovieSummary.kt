package org.discovr.mobile.movies.domain

import org.discovr.mobile.core.domain.Id
import org.discovr.mobile.core.domain.Ids
import org.discovr.mobile.core.domain.Language
import org.discovr.mobile.core.domain.Overview
import org.discovr.mobile.core.domain.Path
import org.discovr.mobile.core.domain.Popularity
import org.discovr.mobile.core.domain.Title
import org.discovr.mobile.core.domain.Vote
import java.util.Date

data class MovieSummary(
    val isAdultContent: Boolean,
    val backdropPath: Path?,
    val genreIds: Ids,
    val id: Id,
    val originalLanguage: Language,
    val originalTitle: Title,
    val overview: Overview,
    val popularity: Popularity,
    val posterPath: Path?,
    val releaseDate: Date,
    val hasVideo: Boolean,
    val vote: Vote
)