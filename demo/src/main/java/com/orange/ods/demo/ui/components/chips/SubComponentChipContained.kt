/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.chips

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.orange.ods.compose.component.chip.OdsChip
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.chips.ChipCustomizationState.LeadingElement

@ExperimentalMaterialApi
@Composable
fun ChipContainedContent(customizationState: ChipCustomizationState) {
    OdsChip(
        text = "ODS chip",
        onClick = { },
        leadingIcon = if (customizationState.selectedLeadingElement.value == LeadingElement.ICON) painterResource(id = R.drawable.ic_heart) else null,
        leadingAvatar = if (customizationState.selectedLeadingElement.value == LeadingElement.AVATAR) painterResource(id = R.drawable.placeholder_small) else null,
        enabled = !customizationState.disabledChecked.value
    )
}