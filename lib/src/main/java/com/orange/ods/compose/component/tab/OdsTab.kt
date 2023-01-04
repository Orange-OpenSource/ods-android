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
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsTheme

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/513d27-tabs/b/50cb71" class="external" target="_blank">ODS tab</a>.
 *
 * An OdsTab is a Jetpack Compose [Tab] with the Orange design and theme.
 * @see Tab documentation
 *
 * This should typically be used inside of an [OdsTabRow].
 *
 * @param selected whether this tab is selected or not
 * @param onClick the callback to be invoked when this tab is selected
 * @param modifier optional [Modifier] for this tab
 * @param enabled controls the enabled state of this tab. When `false`, this tab will not
 * be clickable and will appear disabled to accessibility services.
 * @param text the text label displayed in this tab. Always displayed in uppercase.
 * @param icon the optional icon displayed in this tab
 *
 * @see OdsLeadingIconTab
 */
@Composable
@OdsComponentApi
fun OdsTab(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String? = null,
    icon: Painter? = null,
) {
    Tab(
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        icon = icon?.let { { Icon(painter = icon, contentDescription = null) } },
        text = text?.let { { Text(text.uppercase(), maxLines = 1, overflow = TextOverflow.Ellipsis, style = OdsTheme.typography.button) } },
        selectedContentColor = OdsTheme.colors.tab.selectedContent,
        unselectedContentColor = OdsTheme.colors.tab.unselectedContent,
    )
}

@UiModePreviews.Tab
@Composable
private fun PreviewOdsTab() = Preview {
    var selected by remember { mutableStateOf(false) }
    OdsTab(
        selected = selected,
        onClick = { selected = !selected },
        text = "Text",
        icon = painterResource(id = android.R.drawable.ic_dialog_email)
    )
}
