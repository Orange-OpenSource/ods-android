/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.module.about.data.appnews

import android.content.Context
import androidx.annotation.RawRes
import com.orange.ods.extension.contentAsString
import com.orange.ods.module.about.domain.appnews.AppNews
import com.orange.ods.module.about.domain.appnews.AppNewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject

internal class AppNewsRepositoryImpl @Inject constructor(private val context: Context) : AppNewsRepository {

    private val appNewsParser = AppNewsParser()

    override fun getAppNews(@RawRes fileResId: Int): Flow<List<AppNews>> = flow {
        try {
            val jsonString = context.resources.openRawResource(fileResId).contentAsString().orEmpty()
            val appNews = appNewsParser.parseAppNews(jsonString)
            emit(appNews)
        } catch (_: Exception) {
            Timber.d("Fail to parse JSON file")
        }
    }
}