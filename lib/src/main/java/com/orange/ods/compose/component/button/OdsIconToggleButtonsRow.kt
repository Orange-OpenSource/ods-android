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

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.utilities.DisabledInteractionSource
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
 * @param iconToggleButtons Contains the [OdsIconToggleButtonsRowItem] to display in the toggle group
 * @param selectedIndex The [iconToggleButtons] list index of the selected button.
 * @param onSelectedIndexChange Callback to be invoked when the selection change.
 * @param modifier optional [Modifier] for this OdsIconToggleButtonsRow
 * @param displaySurface optional allow to force the group display on a dark or light
 * surface. By default the appearance applied is based on the system night mode value.
 */
@Composable
@OdsComposable
fun OdsIconToggleButtonsRow(
    iconToggleButtons: List<OdsIconToggleButtonsRowItem>,
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
        iconToggleButtons.forEachIndexed { index, iconToggleButton ->
            IconToggleButtonsRowItem(
                index = index,
                iconToggleButton = iconToggleButton,
                selected = selectedIndex == index,
                displaySurface = displaySurface
            ) { clickedButtonIndex ->
                onSelectedIndexChange(clickedButtonIndex)
            }
            if (index < iconToggleButtons.size) {
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

data class OdsIconToggleButtonsRowItem(
    val icon: Painter,
    val iconDescription: String,
    val enabled: Boolean = true
)

@Composable
private fun IconToggleButtonsRowItem(
    index: Int,
    iconToggleButton: OdsIconToggleButtonsRowItem,
    selected: Boolean,
    displaySurface: OdsDisplaySurface,
    onClick: (Int) -> Unit
) {
    val iconTint by animateColorAsState(buttonToggleIconColor(displaySurface, selected))
    val backgroundAlpha by animateFloatAsState(if (selected) 0.12f else 0f)

    Icon(
        modifier = Modifier
            .background(color = buttonToggleBackgroundColor(displaySurface).copy(alpha = backgroundAlpha))
            .padding(12.dp)
            .clickable(interactionSource = remember { DisabledInteractionSource() }, indication = null) { onClick(index) },
        painter = iconToggleButton.icon,
        contentDescription = iconToggleButton.iconDescription,
        tint = iconTint
    )
}

@Composable
private fun buttonToggleIconColor(displaySurface: OdsDisplaySurface, checked: Boolean) =
    when (displaySurface) {
        OdsDisplaySurface.Default -> if (checked) OdsTheme.colors.primary else OdsTheme.colors.onSurface
        OdsDisplaySurface.Dark -> if (checked) OdsTheme.darkThemeColors.primary else OdsTheme.darkThemeColors.onSurface
        OdsDisplaySurface.Light -> if (checked) OdsTheme.lightThemeColors.primary else OdsTheme.lightThemeColors.onSurface
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
private fun PreviewOdsIconToggleButtonsGroupRow() = Preview {
    val iconToggleButtons = listOf(
        OdsIconToggleButtonsRowItem(painterResource(id = android.R.drawable.ic_dialog_dialer), "Today"),
        OdsIconToggleButtonsRowItem(painterResource(id = android.R.drawable.ic_dialog_email), "Day"),
        OdsIconToggleButtonsRowItem(painterResource(id = android.R.drawable.ic_dialog_alert), "Month")
    )
    var selectedIndex by remember { mutableStateOf(0) }

    OdsIconToggleButtonsRow(
        iconToggleButtons = iconToggleButtons,
        selectedIndex = selectedIndex,
        onSelectedIndexChange = { index -> selectedIndex = index }
    )
}
