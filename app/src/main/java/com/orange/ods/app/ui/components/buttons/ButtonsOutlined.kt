/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.buttons

import android.app.ActionBar.LayoutParams
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.databinding.OdsOutlinedButtonBinding
import com.orange.ods.app.ui.UiFramework
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.FunctionCallCode
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.button.OdsOutlinedButton
import com.orange.ods.compose.theme.OdsDisplaySurface

@Composable
fun ButtonsOutlined(customizationState: ButtonCustomizationState) {
    with(customizationState) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = dimensionResource(id = R.dimen.screen_vertical_margin))
        ) {
            val context = LocalContext.current
            val text = stringResource(if (isEnabled) R.string.component_state_enabled else R.string.component_state_disabled)
            val icon = R.drawable.ic_coffee
            UiFramework<OdsOutlinedButtonBinding>(
                compose = {
                    OutlinedButton(leadingIcon = hasLeadingIcon, enabled = isEnabled, fullScreenWidth = hasFullScreenWidth)

                    Spacer(modifier = Modifier.padding(top = dimensionResource(R.dimen.spacing_s)))

                    InvertedBackgroundColumn {
                        OutlinedButton(
                            leadingIcon = hasLeadingIcon,
                            enabled = isEnabled,
                            fullScreenWidth = hasFullScreenWidth,
                            displaySurface = displaySurface
                        )
                    }

                    CodeImplementationColumn(
                        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin))
                    ) {
                        FunctionCallCode(
                            name = OdsComposable.OdsOutlinedButton.name,
                            exhaustiveParameters = false,
                            parameters = {
                                if (hasFullScreenWidth) fillMaxWidth()
                                if (hasLeadingIcon) icon()
                                if (!isEnabled) enabled(false)
                            })
                    }
                }, xml = {
                    this.text = text
                    outlinedbutton.icon = if (hasLeadingIcon) AppCompatResources.getDrawable(context, icon) else null
                    outlinedbutton.isEnabled = isEnabled
                    val width = if (hasFullScreenWidth) LayoutParams.MATCH_PARENT else LayoutParams.WRAP_CONTENT
                    outlinedbutton.layoutParams = ViewGroup.LayoutParams(width, LayoutParams.WRAP_CONTENT)
                }

            )
        }
    }
}

@Composable
private fun OutlinedButton(
    leadingIcon: Boolean,
    enabled: Boolean,
    fullScreenWidth: Boolean,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    val modifier = Modifier
        .padding(horizontal = dimensionResource(R.dimen.screen_horizontal_margin))
        .padding(top = dimensionResource(R.dimen.spacing_m))

    OdsOutlinedButton(
        modifier = if (fullScreenWidth) modifier.fillMaxWidth() else modifier,
        text = stringResource(if (enabled) R.string.component_state_enabled else R.string.component_state_disabled),
        onClick = {},
        icon = if (leadingIcon) painterResource(id = R.drawable.ic_coffee) else null,
        enabled = enabled,
        displaySurface = displaySurface
    )
}