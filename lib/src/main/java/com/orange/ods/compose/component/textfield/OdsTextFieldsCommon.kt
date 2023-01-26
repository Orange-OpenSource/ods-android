/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.textfield

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.DisabledInteractionSource
import com.orange.ods.compose.text.OdsTextCaption
import com.orange.ods.compose.theme.OdsTheme

/**
 * A character counter to display below the text field
 *
 * @param valueLength the text field current value length
 * @param maxChars the maximum of characters to display in the counter. Note: the limitation behavior should be managed by yourself
 * in the `onValueChange` method of the text field.
 * @param enabled set to false to display the text with a disabled color
 */
@Composable
@OdsComponentApi
fun OdsTextFieldCharacterCounter(valueLength: Int, maxChars: Int, enabled: Boolean = true) {
    OdsTextCaption(
        modifier = Modifier
            .padding(top = dimensionResource(id = R.dimen.spacing_xs), end = dimensionResource(id = R.dimen.spacing_m)),
        text = "$valueLength/$maxChars",
        enabled = enabled
    )
}

sealed class OdsTextFieldTrailing
class OdsTextTrailing(val text: String) : OdsTextFieldTrailing()
class OdsIconTrailing(val painter: Painter, val contentDescription: String? = null, val onClick: () -> Unit = {}) : OdsTextFieldTrailing()

@Composable
internal fun OdsTextFieldBottomRow(isError: Boolean, errorMessage: String?, characterCounter: (@Composable () -> Unit)?) {
    Row {
        Box(modifier = Modifier.weight(1f)) {
            if (isError && errorMessage != null) {
                OdsTextFieldErrorText(message = errorMessage)
            }
        }
        characterCounter?.invoke()
    }
}

@Composable
internal fun OdsTextFieldIcon(painter: Painter, contentDescription: String?, onClick: (() -> Unit)?) {
    val interactionSource = if (onClick != null) remember { MutableInteractionSource() } else remember { DisabledInteractionSource() }
    IconButton(onClick = onClick ?: {}, interactionSource = interactionSource) {
        Icon(
            painter = painter,
            contentDescription = contentDescription,
        )
    }
}

@Composable
internal fun OdsTextFieldTrailing(trailing: OdsTextFieldTrailing, value: String, enabled: Boolean = true) {
    when (trailing) {
        is OdsTextTrailing -> {
            Text(
                modifier = Modifier.padding(end = dimensionResource(id = R.dimen.spacing_s)),
                text = trailing.text,
                style = OdsTheme.typography.subtitle1,
                color = OdsTextFieldDefaults.trailingTextColor(value.isEmpty(), enabled)
            )
        }
        is OdsIconTrailing -> {
            OdsTextFieldIcon(
                painter = trailing.painter,
                contentDescription = trailing.contentDescription,
                onClick = if (enabled) trailing.onClick else null,
            )
        }
    }
}

@Composable
private fun OdsTextFieldErrorText(message: String) {
    Text(
        modifier = Modifier.padding(
            start = dimensionResource(id = R.dimen.spacing_m),
            top = dimensionResource(id = R.dimen.spacing_xs)
        ),
        text = message,
        style = OdsTheme.typography.caption,
        color = OdsTheme.colors.error
    )
}

internal enum class PreviewTrailingType {
    Text, Icon, None
}

@Composable
internal fun TrailingPreview(previewTrailingType: PreviewTrailingType, value: String) {
    when (previewTrailingType) {
        PreviewTrailingType.Icon -> {
            OdsTextFieldTrailing(
                trailing = OdsIconTrailing(painter = painterResource(id = android.R.drawable.ic_input_add)),
                value = value
            )
        }
        PreviewTrailingType.Text -> {
            OdsTextFieldTrailing(trailing = OdsTextTrailing(text = "units"), value = value)
        }
        else -> {}
    }
}

internal data class OdsTextFieldPreviewParameter(
    val hasCounter: Boolean,
    val hasErrorMessage: Boolean,
    val isVeryLongErrorMessage: Boolean,
    val previewTrailingType: PreviewTrailingType
)

internal class OdsTextFieldPreviewParameterProvider : BasicPreviewParameterProvider<OdsTextFieldPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OdsTextFieldPreviewParameter>
    get() {
        val booleanValues = listOf(true, false)
        val trailings = PreviewTrailingType.values().toList()

        return booleanValues.flatMap { hasCounter ->
            booleanValues.flatMap { hasErrorMessage ->
                booleanValues.flatMap { isVeryLongErrorMessage ->
                    trailings.map { trailing ->
                        OdsTextFieldPreviewParameter(
                            hasCounter = hasCounter,
                            hasErrorMessage = hasErrorMessage,
                            isVeryLongErrorMessage = isVeryLongErrorMessage,
                            previewTrailingType = trailing
                        )
                    }
                }
            }
        }
    }
