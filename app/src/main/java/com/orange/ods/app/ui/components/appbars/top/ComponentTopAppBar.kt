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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.ui.LocalMainTopAppBarManager
import com.orange.ods.app.ui.MainTopAppBarState
import com.orange.ods.app.ui.TopAppBarConfiguration
import com.orange.ods.app.ui.components.Variant
import com.orange.ods.app.ui.components.utilities.ComponentCountRow
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.utilities.NavigationItem
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.FunctionCallCode
import com.orange.ods.app.ui.utilities.composable.Subtitle
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.chip.OdsChoiceChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing
import com.orange.ods.compose.text.OdsTextH1
import com.orange.ods.compose.text.OdsTextH2
import com.orange.ods.compose.text.OdsTextH3
import com.orange.ods.compose.text.OdsTextH4
import kotlin.math.max

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentTopAppBar(variant: Variant) {
    val customizationState = rememberTopAppBarCustomizationState()
    val isLargeVariant = variant == Variant.AppBarsTopLarge

    with(customizationState) {
        val customActionCount = max(0, actionCount.value - MainTopAppBarState.DefaultConfiguration.actions.size)
        val customActions = NavigationItem.values()
            .take(customActionCount)
            .map { TopAppBarConfiguration.Action.Custom(stringResource(id = it.textResId), it.iconResId) }
        val topAppBarConfiguration = TopAppBarConfiguration.Builder()
            .large(isLargeVariant)
            .navigationIconEnabled(isNavigationIconEnabled)
            .actions {
                addAll(MainTopAppBarState.DefaultConfiguration.actions.take(actionCount.value))
                addAll(customActions)
                if (isOverflowMenuEnabled) add(TopAppBarConfiguration.Action.OverflowMenu)
            }
            .build()

        with(LocalMainTopAppBarManager.current) {
            updateTopAppBar(topAppBarConfiguration)
            if (isLargeVariant) updateTopAppBarTitle(titleLength.value.titleResId)
        }

        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {
                OdsListItem(
                    text = stringResource(id = R.string.component_app_bars_top_element_navigation_icon),
                    trailing = OdsSwitchTrailing(
                        checked = navigationIconEnabled
                    )
                )
                ComponentCountRow(
                    modifier = Modifier.padding(start = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                    title = stringResource(id = R.string.component_app_bars_top_actions_count),
                    count = actionCount,
                    minusIconContentDescription = stringResource(id = R.string.component_app_bars_top_remove_action),
                    plusIconContentDescription = stringResource(id = R.string.component_app_bars_top_add_action),
                    minCount = minActionCount,
                    maxCount = maxActionCountSelectable
                )
                OdsListItem(
                    text = stringResource(id = R.string.component_app_bars_top_element_overflow_menu),
                    trailing = OdsSwitchTrailing(
                        checked = overflowMenuEnabled,
                        enabled = isOverflowMenuSwitchEnabled
                    )
                )
                if (isLargeVariant) {
                    Subtitle(textRes = R.string.component_element_title, horizontalPadding = true)
                    OdsChoiceChipsFlowRow(
                        selectedChip = titleLength,
                        modifier = Modifier
                            .padding(horizontal = dimensionResource(id = R.dimen.spacing_m))
                            .padding(bottom = dimensionResource(id = R.dimen.spacing_s)),
                        outlinedChips = true
                    ) {
                        OdsChoiceChip(textRes = R.string.component_app_bars_top_large_title_one_line, value = TopAppBarCustomizationState.TitleLength.OneLine)
                        OdsChoiceChip(textRes = R.string.component_app_bars_top_large_title_two_lines, value = TopAppBarCustomizationState.TitleLength.TwoLines)
                        OdsChoiceChip(
                            textRes = R.string.component_app_bars_top_large_title_truncated,
                            value = TopAppBarCustomizationState.TitleLength.Truncated
                        )
                    }
                }
            }) {

            val context = LocalContext.current
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                OdsTextH1(text = "test pour scroll")
                OdsTextH2(text = "test pour scroll")
                OdsTextH3(text = "test pour scroll")
                OdsTextH4(text = "test pour scroll")
                CodeImplementationColumn(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin))) {
                    FunctionCallCode(
                        name = if (isLargeVariant) OdsComposable.OdsLargeTopAppBar.name else OdsComposable.OdsTopAppBar.name,
                        exhaustiveParameters = false,
                        parameters = {
                            title(context.getString(R.string.component_app_bars_top_regular))

                            if (isNavigationIconEnabled) {
                                composable(name = "navigationIcon") {
                                    FunctionCallCode(
                                        name = "Icon",
                                        parameters = {
                                            simple("imageVector", "<image vector>")
                                            contentDescription(context.getString(R.string.top_app_bar_back_icon_desc))
                                        }
                                    )
                                }
                            }

                            composable(name = "actions") {
                                repeat(actionCount.value) {
                                    FunctionCallCode(
                                        name = OdsComposable.OdsTopAppBarActionButton.name,
                                        parameters = {
                                            onClick()
                                            painter()
                                            contentDescription("icon description")
                                        }
                                    )
                                }
                                if (isOverflowMenuEnabled) {
                                    FunctionCallCode(
                                        name = OdsComposable.OdsTopAppBarOverflowMenuBox.name,
                                        parameters = { string("overflowIconContentDescription", "Open overflow menu") }
                                    ) {
                                        for (i in 1..2) {
                                            FunctionCallCode(
                                                name = OdsComposable.OdsDropdownMenuItem.name,
                                                parameters = {
                                                    text("Menu $i")
                                                    onClick()
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}
