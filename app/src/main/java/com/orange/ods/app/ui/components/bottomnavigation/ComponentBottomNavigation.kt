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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
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
import com.orange.ods.app.databinding.OdsBottomNavigationBinding
import com.orange.ods.app.ui.UiFramework
import com.orange.ods.app.ui.components.bottomnavigation.ComponentBottomNavigation.MaxNavigationItemCount
import com.orange.ods.app.ui.components.bottomnavigation.ComponentBottomNavigation.MinNavigationItemCount
import com.orange.ods.app.ui.components.utilities.ComponentCountRow
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.app.ui.utilities.NavigationItem
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.FunctionCallCode
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.bottomnavigation.OdsBottomNavigation
import com.orange.ods.compose.component.bottomnavigation.OdsBottomNavigationItem
import com.orange.ods.compose.component.bottomnavigation.OdsBottomNavigationItemIcon

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

    val bottomNavigationItems = navigationItems.take(selectedNavigationItemCount.value).map { item ->
        val label = stringResource(id = item.textResId)
        OdsBottomNavigationItem(
            icon = OdsBottomNavigationItemIcon(
                painter = painterResource(id = item.iconResId),
                contentDescription = ""
            ),
            label = label,
            selected = selectedNavigationItem.value.textResId == item.textResId,
            onClick = {
                selectedNavigationItem.value = item
                clickOnElement(context, label)
            }
        )
    }

    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            ComponentCountRow(
                modifier = Modifier.padding(start = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin)),
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
                .verticalScroll(rememberScrollState())
                .padding(top = dimensionResource(id = com.orange.ods.R.dimen.screen_vertical_margin))
        ) {
            UiFramework<OdsBottomNavigationBinding>(compose = {
                OdsBottomNavigation(items = bottomNavigationItems)
            }, xml = {
                this.odsBottomNavigation.items = bottomNavigationItems
            })

            CodeImplementationColumn(
                modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin)),
                xmlAvailable = true
            ) {
                FunctionCallCode(
                    name = OdsComposable.OdsBottomNavigation.name,
                    parameters = {
                        list("items") {
                            navigationItems.take(selectedNavigationItemCount.value).forEach { item ->
                                classInstance<OdsBottomNavigationItem> {
                                    classInstance<OdsBottomNavigationItemIcon>("icon") {
                                        painter()
                                        contentDescription("")
                                    }
                                    string("label", context.getString(item.textResId))
                                    selected(selectedNavigationItem.value.textResId == item.textResId)
                                    onClick()
                                }
                            }
                        }
                    })
            }
        }
    }
}