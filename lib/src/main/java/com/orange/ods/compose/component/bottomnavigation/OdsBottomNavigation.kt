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

package com.orange.ods.compose.component.bottomnavigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.content.OdsComponentIcon
import com.orange.ods.compose.component.content.OdsComponentScopeContent
import com.orange.ods.compose.component.divider.OdsDivider
import com.orange.ods.compose.component.utilities.OdsPreview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.text.OdsText
import com.orange.ods.compose.theme.OdsTheme

private const val MaxBottomNavigationItemCount = 5

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/042eb8-bottom-navigation/b/30078d" target="_blank">ODS Bottom navigation</a>.
 *
 * Bottom navigation bars allow movement between primary destinations in an app.
 *
 * OdsBottomNavigation should contain multiple [OdsBottomNavigation.Item]s, each representing a singular
 * destination.
 *
 * See [OdsBottomNavigation.Item] for configuration specific to each item, and not the overall
 * OdsBottomNavigation component.
 *
 * @param items List of [OdsBottomNavigation.Item] displayed into the bottom navigation.
 * @param modifier [Modifier] applied to the bottom navigation.
 */
@Composable
@OdsComposable
fun OdsBottomNavigation(
    items: List<OdsBottomNavigation.Item>,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier.focusProperties { canFocus = false },
        // Need to define backgroundColor cause in Compose default backgroundColor is primarySurface
        containerColor = OdsTheme.colors.components.bottomNavigation.barBackground,
        contentColor = OdsTheme.colors.components.bottomNavigation.barContent,
        tonalElevation = 0.dp, //TODO See with Design: should be a token.
        content = {
            Column {
                OdsDivider()
                Row {
                    items.take(MaxBottomNavigationItemCount).forEach { item ->
                        with(item) { this@NavigationBar.Content() }
                    }
                }
            }
        }
    )
}

/**
 * Contains classes to build a [com.orange.ods.compose.component.bottomnavigation.OdsBottomNavigation].
 */
object OdsBottomNavigation {

    /**
     * The recommended configuration for an OdsBottomNavigation item depends on how many items there are
     * inside an [OdsBottomNavigation]:
     *
     * - Three destinations: Display icons and text labels for all destinations.
     * - Four destinations: Active destinations display an icon and text label. Inactive destinations
     * display icons, and text labels are recommended.
     * - Five destinations: Active destinations display an icon and text label. Inactive destinations
     * use icons, and use text labels if space permits.
     *
     * An [OdsBottomNavigation.Item] always shows text labels (if it exists) when selected. Showing text
     * labels if not selected is controlled by [alwaysShowLabel].
     *
     * @param selected Controls whether this item is selected.
     * @param onClick Callback invoked when this item is selected.
     * @param icon Icon for this item.
     * @param enabled Controls the enabled state of this item. When `false`, this item will not
     * be clickable and will appear disabled to accessibility services.
     * @param label Text label for this item.
     * @param alwaysShowLabel Controls whether the label for this item should always be shown. If `false`, the label will
     * only be shown when this item is selected.
     */
    class Item(
        val selected: Boolean,
        val onClick: () -> Unit,
        val icon: Icon,
        val enabled: Boolean = true,
        val label: String? = null,
        val alwaysShowLabel: Boolean = true
    ) : OdsComponentScopeContent<RowScope, Nothing>(Nothing::class.java) {

        @Composable
        override fun RowScope.Content(modifier: Modifier) {
            val selectedItemContentColor = OdsTheme.colors.components.bottomNavigation.itemSelected
            val unselectedItemContentColor = OdsTheme.colors.components.bottomNavigation.itemUnselected
            this@Content.NavigationBarItem(
                selected = selected,
                onClick = onClick,
                icon = { icon.Content() },
                enabled = enabled,
                label = label?.let {
                    {
                        OdsText(
                            text = label,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = OdsTheme.typography.bodySmall
                        )
                    }
                },
                alwaysShowLabel = alwaysShowLabel,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = selectedItemContentColor,
                    selectedTextColor = selectedItemContentColor,
                    unselectedIconColor = unselectedItemContentColor,
                    unselectedTextColor = unselectedItemContentColor,
                    indicatorColor = OdsTheme.colors.components.bottomNavigation.itemSelectedIndicator
                ),
            )
        }

        /**
         * An icon in an [OdsBottomNavigation.Item].
         */
        class Icon private constructor(
            val graphicsObject: Any,
            val contentDescription: String
        ) : OdsComponentIcon<Nothing>(Nothing::class.java, graphicsObject, contentDescription) {

            /**
             * Creates an instance of [OdsBottomNavigation.Item.Icon].
             *
             * @param painter Painter of the icon.
             * @param contentDescription The content description associated to this [OdsBottomNavigation.Item.Icon].
             */
            constructor(painter: Painter, contentDescription: String) : this(painter as Any, contentDescription)

            /**
             * Creates an instance of [OdsBottomNavigation.Item.Icon].
             *
             * @param imageVector Image vector of the icon.
             * @param contentDescription The content description associated to this [OdsBottomNavigation.Item.Icon].
             */
            constructor(imageVector: ImageVector, contentDescription: String) : this(imageVector as Any, contentDescription)

            /**
             * Creates an instance of [OdsBottomNavigation.Item.Icon].
             *
             * @param bitmap Image bitmap of the icon.
             * @param contentDescription The content description associated to this [OdsBottomNavigation.Item.Icon].
             */
            constructor(bitmap: ImageBitmap, contentDescription: String) : this(bitmap as Any, contentDescription)
        }
    }

}

@UiModePreviews.Default
@Composable
private fun PreviewOdsBottomNavigation() = OdsPreview {
    val items = listOf(
        android.R.drawable.ic_dialog_email to "First item",
        android.R.drawable.ic_dialog_map to "Second item",
        android.R.drawable.ic_dialog_dialer to "Third item",
        android.R.drawable.ic_dialog_info to "About"
    )

    var selectedItemIndex by remember { mutableIntStateOf(0) }
    OdsBottomNavigation(
        items = items.mapIndexed { index, item ->
            OdsBottomNavigation.Item(
                icon = OdsBottomNavigation.Item.Icon(painter = painterResource(id = item.first), contentDescription = ""),
                label = item.second,
                selected = selectedItemIndex == index,
                onClick = { selectedItemIndex = index }
            )
        }
    )
}
