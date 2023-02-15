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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.OdsComponent
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing
import com.orange.ods.compose.component.progressindicator.OdsCircularProgressIndicator
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.demo.ui.utilities.composable.CommonTechnicalTextColumn
import com.orange.ods.demo.ui.utilities.composable.TechnicalText

private const val DeterminateProgressTargetValue = 0.9f
private const val DeterminateProgressAnimDuration = 5000

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProgressActivityIndicator() {
    val customizationState = rememberProgressCustomizationState()
    var determinateProgressValue by remember { mutableStateOf(0f) }
    val determinateProgressAnimation by animateFloatAsState(
        targetValue = determinateProgressValue,
        animationSpec = tween(durationMillis = DeterminateProgressAnimDuration, easing = FastOutSlowInEasing)
    )

    with(customizationState) {
        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {
                OdsListItem(
                    text = stringResource(id = R.string.component_element_label),
                    trailing = OdsSwitchTrailing(checked = label)
                )
            }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())

            ) {
                val label = if (hasLabel) "Downloading …" else null
                OdsCircularProgressIndicator(
                    progress = determinateProgressAnimation,
                    label = label,
                    modifier = Modifier
                        .padding(top = dimensionResource(id = R.dimen.spacing_m))
                        .align(alignment = Alignment.CenterHorizontally)
                )

                LaunchedEffect(DeterminateProgressTargetValue) {
                    determinateProgressValue = DeterminateProgressTargetValue
                }

                CodeImplementationColumn {
                    CommonTechnicalTextColumn(
                        componentName = OdsComponent.OdsCircularProgressIndicator.name
                    ) {
                        TechnicalText(text = "{")
                        TechnicalText(text = "  // add your content here")
                        TechnicalText(text = "}")
                    }
                }

            }

        }
    }
}