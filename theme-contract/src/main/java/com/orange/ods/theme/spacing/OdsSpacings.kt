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

package com.orange.ods.theme.spacing

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ods.theme.OdsThemeConfigurationItem
import com.orange.ods.theme.OdsToken

/**
 * The Orange Design System spacings.
 */
class OdsSpacings(
    override val none: Dp = 0.dp,
    override val extraSmall: Dp = 4.dp,
    override val small: Dp = 8.dp,
    override val medium: Dp = 16.dp,
    override val large: Dp = 24.dp,
    override val extraLarge: Dp = 32.dp,
    override val extraExtraLarge: Dp = 40.dp
) : OdsSpacingsCatalog<Dp>, OdsThemeConfigurationItem.TokenProvider<OdsSpacingsCatalog<OdsToken<Dp>>> {

    override val tokens = object : OdsSpacingsCatalog<OdsToken<Dp>> {
        override val none = OdsToken(OdsToken.Spacing.None, this@OdsSpacings.none)
        override val extraSmall = OdsToken(OdsToken.Spacing.ExtraSmall, this@OdsSpacings.extraSmall)
        override val small = OdsToken(OdsToken.Spacing.Small, this@OdsSpacings.small)
        override val medium = OdsToken(OdsToken.Spacing.Medium, this@OdsSpacings.medium)
        override val large = OdsToken(OdsToken.Spacing.Large, this@OdsSpacings.large)
        override val extraLarge = OdsToken(OdsToken.Spacing.ExtraLarge, this@OdsSpacings.extraLarge)
        override val extraExtraLarge = OdsToken(OdsToken.Spacing.ExtraExtraLarge, this@OdsSpacings.extraExtraLarge)
    }
}

interface OdsSpacingsCatalog<T> : OdsThemeConfigurationItem.Catalog<T> {

    val none: T
    val extraSmall: T
    val small: T
    val medium: T
    val large: T
    val extraLarge: T
    val extraExtraLarge: T

    override val entries: List<T>
        get() = listOf(none, extraSmall, small, medium, large, extraLarge, extraExtraLarge)
}
