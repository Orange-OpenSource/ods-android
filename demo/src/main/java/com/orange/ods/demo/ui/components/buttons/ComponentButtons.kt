/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.buttons

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.button.OdsButtonStyle
import com.orange.ods.compose.text.OdsTextBody2
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.Variant

@Composable
fun ComponentButtons(variant: Variant) {
    when (variant) {
        Variant.ButtonsPrimary -> ButtonsContained(OdsButtonStyle.Primary)
        Variant.ButtonsDefault -> ButtonsContained(OdsButtonStyle.Default)
        Variant.ButtonsOutlined -> ButtonsOutlined()
        Variant.ButtonsText -> ButtonsText()
        Variant.ButtonsFunctional -> ButtonsContained(OdsButtonStyle.FunctionalPositive)
        Variant.ButtonsToggle -> ButtonsToggle()
        Variant.ButtonsIcon -> ButtonsIcon()
        else -> {}
    }
}

@Composable
fun InvertedBackgroundColumn(
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable InvertedBackgroundColumnScope.() -> Unit
) {
    val backgroundColor: Color
    @StringRes val textRes: Int
    val displaySurface: OdsDisplaySurface
    if (isSystemInDarkTheme()) {
        backgroundColor = OdsTheme.lightThemeColors.surface
        textRes = R.string.component_force_on_light
        displaySurface = OdsDisplaySurface.Light
    } else {
        backgroundColor = OdsTheme.darkThemeColors.surface
        textRes = R.string.component_force_on_dark
        displaySurface = OdsDisplaySurface.Dark
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor)
            .padding(bottom = dimensionResource(R.dimen.spacing_m)),
        horizontalAlignment = horizontalAlignment
    ) {
        OdsTextBody2(
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.spacing_m))
                .padding(top = dimensionResource(id = R.dimen.spacing_s))
                .fillMaxWidth()
                .align(Alignment.Start),
            text = stringResource(id = textRes),
            displaySurface = displaySurface
        )
        InvertedBackgroundColumnScope(this, displaySurface).content()
    }
}

class InvertedBackgroundColumnScope(scope: ColumnScope, val displaySurface: OdsDisplaySurface) : ColumnScope by scope
