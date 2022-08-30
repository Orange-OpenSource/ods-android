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

/**
 * Displays a full width [FlowRow] containing customized choice chips [OdsChoiceChipsFlowRowScope.ChoiceChip].
 *
 * @param selectedChip The selected chips value state.
 * @param modifier Modifier to be applied to the flow row.
 * @param outlinedChips If set to true, a border will be drawn around [FlowRow] chips.
 * @param content The content of the choice chips [FlowRow].
 */
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

/**
 * A selectable chip to display in an [OdsChoiceChipsFlowRow]
 *
 * @param text Text displayed in the chip
 * @param enabled If set to false, the chip is no more clickable and appears as disabled
 */
@ExperimentalMaterialApi
@Composable
fun <T> OdsChoiceChipsFlowRowScope<T>.ChoiceChip(text: String, value: T, enabled: Boolean = true) {
    OdsChip(
        text = text,
        selected = selectedChip.value == value,
        onClick = { selectedChip.value = value },
        outlined = outlinedChips,
        enabled = enabled
    )
}

/**
 * A selectable chip to display in an [OdsChoiceChipsFlowRow]
 *
 * @param textRes Text resource identifier to display in the chip
 * @param enabled If set to false, the chip is no more clickable and appears as disabled
 */
@ExperimentalMaterialApi
@Composable
fun <T> OdsChoiceChipsFlowRowScope<T>.ChoiceChip(@StringRes textRes: Int, value: T, enabled: Boolean = true) {
    ChoiceChip(text = stringResource(id = textRes), value = value, enabled = enabled)
}

/**
 * Scope for the children of [OdsChoiceChipsFlowRow].
 */
data class OdsChoiceChipsFlowRowScope<T>(val selectedChip: MutableState<T>, val outlinedChips: Boolean)
