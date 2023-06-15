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

import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.orange.ods.app.databinding.OdsIconButtonBinding
import com.orange.ods.app.ui.UiFramework
import com.orange.ods.app.ui.components.buttons.InvertedBackgroundColumn
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.FunctionCallCode
import com.orange.ods.compose.component.button.OdsIconButton
import com.orange.ods.compose.theme.OdsDisplaySurface

@Composable
fun ButtonsIcon(customizationState: ButtonIconCustomizationState) {

    with(customizationState) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = dimensionResource(id = R.dimen.screen_vertical_margin))
        ) {
            val modifier = Modifier
                .padding(horizontal = dimensionResource(R.dimen.screen_horizontal_margin))
                .padding(top = dimensionResource(R.dimen.spacing_m))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(
                    modifier = modifier,
                    enabled = isEnabled
                )
            }

            Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_s)))

            InvertedBackgroundColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(
                    modifier = modifier,
                    enabled = isEnabled,
                    displaySurface = displaySurface
                )
            }

            CodeImplementationColumn(
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                xmlAvailable = true
            ) {
                FunctionCallCode(
                    name = com.orange.ods.compose.OdsComposable.OdsIconButton.name,
                    exhaustiveParameters = false,
                    parameters = {
                        painter()
                        if (!isEnabled) enabled(false)
                    }
                )
            }
        }
    }
}

@Composable
private fun IconButton(
    modifier: Modifier,
    enabled: Boolean,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    val context = LocalContext.current
    val iconId = R.drawable.ic_search

    Box(modifier = modifier) {
        UiFramework<OdsIconButtonBinding>(
            compose = {
                OdsIconButton(
                    onClick = { clickOnElement(context, context.getString(R.string.component_button_icon)) },
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = stringResource(id = R.string.component_button_icon_search_desc),
                    enabled = enabled,
                    displaySurface = displaySurface
                )
            }, xml = {
                iconbutton.icon = AppCompatResources.getDrawable(context, iconId)
                iconbutton.isEnabled = enabled
                iconbutton.displaySurface = displaySurface
            }
        )
    }
}
