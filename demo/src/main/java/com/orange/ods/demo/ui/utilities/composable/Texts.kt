/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.utilities.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orange.ods.compose.text.OdsTextH5
import com.orange.ods.compose.text.OdsTextSubtitle1
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.demo.R

@Composable
fun Title(@StringRes textRes: Int, modifier: Modifier = Modifier, horizontalPadding: Boolean = false, topPadding: Boolean = false) {
    OdsTextH5(
        text = stringResource(textRes),
        modifier = modifier
            .fillMaxWidth()
            .padding(top = if (topPadding) dimensionResource(R.dimen.spacing_xl) else 0.dp)
            .padding(horizontal = if (horizontalPadding) dimensionResource(R.dimen.screen_horizontal_margin) else 0.dp)
    )
}

@Composable
fun Subtitle(@StringRes textRes: Int, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, horizontalPadding: Boolean = false) {
    val backgroundColor = when (displaySurface) {
        OdsDisplaySurface.Default -> Color.Unspecified
        OdsDisplaySurface.Dark -> OdsTheme.darkThemeColors.surface
        OdsDisplaySurface.Light -> OdsTheme.lightThemeColors.surface
    }
    OdsTextSubtitle1(
        text = stringResource(textRes),
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(top = dimensionResource(id = R.dimen.spacing_m))
            .padding(horizontal = if (horizontalPadding) dimensionResource(R.dimen.screen_horizontal_margin) else 0.dp),
        displaySurface = displaySurface
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