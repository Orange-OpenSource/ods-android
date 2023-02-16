/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.dialog

import androidx.compose.material.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.button.OdsTextButton
import com.orange.ods.compose.component.button.OdsTextButtonStyle
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.text.OdsTextBody2
import com.orange.ods.compose.text.OdsTextSubtitle1
import com.orange.ods.utilities.extension.ifNotNull

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/02ae02-dialogs/b/81772e" class="external" target="_blank">ODS alert dialog</a>.
 *
 * Alert dialogs interrupt users with urgent information, details, or actions.
 *
 * The dialog will position its buttons based on the available space. By default it will try to
 * place them horizontally next to each other and fallback to horizontal placement if not enough
 * space is available.
 *
 * @param text The text which presents the details regarding the Dialog's purpose.
 * @param confirmButtonText The text of the button which is meant to confirm a proposed action, thus resolving
 * what triggered the dialog.
 * @param onConfirmButtonClick The action executed on confirm button click
 * @param modifier Modifier to be applied to the layout of the dialog.
 * @param onDismissRequest Executes when the user tries to dismiss the Dialog by clicking outside
 * or pressing the back button. This is not called when the dismiss button is clicked.
 * @param dismissButtonText The text of the button which is meant to dismiss the dialog.
 * @param onDismissButtonClick The action executed on dismiss button click
 * @param titleText The title of the Dialog which should specify the purpose of the Dialog. The title
 * is not mandatory, because there may be sufficient information inside the [text].
 * @param properties Typically platform specific properties to further configure the dialog.
 */
@Composable
@OdsComponentApi
fun OdsAlertDialog(
    text: String,
    confirmButtonText: String,
    onConfirmButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    dismissButtonText: String? = null,
    onDismissButtonClick: (() -> Unit)? = null,
    titleText: String? = null,
    properties: DialogProperties = DialogProperties()
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        title = titleText?.let {
            { OdsTextSubtitle1(text = titleText) }
        },
        text = { OdsTextBody2(text) },
        confirmButton = {
            OdsTextButton(text = confirmButtonText, style = OdsTextButtonStyle.Primary, onClick = onConfirmButtonClick)
        },
        dismissButton = ifNotNull(dismissButtonText, onDismissButtonClick) { dismissText, onDismissClick ->
            {
                OdsTextButton(text = dismissText, style = OdsTextButtonStyle.Primary, onClick = onDismissClick)
            }
        },
        properties = properties
    )
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsAlertDialog() = Preview {
    OdsAlertDialog(
        text = "Text",
        confirmButtonText = "Confirm",
        onConfirmButtonClick = {},
        dismissButtonText = "Dismiss ",
        onDismissButtonClick = {},
        titleText = "Title"
    )
}
