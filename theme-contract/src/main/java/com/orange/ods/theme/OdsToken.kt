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

package com.orange.ods.theme

import com.orange.ods.theme.typography.OdsTextStyle

/**
 * Represents an Orange Design System token.
 *
 * @property name The token name.
 * @property value The token value.
 */
class OdsToken<T>(val name: String, val value: T) {

    object Spacing {

        const val None = "ods.ref.spacing.none"
        const val ExtraSmall = "ods.ref.spacing.xs"
        const val Small = "ods.ref.spacing.s"
        const val Medium = "ods.ref.spacing.m"
        const val Large = "ods.ref.spacing.l"
        const val ExtraLarge = "ods.ref.spacing.xl"
        const val ExtraExtraLarge = "ods.ref.spacing.2xl"
    }

    object TextStyle {

        const val HeadlineLarge = "ods.aos.sys.font.headline.l"
        const val HeadlineSmall = "ods.aos.sys.font.headline.s"
        const val TitleLarge = "ods.aos.sys.font.title.l"
        const val TitleMedium = "ods.aos.sys.font.title.m"
        const val TitleSmall = "ods.aos.sys.font.title.s"
        const val BodyLarge = "ods.aos.sys.font.body.l"
        const val BodyMedium = "ods.aos.sys.font.body.m"
        const val BodySmall = "ods.aos.sys.font.body.s"
        const val LabelLarge = "ods.aos.sys.font.label.l"
        const val LabelSmall = "ods.aos.sys.font.label.s"
    }
}

val OdsToken<OdsTextStyle>.description: String
    get() = when (name) {
        OdsToken.TextStyle.HeadlineLarge -> "Headline Large"
        OdsToken.TextStyle.HeadlineSmall -> "Headline Small"
        OdsToken.TextStyle.TitleLarge -> "Title Large"
        OdsToken.TextStyle.TitleMedium -> "Title Medium"
        OdsToken.TextStyle.TitleSmall -> "Title Small"
        OdsToken.TextStyle.BodyLarge -> "Body Large"
        OdsToken.TextStyle.BodyMedium -> "Body Medium"
        OdsToken.TextStyle.BodySmall -> "Body Small"
        OdsToken.TextStyle.LabelLarge -> "Label Large"
        OdsToken.TextStyle.LabelSmall -> "Label Small"
        else -> name
    }
