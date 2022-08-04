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

import android.content.res.Configuration
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
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.orange.ods.R
import com.orange.ods.compose.component.utilities.DisabledInteractionSource
import com.orange.ods.compose.component.utilities.OdsImageCircleShape
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.text.OdsTextBody2

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
@ExperimentalMaterialApi
@Composable
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
            BorderStroke(1.dp, MaterialTheme.colors.onSurface.copy(alpha = ChipSurfaceOverlayOpacity))
        } else null,
        enabled = enabled,
        interactionSource = if (enabled) remember { MutableInteractionSource() } else remember { DisabledInteractionSource() },
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
                            OdsChipSelectedIcon(tint = MaterialTheme.colors.onSurface)
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
        OdsTextBody2(text = text)
    }
}

@Composable
private fun OdsChipSelectedIcon(tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)) {
    Icon(
        modifier = Modifier.size(dimensionResource(id = R.dimen.chip_icon_size)),
        painter = painterResource(id = R.drawable.ic_check),
        contentDescription = stringResource(id = R.string.content_desc_selected),
        tint = tint
    )
}

@Composable
@ExperimentalMaterialApi
private fun PreviewOdsFilterChip() = Preview {
    val selected = remember { mutableStateOf(false) }
    OdsFilterChip(
        text = "Text",
        selected = selected.value,
        onClick = { selected.value = !selected.value },
        leadingAvatar = painterResource(id = R.drawable.ic_check)
    )
}

@Preview(name = "OdsFilterChip - Light")
@Composable
@ExperimentalMaterialApi
private fun PreviewOdsFilterChipLight() = PreviewOdsFilterChip()

@Preview(
    name = "OdsFilterChip - Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
@ExperimentalMaterialApi
private fun PreviewOdsFilterChipDark() = PreviewOdsFilterChip()
