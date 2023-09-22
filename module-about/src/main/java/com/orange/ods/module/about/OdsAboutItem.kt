/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.module.about

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

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

val aboutItems = listOf<AboutItem>()
