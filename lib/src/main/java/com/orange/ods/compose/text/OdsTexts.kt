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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.OdsTheme

@Composable
fun OdsTextH1(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, OdsTheme.typography.h1, modifier, displaySurface, enabled)

@Composable
fun OdsTextH2(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, OdsTheme.typography.h2, modifier, displaySurface, enabled)

@Composable
fun OdsTextH3(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, OdsTheme.typography.h3, modifier, displaySurface, enabled)

@Composable
fun OdsTextH4(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, OdsTheme.typography.h4, modifier, displaySurface, enabled)

@Composable
fun OdsTextH5(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, OdsTheme.typography.h5, modifier, displaySurface, enabled)

@Composable
fun OdsTextH6(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, OdsTheme.typography.h6, modifier, displaySurface, enabled)

@Composable
fun OdsTextSubtitle1(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, OdsTheme.typography.subtitle1, modifier, displaySurface, enabled)

@Composable
fun OdsTextSubtitle2(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, OdsTheme.typography.subtitle2, modifier, displaySurface, enabled)

@Composable
fun OdsTextBody1(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, OdsTheme.typography.body1, modifier, displaySurface, enabled)

@Composable
fun OdsTextBody2(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, OdsTheme.typography.body2, modifier, displaySurface, enabled)

@Composable
fun OdsTextButton(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, OdsTheme.typography.button, modifier, displaySurface, enabled)

@Composable
fun OdsTextCaption(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, OdsTheme.typography.caption, modifier, displaySurface, enabled)

@Composable
fun OdsTextOverline(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, OdsTheme.typography.overline, modifier, displaySurface, enabled)

@Composable
private fun OdsText(text: String, textStyle: TextStyle, modifier: Modifier, displaySurface: OdsDisplaySurface, enabled: Boolean) {
    val color = when (displaySurface) {
        OdsDisplaySurface.Default -> if (enabled) OdsTheme.colors.onSurface else OdsTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled)
        OdsDisplaySurface.Dark -> if (enabled) OdsTheme.darkThemeColors.onSurface else OdsTheme.darkThemeColors.onSurface.copy(alpha = ContentAlpha.disabled)
        OdsDisplaySurface.Light -> if (enabled) OdsTheme.lightThemeColors.onSurface else OdsTheme.lightThemeColors.onSurface.copy(alpha = ContentAlpha.disabled)
    }

    Text(text = text, style = textStyle, color = color, modifier = modifier)
}