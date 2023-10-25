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

import androidx.compose.material.Tab
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
 * An OdsTab is a Jetpack Compose [Tab] with the Orange design and theme.
 * @see Tab documentation
 *
 * This should typically be used inside of an [OdsTabRow].
 *
 * @param selected Controls whether this tab is selected or not.
 * @param onClick Callback invoked on tab click, when this tab is selected.
 * @param modifier [Modifier] applied to this tab.
 * @param enabled Controls the enabled state of this tab. When `false`, this tab will not
 * be clickable and will appear disabled to accessibility services.
 * @param text Label displayed in this tab. Always displayed in uppercase.
 * @param icon Icon displayed in this tab.
 *
 * @see OdsLeadingIconTab
 */
@Composable
@OdsComposable
fun OdsTab(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String? = null,
    icon: OdsTabIcon? = null,
) {
    Tab(
        selected = selected,
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        icon = icon?.let { { it.Content() } },
        text = text?.let { { Text(text.uppercase(), maxLines = 1, overflow = TextOverflow.Ellipsis, style = OdsTheme.typography.button) } },
        selectedContentColor = OdsTheme.colors.component.tab.selectedContent,
        unselectedContentColor = OdsTheme.colors.component.tab.unselectedContent,
    )
}

/**
 * An icon in an [OdsTab].
 * It is non-clickable and the content description is optional cause a tab can have a label.
 * Note that for accessibility, if the tab has no text, it is highly recommended to set a content description.
 */
class OdsTabIcon : OdsComponentIcon<Nothing> {

    /**
     * Creates an instance of [OdsTabIcon].
     *
     * @param painter Painter of the icon.
     * @param contentDescription The content description associated to this [OdsTabIcon].
     */
    constructor(painter: Painter, contentDescription: String = "") : super(painter, contentDescription)

    /**
     * Creates an instance of [OdsTabIcon].
     *
     * @param imageVector Image vector of the icon.
     * @param contentDescription The content description associated to this [OdsTabIcon].
     */
    constructor(imageVector: ImageVector, contentDescription: String = "") : super(imageVector, contentDescription)

    /**
     * Creates an instance of [OdsTabIcon].
     *
     * @param bitmap Image bitmap of the icon.
     * @param contentDescription The content description associated to this [OdsTabIcon].
     */
    constructor(bitmap: ImageBitmap, contentDescription: String = "") : super(bitmap, contentDescription)

}

@UiModePreviews.Tab
@Composable
private fun PreviewOdsTab() = Preview {
    var selected by remember { mutableStateOf(false) }
    OdsTab(
        selected = selected,
        onClick = { selected = !selected },
        text = "Text",
        icon = OdsTabIcon(painterResource(id = android.R.drawable.ic_dialog_email))
    )
}
