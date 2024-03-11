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

package com.orange.ods.compose.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.OdsPreview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.text.OdsText
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.compose.utilities.extension.enable
import com.orange.ods.theme.typography.OdsTextStyle

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/06a393-buttons/b/79b091" target="_blank">ODS Buttons</a>.
 *
 * Outlined buttons are medium-emphasis buttons. They contain actions that are important, but aren't
 * the primary action in an app.
 *
 * @param text Text displayed into the button.
 * @param onClick Callback invoked on button click.
 * @param modifier [Modifier] applied to the button.
 * @param icon Icon displayed in the button before the text.
 * @param enabled Controls the enabled state of the button. When `false`, the button is not clickable.
 */
@Composable
@OdsComposable
fun OdsOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: OdsButton.Icon? = null,
    enabled: Boolean = true,
) {
    val color = OdsTheme.colors.onSurface
    OutlinedButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        interactionSource = remember { MutableInteractionSource() },
        shape = OdsTheme.shapes.small,
        border = BorderStroke(
            ButtonDefaults.OutlinedBorderSize,
            color.enable(enabled = enabled)
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent,
            contentColor = color,
            disabledContentColor = color.enable(false)
        )
    ) {
        icon?.Content()
        OdsText(
            text = text,
            style = OdsTextStyle.LabelL,
            enabled = enabled
        )
    }
}


@UiModePreviews.Button
@Composable
private fun PreviewOdsOutlinedButton(@PreviewParameter(OdsOutlinedButtonPreviewParameterProvider::class) enabled: Boolean) = OdsPreview {
    OdsOutlinedButton(text = "Text", onClick = {}, enabled = enabled)
}

private class OdsOutlinedButtonPreviewParameterProvider : BasicPreviewParameterProvider<Boolean>(true, false)

