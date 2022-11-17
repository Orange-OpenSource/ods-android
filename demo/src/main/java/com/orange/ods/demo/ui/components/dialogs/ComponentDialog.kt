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
import com.orange.ods.compose.component.button.OdsButton
import com.orange.ods.compose.component.button.OdsButtonStyle
import com.orange.ods.compose.component.dialog.OdsAlertDialog
import com.orange.ods.compose.text.OdsTextBody1
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.buttons.fullWidthButton
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.components.utilities.clickOnElement
import com.orange.ods.demo.ui.utilities.composable.SwitchListItem

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentDialog() {
    val customizationState = rememberDialogCustomizationState()

    val closeDialogAction = { customizationState.openDialog.value = false }
    val context = LocalContext.current

    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            SwitchListItem(labelRes = R.string.component_element_title, checked = customizationState.titleChecked)
            SwitchListItem(labelRes = R.string.component_dialog_element_dismiss_button, checked = customizationState.dismissButtonChecked)
        }) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(top = dimensionResource(id = R.dimen.screen_vertical_margin), bottom = dimensionResource(id = R.dimen.spacing_s))
        ) {

            OdsTextBody1(
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                text = stringResource(id = R.string.component_dialog_customize)
            )

            OdsButton(
                modifier = Modifier.fullWidthButton(),
                text = stringResource(id = R.string.component_dialog_open),
                style = OdsButtonStyle.Primary,
                onClick = {
                    customizationState.openDialog.value = true
                }
            )

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
}