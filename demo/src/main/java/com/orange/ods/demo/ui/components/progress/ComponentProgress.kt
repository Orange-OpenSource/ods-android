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
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.utilities.composable.Subtitle
import com.orange.ods.demo.ui.utilities.composable.Title

@Composable
fun ComponentProgressContent() {
    Column(
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.ods_screen_horizontal_margin))
            .padding(bottom = dimensionResource(id = R.dimen.ods_spacing_s))
    ) {

        Title(textRes = R.string.component_progress_linear)
        Subtitle(textRes = R.string.component_progress_linear_determinate)
        LinearProgressIndicator(progress = 0.2f, modifier = Modifier.padding(top = dimensionResource(id = R.dimen.ods_spacing_s)))
        Subtitle(textRes = R.string.component_progress_linear_indeterminate)
        LinearProgressIndicator(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.ods_spacing_s)))

        Title(textRes = R.string.component_progress_circular)
        Subtitle(textRes = R.string.component_progress_circular_determinate)
        CircularProgressIndicator(progress = 0.2f, modifier = Modifier.padding(top = dimensionResource(id = R.dimen.ods_spacing_s)))
        Subtitle(textRes = R.string.component_progress_circular_indeterminate)
        CircularProgressIndicator(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.ods_spacing_s)))
    }
}