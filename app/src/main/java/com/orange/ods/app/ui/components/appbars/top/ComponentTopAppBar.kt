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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.ui.LocalMainTopAppBarManager
import com.orange.ods.app.ui.TopAppBarConfiguration
import com.orange.ods.app.ui.components.utilities.ComponentCountRow
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.CodeParameter
import com.orange.ods.app.ui.utilities.composable.ComponentCode
import com.orange.ods.app.ui.utilities.composable.ComposableParameter
import com.orange.ods.app.ui.utilities.composable.IconPainterValue
import com.orange.ods.app.ui.utilities.composable.TextValueParameter
import com.orange.ods.compose.component.OdsComponent
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentTopAppBar() {
    val customizationState = rememberTopAppBarCustomizationState()

    with(customizationState) {
        LocalMainTopAppBarManager.current.updateTopAppBar(
            TopAppBarConfiguration(
                isNavigationIconEnabled = isNavigationIconEnabled,
                actionCount = actionCount.value,
                isOverflowMenuEnabled = isOverflowMenuEnabled
            )
        )

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

            }) {

            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                CodeImplementationColumn(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin))) {
                    ComponentCode(
                        name = OdsComponent.OdsTopAppBar.name,
                        parameters = mutableListOf<CodeParameter>(
                            TextValueParameter.Title(stringResource(id = R.string.component_app_bars_top_regular))
                        ).apply {
                            if (isNavigationIconEnabled) add(ComposableParameter(
                                name = "navigationIcon",
                                value = {
                                    ComponentCode(
                                        name = "Icon",
                                        parameters = listOf(
                                            TextValueParameter.ValueOnlyParameter("imageVector", "<image vector>"),
                                            TextValueParameter.ContentDescription(stringResource(id = R.string.top_app_bar_back_icon_desc))
                                        )
                                    )
                                }
                            ))

                            add(ComposableParameter(
                                name = "actions",
                                value = {
                                    repeat(actionCount.value) {
                                        ComponentCode(
                                            name = "OdsTopAppBarActionButton",
                                            parameters = listOf(
                                                TextValueParameter.OnClick,
                                                TextValueParameter.ValueOnlyParameter("painter", IconPainterValue),
                                                TextValueParameter.ContentDescription("icon description")
                                            )
                                        )
                                    }
                                    if (isOverflowMenuEnabled) {
                                        ComponentCode(
                                            name = "OdsTopAppBarOverflowMenuBox",
                                            parameters = listOf(
                                                TextValueParameter.BetweenQuotesParameter("overflowIconContentDescription", "Open overflow menu"),
                                            )
                                        ) {
                                            for (i in 1..2) {
                                                ComponentCode(
                                                    name = "OdsDropdownMenuItem",
                                                    parameters = listOf(
                                                        TextValueParameter.BetweenQuotesParameter("text", "Menu $i"),
                                                        TextValueParameter.OnClick
                                                    )
                                                )

                                            }

                                        }
                                    }
                                }
                            ))
                        }
                    )
                }
            }
        }
    }
}