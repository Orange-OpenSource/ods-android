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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.text.OdsTextBody1
import com.orange.ods.compose.text.OdsTextH5
import com.orange.ods.compose.theme.LocalDarkThemeColors
import com.orange.ods.compose.theme.LocalLightThemeColors
import com.orange.ods.compose.theme.OdsDisplayAppearance
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.utilities.composable.Subtitle
import com.orange.ods.demo.ui.utilities.composable.Title

@Composable
fun ComponentButtonsContent() {
    OdsTextH5(
        text = stringResource(R.string.component_buttons_title_try),
        modifier = Modifier
            .padding(top = dimensionResource(R.dimen.ods_spacing_m))
            .padding(horizontal = dimensionResource(R.dimen.ods_screen_horizontal_margin))
    )

    OdsTextBody1(
        text = stringResource(R.string.component_buttons_preamble),
        modifier = Modifier
            .padding(top = dimensionResource(id = R.dimen.ods_spacing_s))
            .padding(horizontal = dimensionResource(R.dimen.ods_screen_horizontal_margin))
    )

    Title(R.string.component_buttons_contained_title, withHorizontalPadding = true)
    ButtonsContained()
    ButtonsOutlined()
    ButtonsText()
    ButtonsToggle()
}

fun Modifier.fullWidthButton(withTopPadding: Boolean = true) = composed {
    this
        .fillMaxWidth()
        .padding(horizontal = dimensionResource(R.dimen.ods_screen_horizontal_margin))
        .padding(top = if (withTopPadding) dimensionResource(R.dimen.ods_spacing_s) else 0.dp)
}

@Composable
fun DarkSurface(content: @Composable ColumnScope.() -> Unit) {
    Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.ods_spacing_xs)))
    Subtitle(textRes = R.string.component_force_on_dark, OdsDisplayAppearance.ON_DARK, withHorizontalPadding = true)
    ForcedBackgroundColumn(color = LocalDarkThemeColors.current.surface, content = content)
}

@Composable
fun LightSurface(content: @Composable ColumnScope.() -> Unit) {
    Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.ods_spacing_xs)))
    Subtitle(textRes = R.string.component_force_on_light, OdsDisplayAppearance.ON_LIGHT, withHorizontalPadding = true)
    ForcedBackgroundColumn(color = LocalLightThemeColors.current.surface, content = content)
}

@Composable
private fun ForcedBackgroundColumn(color: Color, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = color)
            .padding(bottom = dimensionResource(R.dimen.ods_spacing_s)),
        content = content
    )
}