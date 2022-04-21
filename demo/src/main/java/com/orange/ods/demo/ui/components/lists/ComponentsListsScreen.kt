/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.lists

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.ComponentsSubLevelNavigationItem
import com.orange.ods.demo.ui.components.utilities.ComponentHeader
import com.orange.ods.demo.ui.utilities.MenuItem

private val listsMenuItems = listOf(
    MenuItem(R.string.component_lists_one_line, ComponentsSubLevelNavigationItem.ListsOneLine.route),
    MenuItem(R.string.component_lists_two_lines, ComponentsSubLevelNavigationItem.ListsTwoLines.route),
    MenuItem(R.string.component_lists_three_lines, ComponentsSubLevelNavigationItem.ListsThreeLines.route)
)

@Composable
@ExperimentalMaterialApi
fun ComponentsListsScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        ComponentHeader(
            imageRes = R.drawable.picture_component_lists,
            description = R.string.component_lists_description
        )
        Column(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.ods_spacing_s))
        ) {
            listsMenuItems.forEach { item ->
                OdsListItem(text = stringResource(id = item.titleRes), modifier = Modifier.clickable {
                    navController.navigate(item.route)
                })
            }
        }
    }
}