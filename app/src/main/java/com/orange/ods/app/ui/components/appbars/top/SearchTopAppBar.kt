/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
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
import com.orange.ods.R
import com.orange.ods.app.ui.utilities.code.CodeBackgroundColumn
import com.orange.ods.app.ui.utilities.code.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.code.FunctionCallCode
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.appbar.top.OdsTopAppBar

@Composable
fun SearchTopAppBarContent() {
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
                        name = OdsComposable.OdsSearchTopAppBar.name,
                        exhaustiveParameters = false,
                        parameters = {
                            string("searchHint", "Search")
                            lambda("onSearchValueChange", "value -> doSomethingWith(value)")
                            if (isNavigationIconEnabled) {
                                classInstance<OdsTopAppBar.NavigationIcon>("navigationIcon") {
                                    imageVector()
                                    contentDescription(context.getString(com.orange.ods.app.R.string.top_app_bar_back_icon_desc))
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}