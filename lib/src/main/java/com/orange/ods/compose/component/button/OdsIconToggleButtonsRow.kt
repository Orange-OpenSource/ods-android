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
import androidx.compose.runtime.mutableIntStateOf
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
import com.orange.ods.compose.component.content.OdsComponentContent
import com.orange.ods.compose.component.content.OdsComponentIcon
import com.orange.ods.compose.component.utilities.DisabledInteractionSource
import com.orange.ods.compose.component.utilities.OdsPreview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.component.utilities.selectionStateDescription
import com.orange.ods.compose.extension.enable
import com.orange.ods.compose.theme.OdsTheme

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/06a393-buttons/b/79b091" target="_blank">ODS Buttons</a>.
 *
 * A group of toggle buttons. Only one option in a group of toggle buttons can be selected and active at a time.
 * Selecting one option deselects any other.
 * @param selectedIconButtonIndex The index of the currently selected icon button.
 * @param iconButtons List of [OdsIconToggleButtonsRow.IconButton] displayed into the toggle group.
 * @param modifier [Modifier] applied to the toggle buttons group.
 */
@Composable
@OdsComposable
fun OdsIconToggleButtonsRow(
    selectedIconButtonIndex: Int,
    iconButtons: List<OdsIconToggleButtonsRow.IconButton>,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .selectableGroup()
            .border(
                width = 1.dp,
                color = buttonToggleBorderColor()
            )
    ) {
        iconButtons.forEachIndexed { index, iconButton ->
            iconButton.Content(extraParameters = OdsIconToggleButtonsRow.IconButton.ExtraParameters(index, selectedIconButtonIndex == index))
            if (index < iconButtons.size) {
                Divider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp),
                    color = buttonToggleBorderColor()
                )
            }
        }
    }
}

/**
 * Contains classes to build an [com.orange.ods.compose.component.button.OdsIconToggleButtonsRow].
 */
object OdsIconToggleButtonsRow {

    /**
     * An icon button in an [OdsIconToggleButtonsRow].
     */
    class IconButton private constructor(
        val graphicsObject: Any,
        val contentDescription: String,
        val onClick: () -> Unit,
        val enabled: Boolean = true
    ) : OdsComponentIcon<IconButton.ExtraParameters>(ExtraParameters::class.java, graphicsObject, contentDescription, enabled) {

        data class ExtraParameters internal constructor(
            internal val index: Int,
            internal val selected: Boolean
        ) : OdsComponentContent.ExtraParameters()

        /**
         * Creates an instance of [OdsIconToggleButtonsRow.IconButton].
         *
         * @param painter Painter of the icon button.
         * @param contentDescription The content description associated to this [OdsIconToggleButtonsRow.IconButton].
         * @param onClick Callback invoked when this icon button is selected.
         * @param enabled Whether or not this [OdsIconToggleButtonsRow.IconButton] will handle input events and appear enabled for
         * semantics purposes, true by default.
         */
        constructor(
            painter: Painter,
            contentDescription: String,
            onClick: () -> Unit,
            enabled: Boolean = true
        ) : this(painter as Any, contentDescription, onClick, enabled)

        /**
         * Creates an instance of [OdsIconToggleButtonsRow.IconButton].
         *
         * @param imageVector Image vector of the icon button.
         * @param contentDescription The content description associated to this [OdsIconToggleButtonsRow.IconButton].
         * @param onClick Callback invoked when this icon button is selected.
         * @param enabled Whether or not this [OdsIconToggleButtonsRow.IconButton] will handle input events and appear enabled for
         * semantics purposes, true by default.
         */
        constructor(
            imageVector: ImageVector,
            contentDescription: String,
            onClick: () -> Unit,
            enabled: Boolean = true
        ) : this(imageVector as Any, contentDescription, onClick, enabled)

        /**
         * Creates an instance of [OdsIconToggleButtonsRow.IconButton].
         *
         * @param bitmap Image bitmap of the icon button.
         * @param contentDescription The content description associated to this [OdsIconToggleButtonsRow.IconButton].
         * @param onClick Callback invoked when this icon button is selected.
         * @param enabled Whether or not this [OdsIconToggleButtonsRow.IconButton] will handle input events and appear enabled for
         * semantics purposes, true by default.
         */
        constructor(
            bitmap: ImageBitmap,
            contentDescription: String,
            onClick: () -> Unit,
            enabled: Boolean = true
        ) : this(bitmap as Any, contentDescription, onClick, enabled)

        override val tint: Color
            @Composable
            get() {
                return with(extraParameters) { getIconColor(selected, enabled) }
            }

        @Composable
        override fun Content(modifier: Modifier) {
            with(extraParameters) {
                val backgroundAlpha = if (selected && enabled) 0.12f else 0f
                val toggleIconStateDescription = selectionStateDescription(selected = selected)

                super.Content(
                    modifier
                        .background(color = buttonToggleBackgroundColor().copy(alpha = backgroundAlpha))
                        .padding(12.dp)
                        .run {
                            if (enabled) {
                                clickable(interactionSource = remember { DisabledInteractionSource() }, indication = null, onClick = onClick)
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
        private fun getIconColor(selected: Boolean, enabled: Boolean) =
            with(OdsTheme.colors) {
                if (selected && enabled) primary else onSurface.enable(enabled = enabled)
            }
    }

}

@UiModePreviews.Default
@Composable
private fun PreviewOdsIconToggleButtonsRow() = OdsPreview {
    var selectedIconButtonIndex by remember { mutableIntStateOf(0) }
    val iconButtons = listOf(
        OdsIconToggleButtonsRow.IconButton(painterResource(id = android.R.drawable.ic_dialog_dialer), "Today", { selectedIconButtonIndex = 0 }),
        OdsIconToggleButtonsRow.IconButton(painterResource(id = android.R.drawable.ic_dialog_email), "Day", { selectedIconButtonIndex = 1 }),
        OdsIconToggleButtonsRow.IconButton(painterResource(id = android.R.drawable.ic_dialog_alert), "Month", { selectedIconButtonIndex = 2 }, false)
    )

    OdsIconToggleButtonsRow(
        selectedIconButtonIndex = selectedIconButtonIndex,
        iconButtons = iconButtons
    )
}
