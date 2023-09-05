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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.content.OdsComponentIcon
import com.orange.ods.compose.component.utilities.DisabledInteractionSource
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.component.utilities.selectionStateDescription
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.utilities.extension.enable

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/06a393-buttons/b/79b091" target="_blank">ODS Buttons</a>.
 *
 * A group of toggle buttons. Only one option in a group of toggle buttons can be selected and active at a time.
 * Selecting one option deselects any other.
 *
 * @param icons Contains the [OdsIconToggleButtonsRowIcon] to display in the toggle group
 * @param selectedIndex The [icons] list index of the selected button.
 * @param onSelectedIndexChange Callback to be invoked when the selection change.
 * @param modifier optional [Modifier] for this OdsIconToggleButtonsRow
 * @param displaySurface optional allow to force the group display on a dark or light
 * surface. By default the appearance applied is based on the system night mode value.
 */
@Composable
@OdsComposable
fun OdsIconToggleButtonsRow(
    icons: List<OdsIconToggleButtonsRowIcon>,
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
        icons.forEachIndexed { index, icon ->
            icon.Content(index = index, displaySurface = displaySurface, selected = selectedIndex == index) { clickedButtonIndex ->
                onSelectedIndexChange(clickedButtonIndex)
            }
            if (index < icons.size) {
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
 * An icon of an [OdsIconToggleButtonsRow].
 */
class OdsIconToggleButtonsRowIcon : OdsComponentIcon {

    /**
     * Creates an instance of [OdsIconToggleButtonsRowIcon].
     *
     * @param painter Painter of the icon.
     * @param contentDescription The content description associated to this [OdsIconToggleButtonsRowIcon].
     * @param enabled Whether or not this [OdsIconToggleButtonsRowIcon] will handle input events and appear enabled for
     * semantics purposes, true by default.
     */
    constructor(painter: Painter, contentDescription: String, enabled: Boolean = true) : super(painter as Any, contentDescription, enabled)

    /**
     * Creates an instance of [OdsIconToggleButtonsRowIcon].
     *
     * @param imageVector Image vector of the icon.
     * @param contentDescription The content description associated to this [OdsIconToggleButtonsRowIcon].
     * @param enabled Whether or not this [OdsIconToggleButtonsRowIcon] will handle input events and appear enabled for
     * semantics purposes, true by default.
     */
    constructor(imageVector: ImageVector, contentDescription: String, enabled: Boolean = true) : super(imageVector as Any, contentDescription, enabled)

    /**
     * Creates an instance of [OdsIconToggleButtonsRowIcon].
     *
     * @param bitmap Image bitmap of the icon.
     * @param contentDescription The content description associated to this [OdsIconToggleButtonsRowIcon].
     * @param enabled Whether or not this [OdsIconToggleButtonsRowIcon] will handle input events and appear enabled for
     * semantics purposes, true by default.
     */
    constructor(bitmap: ImageBitmap, contentDescription: String, enabled: Boolean = true) : super(bitmap as Any, contentDescription, enabled)

    private var index: Int = 0
    private var onClick: (Int) -> Unit = {}
    private var selected: Boolean = false

    override val tint: Color
        @Composable
        get() {
            return buttonToggleIconColor(displaySurface, selected, enabled)
        }

    @Composable
    internal fun Content(index: Int, displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default, selected: Boolean = false, onClick: (Int) -> Unit) {
        this.index = index
        this.displaySurface = displaySurface
        this.selected = selected
        this.onClick = onClick
        Content()
    }

    @Composable
    override fun Content(modifier: Modifier) {
        val backgroundAlpha = if (selected && enabled) 0.12f else 0f
        val toggleIconStateDescription = selectionStateDescription(selected = selected)

        super.Content(
            modifier
                .background(color = buttonToggleBackgroundColor(displaySurface).copy(alpha = backgroundAlpha))
                .padding(12.dp)
                .run {
                    if (enabled) {
                        clickable(interactionSource = remember { DisabledInteractionSource() }, indication = null) { onClick(index) }
                    } else {
                        this
                    }
                }
                .semantics {
                    stateDescription = toggleIconStateDescription
                }
        )
    }
}

@Composable
private fun buttonToggleIconColor(displaySurface: OdsDisplaySurface, selected: Boolean, enabled: Boolean) =
    with(displaySurface.themeColors) {
        if (selected && enabled) primary else onSurface.enable(enabled = enabled)
    }


@UiModePreviews.Default
@Composable
private fun PreviewOdsIconToggleButtonsGroupRow() = Preview {
    val iconToggleButtons = listOf(
        OdsIconToggleButtonsRowIcon(painterResource(id = android.R.drawable.ic_dialog_dialer), "Today"),
        OdsIconToggleButtonsRowIcon(painterResource(id = android.R.drawable.ic_dialog_email), "Day"),
        OdsIconToggleButtonsRowIcon(painterResource(id = android.R.drawable.ic_dialog_alert), "Month", false)
    )
    var selectedIndex by remember { mutableStateOf(0) }

    OdsIconToggleButtonsRow(
        icons = iconToggleButtons,
        selectedIndex = selectedIndex,
        onSelectedIndexChange = { index -> selectedIndex = index }
    )
}
