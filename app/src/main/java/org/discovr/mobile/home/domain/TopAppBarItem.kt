package org.discovr.mobile.home.domain

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.discovr.mobile.R

sealed class TopAppBarItem(
    @DrawableRes val icon: Int,
    @StringRes val contentDescription: Int
) {

    object BackButtonItem : TopAppBarItem(
        icon = R.drawable.filled_arrow_back,
        contentDescription = R.string.top_app_bar_item_back_button_content_description
    )

    object  MenuButtonItem : TopAppBarItem(
        icon = R.drawable.filled_menu,
        contentDescription = R.string.top_app_bar_item_menu_button_content_description
    )
}
