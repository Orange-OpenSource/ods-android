/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ods.compose.component.snackbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarData
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.button.OdsTextButton
import com.orange.ods.compose.component.content.OdsComponentContent
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.text.OdsText
import com.orange.ods.compose.theme.OdsThemeTweak
import com.orange.ods.compose.theme.OdsThemeTweakType
import com.orange.ods.theme.typography.OdsTextStyle

/**
 * Host for [OdsSnackbar]s to be used in [Scaffold] to properly show, hide and dismiss items based
 * on Material specification and the [hostState].
 * The [OdsSnackbarHost] uses the padding provided by the Orange Design System.
 *
 * @see androidx.compose.material.SnackbarHost
 *
 * @param hostState State of this component to read and show [OdsSnackbar]s accordingly.
 * @param modifier [Modifier] applied to the snackbar host.
 * @param snackbar Instance of the [OdsSnackbar] to be shown at the appropriate time with appearance based on
 * the [SnackbarData] provided as a param.
 */
@OdsComposable
@Composable
fun OdsSnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    snackbar: (SnackbarData) -> OdsSnackbarHost.Snackbar = { OdsSnackbarHost.Snackbar(it) }
) {
    SnackbarHost(
        modifier = modifier.padding(dimensionResource(id = R.dimen.spacing_s)),
        hostState = hostState,
        snackbar = { snackbar(it).Content(modifier = Modifier) }
    )
}

/**
 * Contains classes to build an [com.orange.ods.compose.component.snackbar.OdsSnackbarHost].
 */
object OdsSnackbarHost {
    /**
     * A snackbar in an [OdsSnackbarHost].
     *
     * @param data Data used to create the snackbar.
     * @param actionOnNewLine Whether or not the action should be put on a separate line. Recommended for action with long action text.
     * @param onActionClick Callback invoked when the action button is clicked.
     */
    class Snackbar(private val data: SnackbarData, private val actionOnNewLine: Boolean = false, private val onActionClick: () -> Unit = {}) :
        OdsComponentContent<Nothing>(Nothing::class.java) {

        @Composable
        override fun Content(modifier: Modifier) {
            OdsSnackbar(
                modifier = modifier,
                message = data.message,
                actionLabel = data.actionLabel,
                actionOnNewLine = actionOnNewLine,
                onActionClick = onActionClick
            )
        }
    }
}

/**
 * Please directly use [OdsSnackbarHost] to display a snackbar.
 *
 * @param message Text displayed into the snackbar.
 * @param modifier [Modifier] applied to the snackbar layout.
 * @param actionLabel If set, it displays an [OdsTextButton] with the given [actionLabel] as an action of the snackbar.
 * @param actionOnNewLine Whether or not action should be put on a separate line. Recommended for action with long action text.
 * @param onActionClick Callback invoked when the action button is clicked.
 */
@Composable
@OdsComposable
private fun OdsSnackbar(
    message: String,
    modifier: Modifier = Modifier,
    actionLabel: String? = null,
    actionOnNewLine: Boolean = false,
    onActionClick: () -> Unit = {}
) {
    Snackbar(
        modifier = modifier,
        action = actionLabel?.let {
            {
                OdsThemeTweak(tweakType = OdsThemeTweakType.Inverted) {
                    OdsTextButton(
                        style = OdsTextButton.Style.Primary,
                        text = it,
                        onClick = onActionClick
                    )
                }
            }
        },
        actionOnNewLine = actionOnNewLine
    ) {
        OdsText(text = message, style = OdsTextStyle.BodyM)
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsSnackbar(@PreviewParameter(OdsSnackbarPreviewParameterProvider::class) parameter: OdsSnackbarPreviewParameter) = Preview {
    with(parameter) {
        OdsSnackbar(
            message = "This is the message of the snackbar.",
            actionLabel = if (action) "Action" else null,
            actionOnNewLine = actionOnNewLine
        )
    }
}

private data class OdsSnackbarPreviewParameter(
    val action: Boolean,
    val actionOnNewLine: Boolean = false
)

private class OdsSnackbarPreviewParameterProvider :
    BasicPreviewParameterProvider<OdsSnackbarPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OdsSnackbarPreviewParameter>
    get() {
        return listOf(
            OdsSnackbarPreviewParameter(action = false),
            OdsSnackbarPreviewParameter(action = true),
            OdsSnackbarPreviewParameter(action = true, actionOnNewLine = true),
        )
    }