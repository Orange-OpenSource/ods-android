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

package com.orange.ods.compose.component.chip

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.chip.OdsChipDefaults.defaultFilterChipColors
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.DisabledInteractionSource
import com.orange.ods.compose.component.utilities.OdsPreview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.text.OdsText
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.theme.OdsComponentsConfiguration

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/81aa91-chips/b/13c40e" target="_blank">ODS Chips</a>.
 *
 * Chips are small components containing a number of elements that represent a calendar event or contact.
 * Note that [OdsFilterChip] is displayed outlined or filled according to your [OdsTheme] component configuration, outlined by default.
 *
 * Use Accompanist's [Flow Layouts](https://google.github.io/accompanist/flowlayout/) to wrap chips to a new line.
 *
 * @param text Text to be displayed into the chip.
 * @param onClick Callback called on chip click.
 * @param modifier [Modifier] to be applied to the chip.
 * @param enabled Controls the enabled state of the chip. When `false`, this chip will not respond to user input. It also appears visually
 * disabled and is disabled to accessibility services.
 * @param selected Controls the selected state of the chip. When `true`, the chip is highlighted.
 */
@Composable
@OdsComposable
fun OdsFilterChip(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    selected: Boolean = false,
) {
    OdsFilterChip(
        text = text,
        onClick = onClick,
        modifier = modifier,
        outlined = OdsTheme.componentsConfiguration.chipStyle == OdsComponentsConfiguration.ComponentStyle.Outlined,
        enabled = enabled,
        selected = selected,
    )
}

@Composable
@OdsComposable
private fun OdsFilterChip(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    outlined: Boolean = true,
    enabled: Boolean = true,
    selected: Boolean = false,
) {
    val emptyAction = {}

    FilterChip(
        selected = selected,
        onClick = if (enabled) onClick else emptyAction,
        label = {
            OdsText(
                text = text,
                style = OdsTheme.typography.bodyMedium
            )
        },
        modifier = modifier,
        border = if (outlined && !selected) {
            BorderStroke(1.dp, OdsTheme.colors.onSurface.copy(alpha = OdsChipDefaults.SurfaceOverlayOpacity))
        } else null,
        enabled = enabled,
        interactionSource = if (enabled) remember { MutableInteractionSource() } else remember { DisabledInteractionSource() },
        colors = OdsTheme.defaultFilterChipColors(),
        leadingIcon = if (selected) {
            { OdsChipSelectedIcon() }
        } else null,
    )
}

@Composable
private fun OdsChipSelectedIcon() {
    Icon(
        modifier = Modifier.size(dimensionResource(id = R.dimen.chip_icon_size)),
        painter = painterResource(id = R.drawable.ic_check),
        contentDescription = stringResource(id = R.string.ods_selected_stateA11y),
        tint = LocalContentColor.current
    )
}

private class OdsFilterChipPreviewParameterProvider : BasicPreviewParameterProvider<Boolean>(false, true)

@UiModePreviews.Chip
@Composable
private fun PreviewOdsFilterChip(@PreviewParameter(OdsFilterChipPreviewParameterProvider::class) selected: Boolean) = OdsPreview {
    OdsFilterChip(
        text = "Text",
        selected = selected,
        onClick = { },
    )
}