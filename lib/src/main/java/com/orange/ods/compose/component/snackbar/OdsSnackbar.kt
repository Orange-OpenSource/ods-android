/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.snackbar

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarData
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.button.OdsTextButton
import com.orange.ods.compose.component.button.OdsTextButtonStyle
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.OdsTheme

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/887440-toast--snackbars/b/043ece" class="external" target="_blank">ODS snackbar</a>.
 *
 * @see androidx.compose.material.Snackbar
 *
 * @param message text displayed in the snackbar
 * @param modifier modifiers for the Snackbar layout
 * @param actionLabel if set, it displays an [OdsTextButton] with the given [actionLabel] as an action of the snackbar.
 * @param actionOnNewLine whether or not action should be put on the separate line. Recommended
 * for action with long action text
 * @param onActionClick executed on action button click.
 */
@Composable
@OdsComponentApi
fun OdsSnackbar(
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
                OdsTextButton(
                    style = OdsTextButtonStyle.Primary,
                    displaySurface = OdsSnackbarDefaults.actionButtonDisplaySurface,
                    text = it,
                    onClick = onActionClick
                )
            }
        },
        actionOnNewLine = actionOnNewLine,
        backgroundColor = OdsSnackbarDefaults.backgroundColor,
        contentColor = OdsTheme.colors.surface,
    ) { Text(text = message) }
}

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/887440-toast--snackbars/b/043ece" class="external" target="_blank">ODS snackbar</a>.
 *
 * @see androidx.compose.material.Snackbar
 *
 * @param snackbarData data about the current snackbar showing via [SnackbarHostState]
 * @param modifier modifiers for the Snackbar layout
 * @param actionOnNewLine whether or not action should be put on the separate line. Recommended
 * for action with long action text
 * @param onActionClick executed on action button click.
 */
@Composable
fun OdsSnackbar(
    snackbarData: SnackbarData,
    modifier: Modifier = Modifier,
    actionOnNewLine: Boolean = false,
    onActionClick: () -> Unit = {}
) {
    OdsSnackbar(
        modifier = modifier,
        message = snackbarData.message,
        actionLabel = snackbarData.actionLabel,
        actionOnNewLine = actionOnNewLine,
        onActionClick = onActionClick
    )
}

/**
 * Host for [OdsSnackbar]s to be used in [Scaffold] to properly show, hide and dismiss items based
 * on material specification and the [hostState].
 * The [OdsSnackbarHost] use the padding provided by the Orange Design System.
 *
 * @see androidx.compose.material.SnackbarHost
 *
 * @param hostState state of this component to read and show [OdsSnackbar]s accordingly
 * @param modifier optional modifier for this component
 * @param snackbar the instance of the [OdsSnackbar] to be shown at the appropriate time with
 * appearance based on the [SnackbarData] provided as a param
 */
@Composable
fun OdsSnackbarHost(
    hostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    snackbar: @Composable (SnackbarData) -> Unit = { OdsSnackbar(snackbarData = it) }
) {
    SnackbarHost(
        modifier = modifier.padding(dimensionResource(id = R.dimen.spacing_s)),
        hostState = hostState,
        snackbar = snackbar
    )
}

/**
 * Object to hold defaults used by [OdsSnackbar]
 */
private object OdsSnackbarDefaults {

    /**
     * Default alpha of the overlay applied to the [backgroundColor]
     */
    private const val OdsSnackbarOverlayAlpha = 0.87f

    /**
     * Default background color of the [OdsSnackbar]
     */
    val backgroundColor: Color
        @Composable
        get() = OdsTheme.colors.onSurface
            .copy(alpha = OdsSnackbarOverlayAlpha)
            .compositeOver(OdsTheme.colors.surface)

    val actionButtonDisplaySurface: OdsDisplaySurface
        @Composable
        get() = if (isSystemInDarkTheme()) OdsDisplaySurface.Light else OdsDisplaySurface.Dark

}

@UiModePreviews.Default
@Composable
private fun PreviewOdsSnackbar(@PreviewParameter(OdsSnackbarPreviewParameterProvider::class) actionOnNewLine: Boolean) = Preview {
    OdsSnackbar(
        message = "This is the message of the snackbar.",
        actionLabel = "Action",
        actionOnNewLine = actionOnNewLine
    )
}

private class OdsSnackbarPreviewParameterProvider : BasicPreviewParameterProvider<Boolean>(false, true)
