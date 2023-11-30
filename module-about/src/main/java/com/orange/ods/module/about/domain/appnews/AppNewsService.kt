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

import androidx.annotation.RawRes
import kotlinx.coroutines.flow.Flow

internal interface AppNewsService {
    fun getAppNews(@RawRes fileRes: Int): Flow<List<AppNews>>
}
