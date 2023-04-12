/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.progress

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.utilities.composable.BetweenQuotesStringParameter
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.CodeParameter
import com.orange.ods.app.ui.utilities.composable.ComposableCode
import com.orange.ods.app.ui.utilities.composable.PredefinedParameter
import com.orange.ods.app.ui.utilities.composable.StringRepresentationParameter
import com.orange.ods.app.ui.utilities.composable.Subtitle
import com.orange.ods.compose.component.OdsComponent
import com.orange.ods.compose.component.chip.OdsChoiceChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing
import com.orange.ods.compose.component.progressindicator.OdsLinearProgressIndicator

private const val DeterminateProgressTargetValue = 0.9f
private const val DeterminateProgressAnimDuration = 5000

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProgressLinear() {

    val customizationState = rememberProgressCustomizationState()
    var determinateProgressValue by remember { mutableStateOf(0f) }
    val determinateProgressAnimation by animateFloatAsState(
        targetValue = determinateProgressValue,
        animationSpec = tween(durationMillis = DeterminateProgressAnimDuration, easing = FastOutSlowInEasing)
    )

    with(customizationState) {
        if (type.value == ProgressCustomizationState.Type.Indeterminate) currentValue.value = false
        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {
                OdsChoiceChipsFlowRow(
                    selectedChip = type,
                    modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.spacing_m)),
                    outlinedChips = true
                ) {
                    Subtitle(textRes = R.string.component_progress_type)
                    OdsChoiceChip(textRes = R.string.component_progress_determinate, value = ProgressCustomizationState.Type.Determinate)
                    OdsChoiceChip(textRes = R.string.component_progress_indeterminate, value = ProgressCustomizationState.Type.Indeterminate)
                }
                OdsListItem(
                    text = stringResource(id = R.string.component_element_label),
                    trailing = OdsSwitchTrailing(checked = label)
                )
                OdsListItem(
                    text = stringResource(id = R.string.component_element_icon),
                    trailing = OdsSwitchTrailing(checked = icon)
                )
                OdsListItem(
                    text = stringResource(id = R.string.component_progress_linear_value),
                    trailing = OdsSwitchTrailing(
                        checked = currentValue,
                        enabled = isCurrentValueSwitchEnabled
                    ),
                )
            }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                val text = stringResource(id = R.string.component_progress_label)
                OdsLinearProgressIndicator(
                    progress = if (type.value == ProgressCustomizationState.Type.Determinate) determinateProgressAnimation else null,
                    label = if (hasLabel) text else null,
                    showCurrentValue = hasCurrentValue,
                    icon = if (hasIcon) painterResource(id = R.drawable.ic_arrow_down) else null,
                    modifier = Modifier
                        .padding(top = dimensionResource(id = R.dimen.spacing_m))
                        .fillMaxWidth()
                )
                if (type.value == ProgressCustomizationState.Type.Determinate) {
                    LaunchedEffect(DeterminateProgressTargetValue) {
                        determinateProgressValue = DeterminateProgressTargetValue
                    }
                }

                CodeImplementationColumn {
                    ComposableCode(
                        name = OdsComponent.OdsLinearProgressIndicator.name,
                        exhaustiveParameters = false,
                        parameters = mutableListOf<CodeParameter>().apply {
                            if (type.value == ProgressCustomizationState.Type.Determinate) add(
                                StringRepresentationParameter(
                                    "progress",
                                    determinateProgressValue
                                )
                            )
                            if (hasLabel) add(BetweenQuotesStringParameter("label", text))
                            if (hasIcon) add(PredefinedParameter.Icon)
                            if (hasCurrentValue) add(StringRepresentationParameter("showCurrentValue", hasCurrentValue))
                        }
                    )
                }
            }
        }
    }
}