package org.discovr.mobile.core.utils

import org.discovr.mobile.core.domain.Path
import java.net.URL

interface ImageLoader {

    val baseUrl: URL

    fun loadSmall(path: Path?): String

    fun loadMedium(path: Path?): String

    fun loadLarge(path: Path?): String

    fun loadOriginal(path: Path?): String
}