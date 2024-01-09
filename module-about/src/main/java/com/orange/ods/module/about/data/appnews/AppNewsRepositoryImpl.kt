/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components 
 */

package com.orange.ods.module.about.data.appnews

import android.content.Context
import androidx.annotation.RawRes
import com.orange.ods.extension.contentAsString
import com.orange.ods.module.about.domain.appnews.AppNews
import com.orange.ods.module.about.domain.appnews.AppNewsRepository
import kotlinx.coroutines.CancellationException
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
        } catch (e: Exception) {
            if (e is CancellationException) {
                throw e
            } else {
                Timber.d("Fail to parse JSON file: ${e.message}")
            }
        }
    }
}