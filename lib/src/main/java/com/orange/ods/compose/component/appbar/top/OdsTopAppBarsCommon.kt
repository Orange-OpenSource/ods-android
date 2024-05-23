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

package com.orange.ods.compose.component.appbar.top

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.orange.ods.R
import com.orange.ods.compose.component.content.OdsComponentIcon
import com.orange.ods.compose.component.menu.OdsDropdownMenu
import com.orange.ods.compose.theme.OdsTheme

@Composable
internal fun OdsTopAppBarActions(actions: List<OdsTopAppBar.ActionButton>, overflowMenuItems: List<OdsDropdownMenu.Item>) {
    val maxTotalActionCount = 3
    val maxActionCount = if (overflowMenuItems.isNotEmpty()) maxTotalActionCount - 1 else maxTotalActionCount
    actions.take(maxActionCount).forEach { it.Content() }
    if (overflowMenuItems.isNotEmpty()) {
        Box {
            var showMenu by remember { mutableStateOf(false) }
            val contentDescription = stringResource(id = R.string.ods_topAppBar_overflowMenu_labelA11y)
            val dropdownMenuAction = OdsTopAppBar.ActionButton(Icons.Filled.MoreVert, contentDescription, true) { showMenu = !showMenu }
            dropdownMenuAction.Content()
            OdsDropdownMenu(
                items = overflowMenuItems,
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            )
        }
    }
}

/**
 * Contains classes to build a [com.orange.ods.compose.component.appbar.top.OdsTopAppBar] or a [com.orange.ods.compose.component.appbar.top.OdsLargeTopAppBar].
 */
object OdsTopAppBar {

    /**
     * A navigation icon in an OdsTopAppBar.
     */
    class NavigationIcon private constructor(
        val graphicsObject: Any,
        val contentDescription: String,
        val onClick: () -> Unit
    ) : OdsComponentIcon<Nothing>(Nothing::class.java, graphicsObject, contentDescription, onClick = onClick) {

        /**
         * Creates an instance of [OdsTopAppBar.NavigationIcon].
         *
         * @param painter Painter of the icon.
         * @param contentDescription The content description associated to this [OdsTopAppBar.NavigationIcon].
         * @param onClick Will be called when the user clicks on the action icon button.
         */
        constructor(painter: Painter, contentDescription: String, onClick: () -> Unit) : this(painter as Any, contentDescription, onClick)

        /**
         * Creates an instance of [OdsTopAppBar.NavigationIcon].
         *
         * @param imageVector Image vector of the icon.
         * @param contentDescription The content description associated to this [OdsTopAppBar.NavigationIcon].
         * @param onClick Will be called when the user clicks on the action icon button.
         */
        constructor(imageVector: ImageVector, contentDescription: String, onClick: () -> Unit) : this(imageVector as Any, contentDescription, onClick)

        /**
         * Creates an instance of [OdsTopAppBar.NavigationIcon].
         *
         * @param bitmap Image bitmap of the icon.
         * @param contentDescription The content description associated to this [OdsTopAppBar.NavigationIcon].
         * @param onClick Will be called when the user clicks on the action icon button.
         */
        constructor(bitmap: ImageBitmap, contentDescription: String, onClick: () -> Unit) : this(bitmap as Any, contentDescription, onClick)

        override val tint: Color?
            @Composable
            get() = OdsTheme.colors.components.topAppBar.content
    }

    /**
     * An action button displayed in an [OdsTopAppBar].
     */
    class ActionButton private constructor(
        val graphicsObject: Any,
        val contentDescription: String,
        val enabled: Boolean = true,
        val onClick: () -> Unit
    ) : OdsComponentIcon<Nothing>(Nothing::class.java, graphicsObject, contentDescription, enabled, onClick) {

        /**
         * Creates an instance of [OdsTopAppBar.ActionButton].
         *
         * @param painter Painter of the icon.
         * @param contentDescription The content description associated to this [OdsTopAppBar.ActionButton].
         * @param enabled whether or not this [OdsTopAppBar.ActionButton] will handle input events and appear enabled for
         * semantics purposes, true by default.
         * @param onClick Will be called when the user clicks on the action icon button.
         */
        constructor(
            painter: Painter,
            contentDescription: String,
            enabled: Boolean = true,
            onClick: () -> Unit
        ) : this(painter as Any, contentDescription, enabled, onClick)

        /**
         * Creates an instance of [OdsTopAppBar.ActionButton].
         *
         * @param imageVector Image vector of the icon.
         * @param contentDescription The content description associated to this [OdsTopAppBar.ActionButton].
         * @param enabled whether or not this [OdsTopAppBar.ActionButton] will handle input events and appear enabled for
         * semantics purposes, true by default.
         * @param onClick Will be called when the user clicks on the action icon button.
         */
        constructor(
            imageVector: ImageVector,
            contentDescription: String,
            enabled: Boolean = true,
            onClick: () -> Unit
        ) : this(imageVector as Any, contentDescription, enabled, onClick)

        /**
         * Creates an instance of [OdsTopAppBar.ActionButton].
         *
         * @param bitmap Image bitmap of the icon.
         * @param contentDescription The content description associated to this [OdsTopAppBar.ActionButton].
         * @param enabled whether or not this [OdsTopAppBar.ActionButton] will handle input events and appear enabled for
         * semantics purposes, true by default.
         * @param onClick Will be called when the user clicks on the action icon button.
         */
        constructor(
            bitmap: ImageBitmap,
            contentDescription: String,
            enabled: Boolean = true,
            onClick: () -> Unit
        ) : this(bitmap as Any, contentDescription, enabled, onClick)

        override val tint: Color
            @Composable
            get() = OdsTheme.colors.components.topAppBar.content
    }

}
