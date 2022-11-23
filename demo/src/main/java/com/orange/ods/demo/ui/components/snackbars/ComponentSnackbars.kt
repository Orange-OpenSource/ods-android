/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.snackbars

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.snackbar.OdsSnackbar
import com.orange.ods.compose.component.snackbar.OdsSnackbarHost
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.components.utilities.ComponentLaunchContentColumn
import com.orange.ods.demo.ui.utilities.composable.SwitchListItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentSnackbars() {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    val actionButtonChecked = rememberSaveable { mutableStateOf(false) }
    val snackbarMessage = stringResource(id = R.string.component_snackbar_message)
    val snackbarActionLabel = stringResource(id = R.string.component_snackbar_action_label)

    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = scaffoldState,
        snackbarHost = {
            OdsSnackbarHost(hostState = it) { data ->
                OdsSnackbar(snackbarData = data)
            }
        },
        bottomSheetContent = {
            SwitchListItem(labelRes = R.string.component_snackbar_action_button, checked = actionButtonChecked)
        }) {
        ComponentLaunchContentColumn(textRes = R.string.component_snackbar_customize, buttonLabelRes = R.string.component_snackbar_show) {
            coroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = snackbarMessage,
                    actionLabel = if (actionButtonChecked.value) snackbarActionLabel else null
                )
            }
        }
    }
}
