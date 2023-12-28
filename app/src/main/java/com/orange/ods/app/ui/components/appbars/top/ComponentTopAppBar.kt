/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.appbars.top

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    val customizationState = rememberTopAppBarCustomizationState(variant = remember { mutableStateOf(variant) })

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
                        Variant.AppBarsTopSearch -> TopAppBarNavigationIconSetup()
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
fun TopAppBarNavigationIconSetup() {
    with(LocalTopAppBarCustomizationState.current) {
        OdsListItem(
            text = stringResource(id = R.string.component_app_bars_top_element_navigation_icon),
            trailing = OdsListItem.TrailingSwitch(navigationIconEnabled.value, { navigationIconEnabled.value = it })
        )
    }
}