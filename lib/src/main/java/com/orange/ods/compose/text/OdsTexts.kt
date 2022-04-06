/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.text

import androidx.annotation.StringRes
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.orange.ods.compose.theme.OdsDisplayAppearance
import com.orange.ods.compose.theme.odsDarkThemeColors
import com.orange.ods.compose.theme.odsLightThemeColors

@Composable
fun OdsTextH1(@StringRes textRes: Int, modifier: Modifier = Modifier, displayAppearance: OdsDisplayAppearance = OdsDisplayAppearance.DEFAULT) =
    OdsText(textRes, MaterialTheme.typography.h1, modifier, displayAppearance)

@Composable
fun OdsTextH2(@StringRes textRes: Int, modifier: Modifier = Modifier, displayAppearance: OdsDisplayAppearance = OdsDisplayAppearance.DEFAULT) =
    OdsText(textRes, MaterialTheme.typography.h2, modifier, displayAppearance)

@Composable
fun OdsTextH3(@StringRes textRes: Int, modifier: Modifier = Modifier, displayAppearance: OdsDisplayAppearance = OdsDisplayAppearance.DEFAULT) =
    OdsText(textRes, MaterialTheme.typography.h3, modifier, displayAppearance)

@Composable
fun OdsTextH4(@StringRes textRes: Int, modifier: Modifier = Modifier, displayAppearance: OdsDisplayAppearance = OdsDisplayAppearance.DEFAULT) =
    OdsText(textRes, MaterialTheme.typography.h4, modifier, displayAppearance)

@Composable
fun OdsTextH5(@StringRes textRes: Int, modifier: Modifier = Modifier, displayAppearance: OdsDisplayAppearance = OdsDisplayAppearance.DEFAULT) =
    OdsText(textRes, MaterialTheme.typography.h5, modifier, displayAppearance)

@Composable
fun OdsTextH6(@StringRes textRes: Int, modifier: Modifier = Modifier, displayAppearance: OdsDisplayAppearance = OdsDisplayAppearance.DEFAULT) =
    OdsText(textRes, MaterialTheme.typography.h6, modifier, displayAppearance)

@Composable
fun OdsTextSubtitle1(@StringRes textRes: Int, modifier: Modifier = Modifier, displayAppearance: OdsDisplayAppearance = OdsDisplayAppearance.DEFAULT) =
    OdsText(textRes, MaterialTheme.typography.subtitle1, modifier, displayAppearance)

@Composable
fun OdsTextSubtitle2(@StringRes textRes: Int, modifier: Modifier = Modifier, displayAppearance: OdsDisplayAppearance = OdsDisplayAppearance.DEFAULT) =
    OdsText(textRes, MaterialTheme.typography.subtitle2, modifier, displayAppearance)

@Composable
fun OdsTextBody1(@StringRes textRes: Int, modifier: Modifier = Modifier, displayAppearance: OdsDisplayAppearance = OdsDisplayAppearance.DEFAULT) =
    OdsText(textRes, MaterialTheme.typography.body1, modifier, displayAppearance)

@Composable
fun OdsTextBody2(@StringRes textRes: Int, modifier: Modifier = Modifier, displayAppearance: OdsDisplayAppearance = OdsDisplayAppearance.DEFAULT) =
    OdsText(textRes, MaterialTheme.typography.body2, modifier, displayAppearance)

@Composable
fun OdsTextButton(@StringRes textRes: Int, modifier: Modifier = Modifier, displayAppearance: OdsDisplayAppearance = OdsDisplayAppearance.DEFAULT) =
    OdsText(textRes, MaterialTheme.typography.button, modifier, displayAppearance)

@Composable
fun OdsTextCaption(@StringRes textRes: Int, modifier: Modifier = Modifier, displayAppearance: OdsDisplayAppearance = OdsDisplayAppearance.DEFAULT) =
    OdsText(textRes, MaterialTheme.typography.caption, modifier, displayAppearance)

@Composable
fun OdsTextOverline(@StringRes textRes: Int, modifier: Modifier = Modifier, displayAppearance: OdsDisplayAppearance = OdsDisplayAppearance.DEFAULT) =
    OdsText(textRes, MaterialTheme.typography.overline, modifier, displayAppearance)

@Composable
private fun OdsText(@StringRes textRes: Int, textStyle: TextStyle, modifier: Modifier, displayAppearance: OdsDisplayAppearance) {
    val color = when (displayAppearance) {
        OdsDisplayAppearance.DEFAULT -> Color.Unspecified
        OdsDisplayAppearance.ON_DARK -> odsDarkThemeColors.onSurface
        OdsDisplayAppearance.ON_LIGHT -> odsLightThemeColors.onSurface
    }
    Text(text = stringResource(id = textRes), style = textStyle, color = color, modifier = modifier)
}