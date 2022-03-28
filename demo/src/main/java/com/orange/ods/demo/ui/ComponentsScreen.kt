/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui

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
        CardComponentList(
            emptyList()
            //Add item in the list once ready
            /*listOf(
                CardComponentItem.Buttons,
                CardComponentItem.Controls,
                CardComponentItem.BottomNavigation,
            )*/
        )
    }
}

@Composable
fun CardComponentList(cards: List<CardComponentItem>) {
    cards.chunked(2).forEach { cardsPair ->
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            OdsCardSmall(
                modifier = Modifier.weight(0.5f),
                title = stringResource(id = cardsPair[0].title),
                imageRes = cardsPair[0].image,
                onCardClick = {}
            )
            if (cardsPair.size == 2) {
                OdsCardSmall(
                    modifier = Modifier.weight(0.5f),
                    title = stringResource(id = cardsPair[1].title),
                    imageRes = cardsPair[1].image,
                    onCardClick = {}
                )
            } else {
                Box(modifier = Modifier.weight(0.5f)) {}
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComponentsScreenPreview() {
    OdsMaterialTheme {
        ComponentsScreen()
    }
}
