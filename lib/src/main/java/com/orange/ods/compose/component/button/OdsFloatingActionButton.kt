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

import androidx.compose.foundation.layout.size
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.theme.OdsTheme

private val MiniFabSize = 40.dp

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/577022-buttons-fab/b/101b2a" target="_blank">ODS Floating Action Buttons</a>.
 *
 * A floating action button (FAB) represents the primary action of a screen.
 *
 * This FAB is typically used with an [Icon]
 * @see [OdsExtendedFloatingActionButton] for an extended FAB that contains text and an optional icon.
 *
 * @see androidx.compose.material.FloatingActionButton for further information.
 *
 * @param onClick callback invoked when this FAB is clicked
 * @param modifier [Modifier] to be applied to this FAB.
 * @param mini If `true`, the [MiniFabSize] will be applied, otherwise the default size will be used.
 * @param icon [Painter] of the FAB icon.
 * @param iconContentDescription [icon] content description.
 */
@Composable
@OdsComponentApi
fun OdsFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    mini: Boolean = false,
    icon: Painter,
    iconContentDescription: String
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier.let { if (mini) it.size(MiniFabSize) else it },
        backgroundColor = OdsTheme.colors.floatingActionButton.background,
        contentColor = OdsTheme.colors.floatingActionButton.content
    ) {
        Icon(painter = icon, contentDescription = iconContentDescription)
    }
}

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/577022-buttons-fab/b/101b2a" target="_blank">ODS Floating Action Buttons</a>.
 *
 * The extended FAB is wider than a regular FAB, and it includes a text label.
 *
 * This extended FAB contains text and an optional icon that will be placed at the start.
 * @see [OdsFloatingActionButton] for a FAB that just contains an icon.
 *
 * @see androidx.compose.material.ExtendedFloatingActionButton for further information.
 *
 * @param onClick callback invoked when this FAB is clicked
 * @param text The text displayed in the [OdsExtendedFloatingActionButton]
 * @param icon [Painter] of the FAB icon.
 * @param modifier [Modifier] to be applied to this FAB.
 * @param iconContentDescription [icon] content description, can be null cause there is a text.
 */
@Composable
@OdsComponentApi
fun OdsExtendedFloatingActionButton(
    onClick: () -> Unit,
    text: String,
    icon: Painter,
    modifier: Modifier = Modifier,
    iconContentDescription: String? = null
) {
    ExtendedFloatingActionButton(
        onClick = onClick,
        text = { Text(text = text.uppercase(), style = OdsTheme.typography.button) },
        modifier = modifier,
        backgroundColor = OdsTheme.colors.floatingActionButton.background,
        contentColor = OdsTheme.colors.floatingActionButton.content,
        icon = { Icon(painter = icon, contentDescription = iconContentDescription) }
    )
}