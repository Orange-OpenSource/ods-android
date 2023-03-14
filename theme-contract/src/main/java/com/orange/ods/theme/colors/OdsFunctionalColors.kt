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

import androidx.compose.ui.graphics.Color

class OdsFunctionalColors(
    positive: Color,
    onPositive: Color,
    negative: Color,
    onNegative: Color,
    info: Color,
    alert: Color
) {
    var positive = positive
        private set
    var onPositive = onPositive
        private set
    var negative = negative
        private set
    var onNegative = onNegative
        private set
    var info = info
        private set
    var alert = alert
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

