/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ods.app.ui.guidelines

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.ui.utilities.DrawableManager
import com.orange.ods.compose.component.card.OdsCard
import com.orange.ods.compose.component.card.OdsVerticalImageFirstCard

@Composable
fun GuidelinesScreen(onGuidelineClick: (String) -> Unit) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(dimensionResource(id = com.orange.ods.R.dimen.spacing_m)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = com.orange.ods.R.dimen.spacing_m))
    ) {
        Guideline.entries.forEach { guideline ->
            OdsVerticalImageFirstCard(
                title = stringResource(id = guideline.titleRes),
                image = OdsCard.Image(
                    painterResource(id = DrawableManager.getDrawableResIdForCurrentTheme(resId = guideline.imageRes)),
                    "",
                    guideline.imageAlignment,
                    guideline.imageContentScale,
                    guideline.imageBackgroundColor
                ),
                onClick = { onGuidelineClick(guideline.route) }
            )
        }
    }
}
