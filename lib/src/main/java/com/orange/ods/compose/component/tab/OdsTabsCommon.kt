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
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import com.orange.ods.compose.component.content.OdsComponentContent
import com.orange.ods.compose.component.content.OdsComponentIcon
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.compose.utilities.extension.enable


sealed interface OdsTabRowTab

class OdsTab(
    private val graphicsObject: Any?,
    private val text: String?,
    private val selected: Boolean,
    private val enabled: Boolean,
    private val onClick: () -> Unit
) : OdsComponentContent<Nothing>(), OdsTabRowTab {

    /**
     * Creates an instance of [OdsTab].
     *
     * @param painter Painter of the tab icon.
     * @param text Text displayed in the tab.
     * @param selected Controls whether this tab is selected or not.
     * @param enabled Controls whether this tab is enabled or not.
     * @param onClick Callback invoked on tab click.
     */
    constructor(painter: Painter?, text: String?, selected: Boolean, enabled: Boolean = true, onClick: () -> Unit) : this(
        painter as Any?,
        text,
        selected,
        enabled,
        onClick
    )

    /**
     * Creates an instance of [OdsTab].
     *
     * @param imageVector Image vector of the tab icon.
     * @param text Text displayed in the tab.
     * @param selected Controls whether this tab is selected or not.
     * @param enabled Controls whether this tab is enabled or not.
     * @param onClick Callback invoked on tab click.
     */
    constructor(imageVector: ImageVector, text: String?, selected: Boolean, enabled: Boolean = true, onClick: () -> Unit) : this(
        imageVector as Any,
        text,
        selected,
        enabled,
        onClick
    )

    /**
     * Creates an instance of [OdsTab].
     *
     * @param bitmap Image bitmap of the icon.
     * @param text Text displayed in the tab.
     * @param selected Controls whether this tab is selected or not.
     * @param enabled Controls whether this tab is enabled or not.
     * @param onClick Callback invoked on tab click.
     * */
    constructor(bitmap: ImageBitmap, text: String?, selected: Boolean, enabled: Boolean = true, onClick: () -> Unit) : this(
        bitmap as Any?,
        text,
        selected,
        enabled,
        onClick
    )

    @Composable
    override fun Content(modifier: Modifier) {
        val icon = when (graphicsObject) {
            is Painter -> OdsTabIcon(painter = graphicsObject)
            is ImageVector -> OdsTabIcon(imageVector = graphicsObject)
            is ImageBitmap -> OdsTabIcon(bitmap = graphicsObject)
            else -> null
        }
        Tab(
            selected = selected,
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            icon = icon?.let { { it.Content() } },
            text = text?.let { { Text(text.uppercase(), maxLines = 1, overflow = TextOverflow.Ellipsis, style = OdsTheme.typography.button) } },
            selectedContentColor = OdsTheme.colors.component.tab.selectedContent.enable(enabled = enabled),
            unselectedContentColor = OdsTheme.colors.component.tab.unselectedContent.enable(enabled = enabled),
        )
    }
}

