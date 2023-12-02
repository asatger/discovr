package org.discovr.mobile.home.domain

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.discovr.mobile.R

sealed class DrawerItem(
    @StringRes val label: Int,
    @DrawableRes val icon: Int,
    @StringRes val contentDescription: Int
) {

    object MoviesDrawerItem : DrawerItem(
        label = R.string.drawer_item_movies_label,
        icon = R.drawable.filled_movie,
        contentDescription = R.string.drawer_item_movies_content_description
    )
}

typealias DrawerItems = List<DrawerItem>
