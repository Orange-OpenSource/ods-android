/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.dialogs

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.dialog.OdsAlertDialog
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.components.utilities.ComponentLaunchContentColumn
import com.orange.ods.demo.ui.components.utilities.clickOnElement

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentDialog() {
    val customizationState = rememberDialogCustomizationState()

    val closeDialogAction = { customizationState.openDialog.value = false }
    val context = LocalContext.current

    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            OdsListItem(
                text = stringResource(id = R.string.component_element_title),
                trailing = OdsSwitchTrailing(checked = customizationState.titleChecked)
            )
            OdsListItem(
                text = stringResource(id = R.string.component_dialog_element_dismiss_button),
                trailing = OdsSwitchTrailing(checked = customizationState.dismissButtonChecked)
            )
        }) {
        ComponentLaunchContentColumn(textRes = R.string.component_dialog_customize, buttonLabelRes = R.string.component_dialog_open) {
            customizationState.openDialog.value = true
        }
        if (customizationState.shouldOpenDialog) {
            val confirmButtonText =
                stringResource(id = if (customizationState.isDismissButtonChecked) R.string.component_dialog_action_confirm else R.string.component_dialog_action_ok)
            val dismissButtonText = stringResource(id = R.string.component_dialog_action_dismiss)

            OdsAlertDialog(
                titleText = if (customizationState.isTitleChecked) stringResource(id = R.string.component_element_title) else null,
                text = stringResource(id = R.string.component_dialog_text),
                confirmButtonText = confirmButtonText,
                onConfirmButtonClick = {
                    clickOnElement(context = context, clickedElement = confirmButtonText)
                    closeDialogAction()
                },
                dismissButtonText = if (customizationState.isDismissButtonChecked) dismissButtonText else null,
                onDismissButtonClick = {
                    clickOnElement(context = context, clickedElement = dismissButtonText)
                    closeDialogAction()
                },
            )
        }

    }
}