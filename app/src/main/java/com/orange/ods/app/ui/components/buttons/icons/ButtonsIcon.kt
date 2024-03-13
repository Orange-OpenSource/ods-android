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
import com.orange.ods.app.ui.utilities.code.CodeBackgroundColumn
import com.orange.ods.app.ui.utilities.code.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.code.FunctionCallCode
import com.orange.ods.app.ui.utilities.code.XmlViewTag
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.button.OdsIconButton

@Composable
fun ButtonsIcon(customizationState: ButtonIconCustomizationState) {

    with(customizationState) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = dimensionResource(id = com.orange.ods.R.dimen.screen_vertical_margin))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(
                    enabled = isEnabled
                )
            }

            Spacer(modifier = Modifier.padding(top = dimensionResource(com.orange.ods.R.dimen.spacing_s)))

            InvertedBackgroundColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(
                    enabled = isEnabled,
                    invertedTheme = true
                )
            }

            CodeImplementationColumn(
                modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin)),
                composeContent = {
                    FunctionCallCode(
                        name = OdsComposable.OdsIconButton.name,
                        exhaustiveParameters = false,
                        parameters = {
                            classInstance<OdsIconButton.Icon>("icon") {
                                painter()
                                contentDescription("")
                            }
                            if (!isEnabled) enabled(false)
                        }
                    )
                },
                xmlContent = {
                    CodeBackgroundColumn {
                        XmlViewTag(
                            clazz = com.orange.ods.xml.component.button.OdsIconButton::class.java,
                            xmlAttributes = {
                                id("ods_icon_button")
                                layoutWidth()
                                layoutHeight()
                                drawable("icon", "icon")
                                appAttr("iconContentDescription", "Icon description")
                                if (!isEnabled) disabled()
                            }
                        )
                    }
                }
            )
        }
    }
}

@Composable
private fun IconButton(
    enabled: Boolean,
    invertedTheme: Boolean = false
) {
    val context = LocalContext.current
    val iconId = R.drawable.ic_search
    val contentDescription = stringResource(id = R.string.component_button_icon_search_desc)
    val onClick = { clickOnElement(context, context.getString(R.string.component_button_icon)) }

    Box(
        modifier = Modifier.padding(
            horizontal = dimensionResource(com.orange.ods.R.dimen.screen_horizontal_margin),
            vertical = dimensionResource(com.orange.ods.R.dimen.spacing_m)
        )
    ) {
        UiFramework<OdsIconButtonBinding>(
            compose = {
                OdsIconButton(
                    onClick = onClick,
                    icon = OdsIconButton.Icon(painterResource(id = R.drawable.ic_search), contentDescription),
                    enabled = enabled,
                )
            },
            xml = {
                icon = AppCompatResources.getDrawable(context, iconId)
                this.enabled = enabled
                this.invertedTheme = invertedTheme
                iconContentDescription = contentDescription
                odsIconButton.onClick = onClick
            }
        )
    }
}
