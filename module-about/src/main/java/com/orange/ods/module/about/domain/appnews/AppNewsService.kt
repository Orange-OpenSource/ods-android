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

package com.orange.ods.module.about.domain.appnews

import androidx.annotation.RawRes
import kotlinx.coroutines.flow.Flow

internal interface AppNewsService {
    fun getAppNews(@RawRes fileRes: Int): Flow<List<AppNews>>
}
