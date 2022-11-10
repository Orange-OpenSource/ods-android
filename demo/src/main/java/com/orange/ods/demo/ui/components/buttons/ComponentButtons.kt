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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.orange.ods.compose.component.button.OdsButtonStyle
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.Variant
import com.orange.ods.demo.ui.utilities.composable.Subtitle
import com.orange.ods.demo.ui.utilities.composable.TechnicalText
import com.orange.ods.demo.ui.utilities.composable.Title

@Composable
fun ComponentButtons(variant: Variant) {
    when (variant) {
        Variant.ButtonsPrimary -> ButtonsContained(OdsButtonStyle.Primary)
        Variant.ButtonsDefault -> ButtonsContained(OdsButtonStyle.Default)
        Variant.ButtonsOutlined -> ButtonsOutlined()
        Variant.ButtonsText -> ButtonsText()
        Variant.ButtonsFunctional -> ButtonsContained(OdsButtonStyle.FunctionalPositive)
        Variant.ButtonsToggle -> ButtonsToggle()
        else -> {}
    }
}

@Composable
fun StyleTitle(@StringRes titleRes: Int, technicalText: String) {
    Title(titleRes, horizontalPadding = true)
    TechnicalText(text = technicalText, horizontalPadding = true)
}

@Composable
fun DarkSurface(content: @Composable ColumnScope.() -> Unit) {
    ForcedBackgroundColumn(color = OdsTheme.darkThemeColors.surface) {
        Subtitle(textRes = R.string.component_force_on_dark, displaySurface = OdsDisplaySurface.Dark, withHorizontalPadding = true)
        content()
    }
}

@Composable
fun LightSurface(content: @Composable ColumnScope.() -> Unit) {
    ForcedBackgroundColumn(color = OdsTheme.lightThemeColors.surface) {
        Subtitle(textRes = R.string.component_force_on_light, displaySurface = OdsDisplaySurface.Light, withHorizontalPadding = true)
        content()
    }
}

@Composable
private fun ForcedBackgroundColumn(color: Color, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = color)
            .padding(bottom = dimensionResource(R.dimen.spacing_m)),
        content = content
    )
}