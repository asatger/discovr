package org.discovr.mobile.core.data.remote.mappers

import org.discovr.mobile.core.data.remote.models.PageRemote
import org.discovr.mobile.core.domain.Page

fun <I, O> PageRemote<I>.toDomain(mapper: (I) -> O) = Page(
    totalPages = this.totalPages,
    totalResults = this.totalResults,
    currentPage = this.currentPage,
    results = this.results.map(mapper)
)