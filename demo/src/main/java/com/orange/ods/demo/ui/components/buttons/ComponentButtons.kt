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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.theme.DarkSurfaceDefault
import com.orange.ods.compose.theme.OdsDisplayAppearance
import com.orange.ods.compose.theme.White100
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.SubComponent
import com.orange.ods.demo.ui.utilities.composable.Subtitle

@Composable
fun ComponentButtons(subComponent: SubComponent) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        when (subComponent) {
            SubComponent.ButtonsContained -> ButtonsContained()
            SubComponent.ButtonsOutlined -> ButtonsOutlined()
            SubComponent.ButtonsText -> ButtonsText()
            SubComponent.ButtonsToggle -> ButtonsToggle()
            else -> {}
        }
    }
}

fun Modifier.fullWidthButton(withTopPadding: Boolean = true) = composed {
    this
        .fillMaxWidth()
        .padding(horizontal = dimensionResource(R.dimen.ods_screen_horizontal_margin))
        .padding(top = if (withTopPadding) dimensionResource(R.dimen.ods_spacing_s) else 0.dp)
}

@Composable
fun DarkSurface(content: @Composable ColumnScope.() -> Unit) {
    val backgroundColor = DarkSurfaceDefault
    Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.ods_spacing_xs)))
    Subtitle(textRes = R.string.component_force_on_dark, OdsDisplayAppearance.ON_DARK, withHorizontalPadding = true)
    ForcedBackgroundColumn(color = backgroundColor, content = content)
}

@Composable
fun LightSurface(content: @Composable ColumnScope.() -> Unit) {
    val backgroundColor = White100
    Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.ods_spacing_xs)))
    Subtitle(textRes = R.string.component_force_on_light, OdsDisplayAppearance.ON_LIGHT, withHorizontalPadding = true)
    ForcedBackgroundColumn(color = backgroundColor, content = content)
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