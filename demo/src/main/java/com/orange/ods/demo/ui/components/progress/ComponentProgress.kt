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
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.orange.ods.compose.component.progressindicator.OdsCircularProgressIndicator
import com.orange.ods.compose.component.progressindicator.OdsLinearProgressIndicator
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.Variant
import com.orange.ods.demo.ui.utilities.composable.Subtitle

private const val DeterminateProgressTargetValue = 0.9f
private const val DeterminateProgressAnimDuration = 5000

@Composable
fun ComponentProgress(variant: Variant) {
    var determinateProgressValue by remember { mutableStateOf(0f) }
    val determinateProgressAnimation by animateFloatAsState(
        targetValue = determinateProgressValue,
        animationSpec = tween(durationMillis = DeterminateProgressAnimDuration, easing = FastOutSlowInEasing)
    )

    Column(
        modifier = Modifier
            .padding(bottom = dimensionResource(id = R.dimen.screen_vertical_margin))
            .padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin))
            .verticalScroll(rememberScrollState())
    ) {
        when (variant) {
            Variant.ProgressBar -> ProgressBar(determinateProgressAnimation)
            Variant.ProgressActivityIndicator -> ProgressActivityIndicator(determinateProgressAnimation)
            else -> {}
        }

        LaunchedEffect(DeterminateProgressTargetValue) {
            determinateProgressValue = DeterminateProgressTargetValue
        }
    }
}

@Composable
private fun ColumnScope.ProgressBar(determinateProgressAnimation: Float) {
    Subtitle(textRes = R.string.component_progress_bar_determinate)
    OdsLinearProgressIndicator(
        progress = determinateProgressAnimation,
        modifier = Modifier
            .padding(top = dimensionResource(id = R.dimen.spacing_m))
            .fillMaxWidth()
    )
    Subtitle(textRes = R.string.component_progress_bar_indeterminate)
    OdsLinearProgressIndicator(
        modifier = Modifier
            .padding(top = dimensionResource(id = R.dimen.spacing_m))
            .fillMaxWidth()
    )
}

@Composable
private fun ColumnScope.ProgressActivityIndicator(determinateProgressAnimation: Float) {
    Subtitle(textRes = R.string.component_progress_activity_indicator_determinate)
    OdsCircularProgressIndicator(
        progress = determinateProgressAnimation,
        modifier = Modifier
            .padding(top = dimensionResource(id = R.dimen.spacing_m))
            .align(alignment = Alignment.CenterHorizontally)
    )
    Subtitle(textRes = R.string.component_progress_activity_indicator_indeterminate)
    OdsCircularProgressIndicator(
        modifier = Modifier
            .padding(top = dimensionResource(id = R.dimen.spacing_m))
            .align(alignment = Alignment.CenterHorizontally)
    )

}