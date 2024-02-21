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

package com.orange.ods.app.ui.components.appbars.top

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.ui.CustomAppBarConfiguration
import com.orange.ods.app.ui.LocalAppBarManager
import com.orange.ods.app.ui.components.Variant
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.compose.component.listitem.OdsListItem

val LocalTopAppBarCustomizationState =
    staticCompositionLocalOf<TopAppBarCustomizationState> { error("CompositionLocal TopAppBarCustomizationState not present") }

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentTopAppBar(variant: Variant) {
    val customizationState = rememberTopAppBarCustomizationState()
    val isLarge = variant == Variant.AppBarsTopLarge

    with(customizationState) {
        val customAppBarConfiguration = CustomAppBarConfiguration(
            title = if (isLarge) stringResource(id = title.value.titleRes) else stringResource(id = R.string.component_app_bars_top_regular),
            actionCount = actionCount.intValue,
            isNavigationIconEnabled = isNavigationIconEnabled,
            isLarge = isLarge,
            scrollBehavior = scrollBehavior.value,
            isOverflowMenuEnabled = isOverflowMenuEnabled
        )
        LocalAppBarManager.current.setCustomAppBar(customAppBarConfiguration)

        CompositionLocalProvider(
            LocalTopAppBarCustomizationState provides customizationState
        ) {
            ComponentCustomizationBottomSheetScaffold(
                bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
                bottomSheetContent = {
                    when (variant) {
                        Variant.AppBarsTopLarge -> LargeTopAppBarBottomSheetContent()
                        Variant.AppBarsTopSearch -> TopAppBarNavigationIconCustomization()
                        else -> RegularTopAppBarBottomSheetContent()
                    }
                }
            ) {
                when (variant) {
                    Variant.AppBarsTopLarge -> LargeTopAppBarContent()
                    Variant.AppBarsTopSearch -> SearchTopAppBarContent()
                    else -> RegularTopAppBarContent()
                }
            }
        }
    }
}

@Composable
fun TopAppBarNavigationIconCustomization() {
    with(LocalTopAppBarCustomizationState.current) {
        OdsListItem(
            text = stringResource(id = R.string.component_app_bars_top_element_navigation_icon),
            trailing = OdsListItem.TrailingSwitch(navigationIconEnabled.value, { navigationIconEnabled.value = it })
        )
    }
}