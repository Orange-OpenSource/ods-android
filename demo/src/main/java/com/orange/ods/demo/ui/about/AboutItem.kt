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

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.orange.ods.demo.R

@Immutable
data class AboutItem(
    val id: Long,
    @StringRes val titleRes: Int,
    val fileName: String
)

val aboutItems = listOf(
    AboutItem(1L, R.string.about_menu_legal_notice, "about_legal_notice.html"),
    AboutItem(2L, R.string.about_menu_privacy_policy, "about_privacy_policy.html")
)