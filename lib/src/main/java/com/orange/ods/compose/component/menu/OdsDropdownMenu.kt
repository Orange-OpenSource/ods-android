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

package com.orange.ods.compose.component.menu

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.content.OdsComponentContent
import com.orange.ods.compose.component.divider.OdsDivider
import com.orange.ods.compose.component.icon.OdsIcon
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.text.OdsText
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.theme.typography.OdsTextStyle

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/07a69b-menus/b/862cbb" class="external" target="_blank">ODS menus</a>.
 *
 * @see androidx.compose.material.DropdownMenu
 *
 * @param items List of [OdsDropdownMenu.Item] displayed into the dropdown menu.
 * @param expanded Controls whether the menu is currently open and visible to the user.
 * @param onDismissRequest Callback invoked when the user requests to dismiss the menu, such as by tapping outside the menu's bounds.
 * @param modifier [Modifier] applied to the dropdown menu.
 * @param offset [DpOffset] added to the menu position.
 * @param properties [PopupProperties] for further customization of the popup's behavior.
 */
@OdsComposable
@Composable
fun OdsDropdownMenu(
    items: List<OdsDropdownMenu.Item>,
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    offset: DpOffset = DpOffset(0.dp, 0.dp),
    properties: PopupProperties = PopupProperties(focusable = true)
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
        modifier = modifier.background(OdsTheme.colors.surface),
        offset = offset,
        properties = properties,
        content = { items.forEach { it.Content(OdsDropdownMenu.Item.ExtraParameters(onDismissRequest)) } }
    )
}

/**
 * Contains classes to build an [com.orange.ods.compose.component.menu.OdsDropdownMenu].
 */
object OdsDropdownMenu {

    /**
     * @see androidx.compose.material.DropdownMenuItem
     *
     * An item displayed in a [OdsDropdownMenu].
     *
     * @property text The text of the menu item
     * @property icon Optional icon to display in the menu item
     * @property enabled Controls the enabled state of the menu item - when `false`, the menu item
     * @property divider Whether or not a divider is displayed at the bottom of the menu item
     * @property onClick Called when the menu item was clicked
     */
    class Item private constructor(
        private val text: String,
        private val icon: Any?,
        private val enabled: Boolean,
        private val divider: Boolean,
        private val onClick: () -> Unit
    ) : OdsComponentContent<Item.ExtraParameters>() {

        data class ExtraParameters(val onDismissRequest: () -> Unit) : OdsComponentContent.ExtraParameters()

        /**
         * Creates an instance of [OdsDropdownMenu.Item].
         *
         * @param text The text of the menu item.
         * @param enabled Controls the enabled state of the menu item - when `false`, the menu item.
         * @param divider Whether or not a divider is displayed at the bottom of the menu item.
         * @param onClick Called when the menu item was clicked.
         */
        constructor(
            text: String,
            enabled: Boolean = true,
            divider: Boolean = false,
            onClick: () -> Unit
        ) : this(text, null as Any?, enabled, divider, onClick)

        /**
         * Creates an instance of [OdsDropdownMenu.Item].
         *
         * @param text The text of the menu item.
         * @param icon Optional icon to display in the menu item.
         * @param enabled Controls the enabled state of the menu item - when `false`, the menu item.
         * @param divider Whether or not a divider is displayed at the bottom of the menu item.
         * @param onClick Called when the menu item was clicked.
         */
        constructor(
            text: String,
            icon: Painter? = null,
            enabled: Boolean = true,
            divider: Boolean = false,
            onClick: () -> Unit
        ) : this(text, icon as Any?, enabled, divider, onClick)

        /**
         * Creates an instance of [OdsDropdownMenu.Item].
         *
         * @param text The text of the menu item.
         * @param icon Optional icon to display in the menu item.
         * @param enabled Controls the enabled state of the menu item - when `false`, the menu item.
         * @param divider Whether or not a divider is displayed at the bottom of the menu item.
         * @param onClick Called when the menu item was clicked.
         */
        constructor(
            text: String,
            icon: ImageVector? = null,
            enabled: Boolean = true,
            divider: Boolean = false,
            onClick: () -> Unit
        ) : this(text, icon as Any?, enabled, divider, onClick)

        /**
         * Creates an instance of [OdsDropdownMenu.Item].
         *
         * @param text The text of the menu item.
         * @param icon Optional icon to display in the menu item.
         * @param enabled Controls the enabled state of the menu item - when `false`, the menu item.
         * @param divider Whether or not a divider is displayed at the bottom of the menu item.
         * @param onClick Called when the menu item was clicked.
         */
        constructor(
            text: String,
            icon: Bitmap? = null,
            enabled: Boolean = true,
            divider: Boolean = false,
            onClick: () -> Unit
        ) : this(text, icon as Any?, enabled, divider, onClick)

        @Composable
        override fun Content(modifier: Modifier) {
            DropdownMenuItem(
                modifier = modifier,
                onClick = {
                    onClick()
                    extraParameters.onDismissRequest()
                },
                enabled = enabled
            ) {
                icon?.let {
                    OdsIcon(
                        graphicsObject = icon,
                        contentDescription = "",
                        tint = OdsTheme.colors.onSurface,
                        enabled = enabled,
                        modifier = Modifier.padding(end = dimensionResource(id = R.dimen.spacing_m)),
                    )
                }
                OdsText(text = text, style = OdsTextStyle.BodyL, enabled = enabled)
            }
            if (divider) OdsDivider()
        }
    }
}

/**
 * Note: Please use Android Studio preview interactive mode to see the OdsDropdownMenu preview cause expanded is a target state.
 */
@UiModePreviews.Default
@Composable
private fun PreviewOdsDropdownMenu() = Preview {
    val items = listOf(
        OdsDropdownMenu.Item("First menu item", painterResource(id = android.R.drawable.ic_dialog_email)) {},
        OdsDropdownMenu.Item("Second menu item", painterResource(id = android.R.drawable.ic_dialog_map), divider = true) {},
        OdsDropdownMenu.Item("Third menu item", painterResource(id = android.R.drawable.ic_dialog_dialer)) {},
        OdsDropdownMenu.Item("Fourth menu item", painterResource(id = android.R.drawable.ic_dialog_info)) {}
    )
    OdsDropdownMenu(items = items, expanded = true, onDismissRequest = {})
}
