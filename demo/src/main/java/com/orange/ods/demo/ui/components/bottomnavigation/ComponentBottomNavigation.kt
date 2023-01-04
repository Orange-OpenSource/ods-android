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
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.component.bottomnavigation.OdsBottomNavigation
import com.orange.ods.compose.component.bottomnavigation.OdsBottomNavigationItem
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.bottomnavigation.ComponentBottomNavigation.MaxNavigationItemCount
import com.orange.ods.demo.ui.components.bottomnavigation.ComponentBottomNavigation.MinNavigationItemCount
import com.orange.ods.demo.ui.components.utilities.ComponentCountRow
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.components.utilities.clickOnElement

private object ComponentBottomNavigation {
    const val MinNavigationItemCount = 3
    const val MaxNavigationItemCount = 5
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentBottomNavigation() {
    val context = LocalContext.current
    val navigationItems = listOf(
        NavigationItem(R.string.component_bottom_navigation_coffee, R.drawable.ic_cafe),
        NavigationItem(R.string.component_bottom_navigation_cooking_pot, R.drawable.ic_cooking_pot),
        NavigationItem(R.string.component_bottom_navigation_ice_cream, R.drawable.ic_ice_cream),
        NavigationItem(R.string.component_bottom_navigation_restaurant, R.drawable.ic_restaurant),
        NavigationItem(R.string.component_bottom_navigation_favorites, R.drawable.ic_heart)
    )

    val selectedNavigationItemCount = rememberSaveable { mutableStateOf(MinNavigationItemCount) }
    val selectedNavigationItem = remember { mutableStateOf(navigationItems[0]) }

    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            ComponentCountRow(
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                title = stringResource(id = R.string.component_bottom_navigation_navigation_item_count),
                count = selectedNavigationItemCount,
                minusIconContentDescription = stringResource(id = R.string.component_bottom_navigation_remove_item),
                plusIconContentDescription = stringResource(id = R.string.component_bottom_navigation_add_item),
                minCount = MinNavigationItemCount,
                maxCount = MaxNavigationItemCount
            )
        }) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            OdsBottomNavigation {
                navigationItems.take(selectedNavigationItemCount.value)
                    .forEach { navigationItem ->
                        val label = stringResource(id = navigationItem.titleResId)
                        OdsBottomNavigationItem(
                            icon = {
                                Icon(
                                    modifier = Modifier.size(24.dp),
                                    painter = painterResource(id = navigationItem.iconResId),
                                    contentDescription = null
                                )
                            },
                            label = label,
                            selected = selectedNavigationItem.value.titleResId == navigationItem.titleResId,
                            onClick = {
                                selectedNavigationItem.value = navigationItem
                                clickOnElement(context, label)
                            }
                        )
                    }
            }
        }
    }
}

private data class NavigationItem(@StringRes val titleResId: Int, @DrawableRes val iconResId: Int)
