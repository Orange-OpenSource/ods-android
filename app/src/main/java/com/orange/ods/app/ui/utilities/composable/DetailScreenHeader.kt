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

package com.orange.ods.app.ui.utilities.composable

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.orange.ods.app.ui.utilities.DrawableManager
import com.orange.ods.compose.text.OdsTextBodyL

@Composable
fun DetailScreenHeader(
    @StringRes descriptionRes: Int,
    @DrawableRes imageRes: Int,
    imageAlignment: Alignment = Alignment.Center,
    imageContentScale: ContentScale = ContentScale.Fit
) {
    Image(
        painter = painterResource(id = imageRes),
        modifier = Modifier
            .fillMaxWidth()
            .height(190.dp)
            .background(Color(DrawableManager.ImageBackgroundColor)),
        contentScale = imageContentScale,
        contentDescription = null,
        alignment = imageAlignment
    )
    DetailScreenDescription(
        modifier = Modifier.padding(
            horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin)
        ),
        descriptionRes = descriptionRes
    )
}

@Composable
private fun DetailScreenDescription(
    modifier: Modifier = Modifier,
    @StringRes descriptionRes: Int
) {
    OdsTextBodyL(
        text = stringResource(descriptionRes),
        modifier = modifier.padding(
            top = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)
        )
    )
}