/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components 
 */

package com.orange.ods.app.ui.utilities.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orange.ods.app.R
import com.orange.ods.compose.text.OdsText
import com.orange.ods.compose.theme.OdsTheme

@Composable
fun Title(@StringRes textRes: Int, modifier: Modifier = Modifier, horizontalPadding: Boolean = false, topPadding: Boolean = false) {
    OdsText(
        text = stringResource(textRes),
        modifier = modifier
            .fillMaxWidth()
            .padding(top = if (topPadding) OdsTheme.spacings.extraLarge else 0.dp)
            .padding(horizontal = if (horizontalPadding) dimensionResource(com.orange.ods.R.dimen.screen_horizontal_margin) else 0.dp),
        style = OdsTheme.typography.headlineSmall
    )
}

@Composable
fun Subtitle(@StringRes textRes: Int, horizontalPadding: Boolean = false) {
    OdsText(
        text = stringResource(textRes),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = OdsTheme.spacings.medium)
            .padding(horizontal = if (horizontalPadding) dimensionResource(com.orange.ods.R.dimen.screen_horizontal_margin) else 0.dp),
        style = OdsTheme.typography.titleMedium
    )
}

@Composable
fun TechnicalText(text: String, modifier: Modifier = Modifier) {
    val courier = FontFamily(Font(R.font.courier_prime_regular))
    Text(
        text = text,
        modifier = modifier.fillMaxWidth(),
        fontFamily = courier,
        fontSize = 14.sp,
        color = OdsTheme.colors.onSurface.copy(alpha = ContentAlpha.medium)
    )
}