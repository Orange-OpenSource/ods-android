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

package com.orange.ods.compose.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.compose.utilities.extension.enable

@Composable
fun OdsTextHeadlineL(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, OdsTheme.typography.headlineL, modifier, displaySurface, enabled)

@Composable
fun OdsTextHeadlineS(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, OdsTheme.typography.headlineS, modifier, displaySurface, enabled)

@Composable
fun OdsTextTitleL(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, OdsTheme.typography.titleL, modifier, displaySurface, enabled)

@Composable
fun OdsTextTitleM(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, OdsTheme.typography.titleM, modifier, displaySurface, enabled)

@Composable
fun OdsTextTitleS(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, OdsTheme.typography.titleS, modifier, displaySurface, enabled)

@Composable
fun OdsTextBodyL(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, OdsTheme.typography.bodyL, modifier, displaySurface, enabled)

@Composable
fun OdsTextBodyM(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, OdsTheme.typography.bodyM, modifier, displaySurface, enabled)

@Composable
fun OdsTextBodyS(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, OdsTheme.typography.bodyS, modifier, displaySurface, enabled)

@Composable
fun OdsTextLabelL(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text.uppercase(), OdsTheme.typography.labelL, modifier, displaySurface, enabled)

@Composable
fun OdsTextLabelS(text: String, modifier: Modifier = Modifier, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, enabled: Boolean = true) =
    OdsText(text, OdsTheme.typography.labelS, modifier, displaySurface, enabled)

@Composable
private fun OdsText(text: String, textStyle: TextStyle, modifier: Modifier, displaySurface: OdsDisplaySurface, enabled: Boolean) {
    val color = displaySurface.themeColors.onSurface.enable(enabled = enabled)
    Text(text = text, style = textStyle, color = color, modifier = modifier)
}