class OdsLeadingIconTab(
    private val graphicsObject: Any,
    private val text: String,
    private val selected: Boolean,
    private val enabled: Boolean,
    private val onClick: () -> Unit
) : OdsComponentContent<Nothing>(), OdsTabRowTab {

    /**
     * Creates an instance of [OdsLeadingIconTab].
     *
     * @param painter Painter of the tab icon.
     * @param text Text displayed in the tab.
     * @param selected Controls whether this tab is selected or not.
     * @param enabled Controls whether this tab is enabled or not.
     * @param onClick Callback invoked on tab click.
     */
    constructor(painter: Painter, text: String, selected: Boolean, enabled: Boolean = true, onClick: () -> Unit) : this(
        painter as Any,
        text,
        selected,
        enabled,
        onClick
    )

    /**
     * Creates an instance of [OdsLeadingIconTab].
     *
     * @param imageVector Image vector of the tab icon.
     * @param text Text displayed in the tab.
     * @param selected Controls whether this tab is selected or not.
     * @param enabled Controls whether this tab is enabled or not.
     * @param onClick Callback invoked on tab click.
     */
    constructor(imageVector: ImageVector, text: String, selected: Boolean, enabled: Boolean = true, onClick: () -> Unit) : this(
        imageVector as Any,
        text,
        selected,
        enabled,
        onClick
    )

    /**
     * Creates an instance of [OdsLeadingIconTab].
     *
     * @param bitmap Image bitmap of the icon.
     * @param text Text displayed in the tab.
     * @param selected Controls whether this tab is selected or not.
     * @param enabled Controls whether this tab is enabled or not.
     * @param onClick Callback invoked on tab click.
     * */
    constructor(bitmap: ImageBitmap, text: String, selected: Boolean, enabled: Boolean = true, onClick: () -> Unit) : this(
        bitmap as Any,
        text,
        selected,
        enabled,
        onClick
    )

    @Composable
    override fun Content(modifier: Modifier) {
        val icon = when (graphicsObject) {
            is Painter -> OdsLeadingIconTabIcon(painter = graphicsObject)
            is ImageVector -> OdsLeadingIconTabIcon(imageVector = graphicsObject)
            is ImageBitmap -> OdsLeadingIconTabIcon(bitmap = graphicsObject)
            else -> null
        }
        icon?.let {
            LeadingIconTab(
                modifier = modifier,
                icon = { icon.Content() },
                text = { Text(text = text.uppercase(), maxLines = 1, overflow = TextOverflow.Ellipsis, style = OdsTheme.typography.button) },
                selected = selected,
                selectedContentColor = OdsTheme.colors.component.tab.selectedContent.enable(enabled = enabled),
                unselectedContentColor = OdsTheme.colors.component.tab.unselectedContent.enable(enabled = enabled),
                onClick = onClick,
                enabled = enabled
            )
        }
    }
}

internal data class OdsTabPreviewParameter(
    val hasText: Boolean,
    val hasIcon: Boolean,
    val enabled: Boolean = true,
    val hasLeadingIconTabs: Boolean = false
)

internal class OdsTabRowPreviewParameterProvider :
    BasicPreviewParameterProvider<OdsTabPreviewParameter>(*tabRowPreviewParameterValues.toTypedArray())

internal val tabRowPreviewParameterValues: List<OdsTabPreviewParameter>
    get() {
        return listOf(
            OdsTabPreviewParameter(true, true),
            OdsTabPreviewParameter(true, false),
            OdsTabPreviewParameter(false, true),
            OdsTabPreviewParameter(true, true, hasLeadingIconTabs = true),
            OdsTabPreviewParameter(true, true, false)
        )
    }

/**
 * An icon in an [OdsTab].
 * It is non-clickable and the content description is optional cause a tab can have a label.
 * Note that for accessibility, if the tab has no text, it is highly recommended to set a content description.
 */
private class OdsTabIcon : OdsComponentIcon<Nothing> {

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

/**
 * An icon in an [OdsLeadingIconTab].
 * It is non-clickable and no content description is needed cause a leading icon tab always has a text inside.
 */
private class OdsLeadingIconTabIcon : OdsComponentIcon<Nothing> {

    /**
     * Creates an instance of [OdsLeadingIconTabIcon].
     *
     * @param painter Painter of the icon.
     */
    constructor(painter: Painter) : super(painter, "")

    /**
     * Creates an instance of [OdsLeadingIconTabIcon].
     *
     * @param imageVector Image vector of the icon.
     */
    constructor(imageVector: ImageVector) : super(imageVector, "")

    /**
     * Creates an instance of [OdsLeadingIconTabIcon].
     *
     * @param bitmap Image bitmap of the icon.
     */
    constructor(bitmap: ImageBitmap) : super(bitmap, "")

}