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

import androidx.compose.material.LeadingIconTab
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
import com.orange.ods.compose.component.content.OdsComponentIcon
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsTheme

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/513d27-tabs/b/50cb71" class="external" target="_blank">ODS tab</a>.
 *
 * An OdsLeadingIconTab is a Jetpack Compose [LeadingIconTab] with the Orange design and theme.
 * @see LeadingIconTab documentation
 *
 * This should typically be used inside of an [OdsTabRow].
 *
 * @param selected Controls whether the tab is selected or not.
 * @param icon [OdsLeadingIconTabIcon] displayed in the tab.
 * @param text Text label displayed in the tab.
 * @param onClick Callback invoked on tab click, when the tab is selected.
 * @param modifier [Modifier] applied to the tab.
 * @param enabled Controls the enabled state of the tab. When `false`, the tab will not
 * be clickable and will appear disabled to accessibility services.
 *
 * @see OdsTab
 */
@Composable
@OdsComposable
fun OdsLeadingIconTab(
    selected: Boolean,
    icon: OdsLeadingIconTabIcon,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    LeadingIconTab(
        modifier = modifier,
        icon = { icon.Content() },
        text = { Text(text = text.uppercase(), maxLines = 1, overflow = TextOverflow.Ellipsis, style = OdsTheme.typography.button) },
        selected = selected,
        selectedContentColor = OdsTheme.colors.component.tab.selectedContent,
        unselectedContentColor = OdsTheme.colors.component.tab.unselectedContent,
        onClick = onClick,
        enabled = enabled
    )
}

/**
 * An icon in an [OdsLeadingIconTab].
 * It is non-clickable and no content description is needed cause a leading icon tab always has a text inside.
 */
class OdsLeadingIconTabIcon : OdsComponentIcon<Nothing> {

    /**
     * Creates an instance of [OdsLeadingIconTab].
     *
     * @param painter Painter of the icon.
     */
    constructor(painter: Painter) : super(painter, "")

    /**
     * Creates an instance of [OdsLeadingIconTab].
     *
     * @param imageVector Image vector of the icon.
     */
    constructor(imageVector: ImageVector) : super(imageVector, "")

    /**
     * Creates an instance of [OdsLeadingIconTab].
     *
     * @param bitmap Image bitmap of the icon.
     */
    constructor(bitmap: ImageBitmap) : super(bitmap, "")

}

@UiModePreviews.Tab
@Composable
private fun PreviewOdsLeadingIconTab() = Preview {
    var selected by remember { mutableStateOf(false) }
    OdsLeadingIconTab(
        selected = selected,
        onClick = { selected = !selected },
        text = "Text",
        icon = OdsLeadingIconTabIcon(painterResource(id = android.R.drawable.ic_dialog_email))
    )
}
