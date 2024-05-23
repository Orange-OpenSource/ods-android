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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.R
import com.orange.ods.app.databinding.OdsIconToggleButtonsGroupBinding
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.UiFramework
import com.orange.ods.app.ui.components.buttons.InvertedBackgroundColumn
import com.orange.ods.app.ui.utilities.code.CodeBackgroundColumn
import com.orange.ods.app.ui.utilities.code.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.code.FunctionCallCode
import com.orange.ods.app.ui.utilities.code.IndentCodeColumn
import com.orange.ods.app.ui.utilities.code.XmlViewTag
import com.orange.ods.app.ui.utilities.composable.TechnicalText
import com.orange.ods.app.ui.utilities.extension.simpleNestedName
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.button.OdsIconToggleButtonsRow
import com.orange.ods.compose.text.OdsText
import com.orange.ods.compose.theme.OdsTheme

@Composable
fun ButtonsIconToggleGroup(customizationState: ButtonIconCustomizationState) {
    var selectedIconButtonIndex by rememberSaveable { mutableIntStateOf(0) }
    val iconButtons = LocalRecipes.current
        .distinctBy { it.iconResId }
        .filter { it.iconResId != null }
        .take(ButtonIconCustomizationState.MaxToggleCount)
        .mapIndexed { index, recipe ->
            OdsIconToggleButtonsRow.IconButton(
                painterResource(id = recipe.iconResId!!),
                recipe.title,
                { selectedIconButtonIndex = index },
                customizationState.enabled.value
            )
        }

    with(customizationState) {
        val displayedIconButtons = iconButtons.take(toggleCount.intValue)

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = dimensionResource(id = com.orange.ods.R.dimen.screen_vertical_margin))
        ) {

            ToggleButtonsRow(
                selectedIconButtonIndex = selectedIconButtonIndex,
                iconButtons = displayedIconButtons
            )

            Spacer(modifier = Modifier.padding(top = OdsTheme.spacings.small))

            InvertedBackgroundColumn {
                ToggleButtonsRow(
                    selectedIconButtonIndex = selectedIconButtonIndex,
                    iconButtons = displayedIconButtons,
                    invertedTheme = true
                )
            }

            CodeImplementationColumn(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                composeContent = {
                    FunctionCallCode(
                        name = OdsComposable.OdsIconToggleButtonsRow.name,
                        exhaustiveParameters = false,
                        parameters = {
                            stringRepresentation("selectedIconButtonIndex", selectedIconButtonIndex)
                            list("icons") {
                                repeat(displayedIconButtons.size) {
                                    classInstance<OdsIconToggleButtonsRow.IconButton> {
                                        painter()
                                        contentDescription("")
                                        onClick()
                                        if (!isEnabled) enabled(false)
                                    }
                                }
                            }
                        }
                    )
                },
                xmlContent = {
                    CodeBackgroundColumn {
                        XmlViewTag(
                            clazz = com.orange.ods.xml.component.button.OdsIconToggleButtonsRow::class.java,
                            xmlAttributes = {
                                id("ods_icon_toggle_buttons_row")
                                layoutWidth()
                                layoutHeight()
                                appAttr("selectedIconButtonIndex", "$selectedIconButtonIndex")
                            }
                        )
                    }
                    OdsText(
                        modifier = Modifier.padding(
                            top = OdsTheme.spacings.small,
                            bottom = OdsTheme.spacings.extraSmall
                        ),
                        text = stringResource(id = com.orange.ods.app.R.string.component_button_icon_toggle_group_code_add_icons),
                        style = OdsTheme.typography.bodyMedium
                    )
                    CodeBackgroundColumn {
                        TechnicalText(text = "binding.odsIconToggleButtonsRow.icons = listOf(")
                        IndentCodeColumn {
                            repeat(displayedIconButtons.size) {
                                FunctionCallCode(name = OdsIconToggleButtonsRow.IconButton::class.java.simpleNestedName, trailingComma = true, parameters = {
                                    painter()
                                    contentDescription("")
                                    onClick()
                                    if (!isEnabled) enabled(false)
                                })
                            }
                        }
                        TechnicalText(text = ")")
                    }
                }
            )
        }
    }
}

@Composable
private fun ToggleButtonsRow(
    selectedIconButtonIndex: Int,
    iconButtons: List<OdsIconToggleButtonsRow.IconButton>,
    invertedTheme: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = OdsTheme.spacings.medium)
            .padding(horizontal = dimensionResource(com.orange.ods.R.dimen.screen_horizontal_margin)),
        horizontalArrangement = Arrangement.Center
    ) {
        UiFramework<OdsIconToggleButtonsGroupBinding>(
            compose = {
                OdsIconToggleButtonsRow(
                    selectedIconButtonIndex = selectedIconButtonIndex,
                    iconButtons = iconButtons
                )
            }, xml = {
                this.odsIconToggleButtonsRow.iconButtons = iconButtons
                this.selectedIconButtonIndex = selectedIconButtonIndex
                this.invertedTheme = invertedTheme
            }
        )
    }
}