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
data class AboutItem(
    @StringRes val titleRes: Int,
    @RawRes val fileRes: Int,
    val fileFormat: FileFormat
) {
    enum class FileFormat {
        Html, Markdown
    }
}

val AboutItem.id: Long
    get() = aboutItems.indexOf(this).toLong()


val aboutItems = listOf(
    AboutItem(R.string.about_menu_legal_notice, R.raw.about_legal_notice, AboutItem.FileFormat.Html),
    AboutItem(R.string.about_menu_privacy_policy, R.raw.about_privacy_policy, AboutItem.FileFormat.Html),
    AboutItem(R.string.about_menu_changelog, R.raw.changelog, AboutItem.FileFormat.Markdown),
)