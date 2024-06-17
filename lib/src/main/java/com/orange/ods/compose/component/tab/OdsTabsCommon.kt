/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components 
 */

package com.orange.ods.compose.component.tab

import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.Tab
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import com.orange.ods.compose.component.content.OdsComponentContent
import com.orange.ods.compose.component.content.OdsComponentIcon
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.extension.enable
import com.orange.ods.compose.text.OdsText
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.theme.colors.fromToken

/**
 * Contains classes to build an [com.orange.ods.compose.component.tab.OdsTabRow] or an [com.orange.ods.compose.component.tab.OdsScrollableTabRow].
 */
object OdsTabRow {

    class Tab(
        val icon: Icon?,
        val text: String?,
        val enabled: Boolean = true,
        val onClick: () -> Unit
    ) : OdsComponentContent<Tab.ExtraParameters>(ExtraParameters::class.java) {

        data class ExtraParameters internal constructor(
            internal val selected: Boolean,
            internal val iconPosition: Icon.Position
        ) : OdsComponentContent.ExtraParameters()

        @Composable
        override fun Content(modifier: Modifier) {
            val selectedContentColor = OdsTheme.colors.fromToken(OdsTheme.componentsTokens.primaryNavigationTab.activeContentColor).enable(enabled = enabled)
            val unselectedContentColor =
                OdsTheme.colors.fromToken(OdsTheme.componentsTokens.primaryNavigationTab.inactiveContentColor).enable(enabled = enabled)

            if (extraParameters.iconPosition == Icon.Position.Leading && text != null && icon != null) {
                LeadingIconTab(
                    modifier = modifier,
                    icon = { icon.Content() },
                    text = {
                        OdsText(
                            text = text,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = OdsTheme.typography.labelLarge
                        )
                    },
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
                    text = text?.let {
                        {
                            OdsText(
                                text = text,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = OdsTheme.typography.labelLarge
                            )
                        }
                    },
                    selectedContentColor = selectedContentColor,
                    unselectedContentColor = unselectedContentColor,
                )
            }
        }

        /**
         * An icon in an [OdsTabRow.Tab].
         * It is non-clickable and the content description is optional cause a tab can have a label.
         * Note that for accessibility, if the tab has no text, it is highly recommended to set a content description.
         */
        class Icon private constructor(
            val graphicsObject: Any,
            val contentDescription: String
        ) : OdsComponentIcon<Nothing>(Nothing::class.java, graphicsObject, contentDescription) {

            enum class Position {
                Top, Leading
            }

            /**
             * Creates an instance of [OdsTabRow.Tab.Icon].
             *
             * @param painter Painter of the icon.
             * @param contentDescription The content description associated to this [OdsTabRow.Tab.Icon].
             */
            constructor(painter: Painter, contentDescription: String = "") : this(painter as Any, contentDescription)

            /**
             * Creates an instance of [OdsTabRow.Tab.Icon].
             *
             * @param imageVector Image vector of the icon.
             * @param contentDescription The content description associated to this [OdsTabRow.Tab.Icon].
             */
            constructor(imageVector: ImageVector, contentDescription: String = "") : this(imageVector as Any, contentDescription)

            /**
             * Creates an instance of [OdsTabRow.Tab.Icon].
             *
             * @param bitmap Image bitmap of the icon.
             * @param contentDescription The content description associated to this [OdsTabRow.Tab.Icon].
             */
            constructor(bitmap: ImageBitmap, contentDescription: String = "") : this(bitmap as Any, contentDescription)

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
            OdsTabPreviewParameter(hasText = true, hasIcon = true),
            OdsTabPreviewParameter(hasText = true, hasIcon = false),
            OdsTabPreviewParameter(hasText = false, hasIcon = true),
            OdsTabPreviewParameter(hasText = true, hasIcon = true, hasLeadingIconTabs = true),
            OdsTabPreviewParameter(hasText = true, hasIcon = true, enabled = false)
        )
    }
