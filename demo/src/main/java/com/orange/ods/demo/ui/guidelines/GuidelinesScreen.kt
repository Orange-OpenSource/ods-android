/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.guidelines

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.orange.ods.compose.component.OdsCardImageFirst
import com.orange.ods.compose.theme.OdsMaterialTheme

@Composable
fun GuidelinesScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CardList(
            //Add item in the list once ready
            cards = listOf(
                GuidelinesCardItem.Colour,
                //GuidelinesCardItem.Typography,
                //GuidelinesCardItem.Iconography,
                //GuidelinesCardItem.Imagery,
            ),
            navController = navController
        )
    }
}

@Composable
private fun CardList(cards: List<GuidelinesCardItem>, navController: NavController) {
    cards.forEach { card ->
        OdsCardImageFirst(
            imageRes = card.image,
            title = stringResource(id = card.title),
            onCardClick = {
                when (card) {
                    is GuidelinesCardItem.Colour -> {
                        navController.navigate(GuidelineNavigationItem.Color.route)
                    }
                    else -> {
                        //Not handled for the moment
                    }
                }
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GuidelinesScreenPreview() {
    OdsMaterialTheme {
        GuidelinesScreen(rememberNavController())
    }
}
