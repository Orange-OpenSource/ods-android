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
import com.orange.ods.app.ui.utilities.code.CodeBackgroundColumn
import com.orange.ods.app.ui.utilities.code.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.code.FunctionCallCode
import com.orange.ods.app.ui.utilities.code.XmlViewTag
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.button.OdsIconButton
import com.orange.ods.compose.component.button.OdsIconToggleButton

@Composable
fun ButtonsIconToggle(customizationState: ButtonIconCustomizationState) {
    val buttonCheckedState = rememberSaveable { mutableStateOf(false) }

    with(customizationState) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = dimensionResource(id = com.orange.ods.R.dimen.screen_vertical_margin))
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

            Spacer(modifier = Modifier.padding(top = dimensionResource(com.orange.ods.R.dimen.spacing_s)))

            InvertedBackgroundColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                IconToggleButton(
                    checked = buttonCheckedState.value,
                    onCheckedChange = onCheckedChange,
                    enabled = isEnabled,
                    invertedTheme = true
                )
            }

            val uncheckedIconAttrName = "uncheckedIcon"
            val checkedIconAttrName = "checkedIcon"
            CodeImplementationColumn(
                modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin)),
                composeContent = {
                    FunctionCallCode(
                        name = OdsComposable.OdsIconToggleButton.name,
                        exhaustiveParameters = false,
                        parameters = {
                            classInstance<OdsIconButton.Icon>(uncheckedIconAttrName) {
                                painter()
                                contentDescription("")
                            }
                            classInstance<OdsIconButton.Icon>(checkedIconAttrName) {
                                painter()
                                contentDescription("")
                            }
                            checked(buttonCheckedState.value)
                            if (!isEnabled) enabled(false)
                        }
                    )
                },
                xmlContent = {
                    CodeBackgroundColumn {
                        XmlViewTag(
                            clazz = com.orange.ods.xml.component.button.OdsIconToggleButton::class.java,
                            xmlAttributes = {
                                id("ods_icon_toggle_button")
                                layoutWidth()
                                layoutHeight()
                                appAttr("checked", "${buttonCheckedState.value}")
                                drawable(uncheckedIconAttrName, "unchecked_icon")
                                drawable(checkedIconAttrName, "checked_icon")
                                appAttr("uncheckedIconContentDescription", "")
                                appAttr("checkedIconContentDescription", "")
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
private fun IconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    enabled: Boolean,
    invertedTheme: Boolean = false
) {
    val context = LocalContext.current
    val uncheckedIconResId = R.drawable.ic_heart_outlined
    val checkedIconResId = R.drawable.ic_heart
    val uncheckedIconContentDescription = stringResource(id = R.string.component_button_icon_toggle_favorite_add_icon_desc)
    val checkedIconContentDescription = stringResource(id = R.string.component_button_icon_toggle_favorite_remove_icon_desc)

    Box(
        modifier = Modifier.padding(
            horizontal = dimensionResource(com.orange.ods.R.dimen.screen_horizontal_margin),
            vertical = dimensionResource(com.orange.ods.R.dimen.spacing_m)
        )
    ) {
        UiFramework<OdsIconToogleButtonBinding>(
            compose = {
                OdsIconToggleButton(
                    checked = checked,
                    uncheckedIcon = OdsIconButton.Icon(painterResource(id = uncheckedIconResId), uncheckedIconContentDescription),
                    checkedIcon = OdsIconButton.Icon(painterResource(id = checkedIconResId), checkedIconContentDescription),
                    onCheckedChange = onCheckedChange,
                    enabled = enabled,
                )
            }, xml = {
                this.checked = checked
                uncheckedIcon = AppCompatResources.getDrawable(context, uncheckedIconResId)
                checkedIcon = AppCompatResources.getDrawable(context, checkedIconResId)
                uncheckedIconDescription = uncheckedIconContentDescription
                checkedIconDescription = checkedIconContentDescription
                this.enabled = enabled
                this.invertedTheme = invertedTheme
                odsIconToggleButton.onCheckedChange = onCheckedChange
            }
        )
    }
}
