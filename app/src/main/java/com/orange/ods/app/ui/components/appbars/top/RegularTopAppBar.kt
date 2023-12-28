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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.R
import com.orange.ods.app.ui.components.utilities.ComponentCountRow
import com.orange.ods.app.ui.utilities.code.CodeBackgroundColumn
import com.orange.ods.app.ui.utilities.code.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.code.FunctionCallCode
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.appbar.top.OdsTopAppBar
import com.orange.ods.compose.component.listitem.OdsListItem
import com.orange.ods.compose.component.menu.OdsDropdownMenu

@Composable
fun RegularTopAppBarContent() {
    val context = LocalContext.current

    with(LocalTopAppBarCustomizationState.current) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = dimensionResource(id = R.dimen.screen_vertical_margin)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CodeImplementationColumn(
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                contentBackground = false
            ) {
                CodeBackgroundColumn {
                    FunctionCallCode(
                        name = OdsComposable.OdsTopAppBar.name,
                        exhaustiveParameters = false,
                        parameters = {
                            title(context.getString(com.orange.ods.app.R.string.component_app_bars_top_regular))

                            if (isNavigationIconEnabled) {
                                classInstance<OdsTopAppBar.NavigationIcon>("navigationIcon") {
                                    imageVector()
                                    contentDescription(context.getString(com.orange.ods.app.R.string.top_app_bar_back_icon_desc))
                                }
                            }

                            list("actions") {
                                repeat(actionCount.intValue) {
                                    classInstance<OdsTopAppBar.ActionButton> {
                                        onClick()
                                        painter()
                                        contentDescription("icon description")
                                    }
                                }
                            }

                            if (isOverflowMenuEnabled) {
                                list("overflowMenuItems") {
                                    for (i in 1..2) {
                                        classInstance<OdsDropdownMenu.Item> {
                                            text("Menu $i")
                                            onClick()
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

@Composable
fun RegularTopAppBarBottomSheetContent() {
    with(LocalTopAppBarCustomizationState.current) {
        TopAppBarNavigationIconSetup()
        ComponentCountRow(
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.screen_horizontal_margin)),
            title = stringResource(id = com.orange.ods.app.R.string.component_app_bars_top_actions_count),
            count = actionCount,
            minusIconContentDescription = stringResource(id = com.orange.ods.app.R.string.component_app_bars_top_remove_action),
            plusIconContentDescription = stringResource(id = com.orange.ods.app.R.string.component_app_bars_top_add_action),
            minCount = minActionCount,
            maxCount = maxActionCountSelectable
        )
        OdsListItem(
            text = stringResource(id = com.orange.ods.app.R.string.component_app_bars_top_element_overflow_menu),
            trailing = OdsListItem.TrailingSwitch(overflowMenuEnabled.value, { overflowMenuEnabled.value = it }, isOverflowMenuSwitchEnabled)
        )
    }
}