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

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

@Immutable
sealed class AboutItem(
    @StringRes val titleRes: Int
)

val AboutItem.id: Long
    get() = aboutItems.indexOf(this).toLong()

val aboutItems = listOf<AboutItem>()
