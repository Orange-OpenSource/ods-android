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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.orange.ods.compose.theme.OdsMaterialTheme
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.component.OdsCardImageFirst

@Composable
fun GuidelinesScreen() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CardList(
            emptyList()
            //Add item in the list once ready
            /*listOf(
                CardItem.Colour,
                CardItem.Typography,
                CardItem.Iconography,
                CardItem.Imagery,
            )*/
        )
    }
}

@Composable
fun CardList(cards: List<CardItem>) {
    cards.forEach { card ->
        OdsCardImageFirst(
            imageRes = card.image,
            imageContentDescription = stringResource(id = card.imageDescription),
            title = stringResource(id = card.title),
            onCardClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GuidelinesScreenPreview() {
    OdsMaterialTheme {
        GuidelinesScreen()
    }
}
