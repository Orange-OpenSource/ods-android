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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.card.OdsCardImageFirst
import com.orange.ods.demo.R

@Composable
fun GuidelinesScreen(onGuidelineClick: (String) -> Unit, updateTopBarTitle: (Int) -> Unit) {
    updateTopBarTitle(R.string.navigation_item_guidelines)
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.spacing_m))
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_m))
    ) {
        Guideline.values().forEach { guideline ->
            OdsCardImageFirst(
                title = stringResource(id = guideline.titleRes),
                imageRes = guideline.imageRes,
                imageContentScale = guideline.imageContentScale,
                imageBackgroundColor = guideline.imageBackgroundColor,
                onCardClick = { onGuidelineClick(guideline.route) }
            )
        }
    }
}
