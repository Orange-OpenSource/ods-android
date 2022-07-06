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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
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
import com.orange.ods.demo.ui.components.utilities.ComponentHeader
import com.orange.ods.demo.ui.components.utilities.clickOnElement
import com.orange.ods.demo.ui.utilities.composable.LabelledRadioButton

private data class BottomNavigationVariant(@StringRes val titleRes: Int, val itemNumber: Int)

private val bottomNavigationVariants = listOf(
    BottomNavigationVariant(R.string.component_bottom_navigation_three_up, 3),
    BottomNavigationVariant(R.string.component_bottom_navigation_four_up, 4),
    BottomNavigationVariant(R.string.component_bottom_navigation_five_up, 5)
)

@Composable
fun ComponentBottomNavigation() {
    val selectedVariantIndex = rememberSaveable { mutableStateOf(0) }

    Scaffold(
        bottomBar = { BottomNavigationBar(selectedVariantIndex) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                ComponentHeader(
                    imageRes = R.drawable.il_bottom_navigation,
                    description = R.string.component_bottom_navigation_description
                )
                Column(
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_m))
                ) {
                    bottomNavigationVariants.forEachIndexed { index, bottomNavigationVariant ->
                        LabelledRadioButton(
                            selectedRadio = mutableStateOf(bottomNavigationVariants[selectedVariantIndex.value].titleRes.toString()),
                            currentRadio = bottomNavigationVariant.titleRes.toString(),
                            label = stringResource(id = bottomNavigationVariant.titleRes),
                            onClick = { selectedVariantIndex.value = index }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BottomNavigationBar(selectedVariantIndex: MutableState<Int>) {
    val context = LocalContext.current
    val navigationItems = listOf(
        NavigationItem("Favorites", R.drawable.ic_heart),
        NavigationItem("Search", R.drawable.ic_search),
        NavigationItem("Information", R.drawable.ic_info),
        NavigationItem("Notification", R.drawable.ic_notification),
        NavigationItem("Settings", R.drawable.ic_settings)
    )

    val selectedItem = remember { mutableStateOf(navigationItems[0]) }

    OdsBottomNavigation {
        for (item in navigationItems.subList(0, bottomNavigationVariants[selectedVariantIndex.value].itemNumber)) {
            OdsBottomNavigationItem(
                icon = { Icon(painter = painterResource(id = item.icon), contentDescription = null) },
                label = item.title,
                selected = selectedItem.value.title == item.title,
                onClick = {
                    selectedItem.value = item
                    clickOnElement(context, item.title)
                }
            )
        }
    }
}

private data class NavigationItem(val title: String, @DrawableRes val icon: Int)