/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.buttons

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.button.OdsButton
import com.orange.ods.compose.component.button.OdsButtonFunctionalType
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.utilities.composable.Title

@Composable
fun VariantButtonsContained() {
    Title(R.string.component_buttons_contained_primary, withHorizontalPadding = true)
    ContainedButtons(functionalType = OdsButtonFunctionalType.Primary)

    Title(R.string.component_buttons_contained_standard, withHorizontalPadding = true)
    ContainedButtons(functionalType = OdsButtonFunctionalType.Default)

    Title(R.string.component_buttons_contained_positive, withHorizontalPadding = true)
    ContainedButtonsOnDefaultSurface(functionalType = OdsButtonFunctionalType.Positive)

    Title(R.string.component_buttons_contained_negative, withHorizontalPadding = true)
    ContainedButtonsOnDefaultSurface(functionalType = OdsButtonFunctionalType.Negative)
}

@Composable
private fun ContainedButtonsOnDefaultSurface(functionalType: OdsButtonFunctionalType) {
    ContainedButtonsEnabledDisabled(functionalType = functionalType, hasIcon = false)
    ContainedButtonsEnabledDisabled(functionalType = functionalType, hasIcon = true)
}

@Composable
private fun ContainedButtons(functionalType: OdsButtonFunctionalType) {
    ContainedButtonsOnDefaultSurface(functionalType)

    Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_s)))

    LightSurface {
        ContainedButtonsEnabledDisabled(functionalType = functionalType, hasIcon = false, displaySurface = OdsDisplaySurface.Light)
    }
    DarkSurface {
        ContainedButtonsEnabledDisabled(functionalType = functionalType, hasIcon = false, displaySurface = OdsDisplaySurface.Dark)
    }
}

@Composable
private fun ContainedButtonsEnabledDisabled(
    functionalType: OdsButtonFunctionalType,
    hasIcon: Boolean,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    OdsButton(
        modifier = Modifier.fullWidthButton(),
        iconRes = if (hasIcon) R.drawable.ic_search else null,
        text = stringResource(R.string.component_state_enabled),
        onClick = {},
        functionalType = functionalType,
        displaySurface = displaySurface
    )
    OdsButton(
        modifier = Modifier.fullWidthButton(false),
        iconRes = if (hasIcon) R.drawable.ic_search else null,
        text = stringResource(R.string.component_state_disabled),
        onClick = {},
        enabled = false,
        functionalType = functionalType,
        displaySurface = displaySurface
    )

}