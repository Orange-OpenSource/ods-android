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

package com.orange.ods.app.ui.components.utilities

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.button.OdsButton
import com.orange.ods.compose.text.OdsText
import com.orange.ods.compose.theme.OdsTheme

@Composable
fun ComponentLaunchContentColumn(@StringRes textRes: Int, @StringRes buttonLabelRes: Int, onButtonClick: () -> Unit) {
    Column(
        modifier = Modifier.padding(
            top = dimensionResource(id = com.orange.ods.R.dimen.screen_vertical_margin),
            bottom = OdsTheme.spacings.small
        )
    ) {
        OdsText(
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin)),
            text = stringResource(id = textRes),
            style = OdsTheme.typography.bodyLarge
        )

        OdsButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(com.orange.ods.R.dimen.screen_horizontal_margin))
                .padding(top = OdsTheme.spacings.medium),
            text = stringResource(id = buttonLabelRes),
            style = OdsButton.Style.Primary,
            onClick = onButtonClick
        )
    }
}