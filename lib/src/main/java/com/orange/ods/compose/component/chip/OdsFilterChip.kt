/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.chip

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.ChipDefaults.LeadingIconOpacity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.DisabledInteractionSource
import com.orange.ods.compose.component.utilities.OdsImageCircleShape
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsTheme

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/81aa91-chips/b/13c40e" target="_blank">ODS Chips</a>.
 *
 * Chips are small components containing a number of elements that represent a calendar event or contact.
 *
 * Use Accompanist's [Flow Layouts](https://google.github.io/accompanist/flowlayout/) to wrap chips to a new line.
 *
 * @param text Text to display in the chip.
 * @param onClick called when the chip is clicked.
 * @param modifier Modifier to be applied to the chip
 * @param outlined If set to true, a border will be drawn around the chip.
 * @param enabled When disabled, chip will not respond to user input. It will also appear visually
 * disabled and disabled to accessibility services.
 * @param selected Highlight the chip and display a selected icon if set to true.
 * @param leadingAvatar Optional avatar at the start of the chip, preceding the content text.
 * @param leadingContentDescription Content description associated to the leading element.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
@OdsComponentApi
fun OdsFilterChip(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    outlined: Boolean = false,
    enabled: Boolean = true,
    selected: Boolean = false,
    leadingAvatar: Painter? = null,
    leadingContentDescription: String? = null
) {
    val emptyAction = {}

    FilterChip(
        selected = selected,
        onClick = if (enabled) onClick else emptyAction,
        modifier = modifier,
        border = if (outlined) {
            BorderStroke(1.dp, OdsTheme.colors.onSurface.copy(alpha = OdsChipDefaults.SurfaceOverlayOpacity))
        } else null,
        enabled = enabled,
        interactionSource = if (enabled) remember { MutableInteractionSource() } else remember { DisabledInteractionSource() },
        colors = OdsChipDefaults.filterChipColors(),
        leadingIcon = when {
            leadingAvatar != null -> {
                {
                    Box(contentAlignment = Alignment.Center) {
                        OdsImageCircleShape(
                            painter = leadingAvatar,
                            contentDescription = leadingContentDescription,
                            circleSize = dimensionResource(id = R.dimen.icon_size),
                            alpha = if (enabled) 1f else LeadingIconOpacity
                        )
                        if (selected) {
                            OdsImageCircleShape(
                                painter = ColorPainter(color = Color.Black),
                                contentDescription = null,
                                circleSize = dimensionResource(id = R.dimen.icon_size),
                                alpha = LeadingIconOpacity
                            )
                            OdsChipSelectedIcon(tint = OdsTheme.colors.onSurface)
                        }
                    }
                }
            }
            selected -> {
                { OdsChipSelectedIcon() }
            }
            else -> null
        }
    ) {
        Text(
            text = text,
            style = OdsTheme.typography.body2
        ) // Don't use an `OdsText` here cause the color of the chip content is already managed by `OdsChipDefaults.filterChipColors()`
    }
}

@Composable
private fun OdsChipSelectedIcon(tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)) {
    Icon(
        modifier = Modifier.size(dimensionResource(id = R.dimen.chip_icon_size)),
        painter = painterResource(id = R.drawable.ic_check),
        contentDescription = stringResource(id = R.string.state_selected),
        tint = tint
    )
}

@UiModePreviews.OdsFilterChip
@Composable
private fun PreviewOdsFilterChip(@PreviewParameter(OdsFilterChipPreviewParameterProvider::class) outlined: Boolean) = Preview {
    val selected = remember { mutableStateOf(false) }
    OdsFilterChip(
        text = "Text",
        selected = selected.value,
        onClick = { selected.value = !selected.value },
        leadingAvatar = painterResource(id = R.drawable.ic_check),
        outlined = outlined
    )
}

internal class OdsFilterChipPreviewParameterProvider : BasicPreviewParameterProvider<Boolean>(false, true)
