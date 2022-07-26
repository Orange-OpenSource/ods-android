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
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import com.orange.ods.demo.ui.components.ComponentDetail
import com.orange.ods.demo.ui.components.utilities.ComponentCountRow
import com.orange.ods.demo.ui.components.utilities.clickOnElement

private object ComponentBottomNavigation {
    const val minNavigationItemCount = 3
    const val maxNavigationItemCount = 5
}

@Composable
@ExperimentalMaterialApi
fun ComponentBottomNavigation() {
    val selectedNavigationItemCount = rememberSaveable { mutableStateOf(minNavigationItemCount) }
    ComponentDetail(
        component = Component.BottomNavigation,
        bottomBar = { ComponentBottomNavigationBottomBar(selectedNavigationItemCount = selectedNavigationItemCount) },
        content = { ComponentBottomNavigationContent(selectedNavigationItemCount = selectedNavigationItemCount) })
}

@Composable
private fun ComponentBottomNavigationContent(selectedNavigationItemCount: MutableState<Int>) {
    ComponentCountRow(
        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.ods_screen_horizontal_margin)),
        title = stringResource(id = R.string.component_bottom_navigation_navigation_item_count),
        count = selectedNavigationItemCount,
        minCount = minNavigationItemCount,
        maxCount = maxNavigationItemCount
    )
}

@Composable
private fun ComponentBottomNavigationBottomBar(selectedNavigationItemCount: MutableState<Int>) {
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
        navigationItems.take(selectedNavigationItemCount.value)
            .forEach { navigationItem ->
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
