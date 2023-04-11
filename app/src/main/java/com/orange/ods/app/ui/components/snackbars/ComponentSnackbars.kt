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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.components.utilities.ComponentLaunchContentColumn
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.app.ui.utilities.composable.CodeImplementation
import com.orange.ods.app.ui.utilities.composable.TextValueParameter
import com.orange.ods.compose.component.OdsComponent
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing
import com.orange.ods.compose.component.snackbar.OdsSnackbar
import com.orange.ods.compose.component.snackbar.OdsSnackbarHost
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentSnackbars() {
    val context = LocalContext.current
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    val actionButtonChecked = rememberSaveable { mutableStateOf(false) }
    val actionOnNewLineChecked = rememberSaveable { mutableStateOf(false) }
    if (!actionButtonChecked.value) {
        actionOnNewLineChecked.value = false
    }

    val snackbarMessage = stringResource(id = R.string.component_snackbar_message)
    val snackbarActionLabel = stringResource(id = R.string.component_snackbar_action_label)
    val snackbarActionButton = stringResource(id = R.string.component_snackbar_action_button)

    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = bottomSheetScaffoldState,
        snackbarHost = {
            OdsSnackbarHost(hostState = it) { data ->
                OdsSnackbar(snackbarData = data, actionOnNewLine = actionOnNewLineChecked.value, onActionClick = {
                    clickOnElement(context = context, clickedElement = snackbarActionButton)
                })
            }
        },
        bottomSheetContent = {
            OdsListItem(
                text = stringResource(id = R.string.component_snackbar_action_button),
                trailing = OdsSwitchTrailing(checked = actionButtonChecked)
            )
            OdsListItem(
                text = stringResource(id = R.string.component_snackbar_action_on_new_line),
                trailing = OdsSwitchTrailing(checked = actionOnNewLineChecked, enabled = actionButtonChecked.value)
            )
        }) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            ComponentLaunchContentColumn(textRes = R.string.component_snackbar_customize, buttonLabelRes = R.string.component_snackbar_show) {
                coroutineScope.launch {
                    bottomSheetScaffoldState.snackbarHostState.showSnackbar(
                        message = snackbarMessage,
                        actionLabel = if (actionButtonChecked.value) snackbarActionLabel else null,
                    )
                }
            }

            // TODO afficher code lancement
            CodeImplementation("OdsSnackbarHost").CodeImplementationColumn(
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                codeParameters = listOf(TextValueParameter.ValueOnlyParameter("hostState", "<SnackbarHostState>"))
            ) {
                CodeImplementation(OdsComponent.OdsSnackbar.name).ComponentCode(
                    parameters = mutableListOf<TextValueParameter>(
                        TextValueParameter.ValueOnlyParameter("snackbarData", "data") // TODO supprimer les "
                    ).apply {
                        if (actionOnNewLineChecked.value) add(TextValueParameter.StringRepresentationParameter("actionOnNewLine", true))
                        add(TextValueParameter.LambdaParameter("onActionClick"))
                    }
                )
            }
        }
    }
}
