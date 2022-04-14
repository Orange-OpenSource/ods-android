/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.controls

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.orange.ods.compose.component.control.OdsSlider
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.utilities.composable.Subtitle

@Composable
fun ComponentsControlsSlidersScreen() {
    var discreteSliderPosition by remember { mutableStateOf(0f) }
    var discreteWithIconsSliderPosition by remember { mutableStateOf(0f) }
    var continuousSliderPosition by remember { mutableStateOf(0f) }
    var continuousSliderWithIconsPosition by remember { mutableStateOf(0f) }

    ComponentsControlsTemplate(R.string.component_controls_switches_description) {
        Subtitle(R.string.component_controls_slider_discrete, withHorizontalPadding = false)
        OdsSlider(
            value = discreteSliderPosition,
            steps = 10,
            onValueChange = { discreteSliderPosition = it },
        )

        Subtitle(R.string.component_controls_slider_discrete_with_icons, withHorizontalPadding = false)
        OdsSlider(
            value = discreteWithIconsSliderPosition,
            steps = 10,
            onValueChange = { discreteWithIconsSliderPosition = it },
            leftIconRes = R.drawable.ic_heart,
            rightIconRes = R.drawable.ic_heart,
        )

        Subtitle(R.string.component_controls_slider_continuous, withHorizontalPadding = false)
        OdsSlider(
            value = continuousSliderPosition,
            onValueChange = { continuousSliderPosition = it }
        )

        Subtitle(R.string.component_controls_slider_continuous_with_icons, withHorizontalPadding = false)
        OdsSlider(
            value = continuousSliderWithIconsPosition,
            onValueChange = { continuousSliderWithIconsPosition = it },
            leftIconRes = R.drawable.ic_heart,
            rightIconRes = R.drawable.ic_heart,
        )
    }
}