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
import com.orange.ods.app.ui.TopAppBarConfiguration
import com.orange.ods.app.ui.components.utilities.ComponentCountRow
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.FunctionCallCode
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

            val context = LocalContext.current
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                CodeImplementationColumn(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin))) {
                    FunctionCallCode(
                        name = OdsComponent.OdsTopAppBar.name,
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
                                        name = OdsComponent.OdsTopAppBarActionButton.name,
                                        parameters = {
                                            onClick()
                                            painter()
                                            contentDescription("icon description")
                                        }
                                    )
                                }
                                if (isOverflowMenuEnabled) {
                                    FunctionCallCode(
                                        name = OdsComponent.OdsTopAppBarOverflowMenuBox.name,
                                        parameters = { string("overflowIconContentDescription", "Open overflow menu") }
                                    ) {
                                        for (i in 1..2) {
                                            FunctionCallCode(
                                                name = OdsComponent.OdsDropdownMenuItem.name,
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
