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

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
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
        colorScheme = lightColorScheme(
            primary = core.orange200,
            onPrimary = core.white100,
            primaryContainer = core.tmpOrangeFFA14D,
            onPrimaryContainer = core.black900,
            inversePrimary = core.tmpOrangeFFB68E,

            secondary = core.orange200,
            onSecondary = core.black900,
            secondaryContainer = core.tmpGrey333333,
            onSecondaryContainer = core.white100,

            tertiary = core.tmpGrey666666,
            onTertiary = core.white100,
            tertiaryContainer = core.tmpGreyCCCCCC,
            onTertiaryContainer = core.black900,
            background = core.white100,
            onBackground = core.black900,
            surface = core.white100,
            onSurface = core.black900,
            surfaceVariant = core.tmpGreyEEEEEE,
            onSurfaceVariant = core.black900,
            surfaceTint = core.tmpGrey999999,
            inverseSurface = core.tmpBrown362F2C,
            inverseOnSurface = core.white100,
            error = functional.negative200,
            onError = core.white100,
            errorContainer = functional.tmpRedFFDAD6,
            onErrorContainer = functional.tmpRed410002,
            outline = core.black900,
            outlineVariant = core.tmpGreyEBEBEB,
            scrim = core.black900,
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
                itemSelectedIndicator = core.tmpGreyEEEEEE,
                itemSelected = core.orange200,
                itemUnselected = core.black900
            )

            floatingActionButton = OdsFloatingActionButtonColors(
                background = core.orange200,
                content = core.black900
            )

            topAppBar = OdsTopAppBarColors(
                container = core.white100,
                content = core.black900
            )

            switch = OdsSwitchColors(
                uncheckedThumb = core.white100
            )

            tab = OdsTabColors(
                container = core.white100,
                selectedContent = core.orange200,
                unselectedContent = core.black900
            )
        }
    )
}

internal val OrangeDarkColors = with(OrangeColorPalette) {
    OdsColors(
        colorScheme = darkColorScheme(
            primary = core.orange100,
            onPrimary = core.black900,
            primaryContainer = core.tmpOrangeFFA14D,
            onPrimaryContainer = core.black900,
            inversePrimary = core.tmpBrown9C4500,

            secondary = core.white100,
            onSecondary = core.black900,
            secondaryContainer = core.tmpGreyCCCCCC,
            onSecondaryContainer = core.black900,

            tertiary = core.tmpGreyCCCCCC,
            onTertiary = core.black900,
            tertiaryContainer = core.tmpGrey333333,
            onTertiaryContainer = core.white100,
            background = core.black900,
            onBackground = core.white100,
            surface = core.black900,
            onSurface = core.white100,
            surfaceVariant = core.tmpGreyEEEEEE,
            onSurfaceVariant = core.black900,
            surfaceTint = core.white100,
            inverseSurface = core.tmpGreyEEEEEE,
            inverseOnSurface = core.black900,
            error = functional.tmpRedD53F15,
            onError = core.black900,
            errorContainer = functional.tmpRed93000A,
            onErrorContainer = functional.tmpRedFFDAD6,
            outline = core.tmpGreyEEEEEE,
            outlineVariant = core.tmpBrown52443C,
            scrim = core.black900,
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
                itemSelectedIndicator = core.tmpGrey333333,
                itemSelected = core.orange200,
                itemUnselected = core.white100
            )

            floatingActionButton = OdsFloatingActionButtonColors(
                background = core.orange100,
                content = core.black900
            )

            topAppBar = OdsTopAppBarColors(
                container = custom.DarkSurfaceDefault,
                content = core.white100
            )

            switch = OdsSwitchColors(
                uncheckedThumb = grey.Grey400
            )

            tab = OdsTabColors(
                container = custom.DarkSurfaceDefault,
                selectedContent = core.orange100,
                unselectedContent = core.white100
            )
        }
    )
}