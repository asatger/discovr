package org.discovr.mobile.core.domain

data class Page<T>(
    val totalPages: Int,
    val totalResults: Int,
    val currentPage: Int,
    val results: List<T>
) {
    companion object {
        fun <T> empty(page: Int) = Page<T>(
            totalPages = 0,
            totalResults = 0,
            currentPage = page,
            results = listOf()
        )
    }

    val resultsCount
        get(): Int = this.results.size

    val next
        get(): Int = if (hasNext) this.currentPage + 1 else this.currentPage

    val previous
        get(): Int = if (hasPrevious) this.currentPage - 1 else this.currentPage

    private val hasNext
        get(): Boolean = this.currentPage < this.totalPages

    private val hasPrevious
        get(): Boolean = this.currentPage > 1
}