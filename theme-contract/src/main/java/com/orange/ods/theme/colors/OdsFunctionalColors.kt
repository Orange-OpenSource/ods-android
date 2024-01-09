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

package com.orange.ods.theme.colors

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.graphics.Color

class OdsFunctionalColors(
    positive: Color,
    onPositive: Color,
    negative: Color,
    onNegative: Color,
    info: Color,
    alert: Color
) {
    var positive by mutableStateOf(positive, structuralEqualityPolicy())
        private set
    var onPositive by mutableStateOf(onPositive, structuralEqualityPolicy())
        private set
    var negative by mutableStateOf(negative, structuralEqualityPolicy())
        private set
    var onNegative by mutableStateOf(onNegative, structuralEqualityPolicy())
        private set
    var info by mutableStateOf(info, structuralEqualityPolicy())
        private set
    var alert by mutableStateOf(alert, structuralEqualityPolicy())
        private set
}

