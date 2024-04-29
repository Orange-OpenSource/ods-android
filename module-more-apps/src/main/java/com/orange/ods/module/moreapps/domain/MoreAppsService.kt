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

package com.orange.ods.module.moreapps.domain

import kotlinx.coroutines.flow.Flow
import java.util.Locale

internal interface MoreAppsService {
    suspend fun getMoreAppsItems(apiKey: String, locale: Locale, filter: String?): Flow<Resource<List<MoreAppsItem>>>
}
