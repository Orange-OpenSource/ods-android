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
    functionalPositive: Color,
    onFunctionalPositive: Color,
    functionalNegative: Color,
    onFunctionalNegative: Color,
    functionalInfo: Color,
    functionalAlert: Color
) {
    var functionalPositive = functionalPositive
        private set
    var onFunctionalPositive = onFunctionalPositive
        private set
    var functionalNegative = functionalNegative
        private set
    var onFunctionalNegative = onFunctionalNegative
        private set
    var functionalInfo = functionalInfo
        private set
    var functionalAlert = functionalAlert
        private set

    fun copy(
        functionalPositive: Color = this.functionalPositive,
        onFunctionalPositive: Color = this.onFunctionalPositive,
        functionalNegative: Color = this.functionalNegative,
        onFunctionalNegative: Color = this.onFunctionalNegative,
        functionalInfo: Color = this.functionalInfo,
        functionalAlert: Color = this.functionalAlert,
    ): OdsFunctionalColors = OdsFunctionalColors(
        functionalPositive,
        onFunctionalPositive,
        functionalNegative,
        onFunctionalNegative,
        functionalInfo,
        functionalAlert
    )
}