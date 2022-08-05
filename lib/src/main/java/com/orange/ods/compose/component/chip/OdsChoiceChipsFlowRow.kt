/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.chip

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.google.accompanist.flowlayout.FlowRow
import com.orange.ods.R

@Composable
fun <T> OdsChoiceChipsFlowRow(
    selectedChip: MutableState<T>,
    modifier: Modifier = Modifier,
    outlinedChips: Boolean = false,
    content: @Composable OdsChoiceChipsFlowRowScope<T>.() -> Unit
) {
    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .selectableGroup(),
        mainAxisSpacing = dimensionResource(id = R.dimen.spacing_s),
        content = { OdsChoiceChipsFlowRowScope(selectedChip, outlinedChips).content() }
    )
}

@ExperimentalMaterialApi
@Composable
fun <T> OdsChoiceChipsFlowRowScope<T>.SelectableChip(text: String, value: T, enabled: Boolean = true) {
    OdsChip(
        text = text,
        selected = selectedChip.value == value,
        onClick = { selectedChip.value = value },
        outlined = outlinedChips,
        enabled = enabled
    )
}

@ExperimentalMaterialApi
@Composable
fun <T> OdsChoiceChipsFlowRowScope<T>.SelectableChip(@StringRes textRes: Int, value: T, enabled: Boolean = true) {
    SelectableChip(text = stringResource(id = textRes), value = value, enabled = enabled)
}

data class OdsChoiceChipsFlowRowScope<T>(val selectedChip: MutableState<T>, val outlinedChips: Boolean)
