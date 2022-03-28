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

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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
import com.orange.ods.demo.R

sealed class GuidelinesCardItem(
    @DrawableRes var image: Int,
    @StringRes var imageDescription: Int,
    @StringRes var title: Int,
    @StringRes var subTitle: Int?,
) {
    object Colour :
        GuidelinesCardItem(
            image = R.drawable.picture_guideline_colour,
            imageDescription = R.string.guideline_colour_text,
            title = R.string.guideline_colour_text,
            subTitle = null
        )
    object Typography :
        GuidelinesCardItem(
            image = R.drawable.picture_guideline_typography,
            imageDescription = R.string.guideline_typography_text,
            title = R.string.guideline_typography_text,
            subTitle = null
        )
    object Imagery :
        GuidelinesCardItem(
            image = R.drawable.picture_guideline_imagery,
            imageDescription = R.string.guideline_imagery_text,
            title = R.string.guideline_imagery_text,
            subTitle = null
        )
    object Iconography :
        GuidelinesCardItem(
            image = R.drawable.picture_guideline_iconography,
            imageDescription = R.string.guideline_iconography_text,
            title = R.string.guideline_iconography_text,
            subTitle = null
        )
}

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
                GuidelinesCardItem.Colour,
                GuidelinesCardItem.Typography,
                GuidelinesCardItem.Iconography,
                GuidelinesCardItem.Imagery,
            )*/
        )
    }
}

@Composable
private fun CardList(cards: List<GuidelinesCardItem>) {
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
