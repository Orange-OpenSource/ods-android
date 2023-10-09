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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.component.utilities.selectionStateDescription
import com.orange.ods.compose.theme.OdsTheme

/**
 * Displays a full width [FlowRow] containing customized [OdsChoiceChip]s.
 * Only one chip can be selected at a time. When the OdsChoiceChipsFlowRow value changes, [onValueChange] method is invoked.
 *
 * Note that [OdsChoiceChip] are displayed outlined or filled according to your [OdsTheme] component configuration, outlined by default.
 *
 * @param value The initial value of the choice chips flow row
 * @param onValueChange Callback invoked when the value changes. The new value is provided as parameter.
 * @param modifier [Modifier] applied to the chips flow row
 * @param chips The list of [OdsChoiceChip] displayed into the chips flow row
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
@OdsComposable
fun <T> OdsChoiceChipsFlowRow(
    value: T,
    onValueChange: (value: T) -> Unit,
    modifier: Modifier = Modifier,
    chips: List<OdsChoiceChip<T>>
) {
    var selectedChipValue by remember { mutableStateOf(value) }

    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .selectableGroup(),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_s)),
        content = {
            chips.forEachIndexed { index, odsChoiceChip ->
                odsChoiceChip.Content(selected = selectedChipValue == odsChoiceChip.value) { selected ->
                    if (selected) {
                        selectedChipValue = chips[index].value
                        onValueChange(chips[index].value)
                    }
                }
            }
        }
    )
}

/**
 * OdsChoiceChip used in a [OdsChoiceChipsFlowRow]
 *
 * @param text Text displayed in the chip
 * @param value The chip value
 * @param enabled If set to false, the chip is no more clickable and appears as disabled. True by default.
 * @param semantics The semantics applied on this choice chip
 */
class OdsChoiceChip<T>(
    val text: String,
    val value: T,
    val enabled: Boolean = true,
    val semantics: SemanticsPropertyReceiver.() -> Unit = {}
) {

    @Composable
    fun Content(selected: Boolean, onSelectedStateChange: (selected: Boolean) -> Unit) {
        val chipStateDescription = selectionStateDescription(selected = selected)
        OdsChip(
            text = text,
            modifier = Modifier.semantics {
                stateDescription = chipStateDescription
                semantics()
            },
            selected = selected,
            onClick = { onSelectedStateChange(!selected) },
            enabled = enabled
        )
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsChoiceChipsFlowRow() = Preview {
    val choiceChips = listOf(
        OdsChoiceChip(text = "First", value = 1),
        OdsChoiceChip("Second", value = 2),
        OdsChoiceChip("Third", value = 3, enabled = false),
        OdsChoiceChip("Fourth", value = 4)
    )

    val selectedChip = remember { mutableStateOf(choiceChips.first().value) }
    OdsChoiceChipsFlowRow(
        value = selectedChip.value,
        onValueChange = { value -> selectedChip.value = value },
        modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)),
        chips = choiceChips
    )
}