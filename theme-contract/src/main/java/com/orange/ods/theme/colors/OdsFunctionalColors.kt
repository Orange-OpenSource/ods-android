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

package com.orange.ods.theme.colors

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.graphics.Color
import com.orange.ods.theme.OdsThemeConfigurationItem
import com.orange.ods.theme.OdsToken

class OdsFunctionalColors(
    positive: Color,
    onPositive: Color,
    negative: Color,
    onNegative: Color,
    info: Color,
    alert: Color
) : OdsFunctionalColorsCatalog<Color>, OdsThemeConfigurationItem.TokenProvider<OdsFunctionalColorsCatalog<OdsToken<Color>>> {

    override var positive by mutableStateOf(positive, structuralEqualityPolicy())
        private set
    override var onPositive by mutableStateOf(onPositive, structuralEqualityPolicy())
        private set
    override var negative by mutableStateOf(negative, structuralEqualityPolicy())
        private set
    override var onNegative by mutableStateOf(onNegative, structuralEqualityPolicy())
        private set
    override var info by mutableStateOf(info, structuralEqualityPolicy())
        private set
    override var alert by mutableStateOf(alert, structuralEqualityPolicy())
        private set

    override val tokens = object : OdsFunctionalColorsCatalog<OdsToken<Color>> {
        override val positive = OdsToken(OdsToken.Colors.Functional.Positive, this@OdsFunctionalColors.positive)
        override val onPositive = OdsToken(OdsToken.Colors.Functional.OnPositive, this@OdsFunctionalColors.onPositive)
        override val negative = OdsToken(OdsToken.Colors.Functional.Negative, this@OdsFunctionalColors.negative)
        override val onNegative = OdsToken(OdsToken.Colors.Functional.OnNegative, this@OdsFunctionalColors.onNegative)
        override val info = OdsToken(OdsToken.Colors.Functional.Info, this@OdsFunctionalColors.info)
        override val alert = OdsToken(OdsToken.Colors.Functional.Alert, this@OdsFunctionalColors.alert)
    }
}

interface OdsFunctionalColorsCatalog<T> : OdsThemeConfigurationItem.Catalog<T> {

    val positive: T
    val onPositive: T
    val negative: T
    val onNegative: T
    val info: T
    val alert: T
}
