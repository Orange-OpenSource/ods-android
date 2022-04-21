/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.cards

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

private val cardMenuItems = listOf(
    MenuItem(R.string.component_card_image_first, ComponentsSubLevelNavigationItem.CardImageFirst.route),
    MenuItem(R.string.component_card_title_first, ComponentsSubLevelNavigationItem.CardTitleFirst.route),
    MenuItem(R.string.component_card_small, ComponentsSubLevelNavigationItem.CardSmall.route)
)

@ExperimentalMaterialApi
@Composable
fun ComponentsCardsScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        ComponentHeader(
            imageRes = R.drawable.picture_component_cards,
            description = R.string.component_card_description
        )

        Column(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.ods_spacing_s))
        ) {
            for (cardMenuItem in cardMenuItems) {
                OdsListItem(text = stringResource(id = cardMenuItem.titleRes), modifier = Modifier.clickable {
                    navController.navigate(cardMenuItem.route)
                })
            }
        }
    }
}