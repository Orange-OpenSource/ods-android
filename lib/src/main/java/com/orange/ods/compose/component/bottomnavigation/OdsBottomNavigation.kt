/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.bottomnavigation

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.theme.OdsTheme

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/042eb8-bottom-navigation/b/30078d" target="_blank">ODS Bottom navigation</a>.
 *
 * Bottom navigation bars allow movement between primary destinations in an app.
 *
 * OdsBottomNavigation should contain multiple [OdsBottomNavigationItem]s, each representing a singular
 * destination.
 *
 * See [OdsBottomNavigationItem] for configuration specific to each item, and not the overall
 * OdsBottomNavigation component.
 *
 * @param modifier optional [Modifier] for this OdsBottomNavigation
 * @param content destinations inside this OdsBottomNavigation, this should contain multiple
 * [OdsBottomNavigationItem]s
 */
@Composable
@OdsComponentApi
fun OdsBottomNavigation(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    BottomNavigation(
        modifier = modifier,
        // Need to define backgroundColor cause in Compose default backgroundColor is primarySurface
        backgroundColor = OdsTheme.colors.surface,
        content = content
    )
}

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/042eb8-bottom-navigation/b/30078d" target="_blank">ODS Bottom navigation</a>.
 *
 * The recommended configuration for an OdsBottomNavigationItem depends on how many items there are
 * inside an [OdsBottomNavigation]:
 *
 * - Three destinations: Display icons and text labels for all destinations.
 * - Four destinations: Active destinations display an icon and text label. Inactive destinations
 * display icons, and text labels are recommended.
 * - Five destinations: Active destinations display an icon and text label. Inactive destinations
 * use icons, and use text labels if space permits.
 *
 * An OdsBottomNavigationItem always shows text labels (if it exists) when selected. Showing text
 * labels if not selected is controlled by [alwaysShowLabel].
 *
 * @param selected whether this item is selected
 * @param onClick the callback to be invoked when this item is selected
 * @param icon icon for this item, typically this will be an [Icon]
 * @param modifier optional [Modifier] for this item
 * @param enabled controls the enabled state of this item. When `false`, this item will not
 * be clickable and will appear disabled to accessibility services.
 * @param label optional text label for this item
 * @param alwaysShowLabel whether to always show the label for this item. If false, the label will
 * only be shown when this item is selected.
 */
@Composable
@OdsComponentApi
fun RowScope.OdsBottomNavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String?,
    alwaysShowLabel: Boolean = true
) {
    BottomNavigationItem(
        selected = selected,
        onClick = onClick,
        icon = icon,
        modifier = modifier,
        enabled = enabled,
        label = label?.let {
            {
                Text(
                    text = label,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = OdsTheme.typography.caption
                )
            }
        },
        alwaysShowLabel = alwaysShowLabel,
        // Need to define these attributes cause they are not taken from style
        selectedContentColor = OdsTheme.colors.primary,
        unselectedContentColor = OdsTheme.colors.onSurface
    )
}

@Composable
private fun PreviewOdsBottomNavigation() = Preview {
    data class Item(@DrawableRes val iconResId: Int, val label: String)

    val items = listOf(
        Item(android.R.drawable.ic_dialog_email, "First item"),
        Item(android.R.drawable.ic_dialog_map, "Second item"),
        Item(android.R.drawable.ic_dialog_dialer, "Third item"),
        Item(android.R.drawable.ic_dialog_info, "Fourth item")
    )

    val selectedItemIndex = remember { mutableStateOf(0) }
    OdsBottomNavigation {
        items.forEachIndexed { index, item ->
            OdsBottomNavigationItem(
                icon = { Icon(painter = painterResource(id = item.iconResId), contentDescription = null) },
                label = item.label,
                selected = selectedItemIndex.value == index,
                onClick = { selectedItemIndex.value = index }
            )
        }
    }
}

@Preview(name = "OdsBottomNavigation - Light")
@Composable
private fun PreviewOdsBottomNavigationLight() = PreviewOdsBottomNavigation()

@Preview(
    name = "OdsBottomNavigation - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
private fun PreviewOdsBottomNavigationDark() = PreviewOdsBottomNavigation()
