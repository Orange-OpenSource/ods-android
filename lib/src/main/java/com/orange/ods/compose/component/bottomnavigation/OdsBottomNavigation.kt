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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.selection.selectable
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.content.OdsComponentIcon
import com.orange.ods.compose.component.content.OdsComponentScopeContent
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsTheme

private const val BottomNavigationItemMaxCount = 5

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
    BottomNavigation(
        modifier = modifier,
        // Need to define backgroundColor cause in Compose default backgroundColor is primarySurface
        backgroundColor = OdsTheme.colors.component.bottomNavigation.barBackground,
        contentColor = OdsTheme.colors.component.bottomNavigation.barContent,
        content = {
            items.take(BottomNavigationItemMaxCount).forEach { item ->
                with(item) { this@BottomNavigation.Content() }
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
    ) : OdsComponentScopeContent<RowScope, Nothing>() {

        @Composable
        override fun RowScope.Content(modifier: Modifier) {
            val itemPadding = dimensionResource(id = R.dimen.spacing_s)
            val selectedLineHeight = dimensionResource(id = R.dimen.spacing_xs)
            
            ConstraintLayout(
                modifier = modifier
                    .weight(1f)
                    .selectable(
                        selected = selected,
                        onClick = onClick,
                        enabled = enabled,
                        role = Role.Tab
                    )
            ) {
                val (itemRef, lineRef) = createRefs()
                this@Content.BottomNavigationItem(
                    modifier = Modifier.constrainAs(itemRef) {
                        centerTo(parent)
                    },
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

                // Visual alternative for selected item (a11y)
                this@Content.AnimatedVisibility(
                    modifier = Modifier
                        .constrainAs(lineRef) {
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start, itemPadding)
                            end.linkTo(parent.end, itemPadding)
                            height = Dimension.value(selectedLineHeight)
                            width = Dimension.fillToConstraints
                        },
                    visible = selected,
                    enter = fadeIn() + slideInVertically { it },
                    exit = fadeOut() + slideOutVertically { it }
                ) {
                    Box(modifier = Modifier.background(OdsTheme.colors.component.bottomNavigation.itemSelected))
                }
            }
        }

        /**
         * An icon in an [OdsBottomNavigation.Item].
         */
        class Icon : OdsComponentIcon<Nothing> {

            /**
             * Creates an instance of [OdsBottomNavigation.Item.Icon].
             *
             * @param painter Painter of the icon.
             * @param contentDescription The content description associated to this [OdsBottomNavigation.Item.Icon].
             */
            constructor(painter: Painter, contentDescription: String) : super(painter, contentDescription)

            /**
             * Creates an instance of [OdsBottomNavigation.Item.Icon].
             *
             * @param imageVector Image vector of the icon.
             * @param contentDescription The content description associated to this [OdsBottomNavigation.Item.Icon].
             */
            constructor(imageVector: ImageVector, contentDescription: String) : super(imageVector, contentDescription)

            /**
             * Creates an instance of [OdsBottomNavigation.Item.Icon].
             *
             * @param bitmap Image bitmap of the icon.
             * @param contentDescription The content description associated to this [OdsBottomNavigation.Item.Icon].
             */
            constructor(bitmap: ImageBitmap, contentDescription: String) : super(bitmap, contentDescription)
        }
    }

}

@UiModePreviews.Default
@Composable
private fun PreviewOdsBottomNavigation() = Preview {
    val items = listOf(
        android.R.drawable.ic_dialog_email to "First item",
        android.R.drawable.ic_dialog_map to "Second item",
        android.R.drawable.ic_dialog_dialer to "Third item",
        android.R.drawable.ic_dialog_info to "About"
    )

    var selectedItemIndex by remember { mutableStateOf(0) }
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
