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
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.google.accompanist.flowlayout.FlowRow
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComponentApi
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
@OdsComponentApi
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
 * @param enabled If set to false, the chip is no more clickable and appears as disabled
 */
@Composable
@OdsComponentApi
fun <T> OdsChoiceChipsFlowRowScope<T>.OdsChoiceChip(text: String, value: T, enabled: Boolean = true) {
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
@Composable
fun <T> OdsChoiceChipsFlowRowScope<T>.OdsChoiceChip(@StringRes textRes: Int, value: T, enabled: Boolean = true) {
    OdsChoiceChip(text = stringResource(id = textRes), value = value, enabled = enabled)
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

internal class OdsChoiceChipsFlowRowPreviewParameterProvider : BasicPreviewParameterProvider<Boolean>(false, true)
