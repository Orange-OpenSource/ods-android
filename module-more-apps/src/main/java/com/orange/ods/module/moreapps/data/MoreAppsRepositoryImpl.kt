/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ods.module.moreapps.data

import com.orange.ods.module.moreapps.domain.MoreAppsItem
import com.orange.ods.module.moreapps.domain.MoreAppsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.util.Locale


internal class MoreAppsRepositoryImpl(private val appsPlusApi: AppsPlusApi) : MoreAppsRepository {

    override fun getMoreAppsItems(apiKey: String, locale: Locale, filter: String?): Flow<List<MoreAppsItem>> = flow {
        val response = appsPlusApi.getApps(apiKey, locale.toString(), filter)

        if (!response.isSuccessful) {
            throw IOException("Unexpected HTTP code: ${response.code()}")
        } else {
            emit(response.body()?.items?.toModel().orEmpty())
        }
    }
}