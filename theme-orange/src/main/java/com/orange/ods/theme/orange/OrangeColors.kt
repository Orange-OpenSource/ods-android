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

package com.orange.ods.theme.orange

import androidx.compose.material.Colors
import com.orange.ods.theme.colors.OdsBottomNavigationColors
import com.orange.ods.theme.colors.OdsColors
import com.orange.ods.theme.colors.OdsComponentsColors
import com.orange.ods.theme.colors.OdsFloatingActionButtonColors
import com.orange.ods.theme.colors.OdsFunctionalColors
import com.orange.ods.theme.colors.OdsSwitchColors
import com.orange.ods.theme.colors.OdsTabColors
import com.orange.ods.theme.colors.OdsTopAppBarColors

internal val OrangeLightColors = with(OrangeColorPalette) {
    OdsColors(
        material = Colors(
            primary = core.orange200,
            primaryVariant = core.orange100,
            secondary = core.orange200,
            secondaryVariant = core.orange200,
            background = core.white100,
            surface = core.white100,
            error = functional.negative200,
            onPrimary = core.black900,
            onSecondary = core.black900,
            onBackground = core.black900,
            onSurface = core.black900,
            onError = core.black900,
            isLight = true
        ),

        functional = OdsFunctionalColors(
            positive = functional.positive200,
            onPositive = core.white100,
            negative = functional.negative200,
            onNegative = core.white100,
            info = functional.info200,
            alert = functional.alert200
        ),

        components = OdsComponentsColors(
            systemBarsBackground = core.white100
        ) {
            bottomNavigation = OdsBottomNavigationColors(
                barBackground = core.white100,
                barContent = core.black900,
                itemSelected = core.orange200,
                itemUnselected = core.black900
            )

            floatingActionButton = OdsFloatingActionButtonColors(
                background = core.orange200,
                content = core.black900
            )

            topAppBar = OdsTopAppBarColors(
                barBackground = core.white100,
                barContent = core.black900
            )

            switch = OdsSwitchColors(
                uncheckedThumb = core.white100
            )

            tab = OdsTabColors(
                background = core.white100,
                selectedContent = core.orange200,
                unselectedContent = core.black900
            )
        }
    )
}

internal val OrangeDarkColors = with(OrangeColorPalette) {
    OdsColors(
        material = Colors(
            primary = core.orange100,
            primaryVariant = core.orange200,
            secondary = core.orange200,
            secondaryVariant = core.orange200,
            background = core.black900,
            surface = custom.DarkSurfaceDefault,
            error = functional.negative100,
            onPrimary = core.black900,
            onSecondary = core.black900,
            onBackground = core.white100,
            onSurface = core.white100,
            onError = core.black900,
            isLight = false
        ),

        functional = OdsFunctionalColors(
            positive = functional.positive100,
            onPositive = core.black900,
            negative = functional.negative100,
            onNegative = core.white100,
            info = functional.info100,
            alert = functional.alert100
        ),

        components = OdsComponentsColors(
            systemBarsBackground = core.black900
        ) {
            bottomNavigation = OdsBottomNavigationColors(
                barBackground = core.black900,
                barContent = core.white100,
                itemSelected = core.orange200,
                itemUnselected = core.white100
            )

            floatingActionButton = OdsFloatingActionButtonColors(
                background = core.orange100,
                content = core.black900
            )

            topAppBar = OdsTopAppBarColors(
                barBackground = custom.DarkSurfaceDefault,
                barContent = core.white100
            )

            switch = OdsSwitchColors(
                uncheckedThumb = grey.Grey400
            )

            tab = OdsTabColors(
                background = custom.DarkSurfaceDefault,
                selectedContent = core.orange100,
                unselectedContent = core.white100
            )
        }
    )
}