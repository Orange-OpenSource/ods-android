/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.module.about.configuration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.module.about.R

@Composable
@Stable
internal fun mandatoryMenuItems(): List<OdsAboutMenuItem> = listOf(
    FileAboutItem(
        painterResource(id = R.drawable.ic_dataprotection),
        stringResource(id = R.string.ods_about_menu_privacy_policy),
        100,
        "about_privacy_policy",
        FileAboutItem.FileFormat.Html
    ),
    FileAboutItem(
        painterResource(id = R.drawable.ic_tasklist),
        stringResource(id = R.string.ods_about_menu_terms_of_service),
        101,
        "about_terms_of_service",
        FileAboutItem.FileFormat.Html
    )
)

/**
 * Item to display in the about menu
 *
 * @property icon The leading icon of the menu item.
 * @property text The primary text of the menu item
 * @property positionIndex Index corresponding to the item position in the menu.
 * @property secondaryText The secondary text of the menu item displayed below the primary text.
 */
@Immutable
sealed class OdsAboutMenuItem(val icon: Painter, val text: String, val positionIndex: Int, val secondaryText: String? = null)

class FileAboutItem(
    icon: Painter,
    title: String,
    positionIndex: Int,
    val fileName: String,
    val fileFormat: FileFormat,
    subtitle: String? = null
) : OdsAboutMenuItem(icon, title, positionIndex, subtitle) {

    enum class FileFormat {
        Html, Markdown
    }
}

class UrlAboutItem(
    icon: Painter,
    title: String,
    positionIndex: Int,
    val url: String,
    subtitle: String? = null
) : OdsAboutMenuItem(icon, title, positionIndex, subtitle)