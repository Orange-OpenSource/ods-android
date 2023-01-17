/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.about

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.orange.ods.demo.R

@Immutable
sealed class AboutItem(
    @StringRes val titleRes: Int
)

class FileAboutItem(
    @StringRes titleRes: Int,
    @RawRes val fileRes: Int,
    val fileFormat: FileFormat
) : AboutItem(titleRes) {

    enum class FileFormat {
        Html, Markdown
    }
}

class UrlAboutItem(
    @StringRes titleRes: Int,
    val url: String
) : AboutItem(titleRes)

val AboutItem.id: Long
    get() = aboutItems.indexOf(this).toLong()

val aboutItems = listOf(
    UrlAboutItem(R.string.about_menu_design_guidelines, "https://system.design.orange.com/0c1af118d/p/019ecc-android/"),
    FileAboutItem(R.string.about_menu_legal_notice, R.raw.about_legal_notice, FileAboutItem.FileFormat.Html),
    FileAboutItem(R.string.about_menu_privacy_policy, R.raw.about_privacy_policy, FileAboutItem.FileFormat.Html),
    FileAboutItem(R.string.about_menu_changelog, R.raw.changelog, FileAboutItem.FileFormat.Markdown),
    UrlAboutItem(R.string.about_menu_report_issue, "https://github.com/Orange-OpenSource/ods-android/issues/new/choose"),
)
