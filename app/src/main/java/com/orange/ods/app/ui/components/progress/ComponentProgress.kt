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

package com.orange.ods.app.ui.components.progress

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.orange.ods.app.ui.components.Variant

@Composable
fun ComponentProgress(variant: Variant) {

    Column {
        when (variant) {
            Variant.ProgressLinear -> ProgressLinear()
            Variant.ProgressCircular -> ProgressCircular()
            else -> {}
        }

    }
}
