package org.discovr.mobile.core.data.remote.models

import com.squareup.moshi.Json

data class PageRemote<T>(
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int,
    @Json(name = "page") val currentPage: Int,
    @Json(name = "results") val results: List<T>
)
