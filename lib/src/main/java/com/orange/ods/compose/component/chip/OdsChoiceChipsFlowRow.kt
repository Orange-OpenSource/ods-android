/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components 
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.content.OdsComponentContent
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.component.utilities.selectionStateDescription
import com.orange.ods.compose.theme.OdsTheme

/**
 * Displays a full width [FlowRow] containing [OdsChoiceChipsFlowRow.ChoiceChip].
 * Only one chip can be selected at a time. When a chip is selected, the [OdsChoiceChipsFlowRow.ChoiceChip.onClick] method of the associated chip is invoked.
 *
 * Note that [OdsChoiceChipsFlowRow.ChoiceChip] are displayed outlined or filled according to your [OdsTheme] component configuration, outlined by default.
 *
 * @param selectedChoiceChipIndex The index of the currently selected chip.
 * @param choiceChips The list of [OdsChoiceChipsFlowRow.ChoiceChip] displayed into the chips flow row.
 * @param modifier [Modifier] applied to the chips flow row.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
@OdsComposable
fun OdsChoiceChipsFlowRow(
    selectedChoiceChipIndex: Int,
    choiceChips: List<OdsChoiceChipsFlowRow.ChoiceChip>,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .selectableGroup(),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_s)),
        verticalArrangement = Arrangement.spacedBy((-4).dp),
        content = {
            choiceChips.forEachIndexed { index, choiceChip ->
                choiceChip.Content(OdsChoiceChipsFlowRow.ChoiceChip.ExtraParameters(index == selectedChoiceChipIndex))
            }
        }
    )
}

/**
 * Contains classes to build an [com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow].
 */
object OdsChoiceChipsFlowRow {

    /**
     * A chip used in a [OdsChoiceChipsFlowRow].
     *
     * @param text Text displayed in the chip.
     * @param onClick Callback invoked when this chip is selected.
     * @param enabled If set to false, the chip is no more clickable and appears as disabled. True by default.
     * @param semantics The semantics applied on this chip.
     */
    class ChoiceChip(
        val text: String,
        val onClick: () -> Unit,
        val enabled: Boolean = true,
        val semantics: SemanticsPropertyReceiver.() -> Unit = {}
    ) : OdsComponentContent<ChoiceChip.ExtraParameters>(ExtraParameters::class.java) {

        data class ExtraParameters(val selected: Boolean) : OdsComponentContent.ExtraParameters()

        @Composable
        override fun Content(modifier: Modifier) {
            val chipStateDescription = selectionStateDescription(selected = extraParameters.selected)
            OdsChip(
                text = text,
                modifier = Modifier.semantics {
                    stateDescription = chipStateDescription
                    semantics()
                },
                selected = extraParameters.selected,
                onClick = onClick,
                enabled = enabled
            )
        }
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsChoiceChipsFlowRow() = Preview {
    val texts = listOf("First", "Second", "Third", "Fourth")
    var selectedChoiceChipIndex by remember { mutableIntStateOf(0) }
    OdsChoiceChipsFlowRow(
        selectedChoiceChipIndex = selectedChoiceChipIndex,
        modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)),
        choiceChips = texts.mapIndexed { index, text ->
            OdsChoiceChipsFlowRow.ChoiceChip(
                text = text,
                enabled = index != 2,
                onClick = { selectedChoiceChipIndex = index }
            )
        }
    )
}
