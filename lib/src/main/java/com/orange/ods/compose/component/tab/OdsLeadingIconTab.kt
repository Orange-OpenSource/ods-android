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

import androidx.compose.material.Icon
import androidx.compose.material.LeadingIconTab
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/513d27-tabs/b/50cb71" class="external" target="_blank">ODS tab</a>.
 *
 * Tabs organize content across different screens, data sets, and other interactions.
 *
 * A LeadingIconTab represents a single page of content using a text label and an icon in
 * front of the label.
 *
 * This should typically be used inside of an [OdsTabRow].
 *
 * @param selected whether this tab is selected or not
 * @param onClick the callback to be invoked when this tab is selected
 * @param text the text label displayed in this tab
 * @param icon the icon displayed in this tab
 * @param modifier optional [Modifier] for this tab
 * @param enabled controls the enabled state of this tab. When `false`, this tab will not
 * be clickable and will appear disabled to accessibility services.
 *
 * @see OdsTab
 */
@Composable
fun OdsLeadingIconTab(
    selected: Boolean,
    onClick: () -> Unit,
    text: String,
    icon: Painter,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    LeadingIconTab(
        modifier = modifier,
        icon = { Icon(painter = icon, contentDescription = null) },
        text = { Text(text) },
        selected = selected,
        selectedContentColor = MaterialTheme.colors.primary,
        unselectedContentColor = MaterialTheme.colors.onSurface,
        onClick = onClick,
        enabled = enabled
    )
}