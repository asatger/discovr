package org.discovr.mobile.home.viewmodels

import androidx.lifecycle.ViewModel
import org.discovr.mobile.R
import org.discovr.mobile.home.domain.DrawerItem
import org.discovr.mobile.home.domain.DrawerItems
import org.discovr.mobile.home.domain.TopAppBarItem

class HomeViewModel : ViewModel() {

    val title: Int
        get() = R.string.app_name

    val drawerItems: DrawerItems = listOf(DrawerItem.MoviesDrawerItem)

    val menuButtonItem: TopAppBarItem
        get() = TopAppBarItem.MenuButtonItem

    val backButtonItem: TopAppBarItem
        get() = TopAppBarItem.BackButtonItem

    val drawerTopSpacerHeight: Int
        get() = R.dimen.drawer_top_spacer_height

}