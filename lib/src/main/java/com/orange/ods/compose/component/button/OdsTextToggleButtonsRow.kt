/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.button

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.OdsTheme

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/06a393-buttons/b/79b091" target="_blank">ODS Buttons</a>.
 *
 * A group of toggle buttons. Only one option in a group of toggle buttons can be selected and active at a time.
 * Selecting one option deselects any other.
 *
 * @param textToggleButtons Contains the [OdsTextToggleButtonsRowItem] to display in the toggle group
 * @param selectedIndex The [textToggleButtons] list index of the selected button.
 * @param onSelectedIndexChange Callback to be invoked when the selection change.
 * @param modifier optional [Modifier] for this OdsTextToggleButtonsRow
 * @param displaySurface optional allow to force the group display on a dark or light
 * surface. By default the appearance applied is based on the system night mode value.
 */
@Composable
@OdsComposable
fun OdsTextToggleButtonsRow(
    textToggleButtons: List<OdsTextToggleButtonsRowItem>,
    selectedIndex: Int,
    onSelectedIndexChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
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
            val backgroundAlpha by animateFloatAsState(if (selectedIndex == index) 0.12f else 0f)
            Column(
                modifier = Modifier
                    .weight(1f)
                    .background(color = buttonToggleBackgroundColor(displaySurface).copy(alpha = backgroundAlpha))
            ) {
                TextToggleButtonsRowItem(
                    index = index,
                    textToggleButton = textToggleButton,
                    selected = selectedIndex == index,
                    displaySurface = displaySurface
                ) { clickedButtonIndex ->
                    onSelectedIndexChange(clickedButtonIndex)
                }
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

data class OdsTextToggleButtonsRowItem(
    val text: String,
    val enabled: Boolean = true
)

@Composable
private fun TextToggleButtonsRowItem(
    index: Int,
    textToggleButton: OdsTextToggleButtonsRowItem,
    selected: Boolean,
    displaySurface: OdsDisplaySurface,
    onClick: (Int) -> Unit
) {
    OdsTextButton(
        text = textToggleButton.text,
        enabled = textToggleButton.enabled,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        displaySurface = displaySurface,
        style = if (selected) OdsTextButtonStyle.Primary else OdsTextButtonStyle.Default,
        onClick = { onClick(index) }
    )
}

@Composable
private fun buttonToggleBackgroundColor(displaySurface: OdsDisplaySurface) =
    when (displaySurface) {
        OdsDisplaySurface.Default -> OdsTheme.colors.primary
        OdsDisplaySurface.Dark -> OdsTheme.darkThemeColors.primary
        OdsDisplaySurface.Light -> OdsTheme.lightThemeColors.primary
    }

@Composable
private fun buttonToggleBorderColor(displaySurface: OdsDisplaySurface) =
    when (displaySurface) {
        OdsDisplaySurface.Default -> OdsTheme.colors.onSurface
        OdsDisplaySurface.Dark -> OdsTheme.darkThemeColors.onSurface
        OdsDisplaySurface.Light -> OdsTheme.lightThemeColors.onSurface
    }.copy(alpha = 0.12f)

@UiModePreviews.Default
@Composable
private fun PreviewOdsTextToggleButtonsGroupRow() = Preview {
    val textToggleButtons = listOf(
        OdsTextToggleButtonsRowItem("XML", true),
        OdsTextToggleButtonsRowItem("COMPOSE", true),
    )
    var selectedIndex by remember { mutableStateOf(0) }

    OdsTextToggleButtonsRow(
        textToggleButtons = textToggleButtons,
        selectedIndex = selectedIndex,
        onSelectedIndexChange = { index -> selectedIndex = index }
    )
}
