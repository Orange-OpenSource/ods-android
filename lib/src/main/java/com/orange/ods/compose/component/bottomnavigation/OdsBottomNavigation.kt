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

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.content.OdsComponentScopeBuilder
import com.orange.ods.compose.component.content.OdsIconBuilder
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsTheme

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/042eb8-bottom-navigation/b/30078d" target="_blank">ODS Bottom navigation</a>.
 *
 * Bottom navigation bars allow movement between primary destinations in an app.
 *
 * OdsBottomNavigation should contain multiple [OdsBottomNavigationItemBuilder]s, each representing a singular
 * destination.
 *
 * See [OdsBottomNavigationItemBuilder] for configuration specific to each item, and not the overall
 * OdsBottomNavigation component.
 *
 * @param items List of [OdsBottomNavigationItemBuilder] displayed into the bottom navigation.
 * @param modifier [Modifier] applied to the bottom navigation.
 */
@Composable
@OdsComposable
fun OdsBottomNavigation(
    items: List<OdsBottomNavigationItemBuilder>,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        modifier = modifier,
        // Need to define backgroundColor cause in Compose default backgroundColor is primarySurface
        backgroundColor = OdsTheme.colors.component.bottomNavigation.barBackground,
        contentColor = OdsTheme.colors.component.bottomNavigation.barContent,
        content = { items.forEach { with(it) { Content() } } }
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
 * @param selected whether this item is selected.
 * @param onClick the callback to be invoked when this item is selected.
 * @param icon icon for this item.
 * @param enabled controls the enabled state of this item. When `false`, this item will not
 * be clickable and will appear disabled to accessibility services.
 * @param label optional text label for this item.
 * @param alwaysShowLabel whether to always show the label for this item. If false, the label will
 * only be shown when this item is selected.
 */
class OdsBottomNavigationItemBuilder(
    val selected: Boolean,
    val onClick: () -> Unit,
    val icon: OdsBottomNavigationItemIconBuilder,
    val enabled: Boolean = true,
    val label: String? = null,
    val alwaysShowLabel: Boolean = true
) : OdsComponentScopeBuilder<RowScope, Nothing>() {

    @Composable
    override fun RowScope.Content(modifier: Modifier) {
        BottomNavigationItem(
            selected = selected,
            onClick = onClick,
            icon = { icon.Content() },
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
            selectedContentColor = OdsTheme.colors.component.bottomNavigation.itemSelected,
            unselectedContentColor = OdsTheme.colors.component.bottomNavigation.itemUnselected
        )
    }
}

/**
 * An icon in an [OdsBottomNavigationItemBuilder].
 */
class OdsBottomNavigationItemIconBuilder : OdsIconBuilder<Nothing> {

    /**
     * Creates an instance of [OdsBottomNavigationItemIconBuilder].
     *
     * @param painter Painter of the icon.
     * @param contentDescription The content description associated to this [OdsBottomNavigationItemIconBuilder].
     */
    constructor(painter: Painter, contentDescription: String) : super(painter, contentDescription)

    /**
     * Creates an instance of [OdsBottomNavigationItemIconBuilder].
     *
     * @param imageVector Image vector of the icon.
     * @param contentDescription The content description associated to this [OdsBottomNavigationItemIconBuilder].
     */
    constructor(imageVector: ImageVector, contentDescription: String) : super(imageVector, contentDescription)

    /**
     * Creates an instance of [OdsBottomNavigationItemIconBuilder].
     *
     * @param bitmap Image bitmap of the icon.
     * @param contentDescription The content description associated to this [OdsBottomNavigationItemIconBuilder].
     */
    constructor(bitmap: ImageBitmap, contentDescription: String) : super(bitmap, contentDescription)
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsBottomNavigation() = Preview {
    val items = listOf(
        android.R.drawable.ic_dialog_email to "First item",
        android.R.drawable.ic_dialog_map to "Second item",
        android.R.drawable.ic_dialog_dialer to "Third item",
        android.R.drawable.ic_dialog_info to "Fourth item"
    )

    var selectedItemIndex by remember { mutableStateOf(0) }
    OdsBottomNavigation(
        items = items.mapIndexed { index, item ->
            OdsBottomNavigationItemBuilder(
                icon = OdsBottomNavigationItemIconBuilder(painter = painterResource(id = item.first), contentDescription = ""),
                label = item.second,
                selected = selectedItemIndex == index,
                onClick = { selectedItemIndex = index }
            )
        }
    )
}
