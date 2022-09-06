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

import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.odsDarkThemeColors
import com.orange.ods.compose.theme.odsLightThemeColors

@Composable
fun OdsTextH1(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, MaterialTheme.typography.h1, modifier, displaySurface, enabled)

@Composable
fun OdsTextH2(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, MaterialTheme.typography.h2, modifier, displaySurface, enabled)

@Composable
fun OdsTextH3(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, MaterialTheme.typography.h3, modifier, displaySurface, enabled)

@Composable
fun OdsTextH4(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, MaterialTheme.typography.h4, modifier, displaySurface, enabled)

@Composable
fun OdsTextH5(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, MaterialTheme.typography.h5, modifier, displaySurface, enabled)

@Composable
fun OdsTextH6(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, MaterialTheme.typography.h6, modifier, displaySurface, enabled)

@Composable
fun OdsTextSubtitle1(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, MaterialTheme.typography.subtitle1, modifier, displaySurface, enabled)

@Composable
fun OdsTextSubtitle2(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, MaterialTheme.typography.subtitle2, modifier, displaySurface, enabled)

@Composable
fun OdsTextBody1(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, MaterialTheme.typography.body1, modifier, displaySurface, enabled)

@Composable
fun OdsTextBody2(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, MaterialTheme.typography.body2, modifier, displaySurface, enabled)

@Composable
fun OdsTextButton(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, MaterialTheme.typography.button, modifier, displaySurface, enabled)

@Composable
fun OdsTextCaption(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, MaterialTheme.typography.caption, modifier, displaySurface, enabled)

@Composable
fun OdsTextOverline(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, MaterialTheme.typography.overline, modifier, displaySurface, enabled)

@Composable
private fun OdsText(text: String, textStyle: TextStyle, modifier: Modifier, displaySurface: OdsDisplaySurface, enabled: Boolean) {
    val color = when (displaySurface) {
        OdsDisplaySurface.Default -> if (enabled) Color.Unspecified else MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled)
        OdsDisplaySurface.Dark -> if (enabled) odsDarkThemeColors.onSurface else odsDarkThemeColors.onSurface.copy(alpha = ContentAlpha.disabled)
        OdsDisplaySurface.Light -> if (enabled) odsLightThemeColors.onSurface else odsLightThemeColors.onSurface.copy(alpha = ContentAlpha.disabled)
    }

    Text(text = text, style = textStyle, color = color, modifier = modifier)
}