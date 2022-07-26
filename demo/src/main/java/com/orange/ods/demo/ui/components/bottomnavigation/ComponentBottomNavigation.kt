/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.bottomnavigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.bottomnavigation.OdsBottomNavigation
import com.orange.ods.compose.component.bottomnavigation.OdsBottomNavigationItem
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.Component
import com.orange.ods.demo.ui.components.utilities.ComponentCountRow
import com.orange.ods.demo.ui.components.utilities.ComponentHeader
import com.orange.ods.demo.ui.components.utilities.clickOnElement

private const val minNavigationItemCount = 3
private const val maxNavigationItemCount = 5

@Composable
fun ComponentBottomNavigation() {
    val selectedNavigationItemCount = rememberSaveable { mutableStateOf(minNavigationItemCount) }

    Scaffold(
        bottomBar = { BottomNavigationBar(selectedNavigationItemCount.value) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                ComponentHeader(
                    imageRes = R.drawable.il_bottom_navigation,
                    imageAlignment = Component.BottomNavigation.imageAlignment,
                    description = R.string.component_bottom_navigation_description
                )
                Column(
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_m))
                ) {
                    ComponentCountRow(
                        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.ods_screen_horizontal_margin)),
                        title = stringResource(id = R.string.component_bottom_navigation_navigation_item_count),
                        count = selectedNavigationItemCount,
                        minCount = minNavigationItemCount,
                        maxCount = maxNavigationItemCount
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomNavigationBar(navigationItemCount: Int) {
    val context = LocalContext.current
    val navigationItems = listOf(
        NavigationItem("Favorites", R.drawable.ic_heart),
        NavigationItem("Search", R.drawable.ic_search),
        NavigationItem("Information", R.drawable.ic_info),
        NavigationItem("Notification", R.drawable.ic_notification),
        NavigationItem("Settings", R.drawable.ic_settings)
    )

    val selectedNavigationItem = remember { mutableStateOf(navigationItems[0]) }

    OdsBottomNavigation {
        navigationItems.take(navigationItemCount).forEach { navigationItem ->
            OdsBottomNavigationItem(
                icon = { Icon(painter = painterResource(id = navigationItem.icon), contentDescription = null) },
                label = navigationItem.title,
                selected = selectedNavigationItem.value.title == navigationItem.title,
                onClick = {
                    selectedNavigationItem.value = navigationItem
                    clickOnElement(context, navigationItem.title)
                }
            )
        }
    }
}

private data class NavigationItem(val title: String, @DrawableRes val icon: Int)
