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

package com.orange.ods.compose.component.dialog

import androidx.compose.material.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogProperties
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.button.OdsTextButton
import com.orange.ods.compose.component.content.OdsComponentContent
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.text.OdsText
import com.orange.ods.compose.text.OdsTextStyle

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/02ae02-dialogs/b/81772e" class="external" target="_blank">ODS alert dialog</a>.
 *
 * Alert dialogs interrupt users with urgent information, details, or actions.
 *
 * The dialog will position its buttons based on the available space. By default it will try to
 * place them horizontally next to each other and fallback to horizontal placement if not enough
 * space is available.
 *
 * @param text Text displayed into the dialog which presents the details regarding the Dialog's purpose.
 * @param confirmButton [OdsAlertDialog.Button] displayed into the dialog which is meant to confirm a proposed action, thus resolving what triggered
 * the dialog
 * @param modifier [Modifier] applied to the layout of the dialog.
 * @param onDismissRequest Callback invoked when the user tries to dismiss the dialog by clicking outside or pressing the back button. This is not called
 * when the dismiss button is clicked.
 * @param dismissButton [OdsAlertDialog.Button] displayed into the dialog which is meant to dismiss the dialog.
 * @param title Title displayed into the dialog which should specify the purpose of the dialog. The title is not mandatory, because there may be
 * sufficient information inside the `text`.
 * @param properties Typically platform specific properties to further configure the dialog.
 */
@Composable
@OdsComposable
fun OdsAlertDialog(
    text: String,
    confirmButton: OdsAlertDialog.Button,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    dismissButton: OdsAlertDialog.Button? = null,
    title: String? = null,
    properties: DialogProperties = DialogProperties()
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        title = title?.let {
            { OdsText(text = title, style = OdsTextStyle.TitleM) }
        },
        text = { OdsText(text, style = OdsTextStyle.BodyM) },
        confirmButton = {
            confirmButton.Content()
        },
        dismissButton = dismissButton?.let { { dismissButton.Content() } },
        properties = properties
    )
}

/**
 * Contains classes to build an [com.orange.ods.compose.component.dialog.OdsAlertDialog].
 */
object OdsAlertDialog {

    /**
     * A button in an [OdsAlertDialog].
     *
     * @constructor Creates an instance of [OdsAlertDialog.Button].
     * @param text Text of the button.
     * @param onClick Will be called when the user clicks the button.
     */
    class Button(private val text: String, private val onClick: () -> Unit) : OdsComponentContent<Nothing>() {

        @Composable
        override fun Content(modifier: Modifier) {
            OdsTextButton(text = text, onClick = onClick, modifier = modifier, style = OdsTextButton.Style.Primary)
        }
    }

}

@UiModePreviews.Default
@Composable
private fun PreviewOdsAlertDialog() = Preview {
    OdsAlertDialog(
        text = "Text",
        confirmButton = OdsAlertDialog.Button("Confirm") {},
        dismissButton = OdsAlertDialog.Button("Dismiss") {},
        title = "Title"
    )
}
