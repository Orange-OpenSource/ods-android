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
import com.orange.ods.compose.component.content.OdsComponentBuilder
import com.orange.ods.compose.component.content.OdsIconBuilder
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.compose.utilities.extension.enable

class OdsTabRowTabBuilder(
    private val icon: OdsTabRowTabIconBuilder?,
    private val text: String?,
    private val enabled: Boolean = true,
    private val onClick: () -> Unit
) : OdsComponentBuilder<OdsTabRowTabBuilder.ExtraParameters>() {

    data class ExtraParameters(
        val selected: Boolean,
        val iconPosition: OdsTabRowTabIconPosition
    ) : OdsComponentBuilder.ExtraParameters()

    @Composable
    override fun Content(modifier: Modifier) {
        val selectedContentColor = OdsTheme.colors.component.tab.selectedContent.enable(enabled = enabled)
        val unselectedContentColor = OdsTheme.colors.component.tab.unselectedContent.enable(enabled = enabled)

        if (extraParameters.iconPosition == OdsTabRowTabIconPosition.Leading && text != null && icon != null) {
            LeadingIconTab(
                modifier = modifier,
                icon = { icon.Content() },
                text = { Text(text = text.uppercase(), maxLines = 1, overflow = TextOverflow.Ellipsis, style = OdsTheme.typography.button) },
                selected = extraParameters.selected,
                selectedContentColor = selectedContentColor,
                unselectedContentColor = unselectedContentColor,
                onClick = onClick,
                enabled = enabled
            )
        } else {
            Tab(
                selected = extraParameters.selected,
                onClick = onClick,
                modifier = modifier,
                enabled = enabled,
                icon = icon?.let { { it.Content() } },
                text = text?.let { { Text(text.uppercase(), maxLines = 1, overflow = TextOverflow.Ellipsis, style = OdsTheme.typography.button) } },
                selectedContentColor = selectedContentColor,
                unselectedContentColor = unselectedContentColor,
            )
        }
    }
}

enum class OdsTabRowTabIconPosition {
    Top, Leading
}

/**
 * An icon in an [OdsTab].
 * It is non-clickable and the content description is optional cause a tab can have a label.
 * Note that for accessibility, if the tab has no text, it is highly recommended to set a content description.
 */
class OdsTabRowTabIconBuilder : OdsIconBuilder<Nothing> {

    /**
     * Creates an instance of [OdsTabRowTabIconBuilder].
     *
     * @param painter Painter of the icon.
     * @param contentDescription The content description associated to this [OdsTabRowTabIconBuilder].
     */
    constructor(painter: Painter, contentDescription: String = "") : super(painter, contentDescription)

    /**
     * Creates an instance of [OdsTabRowTabIconBuilder].
     *
     * @param imageVector Image vector of the icon.
     * @param contentDescription The content description associated to this [OdsTabRowTabIconBuilder].
     */
    constructor(imageVector: ImageVector, contentDescription: String = "") : super(imageVector, contentDescription)

    /**
     * Creates an instance of [OdsTabRowTabIconBuilder].
     *
     * @param bitmap Image bitmap of the icon.
     * @param contentDescription The content description associated to this [OdsTabRowTabIconBuilder].
     */
    constructor(bitmap: ImageBitmap, contentDescription: String = "") : super(bitmap, contentDescription)

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
