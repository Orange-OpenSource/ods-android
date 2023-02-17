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

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.orange.ods.demo.ui.components.Variant

private const val DeterminateProgressTargetValue = 0.9f
private const val DeterminateProgressAnimDuration = 5000

@Composable
fun ComponentProgress(variant: Variant) {

    Column {
        when (variant) {
            Variant.ProgressLinear -> ProgressBar()
            Variant.ProgressCircular -> ProgressActivityIndicator()
            else -> {}
        }

    }
}

/*@Composable
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
}*/

/*
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

}*/
