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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.card.OdsImageFirstCard
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.LocalMainTopAppBarManager

@ExperimentalMaterialApi
@Composable
fun GuidelinesScreen(onGuidelineClick: (String) -> Unit) {
    LocalMainTopAppBarManager.current.updateTopAppBarTitle(R.string.navigation_item_guidelines)
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(dimensionResource(id = R.dimen.spacing_m)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_m))
    ) {
        Guideline.values().forEach { guideline ->
            OdsImageFirstCard(
                title = stringResource(id = guideline.titleRes),
                image = painterResource(id = guideline.imageRes),
                imageContentScale = guideline.imageContentScale,
                imageBackgroundColor = guideline.imageBackgroundColor,
                imageAlignment = guideline.imageAlignment,
                onCardClick = { onGuidelineClick(guideline.route) }
            )
        }
    }
}
