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
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsDisplaySurface

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/06a393-buttons/b/79b091" target="_blank">ODS Buttons</a>.
 *
 * A group of toggle buttons. Only one option in a group of toggle buttons can be selected and active at a time.
 * Selecting one option deselects any other.
 *
 * @param textToggleButtons List of [OdsTextToggleButtonsRow.Item] displayed into the toggle group.
 * @param selectedIndex [textToggleButtons] list index of the selected button.
 * @param onSelectedIndexChange Callback invoked on selection change.
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
    textToggleButtons: List<OdsTextToggleButtonsRow.Item>,
    selectedIndex: Int,
    onSelectedIndexChange: (Int) -> Unit,
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
        textToggleButtons.forEachIndexed { index, textToggleButton ->
            TextToggleButtonsRowItem(
                index = index,
                textToggleButton = textToggleButton,
                selected = selectedIndex == index,
                sameItemsWeight = sameItemsWeight,
                displaySurface = displaySurface
            ) { clickedButtonIndex ->
                onSelectedIndexChange(clickedButtonIndex)
            }
            if (index < textToggleButtons.size) {
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
     * An item in an [OdsTextToggleButtonsRow].
     */
    data class Item(
        val text: String,
        val enabled: Boolean = true
    )

}

@Composable
private fun RowScope.TextToggleButtonsRowItem(
    index: Int,
    textToggleButton: OdsTextToggleButtonsRow.Item,
    selected: Boolean,
    sameItemsWeight: Boolean,
    displaySurface: OdsDisplaySurface,
    onClick: (Int) -> Unit
) {
    val backgroundAlpha by animateFloatAsState(if (selected) 0.12f else 0f, label = "")

    OdsTextButton(
        text = textToggleButton.text,
        enabled = textToggleButton.enabled,
        modifier = Modifier
            .background(color = buttonToggleBackgroundColor(displaySurface).copy(alpha = backgroundAlpha))
            .fillMaxHeight()
            .let {
                if (sameItemsWeight) it.weight(1f) else it
            },
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        displaySurface = displaySurface,
        style = OdsTextButton.Style.Default,
        onClick = { onClick(index) }
    )
}

@OptIn(ExperimentalOdsApi::class)
@UiModePreviews.Default
@Composable
private fun PreviewOdsTextToggleButtonsGroupRow() = Preview {
    val textToggleButtons = listOf(
        OdsTextToggleButtonsRow.Item("XML", true),
        OdsTextToggleButtonsRow.Item("COMPOSE", true),
    )
    var selectedIndex by remember { mutableIntStateOf(0) }

    OdsTextToggleButtonsRow(
        textToggleButtons = textToggleButtons,
        selectedIndex = selectedIndex,
        onSelectedIndexChange = { index -> selectedIndex = index }
    )
}
