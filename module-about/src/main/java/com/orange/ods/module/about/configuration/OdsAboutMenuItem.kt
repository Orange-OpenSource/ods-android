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

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
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

internal val mandatoryMenuItems: List<OdsAboutMenuItem> = listOf(
    FileAboutItem(R.drawable.ic_dataprotection, R.string.ods_about_menu_privacy_policy, 100, "about_privacy_policy", FileAboutItem.FileFormat.Html),
    FileAboutItem(R.drawable.ic_tasklist, R.string.ods_about_menu_terms_of_service, 101, "about_terms_of_service", FileAboutItem.FileFormat.Html),
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
sealed class OdsAboutMenuItem(@DrawableRes val iconRes: Int, @StringRes val titleRes: Int, val positionIndex: Int, @StringRes val subtitleRes: Int? = null)

class FileAboutItem(
    @DrawableRes iconRes: Int,
    @StringRes titleRes: Int,
    positionIndex: Int,
    val fileName: String,
    val fileFormat: FileFormat,
    @StringRes subtitleRes: Int? = null
) : OdsAboutMenuItem(iconRes, titleRes, positionIndex, subtitleRes) {

    enum class FileFormat {
        Html, Markdown
    }
}

class UrlAboutItem(
    @DrawableRes iconRes: Int,
    @StringRes titleRes: Int,
    positionIndex: Int,
    val url: String,
    @StringRes subtitleRes: Int? = null
) : OdsAboutMenuItem(iconRes, titleRes, positionIndex, subtitleRes)
