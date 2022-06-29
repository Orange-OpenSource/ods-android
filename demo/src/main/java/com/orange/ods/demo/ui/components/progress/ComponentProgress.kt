/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.progress

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.utilities.composable.Subtitle
import com.orange.ods.demo.ui.utilities.composable.Title

private const val DeterminateProgressTargetValue = 0.9f
private const val DeterminateProgressAnimDuration = 5000

@Composable
fun ComponentProgressContent() {
    var determinateProgressValue by remember { mutableStateOf(0f) }
    val determinateProgressAnimation by animateFloatAsState(
        targetValue = determinateProgressValue,
        animationSpec = tween(durationMillis = DeterminateProgressAnimDuration, easing = FastOutSlowInEasing)
    )

    Column(
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.ods_screen_horizontal_margin))
            .padding(bottom = dimensionResource(id = R.dimen.ods_spacing_s))
    ) {
        Title(textRes = R.string.component_progress_bars)
        Subtitle(textRes = R.string.component_progress_bar_determinate)
        LinearProgressIndicator(progress = determinateProgressAnimation, modifier = Modifier.padding(top = dimensionResource(id = R.dimen.ods_spacing_s)))
        Subtitle(textRes = R.string.component_progress_bar_indeterminate)
        LinearProgressIndicator(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.ods_spacing_s)))

        Title(textRes = R.string.component_progress_activity_indicator)
        Subtitle(textRes = R.string.component_progress_activity_indicator_determinate)
        CircularProgressIndicator(
            progress = determinateProgressAnimation,
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.ods_spacing_s))
                .align(alignment = Alignment.CenterHorizontally)
        )
        Subtitle(textRes = R.string.component_progress_activity_indicator_indeterminate)
        CircularProgressIndicator(
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.ods_spacing_s))
                .align(alignment = Alignment.CenterHorizontally)
        )

        LaunchedEffect(DeterminateProgressTargetValue) {
            determinateProgressValue = DeterminateProgressTargetValue
        }
    }
}
