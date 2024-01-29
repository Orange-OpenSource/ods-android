/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
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
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.button.OdsIconToggleButtonsRow
import com.orange.ods.compose.text.OdsTextBodyM
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.extension.simpleNestedName

@Composable
fun ButtonsIconToggleGroup(customizationState: ButtonIconCustomizationState) {
    val icons =
        LocalRecipes.current.distinctBy { it.iconResId }.filter { it.iconResId != null }.take(ButtonIconCustomizationState.MaxToggleCount).map { recipe ->
            OdsIconToggleButtonsRow.Icon(painterResource(id = recipe.iconResId!!), recipe.title, customizationState.enabled.value)
        }
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

    with(customizationState) {
        val displayedIcons = icons.take(toggleCount.intValue)

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = dimensionResource(id = com.orange.ods.R.dimen.screen_vertical_margin))
        ) {

            ToggleButtonsRow(
                icons = displayedIcons,
                selectedIndex = selectedIndex,
                onSelectedIndexChange = { index -> selectedIndex = index },
            )

            Spacer(modifier = Modifier.padding(top = dimensionResource(com.orange.ods.R.dimen.spacing_s)))

            InvertedBackgroundColumn {
                ToggleButtonsRow(
                    icons = displayedIcons,
                    selectedIndex = selectedIndex,
                    onSelectedIndexChange = { index -> selectedIndex = index },
                    displaySurface = displaySurface
                )
            }

            CodeImplementationColumn(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                composeContent = {
                    FunctionCallCode(
                        name = OdsComposable.OdsIconToggleButtonsRow.name,
                        exhaustiveParameters = false,
                        parameters = {
                            list("icons") {
                                repeat(displayedIcons.size) {
                                    classInstance<OdsIconToggleButtonsRow.Icon> {
                                        painter()
                                        contentDescription("")
                                        if (!isEnabled) enabled(false)
                                    }
                                }
                            }
                            stringRepresentation("selectedIndex", selectedIndex)
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
                                appAttr("selectedIndex", "$selectedIndex")
                            }
                        )
                    }
                    OdsTextBodyM(
                        modifier = Modifier.padding(
                            top = dimensionResource(id = R.dimen.spacing_s),
                            bottom = dimensionResource(id = R.dimen.spacing_xs)
                        ),
                        text = stringResource(id = com.orange.ods.app.R.string.component_button_icon_toggle_group_code_add_icons)
                    )
                    CodeBackgroundColumn {
                        TechnicalText(text = "binding.odsIconToggleButtonsRow.icons = listOf(")
                        IndentCodeColumn {
                            repeat(displayedIcons.size) {
                                FunctionCallCode(name = OdsIconToggleButtonsRow.Icon::class.java.simpleNestedName, trailingComma = true, parameters = {
                                    painter()
                                    contentDescription("")
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
    icons: List<OdsIconToggleButtonsRow.Icon>,
    selectedIndex: Int,
    onSelectedIndexChange: (Int) -> Unit,
    displaySurface: OdsDisplaySurface = OdsDisplaySurface.Default
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(com.orange.ods.R.dimen.spacing_m))
            .padding(horizontal = dimensionResource(com.orange.ods.R.dimen.screen_horizontal_margin)),
        horizontalArrangement = Arrangement.Center
    ) {
        UiFramework<OdsIconToggleButtonsGroupBinding>(
            compose = {
                OdsIconToggleButtonsRow(
                    icons = icons,
                    selectedIndex = selectedIndex,
                    onSelectedIndexChange = onSelectedIndexChange,
                    displaySurface = displaySurface
                )
            }, xml = {
                this.odsIconToggleButtonsRow.icons = icons
                this.selectedIndex = selectedIndex
                this.displaySurface = displaySurface
                this.odsIconToggleButtonsRow.onSelectedIndexChange = onSelectedIndexChange
            }
        )
    }
}