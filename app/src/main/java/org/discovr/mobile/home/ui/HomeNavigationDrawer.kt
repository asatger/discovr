package org.discovr.mobile.home.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import org.discovr.mobile.home.viewmodels.HomeViewModel
import org.discovr.mobile.navigation.ui.AppNavHost

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeNavigationDrawer() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val viewModel = viewModel(modelClass = HomeViewModel::class.java)
    val drawerItems = viewModel.drawerItems
    val selectedItem = remember { mutableStateOf(drawerItems[0]) }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val navController = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(dimensionResource(id = viewModel.drawerTopSpacerHeight)))
                drawerItems.forEach { item ->
                    NavigationDrawerItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = stringResource(id = item.contentDescription)
                            )
                        },
                        label = { Text(stringResource(id = item.label)) },
                        selected = item == selectedItem.value,
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedItem.value = item
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        },
        content = {
            Scaffold(
                modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = {
                    TopAppBar(
                        title = { Text(text = stringResource(id = viewModel.title)) },
                        navigationIcon = {
                            navController.previousBackStackEntry
                                ?.let {
                                    IconButton(onClick = { navController.navigateUp() }) {
                                        Icon(
                                            painter = painterResource(id = viewModel.backButtonItem.icon),
                                            contentDescription = stringResource(id = viewModel.backButtonItem.contentDescription)
                                        )
                                    }
                                }
                                ?: run {
                                    IconButton(onClick = {
                                        scope.launch {
                                            drawerState.apply { if (isClosed) open() else close() }
                                        }
                                    }) {
                                        Icon(
                                            painter = painterResource(id = viewModel.menuButtonItem.icon),
                                            contentDescription = stringResource(id = viewModel.menuButtonItem.contentDescription)
                                        )
                                    }
                                }
                        },
                        scrollBehavior = scrollBehavior
                    )
                },
            ) { innerPadding ->
                AppNavHost(navController = navController, modifier = Modifier.padding(innerPadding))
            }
        }
    )
}