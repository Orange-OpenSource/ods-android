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

@ExperimentalMaterialApi
@Composable
fun ChipOutlinedContent(customizationState: ChipCustomizationState) {
    OdsChip(
        text = "ODS chip",
        onClick = { },
        leadingIcon = when (customizationState.selectedLeadingElement.value) {
            ChipCustomizationState.LeadingElement.ICON -> painterResource(id = R.drawable.ic_heart)
            ChipCustomizationState.LeadingElement.AVATAR -> painterResource(id = R.drawable.ic_heart)
            ChipCustomizationState.LeadingElement.NONE -> null
        }
    )
}