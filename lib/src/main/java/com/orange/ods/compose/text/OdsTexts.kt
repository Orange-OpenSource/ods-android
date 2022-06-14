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

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.orange.ods.compose.theme.LocalDarkThemeColors
import com.orange.ods.compose.theme.LocalLightThemeColors
import com.orange.ods.compose.theme.OdsDisplayAppearance
import com.orange.ods.compose.theme.OdsTheme

@Composable
fun OdsTextH1(
    text: String,
    modifier: Modifier = Modifier,
    displayAppearance: OdsDisplayAppearance = OdsDisplayAppearance.DEFAULT
) =
    OdsText(text, OdsTheme.typography.h1, modifier, displayAppearance)

@Composable
fun OdsTextH2(
    text: String,
    modifier: Modifier = Modifier,
    displayAppearance: OdsDisplayAppearance = OdsDisplayAppearance.DEFAULT
) =
    OdsText(text, OdsTheme.typography.h2, modifier, displayAppearance)

@Composable
fun OdsTextH3(
    text: String,
    modifier: Modifier = Modifier,
    displayAppearance: OdsDisplayAppearance = OdsDisplayAppearance.DEFAULT
) =
    OdsText(text, OdsTheme.typography.h3, modifier, displayAppearance)

@Composable
fun OdsTextH4(
    text: String,
    modifier: Modifier = Modifier,
    displayAppearance: OdsDisplayAppearance = OdsDisplayAppearance.DEFAULT
) =
    OdsText(text, OdsTheme.typography.h4, modifier, displayAppearance)

@Composable
fun OdsTextH5(
    text: String,
    modifier: Modifier = Modifier,
    displayAppearance: OdsDisplayAppearance = OdsDisplayAppearance.DEFAULT
) =
    OdsText(text, OdsTheme.typography.h5, modifier, displayAppearance)

@Composable
fun OdsTextH6(
    text: String,
    modifier: Modifier = Modifier,
    displayAppearance: OdsDisplayAppearance = OdsDisplayAppearance.DEFAULT
) =
    OdsText(text, OdsTheme.typography.h6, modifier, displayAppearance)

@Composable
fun OdsTextSubtitle1(
    text: String,
    modifier: Modifier = Modifier,
    displayAppearance: OdsDisplayAppearance = OdsDisplayAppearance.DEFAULT
) =
    OdsText(text, OdsTheme.typography.subtitle1, modifier, displayAppearance)

@Composable
fun OdsTextSubtitle2(
    text: String,
    modifier: Modifier = Modifier,
    displayAppearance: OdsDisplayAppearance = OdsDisplayAppearance.DEFAULT
) =
    OdsText(text, OdsTheme.typography.subtitle2, modifier, displayAppearance)

@Composable
fun OdsTextBody1(
    text: String,
    modifier: Modifier = Modifier,
    displayAppearance: OdsDisplayAppearance = OdsDisplayAppearance.DEFAULT
) =
    OdsText(text, OdsTheme.typography.body1, modifier, displayAppearance)

@Composable
fun OdsTextBody2(
    text: String,
    modifier: Modifier = Modifier,
    displayAppearance: OdsDisplayAppearance = OdsDisplayAppearance.DEFAULT
) =
    OdsText(text, OdsTheme.typography.body2, modifier, displayAppearance)

@Composable
fun OdsTextButton(
    text: String,
    modifier: Modifier = Modifier,
    displayAppearance: OdsDisplayAppearance = OdsDisplayAppearance.DEFAULT
) =
    OdsText(text, OdsTheme.typography.button, modifier, displayAppearance)

@Composable
fun OdsTextCaption(
    text: String,
    modifier: Modifier = Modifier,
    displayAppearance: OdsDisplayAppearance = OdsDisplayAppearance.DEFAULT
) =
    OdsText(text, OdsTheme.typography.caption, modifier, displayAppearance)

@Composable
fun OdsTextOverline(
    text: String,
    modifier: Modifier = Modifier,
    displayAppearance: OdsDisplayAppearance = OdsDisplayAppearance.DEFAULT
) =
    OdsText(text, OdsTheme.typography.overline, modifier, displayAppearance)

@Composable
private fun OdsText(
    text: String,
    textStyle: TextStyle,
    modifier: Modifier,
    displayAppearance: OdsDisplayAppearance
) {
    val color = when (displayAppearance) {
        OdsDisplayAppearance.DEFAULT -> OdsTheme.colors.coreOnSurface
        OdsDisplayAppearance.ON_DARK -> LocalDarkThemeColors.current.coreOnSurface
        OdsDisplayAppearance.ON_LIGHT -> LocalLightThemeColors.current.coreOnSurface
    }
    Text(text = text, style = textStyle, color = color, modifier = modifier)
}