/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.orange.ods.compose.component.card.OdsCardSmall
import com.orange.ods.compose.theme.OdsMaterialTheme
import com.orange.ods.demo.R

private val componentsItems = listOf(
    ComponentsNavigationItem.BottomNavigation,
    ComponentsNavigationItem.Buttons,
    ComponentsNavigationItem.Controls,
    ComponentsNavigationItem.Cards,
    ComponentsNavigationItem.Progress
)

@Composable
fun ComponentsScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.ods_spacing_s))
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.ods_spacing_s))
    ) {
        componentsItems.chunked(2).forEach { rowCards ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.ods_spacing_s)),
            ) {
                ComponentCard(componentsNavigationItem = rowCards[0], navController)
                if (rowCards.size == 2) {
                    ComponentCard(componentsNavigationItem = rowCards[1], navController)
                } else {
                    Box(modifier = Modifier.weight(0.5f)) {}
                }
            }
        }
    }
}

@Composable
private fun RowScope.ComponentCard(componentsNavigationItem: ComponentsNavigationItem, navController: NavController) {
    OdsCardSmall(
        modifier = Modifier.weight(0.5f),
        title = stringResource(id = componentsNavigationItem.title),
        imageRes = componentsNavigationItem.image,
        onCardClick = {
            navController.navigate(componentsNavigationItem.route)
        },
    )
}

@Preview(showBackground = true)
@Composable
fun ComponentsScreenPreview() {
    OdsMaterialTheme {
        ComponentsScreen(rememberNavController())
    }
}
