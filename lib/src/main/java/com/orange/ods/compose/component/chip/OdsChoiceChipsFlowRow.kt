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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.google.accompanist.flowlayout.FlowRow
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews

/**
 * Displays a full width [FlowRow] containing customized choice chips [OdsChoiceChipsFlowRowScope.OdsChoiceChip].
 *
 * @param selectedChip The selected chips value state.
 * @param modifier Modifier to be applied to the flow row.
 * @param outlinedChips If true, a border will be drawn around [FlowRow] chips. Otherwise chips will be filled.
 * @param content The content of the choice chips [FlowRow].
 */
@Composable
@OdsComposable
fun <T> OdsChoiceChipsFlowRow(
    selectedChip: MutableState<T>,
    modifier: Modifier = Modifier,
    outlinedChips: Boolean = true,
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
 * @param value The chip value
 * @param modifier The modifier applied to the OdsChoiceChip
 * @param enabled If set to false, the chip is no more clickable and appears as disabled
 */
@Composable
@OdsComposable
fun <T> OdsChoiceChipsFlowRowScope<T>.OdsChoiceChip(text: String, value: T, modifier: Modifier = Modifier, enabled: Boolean = true) {
    val selected = selectedChip.value == value
    val chipStateDescription = odsChipStateDescription(selected = selected)
    OdsChip(
        text = text,
        modifier = modifier.semantics {
            stateDescription = chipStateDescription
        },
        selected = selected,
        onClick = { selectedChip.value = value },
        outlined = outlinedChips,
        enabled = enabled
    )
}

/**
 * A selectable chip to display in an [OdsChoiceChipsFlowRow]
 *
 * @param textRes Text resource identifier to display in the chip
 * @param value The chip value
 * @param modifier The modifier applied to the OdsChoiceChip
 * @param enabled If set to false, the chip is no more clickable and appears as disabled
 */
@Composable
fun <T> OdsChoiceChipsFlowRowScope<T>.OdsChoiceChip(@StringRes textRes: Int, value: T, modifier: Modifier = Modifier, enabled: Boolean = true) {
    OdsChoiceChip(text = stringResource(id = textRes), value = value, modifier = modifier, enabled = enabled)
}

/**
 * Scope for the children of [OdsChoiceChipsFlowRow].
 */
data class OdsChoiceChipsFlowRowScope<T>(val selectedChip: MutableState<T>, val outlinedChips: Boolean)

@UiModePreviews.Default
@Composable
private fun PreviewOdsChoiceChipsFlowRow(@PreviewParameter(OdsChoiceChipsFlowRowPreviewParameterProvider::class) outlinedChips: Boolean) = Preview {
    data class ChoiceChip(val text: String, val enabled: Boolean, val value: Int)

    val choiceChips = listOf(
        ChoiceChip("First", true, 1),
        ChoiceChip("Second", true, 2),
        ChoiceChip("Third", false, 3),
        ChoiceChip("Fourth", true, 4)
    )

    val selectedChip = remember { mutableStateOf(choiceChips.first().value) }
    OdsChoiceChipsFlowRow(
        selectedChip = selectedChip,
        outlinedChips = outlinedChips
    ) {
        choiceChips.forEach { choiceChip ->
            with(choiceChip) {
                OdsChoiceChip(text = text, value = value, enabled = enabled)
            }
        }
    }
}

private class OdsChoiceChipsFlowRowPreviewParameterProvider : BasicPreviewParameterProvider<Boolean>(false, true)
