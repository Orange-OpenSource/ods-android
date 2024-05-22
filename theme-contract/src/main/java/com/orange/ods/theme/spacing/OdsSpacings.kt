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

import com.orange.ods.theme.OdsThemeConfigurationItem
import com.orange.ods.theme.OdsToken
import com.orange.ods.theme.R

/**
 * The Orange Design System spacings.
 */
class OdsSpacings(
    override val none: OdsSpacing = OdsSpacing.Resource(R.dimen.spacing_none),
    override val extraSmall: OdsSpacing = OdsSpacing.Resource(R.dimen.spacing_xs),
    override val small: OdsSpacing = OdsSpacing.Resource(R.dimen.spacing_s),
    override val medium: OdsSpacing = OdsSpacing.Resource(R.dimen.spacing_m),
    override val large: OdsSpacing = OdsSpacing.Resource(R.dimen.spacing_l),
    override val extraLarge: OdsSpacing = OdsSpacing.Resource(R.dimen.spacing_xl),
    override val extraExtraLarge: OdsSpacing = OdsSpacing.Resource(R.dimen.spacing_2xl)
) : OdsSpacingsCatalog<OdsSpacing>, OdsThemeConfigurationItem.TokenProvider<OdsSpacingsCatalog<OdsToken<OdsSpacing>>> {

    override val tokens = object : OdsSpacingsCatalog<OdsToken<OdsSpacing>> {
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
}
