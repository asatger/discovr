package org.discovr.mobile.core.domain.models

sealed class Resource<out R, out F> {

    data class Success<out R>(val result: R) : Resource<R, Nothing>()

    data class Failure<out F>(val failure: F) : Resource<Nothing, F>()

    data object Loading : Resource<Nothing, Nothing>()
}