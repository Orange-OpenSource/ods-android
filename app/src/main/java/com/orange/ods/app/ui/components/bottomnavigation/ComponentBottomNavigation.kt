/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.bottomnavigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.orange.ods.app.R
import com.orange.ods.app.ui.components.bottomnavigation.ComponentBottomNavigation.MaxNavigationItemCount
import com.orange.ods.app.ui.components.bottomnavigation.ComponentBottomNavigation.MinNavigationItemCount
import com.orange.ods.app.ui.components.utilities.ComponentCountRow
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.app.ui.utilities.NavigationItem
import com.orange.ods.app.ui.utilities.composable.BetweenQuotesStringParameter
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.ComposableCode
import com.orange.ods.app.ui.utilities.composable.PredefinedParameter
import com.orange.ods.compose.component.OdsComponent
import com.orange.ods.compose.component.bottomnavigation.OdsBottomNavigation
import com.orange.ods.compose.component.bottomnavigation.OdsBottomNavigationItem

private object ComponentBottomNavigation {
    const val MinNavigationItemCount = 3
    const val MaxNavigationItemCount = 5
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentBottomNavigation() {
    val context = LocalContext.current
    val navigationItems = NavigationItem.values()
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
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center
        ) {
            OdsBottomNavigation {
                navigationItems.take(selectedNavigationItemCount.value).forEach { navigationItem ->
                    val label = stringResource(id = navigationItem.textResId)
                    OdsBottomNavigationItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = navigationItem.iconResId),
                                contentDescription = null
                            )
                        },
                        label = label,
                        selected = selectedNavigationItem.value.textResId == navigationItem.textResId,
                        onClick = {
                            selectedNavigationItem.value = navigationItem
                            clickOnElement(context, label)
                        }
                    )
                }
            }

            CodeImplementationColumn(
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin))
            ) {
                ComposableCode(name = OdsComponent.OdsBottomNavigation.name) {
                    navigationItems.take(2).forEach { item ->
                        ComposableCode(
                            name = "OdsBottomNavigationItem",
                            parameters = listOf(
                                PredefinedParameter.Icon,
                                BetweenQuotesStringParameter("label", stringResource(id = item.textResId)),
                                PredefinedParameter.Selected(selectedNavigationItem.value.textResId == item.textResId),
                                PredefinedParameter.OnClick,
                            )
                        )
                    }
                }
            }
        }
    }
}
