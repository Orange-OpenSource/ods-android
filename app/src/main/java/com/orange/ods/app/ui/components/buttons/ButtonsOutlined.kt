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

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.layout.Box
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
import com.orange.ods.app.ui.utilities.code.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.code.FunctionCallCode
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.button.OdsButtonIconBuilder
import com.orange.ods.compose.component.button.OdsOutlinedButton
import com.orange.ods.compose.theme.OdsDisplaySurface

@Composable
fun ButtonsOutlined(customizationState: ButtonCustomizationState) {
    with(customizationState) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = dimensionResource(id = com.orange.ods.R.dimen.screen_vertical_margin))
        ) {

            OutlinedButton(leadingIcon = hasLeadingIcon, enabled = isEnabled, fullScreenWidth = hasFullScreenWidth)

            Spacer(modifier = Modifier.padding(top = dimensionResource(com.orange.ods.R.dimen.spacing_s)))

            InvertedBackgroundColumn {
                OutlinedButton(leadingIcon = hasLeadingIcon, enabled = isEnabled, fullScreenWidth = hasFullScreenWidth, displaySurface = displaySurface)
            }

            CodeImplementationColumn(
                modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin)),
                xmlAvailable = true
            ) {
                FunctionCallCode(
                    name = OdsComposable.OdsOutlinedButton.name,
                    exhaustiveParameters = false,
                    parameters = {
                        if (hasFullScreenWidth) fillMaxWidth()
                        if (hasLeadingIcon) {
                            classInstance<OdsButtonIconBuilder>("icon") {
                                painter()
                            }
                        }
                        if (!isEnabled) enabled(false)
                    })
            }
        }
    }
}

@Composable
private fun OutlinedButton(
    leadingIcon: Boolean,
    enabled: Boolean,
    fullScreenWidth: Boolean,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default,
) {
    val context = LocalContext.current
    val text = stringResource(if (enabled) R.string.component_state_enabled else R.string.component_state_disabled)
    val iconId = R.drawable.ic_coffee

    Box(
        modifier = Modifier.padding(
            horizontal = dimensionResource(com.orange.ods.R.dimen.screen_horizontal_margin),
            vertical = dimensionResource(com.orange.ods.R.dimen.spacing_m)
        )
    ) {
        UiFramework<OdsOutlinedButtonBinding>(
            compose = {
                OdsOutlinedButton(
                    modifier = if (fullScreenWidth) Modifier.fillMaxWidth() else Modifier,
                    text = text,
                    onClick = {},
                    icon = if (leadingIcon) OdsButtonIconBuilder(painterResource(id = iconId)) else null,
                    enabled = enabled,
                    displaySurface = displaySurface
                )
            }, xml = {
                this.text = text
                this.icon = if (leadingIcon) AppCompatResources.getDrawable(context, iconId) else null
                this.enabled = enabled
                this.displaySurface = displaySurface

                root.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                val width = if (fullScreenWidth) FrameLayout.LayoutParams.MATCH_PARENT else FrameLayout.LayoutParams.WRAP_CONTENT
                odsOutlinedButton.layoutParams = FrameLayout.LayoutParams(width, FrameLayout.LayoutParams.WRAP_CONTENT)
            }
        )
    }
}