/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.sliders

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.orange.ods.compose.component.control.OdsSlider
import com.orange.ods.compose.component.control.OdsSliderLockups
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.utilities.composable.Subtitle

@Composable
fun ComponentSlidersContent() {
    var discreteSliderPosition by remember { mutableStateOf(0f) }

    Column(
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.ods_spacing_s))
    ) {
        Subtitle(R.string.component_slider_discrete, withHorizontalPadding = false)
        OdsSlider(
            value = discreteSliderPosition,
            steps = 10,
            onValueChange = { discreteSliderPosition = it },
        )

        var discreteWithIconsSliderPosition by remember { mutableStateOf(0f) }

        Subtitle(R.string.component_slider_discrete_with_icons, withHorizontalPadding = false)
        OdsSlider(
            value = discreteWithIconsSliderPosition,
            steps = 10,
            onValueChange = { discreteWithIconsSliderPosition = it },
            leftIconRes = R.drawable.ic_heart,
            rightIconRes = R.drawable.ic_heart,
        )

        var continuousSliderPosition by remember { mutableStateOf(0f) }

        Subtitle(R.string.component_slider_continuous, withHorizontalPadding = false)
        OdsSlider(
            value = continuousSliderPosition,
            onValueChange = { continuousSliderPosition = it }
        )

        var continuousSliderWithIconsPosition by remember { mutableStateOf(0f) }

        Subtitle(R.string.component_slider_continuous_with_icons, withHorizontalPadding = false)
        OdsSlider(
            value = continuousSliderWithIconsPosition,
            onValueChange = { continuousSliderWithIconsPosition = it },
            leftIconRes = R.drawable.ic_heart,
            rightIconRes = R.drawable.ic_heart,
        )

        var discreteLockupsSliderPosition by remember { mutableStateOf(0f) }

        Subtitle(R.string.component_slider_discrete_lockups, withHorizontalPadding = false)
        OdsSliderLockups(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.ods_spacing_s)),
            value = discreteLockupsSliderPosition,
            valueRange = 0f..100f,
            steps = 9,
            onValueChange = { discreteLockupsSliderPosition = it }
        )

        var continuousLockupsSliderPosition by remember { mutableStateOf(0f) }

        Subtitle(R.string.component_slider_continuous_lockups, withHorizontalPadding = false)
        OdsSliderLockups(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.ods_spacing_s)),
            value = continuousLockupsSliderPosition,
            valueRange = 0f..100f,
            onValueChange = { continuousLockupsSliderPosition = it }
        )
    }
}