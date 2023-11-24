/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.module.about.domain.appnews

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AppNews(
    val version: String,
    val date: String,
    val news: String,
) : Parcelable