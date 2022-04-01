/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Colors
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.orange.ods.compose.theme.OdsDisplayAppearance
import com.orange.ods.compose.theme.Transparent
import com.orange.ods.compose.theme.darkThemeColors
import com.orange.ods.compose.theme.lightThemeColors

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/06a393-buttons/b/79b091" target="_blank">ODS Buttons</a>.
 *
 * Outlined buttons are medium-emphasis buttons. They contain actions that are important, but aren't
 * the primary action in an app.
 *
 * @param text Text displayed in the button
 * @param onClick Will be called when the user clicks the button
 * @param modifier Modifier to be applied to the button
 * @param iconRes Drawable resource of the icon. If `null`, no icon will be displayed.
 * @param enabled Controls the enabled state of the button. When `false`, this button will not
 * be clickable
 * @param displayAppearance optional allow to force the button display on a dark or light
 * surface. By default the appearance applied is based on the system night mode value.
 */
@Composable
fun OdsOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes
    iconRes: Int? = null,
    enabled: Boolean = true,
    displayAppearance: OdsDisplayAppearance = OdsDisplayAppearance.DEFAULT
) {
    OutlinedButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        interactionSource = remember { MutableInteractionSource() },
        shape = odsButtonShape,
        border = BorderStroke(
            ButtonDefaults.OutlinedBorderSize,
            if (enabled) {
                MaterialTheme.colors.outlinedButtonColor(displayAppearance)
            } else {
                MaterialTheme.colors.outlinedButtonDisabledColor(displayAppearance)
            }
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Transparent,
            contentColor = MaterialTheme.colors.outlinedButtonColor(displayAppearance),
            disabledContentColor = MaterialTheme.colors.outlinedButtonDisabledColor(displayAppearance)
        )
    ) {
        iconRes?.let { ButtonIcon(it) }
        Text(text.uppercase())
    }
}

@Composable
private fun Colors.outlinedButtonColor(displayAppearance: OdsDisplayAppearance) =
    when (displayAppearance) {
        OdsDisplayAppearance.DEFAULT -> MaterialTheme.colors.onSurface
        OdsDisplayAppearance.ON_DARK -> darkThemeColors.onSurface
        OdsDisplayAppearance.ON_LIGHT -> lightThemeColors.onSurface
    }

@Composable
private fun Colors.outlinedButtonDisabledColor(displayAppearance: OdsDisplayAppearance) =
    outlinedButtonColor(displayAppearance).copy(alpha = ContentAlpha.disabled)