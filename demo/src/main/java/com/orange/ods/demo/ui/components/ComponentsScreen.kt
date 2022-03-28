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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.orange.ods.compose.theme.OdsMaterialTheme
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.component.OdsCardSmall

@Composable
fun ComponentsScreen() {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ComponentsCardsList(
            emptyList()
            //Add item in the list once ready
            /*listOf(
                ComponentsCardItem.Buttons,
                ComponentsCardItem.Controls,
                ComponentsCardItem.BottomNavigation,
            )*/
        )
    }
}

@Composable
private fun ComponentsCardsList(cards: List<ComponentsCardItem>) {
    cards.chunked(2).forEach { rowCards ->
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            ComponentCard(componentsCardItem = rowCards[0])
            if (rowCards.size == 2) {
                ComponentCard(componentsCardItem = rowCards[1])
            } else {
                Box(modifier = Modifier.weight(0.5f)) {}
            }
        }
    }
}

@Composable
private fun RowScope.ComponentCard(componentsCardItem: ComponentsCardItem) {
    OdsCardSmall(
        modifier = Modifier.weight(0.5f),
        title = stringResource(id = componentsCardItem.title),
        imageRes = componentsCardItem.image,
        onCardClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun ComponentsScreenPreview() {
    OdsMaterialTheme {
        ComponentsScreen()
    }
}
