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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.theme.DarkSurfaceDefault
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.White100
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.Variant
import com.orange.ods.demo.ui.utilities.composable.Subtitle

@Composable
fun ComponentButtons(variant: Variant) {
    Column(
        modifier = Modifier
            .padding(bottom = dimensionResource(id = R.dimen.ods_screen_vertical_margin))
            .verticalScroll(rememberScrollState())
    ) {
        when (variant) {
            Variant.ButtonsPrimary -> ButtonsPrimary()
            Variant.ButtonsContained -> ButtonsContained()
            Variant.ButtonsOutlined -> ButtonsOutlined()
            Variant.ButtonsText -> ButtonsText()
            Variant.ButtonsFunctional -> ButtonsFunctional()
            Variant.ButtonsToggle -> ButtonsToggle()
            else -> {}
        }
    }
}

fun Modifier.fullWidthButton(withTopPadding: Boolean = true) = composed {
    this
        .fillMaxWidth()
        .padding(horizontal = dimensionResource(R.dimen.ods_screen_horizontal_margin))
        .padding(top = if (withTopPadding) dimensionResource(R.dimen.spacing_m) else 0.dp)
}

@Composable
fun DarkSurface(content: @Composable ColumnScope.() -> Unit) {
    val backgroundColor = DarkSurfaceDefault
    Subtitle(textRes = R.string.component_force_on_dark, OdsDisplaySurface.Dark, withHorizontalPadding = true)
    ForcedBackgroundColumn(color = backgroundColor, content = content)
}

@Composable
fun LightSurface(content: @Composable ColumnScope.() -> Unit) {
    val backgroundColor = White100
    Subtitle(textRes = R.string.component_force_on_light, OdsDisplaySurface.Light, withHorizontalPadding = true)
    ForcedBackgroundColumn(color = backgroundColor, content = content)
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