/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.buttons.icons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.ui.components.buttons.InvertedBackgroundColumn
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.CodeParameter
import com.orange.ods.app.ui.utilities.composable.ComposableCode
import com.orange.ods.app.ui.utilities.composable.PredefinedParameter
import com.orange.ods.app.ui.utilities.composable.TechnicalText
import com.orange.ods.compose.component.OdsComponent
import com.orange.ods.compose.component.button.OdsIconButton

@Composable
fun ButtonsIcon(customizationState: ButtonIconCustomizationState) {
    val context = LocalContext.current

    with(customizationState) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = dimensionResource(id = R.dimen.screen_vertical_margin))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                OdsIconButton(
                    onClick = { clickOnElement(context, context.getString(R.string.component_button_icon)) },
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = stringResource(id = R.string.component_button_icon_search_desc),
                    enabled = isEnabled
                )
            }

            Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_s)))

            InvertedBackgroundColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                OdsIconButton(
                    onClick = { clickOnElement(context, context.getString(R.string.component_button_icon)) },
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = stringResource(id = R.string.component_button_icon_search_desc),
                    enabled = isEnabled,
                    displaySurface = displaySurface
                )
            }

            CodeImplementationColumn(
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin))
            ) {
                ComposableCode(
                    name = OdsComponent.OdsIconButton.name,
                    exhaustiveParameters = false,
                    parameters = mutableListOf<CodeParameter>(PredefinedParameter.Painter).apply {
                        if (!isEnabled) {
                            add(PredefinedParameter.Enabled(false))
                        }
                    }
                )
            }
        }
    }
}
