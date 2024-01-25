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

package com.orange.ods.app.ui.components.buttons

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.ui.components.Variant
import com.orange.ods.app.ui.components.utilities.ComponentCountRow
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.utilities.composable.Subtitle
import com.orange.ods.compose.component.button.OdsButton
import com.orange.ods.compose.component.button.OdsTextButton
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.listitem.OdsListItem
import com.orange.ods.compose.text.OdsText
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.theme.typography.OdsTextStyle

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentButtons(variant: Variant) {
    val customizationState = rememberButtonCustomizationState(buttonStyle = rememberSaveable {
        mutableStateOf(
            when (variant) {
                Variant.ButtonsPrimary -> OdsButton.Style.Primary
                Variant.ButtonsFunctional -> OdsButton.Style.FunctionalPositive
                else -> OdsButton.Style.Default
            }
        )
    })

    with(customizationState) {
        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {
                when (variant) {
                    Variant.ButtonsFunctional -> {
                        Subtitle(textRes = R.string.component_button_style_functional, horizontalPadding = true)
                        val buttonStyles = listOf(OdsButton.Style.FunctionalPositive, OdsButton.Style.FunctionalNegative)
                        OdsChoiceChipsFlowRow(
                            selectedChoiceChipIndex = buttonStyles.indexOf(buttonStyle.value),
                            modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)),
                            choiceChips = buttonStyles.map { buttonStyle ->
                                val text = when (buttonStyle) {
                                    OdsButton.Style.FunctionalPositive -> stringResource(id = R.string.component_button_style_functional_positive)
                                    else -> stringResource(id = R.string.component_button_style_functional_negative)
                                }
                                OdsChoiceChipsFlowRow.ChoiceChip(text, { this.buttonStyle.value = buttonStyle })
                            }
                        )
                    }
                    Variant.ButtonsText -> {
                        Subtitle(textRes = R.string.component_style, horizontalPadding = true)
                        OdsChoiceChipsFlowRow(
                            selectedChoiceChipIndex = OdsTextButton.Style.entries.indexOf(textButtonStyle.value),
                            modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)),
                            choiceChips = OdsTextButton.Style.entries.map { textButtonStyle ->
                                val text = when (textButtonStyle) {
                                    OdsTextButton.Style.Default -> stringResource(id = R.string.component_button_style_primary)
                                    OdsTextButton.Style.Primary -> stringResource(id = R.string.component_button_style_default)
                                }
                                OdsChoiceChipsFlowRow.ChoiceChip(text, { this.textButtonStyle.value = textButtonStyle })
                            }
                        )
                    }
                    Variant.ButtonsTextToggleGroup -> {
                        ComponentCountRow(
                            modifier = Modifier.padding(start = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin)),
                            title = stringResource(id = R.string.component_button_icon_toggle_count),
                            count = toggleCount,
                            minusIconContentDescription = stringResource(id = R.string.component_button_icon_toggle_remove),
                            plusIconContentDescription = stringResource(id = R.string.component_button_icon_toggle_add),
                            minCount = ButtonCustomizationState.MinToggleCount,
                            maxCount = ButtonCustomizationState.MaxToggleCount
                        )

                        OdsListItem(
                            text = stringResource(id = R.string.component_buttons_text_toggle_group_same_weight),
                            trailing = OdsListItem.TrailingSwitch(sameItemsWeight.value, { sameItemsWeight.value = it })
                        )
                    }
                    else -> {}
                }

                if (variant != Variant.ButtonsTextToggleGroup) {
                    OdsListItem(
                        text = stringResource(id = R.string.component_element_icon),
                        trailing = OdsListItem.TrailingSwitch(leadingIcon.value, { leadingIcon.value = it })
                    )
                    OdsListItem(
                        text = stringResource(id = R.string.component_button_full_screen_width),
                        trailing = OdsListItem.TrailingSwitch(fullScreenWidth.value, { fullScreenWidth.value = it })
                    )
                }

                OdsListItem(
                    text = stringResource(id = R.string.component_state_enabled),
                    trailing = OdsListItem.TrailingSwitch(enabled.value, { enabled.value = it })
                )
            }) {

            when (variant) {
                Variant.ButtonsPrimary, Variant.ButtonsDefault, Variant.ButtonsFunctional -> ButtonsContained(customizationState)
                Variant.ButtonsOutlined -> ButtonsOutlined(customizationState)
                Variant.ButtonsText -> ButtonsText(customizationState)
                Variant.ButtonsTextToggleGroup -> ButtonsTextToggleButtonsRow(customizationState)
                else -> {}
            }
        }
    }
}

@Composable
fun InvertedBackgroundColumn(
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable InvertedBackgroundColumnScope.() -> Unit
) {
    val backgroundColor: Color
    @StringRes val textRes: Int
    val displaySurface: OdsDisplaySurface
    if (isSystemInDarkTheme()) {
        backgroundColor = OdsTheme.lightThemeColors.surface
        textRes = R.string.component_force_on_light
        displaySurface = OdsDisplaySurface.Light
    } else {
        backgroundColor = OdsTheme.darkThemeColors.surface
        textRes = R.string.component_force_on_dark
        displaySurface = OdsDisplaySurface.Dark
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor)
            .padding(bottom = dimensionResource(com.orange.ods.R.dimen.spacing_m)),
        horizontalAlignment = horizontalAlignment
    ) {
        OdsText(
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m))
                .padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_s))
                .fillMaxWidth()
                .align(Alignment.Start),
            text = stringResource(id = textRes),
            displaySurface = displaySurface,
            style = OdsTextStyle.BodyM
        )
        InvertedBackgroundColumnScope(this, displaySurface).content()
    }
}

class InvertedBackgroundColumnScope(scope: ColumnScope, val displaySurface: OdsDisplaySurface) : ColumnScope by scope
