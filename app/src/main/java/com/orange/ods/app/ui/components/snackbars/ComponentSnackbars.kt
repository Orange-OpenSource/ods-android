/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.snackbars

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.components.utilities.ComponentLaunchContentColumn
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.app.ui.utilities.code.CodeBackgroundColumn
import com.orange.ods.app.ui.utilities.code.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.code.FunctionCallCode
import com.orange.ods.app.ui.utilities.code.IndentCodeColumn
import com.orange.ods.app.ui.utilities.composable.TechnicalText
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.snackbar.OdsSnackbar
import com.orange.ods.compose.component.snackbar.OdsSnackbarHost
import com.orange.ods.compose.text.OdsTextBody2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentSnackbars() {
    val context = LocalContext.current
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    var actionButtonChecked by rememberSaveable { mutableStateOf(false) }
    var actionOnNewLineChecked by rememberSaveable { mutableStateOf(false) }
    if (!actionButtonChecked) {
        actionOnNewLineChecked = false
    }

    val snackbarMessage = stringResource(id = R.string.component_snackbar_message)
    val snackbarActionLabel = stringResource(id = R.string.component_snackbar_action_label)
    val snackbarActionButton = stringResource(id = R.string.component_snackbar_action_button)

    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = bottomSheetScaffoldState,
        snackbarHost = {
            OdsSnackbarHost(hostState = it) { data ->
                OdsSnackbar(data = data, actionOnNewLine = actionOnNewLineChecked, onActionClick = {
                    clickOnElement(context = context, clickedElement = snackbarActionButton)
                })
            }
        },
        bottomSheetContent = {
            OdsListItem(
                text = stringResource(id = R.string.component_snackbar_action_button),
                trailing = OdsListItem.TrailingSwitch(actionButtonChecked, { actionButtonChecked = it })
            )
            OdsListItem(
                text = stringResource(id = R.string.component_snackbar_action_on_new_line),
                trailing = OdsListItem.TrailingSwitch(actionOnNewLineChecked, { actionOnNewLineChecked = it }, actionButtonChecked)
            )
        }) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            ComponentLaunchContentColumn(textRes = R.string.component_snackbar_customize, buttonLabelRes = R.string.component_snackbar_show) {
                coroutineScope.launch {
                    bottomSheetScaffoldState.snackbarHostState.showSnackbar(
                        message = snackbarMessage,
                        actionLabel = if (actionButtonChecked) snackbarActionLabel else null
                    )
                }
            }

            CodeImplementationColumn(
                modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin)),
                contentBackground = false
            ) {
                OdsTextBody2(
                    modifier = Modifier.padding(
                        top = dimensionResource(id = com.orange.ods.R.dimen.spacing_s),
                        bottom = dimensionResource(id = com.orange.ods.R.dimen.spacing_xs)
                    ),
                    text = stringResource(id = R.string.component_snackbar_code_first_step)
                )
                CodeBackgroundColumn {
                    FunctionCallCode(
                        name = OdsComposable.OdsSnackbarHost.name,
                        parameters = { simple("hostState", "<SnackbarHostState>") }
                    ) {
                        FunctionCallCode(
                            name = OdsComposable.OdsSnackbar.name,
                            parameters = {
                                simple("snackbarData", "data")
                                if (actionOnNewLineChecked) stringRepresentation("actionOnNewLine", true)
                                lambda("onActionClick")
                            }
                        )
                    }
                }

                OdsTextBody2(
                    modifier = Modifier.padding(
                        top = dimensionResource(id = com.orange.ods.R.dimen.spacing_s),
                        bottom = dimensionResource(id = com.orange.ods.R.dimen.spacing_xs)
                    ),
                    text = stringResource(id = R.string.component_snackbar_code_second_step)
                )
                CodeBackgroundColumn {
                    TechnicalText(text = "coroutineScope.launch {")
                    IndentCodeColumn {
                        FunctionCallCode(
                            name = "scaffoldState.snackbarHostState.showSnackbar",
                            parameters = {
                                string("message", snackbarMessage)
                                if (actionButtonChecked) string("actionLabel", snackbarActionLabel)
                            })
                    }
                    TechnicalText(text = "}")
                }
            }

        }
    }
}
