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

/*
val aboutItems = listOf(
    UrlAboutItem(R.string.about_menu_design_guidelines, "https://system.design.orange.com/0c1af118d/p/019ecc-android/"),
    FileAboutItem(R.string.about_menu_legal_notice, R.raw.about_legal_notice, FileAboutItem.FileFormat.Html),
    FileAboutItem(R.string.about_menu_privacy_policy, R.raw.about_privacy_policy, FileAboutItem.FileFormat.Html),
    FileAboutItem(R.string.about_menu_changelog, R.raw.changelog, FileAboutItem.FileFormat.Markdown),
    UrlAboutItem(R.string.about_menu_report_issue, "https://github.com/Orange-OpenSource/ods-android/issues/new/choose"),
)

"file:///android_res/raw/$fileName"
*/

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
    ),
    // OdsAboutMenuItem(R.drawable.ic_accessibility, R.string.ods_about_menu_accessibility_statement, 102),
)

/**
 * Item to display in the about menu TODO documentation
 *
 * @property iconRes
 * @property titleRes
 * @property positionIndex
 * @property subtitleRes
 */
@Immutable
sealed class OdsAboutMenuItem(val icon: Painter, val title: String, val positionIndex: Int, val subtitle: String? = null)

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