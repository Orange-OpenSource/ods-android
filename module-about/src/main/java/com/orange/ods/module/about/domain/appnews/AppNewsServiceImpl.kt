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
import javax.inject.Inject

internal class AppNewsServiceImpl @Inject constructor(private val appNewsRepository: AppNewsRepository) : AppNewsService {

    override fun getAppNews(@RawRes fileRes: Int): Flow<List<AppNews>> {
        return appNewsRepository.getAppNews(fileRes)
    }
}
