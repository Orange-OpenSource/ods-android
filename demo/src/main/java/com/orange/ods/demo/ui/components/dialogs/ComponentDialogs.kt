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

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.button.OdsButton
import com.orange.ods.compose.component.control.OdsCheckbox
import com.orange.ods.compose.component.dialog.OdsAlertDialog
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.buttons.fullWidthButton
import com.orange.ods.demo.ui.utilities.composable.Title

@ExperimentalMaterialApi
@Composable
fun ComponentDialogsContent() {
    val customizationState = rememberComponentDialogsContentState()
    val openDialog = rememberSaveable { mutableStateOf(false) }
    val closeDialogAction = { openDialog.value = false }
    val confirmActionRes = if (customizationState.isDismissButtonChecked) R.string.component_dialog_action_confirm else R.string.component_dialog_action_ok

    Title(textRes = R.string.component_dialogs_customize, withHorizontalPadding = true)
    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.ods_spacing_xs)))
    DialogCustomizationListItem(R.string.component_element_title, customizationState.titleChecked)
    DialogCustomizationListItem(R.string.component_dialog_element_dismiss_button, customizationState.dismissButtonChecked)
    OdsButton(
        modifier = Modifier.fullWidthButton(),
        text = stringResource(id = R.string.component_dialogs_open_dialog),
        hasPrimaryColor = true,
        onClick = {
            openDialog.value = true
        }
    )

    if (openDialog.value) {
        OdsAlertDialog(
            titleText = if (customizationState.isTitleChecked) stringResource(id = R.string.component_element_title) else null,
            text = stringResource(id = R.string.component_dialog_text),
            confirmButtonText = stringResource(id = confirmActionRes),
            onConfirmButtonClick = closeDialogAction,
            dismissButtonText = if (customizationState.isDismissButtonChecked) stringResource(id = R.string.component_dialog_action_dismiss) else null,
            onDismissButtonClick = closeDialogAction
        )
    }
}

@ExperimentalMaterialApi
@Composable
private fun DialogCustomizationListItem(@StringRes labelRes: Int, checked: MutableState<Boolean>) {
    OdsListItem(
        modifier = Modifier.clickable { checked.value = !checked.value },
        text = stringResource(id = labelRes),
        trailing = { OdsCheckbox(checked = checked.value, onCheckedChange = { checked.value = it }) }
    )
}