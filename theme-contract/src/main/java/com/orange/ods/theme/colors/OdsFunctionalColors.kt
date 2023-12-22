/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
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

    fun copy(
        positive: Color = this.positive,
        onPositive: Color = this.onPositive,
        negative: Color = this.negative,
        onNegative: Color = this.onNegative,
        info: Color = this.info,
        alert: Color = this.alert,
    ): OdsFunctionalColors = OdsFunctionalColors(
        positive,
        onPositive,
        negative,
        onNegative,
        info,
        alert
    )

    /**
     * Updates the internal values of the given OdsFunctionalColors with values from the other.
     * This allows efficiently updating a subset of OdsFunctionalColors, without recomposing every composable that consumes values from LocalColors.
     */
    internal fun updateColorsFrom(other: OdsFunctionalColors) {
        positive = other.positive
        onPositive = other.onPositive
        negative = other.negative
        onNegative = other.onNegative
        info = other.info
        alert = other.alert
    }
}

