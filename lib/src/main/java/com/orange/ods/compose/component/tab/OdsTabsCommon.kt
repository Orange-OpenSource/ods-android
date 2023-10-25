/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.tab

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector


/**
 * A tab in an [OdsTabRow] or an [OdsScrollableTabRow].
 *
 * @constructor Creates an instance of [OdsTabRowTab].
 */
class OdsTabRowTab(private val graphicsObject: Any?, private val text: String?, private val selected: Boolean, private val onClick: () -> Unit) {

    /**
     * Creates an instance of [OdsTabRowTab].
     *
     * @param painter Painter of the tab icon.
     * @param text Text displayed in the tab.
     * @param selected Controls whether this tab is selected or not.
     * @param onClick Callback invoked on tab click.
     */
    constructor(painter: Painter?, text: String?, selected: Boolean, onClick: () -> Unit) : this(painter as Any?, text, selected, onClick)

    /**
     * Creates an instance of [OdsTabRowTab].
     *
     * @param imageVector Image vector of the tab icon.
     * @param text Text displayed in the tab.
     * @param selected Controls whether this tab is selected or not.
     * @param onClick Callback invoked on tab click.
     */
    constructor(imageVector: ImageVector, text: String?, selected: Boolean, onClick: () -> Unit) : this(imageVector as Any, text, selected, onClick)

    /**
     * Creates an instance of [OdsTabRowTab].
     *
     * @param bitmap Image bitmap of the icon.
     * @param text Text displayed in the tab.
     * @param selected Controls whether this tab is selected or not.
     * @param onClick Callback invoked on tab click.
     * */
    constructor(bitmap: ImageBitmap, text: String?, selected: Boolean, onClick: () -> Unit) : this(bitmap as Any?, text, selected, onClick)

    @Composable
    fun Content(leadingIconTabs: Boolean) {
        val icon = when (graphicsObject) {
            is Painter -> {
                if (leadingIconTabs) OdsLeadingIconTabIcon(painter = graphicsObject) else OdsTabIcon(painter = graphicsObject)
            }
            is ImageVector -> {
                if (leadingIconTabs) OdsLeadingIconTabIcon(imageVector = graphicsObject) else OdsTabIcon(imageVector = graphicsObject)
            }
            is ImageBitmap -> {
                if (leadingIconTabs) OdsLeadingIconTabIcon(bitmap = graphicsObject) else OdsTabIcon(bitmap = graphicsObject)
            }
            else -> null
        }

        if (leadingIconTabs && text != null && icon is OdsLeadingIconTabIcon) {
            OdsLeadingIconTab(selected = selected, icon = icon, text = text, onClick = onClick)
        } else {
            OdsTab(selected = selected, onClick = onClick, icon = icon as? OdsTabIcon, text = text)
        }
    }

}