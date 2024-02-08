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

package com.orange.ods.compose.component.button

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.annotation.ExperimentalOdsApi
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.content.OdsComponentContent
import com.orange.ods.compose.component.content.OdsComponentScopeContent
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsDisplaySurface

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/06a393-buttons/b/79b091" target="_blank">ODS Buttons</a>.
 *
 * A group of toggle buttons. Only one option in a group of toggle buttons can be selected and active at a time.
 * Selecting one option deselects any other.
 *
 * @param selectedTextButtonIndex The index of the currently selected text button.
 * @param textButtons List of [OdsTextToggleButtonsRow.TextButton] displayed into the toggle group.
 * @param modifier [Modifier] applied to the toggle buttons row.
 * @param sameItemsWeight Controls the place occupied by each item. When `true`, same weight of importance will be applied to each item, they will occupy
 * the same width.
 * @param displaySurface [OdsDisplaySurface] applied to the button. It allows to force the button display on light or dark surface. By default,
 * the appearance applied is based on the system night mode value.
 */
@Composable
@OdsComposable
@ExperimentalOdsApi
fun OdsTextToggleButtonsRow(
    selectedTextButtonIndex: Int,
    textButtons: List<OdsTextToggleButtonsRow.TextButton>,
    modifier: Modifier = Modifier,
    sameItemsWeight: Boolean = false,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .selectableGroup()
            .border(
                width = 1.dp,
                color = buttonToggleBorderColor(displaySurface)
            )
    ) {
        textButtons.forEachIndexed { index, textButton ->
            with(textButton) {
                Content(
                    extraParameters = OdsTextToggleButtonsRow.TextButton.ExtraParameters(
                        index,
                        displaySurface,
                        selectedTextButtonIndex == index,
                        sameItemsWeight
                    )
                )
            }
            if (index < textButtons.size) {
                Divider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp),
                    color = buttonToggleBorderColor(displaySurface)
                )
            }
        }
    }
}

/**
 * Contains classes to build an [com.orange.ods.compose.component.button.OdsTextToggleButtonsRow].
 */
object OdsTextToggleButtonsRow {

    /**
     * An text button in an [OdsTextToggleButtonsRow].
     *
     * @property text The text of the button.
     * @property onClick Callback invoked when this text button is selected.
     * @property enabled Whether or not this [OdsTextToggleButtonsRow.TextButton] will handle input events and appear enabled for
     * semantics purposes, true by default.
     */
    class TextButton(
        val text: String,
        val onClick: () -> Unit,
        val enabled: Boolean = true
    ) : OdsComponentScopeContent<RowScope, TextButton.ExtraParameters>(ExtraParameters::class.java) {

        data class ExtraParameters(
            val index: Int,
            val displaySurface: OdsDisplaySurface,
            val selected: Boolean,
            val sameItemsWeight: Boolean
        ) : OdsComponentContent.ExtraParameters()

        @Composable
        override fun RowScope.Content(modifier: Modifier) {
            val backgroundAlpha by animateFloatAsState(if (extraParameters.selected) 0.12f else 0f, label = "")

            OdsTextButton(
                text = text,
                enabled = enabled,
                modifier = Modifier
                    .background(color = buttonToggleBackgroundColor(extraParameters.displaySurface).copy(alpha = backgroundAlpha))
                    .fillMaxHeight()
                    .let {
                        if (extraParameters.sameItemsWeight) it.weight(1f) else it
                    },
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                displaySurface = extraParameters.displaySurface,
                style = OdsTextButton.Style.Default,
                onClick = onClick
            )
        }
    }
}

@OptIn(ExperimentalOdsApi::class)
@UiModePreviews.Default
@Composable
private fun PreviewOdsTextToggleButtonsRow() = Preview {
    var selectedTextButtonIndex by remember { mutableIntStateOf(0) }
    val textButtons = listOf(
        OdsTextToggleButtonsRow.TextButton("XML", { selectedTextButtonIndex = 0 }, true),
        OdsTextToggleButtonsRow.TextButton("COMPOSE", { selectedTextButtonIndex = 1 }, true),
    )

    OdsTextToggleButtonsRow(
        selectedTextButtonIndex = selectedTextButtonIndex,
        textButtons = textButtons,
    )
}
