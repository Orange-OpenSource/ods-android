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

import android.content.res.Configuration
import androidx.compose.material.Icon
import androidx.compose.material.LeadingIconTab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.theme.OdsTheme

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/513d27-tabs/b/50cb71" class="external" target="_blank">ODS tab</a>.
 *
 * An OdsLeadingIconTab is a Jetpack Compose [LeadingIconTab] with the Orange design and theme.
 * @see LeadingIconTab documentation
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
@OdsComponentApi
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
        text = { Text(text = text.uppercase(), maxLines = 1, overflow = TextOverflow.Ellipsis, style = OdsTheme.typography.button) },
        selected = selected,
        selectedContentColor = OdsTheme.colors.primary,
        unselectedContentColor = OdsTheme.colors.onSurface,
        onClick = onClick,
        enabled = enabled
    )
}

@Composable
private fun PreviewOdsLeadingIconTab() = Preview {
    val selected = remember { mutableStateOf(false) }
    OdsLeadingIconTab(
        selected = selected.value,
        onClick = { selected.value = !selected.value },
        text = "Text",
        icon = painterResource(id = android.R.drawable.ic_dialog_email)
    )
}

@Preview(
    name = "OdsLeadingIconTab - Light",
    widthDp = 100
)
@Composable
private fun PreviewOdsLeadingIconTabLight() = PreviewOdsLeadingIconTab()

@Preview(
    name = "OdsLeadingIconTab - Dark",
    widthDp = 100,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
private fun PreviewOdsLeadingIconTabDark() = PreviewOdsLeadingIconTab()
