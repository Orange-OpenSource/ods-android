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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.databinding.OdsIconToogleButtonBinding
import com.orange.ods.app.ui.UiFramework
import com.orange.ods.app.ui.components.buttons.InvertedBackgroundColumn
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.FunctionCallCode
import com.orange.ods.app.ui.utilities.composable.IconPainterValue
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.button.OdsIconToggleButton
import com.orange.ods.compose.theme.OdsDisplaySurface

@Composable
fun ButtonsIconToggle(customizationState: ButtonIconCustomizationState) {
    val buttonCheckedState = rememberSaveable { mutableStateOf(false) }

    with(customizationState) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = dimensionResource(id = R.dimen.screen_vertical_margin))
        ) {
            val onCheckedChange: (Boolean) -> Unit = { checked -> buttonCheckedState.value = checked }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                IconToggleButton(
                    checked = buttonCheckedState.value,
                    onCheckedChange = onCheckedChange,
                    enabled = isEnabled
                )
            }

            Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_s)))

            InvertedBackgroundColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                IconToggleButton(
                    checked = buttonCheckedState.value,
                    onCheckedChange = onCheckedChange,
                    enabled = isEnabled,
                    displaySurface = displaySurface
                )
            }

            CodeImplementationColumn(
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                xmlAvailable = true
            ) {
                FunctionCallCode(
                    name = OdsComposable.OdsIconToggleButton.name,
                    exhaustiveParameters = false,
                    parameters = {
                        painter()
                        simple("painterChecked", IconPainterValue)
                        checked(buttonCheckedState.value)
                        if (!isEnabled) enabled(false)
                    }
                )
            }
        }
    }
}

@Composable
private fun IconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    enabled: Boolean,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    val context = LocalContext.current
    val uncheckedIconResId = R.drawable.ic_heart_outlined
    val checkedIconResId = R.drawable.ic_heart
    val iconContentDescription = stringResource(id = R.string.component_button_icon_toggle_favorite_icon_desc)

    Box(modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.screen_horizontal_margin), vertical = dimensionResource(R.dimen.spacing_m))) {
        UiFramework<OdsIconToogleButtonBinding>(
            compose = {
                OdsIconToggleButton(
                    checked = checked,
                    uncheckedPainter = painterResource(id = uncheckedIconResId),
                    checkedPainter = painterResource(id = checkedIconResId),
                    iconContentDescription = iconContentDescription,
                    onCheckedChange = onCheckedChange,
                    enabled = enabled,
                    displaySurface = displaySurface
                )
            }, xml = {
                this.checked = checked
                checkedIcon = AppCompatResources.getDrawable(context, checkedIconResId)
                uncheckedIcon = AppCompatResources.getDrawable(context, uncheckedIconResId)
                iconDescription = iconContentDescription
                this.enabled = enabled
                this.displaySurface = displaySurface
                odsIconToggleButton.onCheckedChange = onCheckedChange
            }
        )
    }
}
