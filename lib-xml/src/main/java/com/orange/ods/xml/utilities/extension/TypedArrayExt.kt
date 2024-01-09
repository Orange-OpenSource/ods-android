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

package com.orange.ods.xml.utilities.extension

import android.content.res.TypedArray
import androidx.annotation.AnyRes
import androidx.annotation.StyleableRes
import androidx.core.content.res.getResourceIdOrThrow

@AnyRes
internal fun TypedArray.getResourceIdOrNull(@StyleableRes index: Int): Int? {
    return try {
        getResourceIdOrThrow(index)
    } catch (_: Throwable) {
        null
    }
}
