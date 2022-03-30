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

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.orange.ods.compose.component.OdsCardSmall
import com.orange.ods.compose.theme.OdsMaterialTheme

@Composable
fun ComponentsScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ComponentsCardsList(
            listOf(
                ComponentsCardItem.Buttons,
                ComponentsCardItem.Controls,
                ComponentsCardItem.Cards
                //ComponentsCardItem.BottomNavigation,
            ), navController
        )
    }
}

@Composable
private fun ComponentsCardsList(cards: List<ComponentsCardItem>, navController: NavController) {
    cards.chunked(2).forEach { rowCards ->
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            ComponentCard(componentsCardItem = rowCards[0], navController)
            if (rowCards.size == 2) {
                ComponentCard(componentsCardItem = rowCards[1], navController)
            } else {
                Box(modifier = Modifier.weight(0.5f)) {}
            }
        }
    }
}

@Composable
private fun RowScope.ComponentCard(componentsCardItem: ComponentsCardItem, navController: NavController) {
    OdsCardSmall(
        modifier = Modifier.weight(0.5f),
        title = stringResource(id = componentsCardItem.title),
        imageRes = componentsCardItem.image,
        onCardClick = {
            navController.navigate(componentsCardItem.route)
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
