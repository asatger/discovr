package org.discovr.mobile.core.domain

data class Page<L : List<*>>(
    val totalPages: Int,
    val totalResults: Int,
    val currentPage: Int,
    val results: L
) {

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