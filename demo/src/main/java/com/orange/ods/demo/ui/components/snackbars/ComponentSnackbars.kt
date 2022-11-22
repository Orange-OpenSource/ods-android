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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.button.OdsOutlinedButton
import com.orange.ods.compose.component.snackbar.OdsSnackbar
import com.orange.ods.compose.component.snackbar.OdsSnackbarHost
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
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
        Column(modifier = Modifier.fillMaxWidth()) {
            OdsOutlinedButton(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(top = dimensionResource(id = R.dimen.screen_vertical_margin)),
                text = stringResource(id = R.string.component_snackbar_show),
                onClick = {
                    coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = snackbarMessage,
                            actionLabel = if (actionButtonChecked.value) snackbarActionLabel else null
                        )
                    }
                })
        }
    }
}
