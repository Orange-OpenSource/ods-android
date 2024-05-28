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

/**
 * Represents an Orange Design System token.
 *
 * @property name The token name.
 * @property value The token value.
 */
class OdsToken<T> internal constructor(val name: String, val value: T) {

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

    object Colors {

        const val Primary = "Colors.Primary"
        const val PrimaryVariant = "Colors.PrimaryVariant"
        const val Secondary = "Colors.Secondary"
        const val SecondaryVariant = "Colors.SecondaryVariant"
        const val Background = "Colors.Background"
        const val Surface = "Colors.Surface"
        const val Error = "Colors.Error"
        const val OnPrimary = "Colors.OnPrimary"
        const val OnSecondary = "Colors.OnSecondary"
        const val OnBackground = "Colors.OnBackground"
        const val OnSurface = "Colors.OnSurface"
        const val OnError = "Colors.OnError"

        object Functional {

            const val Positive = "Colors.Functional.Positive"
            const val OnPositive = "Colors.Functional.OnPositive"
            const val Negative = "Colors.Functional.Negative"
            const val OnNegative = "Colors.Functional.OnNegative"
            const val Info = "Colors.Functional.Info"
            const val Alert = "Colors.Functional.Alert"
        }

        object Component {

            const val SystemBarsBackground = "Colors.Component.SystemBarsBackground"

            object BottomNavigation {
                const val BarBackground = "Colors.Component.BottomNavigation.BarBackground"
                const val BarContent = "Colors.Component.BottomNavigation.BarContent"
                const val ItemSelected = "Colors.Component.BottomNavigation.ItemSelected"
                const val ItemUnselected = "Colors.Component.BottomNavigation.ItemUnselected"
                const val ItemSelectedIndicator = "Colors.Component.BottomNavigation.Indicator"
            }

            object FloatingActionButton {

                const val Background = "Colors.Component.FloatingActionButton.Background"
                const val Content = "Colors.Component.FloatingActionButton.Content"
            }

            object TopAppBar {

                const val Container = "Colors.Component.TopAppBar.Container"
                const val Content = "Colors.Component.TopAppBar.Content"
            }

            object Switch {

                const val UncheckedThumb = "Colors.Component.Switch.UncheckedThumb"
            }

            object Tab {

                const val Background = "Colors.Component.Tab.Background"
                const val SelectedContent = "Colors.Component.Tab.SelectedContent"
                const val UnselectedContent = "Colors.Component.Tab.UnselectedContent"
            }
        }
    }
}
