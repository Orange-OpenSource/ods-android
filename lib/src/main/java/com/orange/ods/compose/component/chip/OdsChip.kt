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

import androidx.compose.foundation.layout.size
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ChipDefaults.LeadingIconOpacity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.dimensionResource
import com.orange.ods.R
import com.orange.ods.compose.component.utilities.OdsImageCircleShape
import com.orange.ods.compose.text.OdsTextBody2

@ExperimentalMaterialApi
@Composable
fun OdsChip(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: Painter? = null,
    leadingAvatar: Painter? = null,
    leadingElementContentDescription: String? = null
) {
    Chip(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        leadingIcon = when {
            leadingIcon != null -> {
                {
                    Icon(
                        modifier = Modifier.size(dimensionResource(id = R.dimen.chip_icon_size)),
                        painter = leadingIcon,
                        contentDescription = leadingElementContentDescription
                    )
                }
            }
            leadingAvatar != null -> {
                {
                    OdsImageCircleShape(
                        painter = leadingAvatar,
                        contentDescription = leadingElementContentDescription,
                        circleSize = dimensionResource(id = R.dimen.icon_size),
                        alpha = if (enabled) 1f else LeadingIconOpacity
                    )
                }
            }
            else -> null
        },
        content = {
            OdsTextBody2(text = text)
        }
    )
}


@ExperimentalMaterialApi
@Composable
private fun odsChipColors() = ChipDefaults.chipColors(

)