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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.ui.components.Variant
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.utilities.composable.Subtitle
import com.orange.ods.compose.component.button.OdsButtonStyle
import com.orange.ods.compose.component.button.OdsTextButtonStyle
import com.orange.ods.compose.component.chip.OdsChoiceChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing
import com.orange.ods.compose.text.OdsTextBody2
import com.orange.ods.compose.theme.OdsDisplaySurface
import com.orange.ods.compose.theme.OdsTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentButtons(variant: Variant) {
    val customizationState = rememberButtonCustomizationState()

    with(customizationState) {
        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {
                if (variant == Variant.ButtonsFunctional) {
                    this.buttonStyle.value = OdsButtonStyle.FunctionalPositive
                    Subtitle(textRes = R.string.component_button_style_functional, horizontalPadding = true)
                    OdsChoiceChipsFlowRow(
                        selectedChip = buttonStyle,
                        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                        outlinedChips = true
                    ) {
                        OdsChoiceChip(textRes = R.string.component_button_style_functional_positive, value = OdsButtonStyle.FunctionalPositive)
                        OdsChoiceChip(textRes = R.string.component_button_style_functional_negative, value = OdsButtonStyle.FunctionalNegative)
                    }
                } else if (variant == Variant.ButtonsText) {
                    Subtitle(textRes = R.string.component_style, horizontalPadding = true)
                    OdsChoiceChipsFlowRow(
                        selectedChip = textButtonStyle,
                        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                        outlinedChips = true
                    ) {
                        OdsChoiceChip(textRes = R.string.component_button_style_primary, value = OdsTextButtonStyle.Primary)
                        OdsChoiceChip(textRes = R.string.component_button_style_default, value = OdsTextButtonStyle.Default)
                    }
                }
                OdsListItem(
                    text = stringResource(id = R.string.component_element_icon),
                    trailing = OdsSwitchTrailing(checked = leadingIcon)
                )
                OdsListItem(
                    text = stringResource(id = R.string.component_button_full_screen_width),
                    trailing = OdsSwitchTrailing(checked = fullScreenWidth)
                )
                OdsListItem(
                    text = stringResource(id = R.string.component_state_enabled),
                    trailing = OdsSwitchTrailing(checked = enabled)
                )
            }) {

            when (variant) {
                Variant.ButtonsPrimary, Variant.ButtonsDefault, Variant.ButtonsFunctional -> ButtonsContained(customizationState)
                Variant.ButtonsOutlined -> ButtonsOutlined(customizationState)
                Variant.ButtonsText -> ButtonsText(customizationState)
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
            .padding(bottom = dimensionResource(R.dimen.spacing_m)),
        horizontalAlignment = horizontalAlignment
    ) {
        OdsTextBody2(
            modifier = Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.spacing_m))
                .padding(top = dimensionResource(id = R.dimen.spacing_s))
                .fillMaxWidth()
                .align(Alignment.Start),
            text = stringResource(id = textRes),
            displaySurface = displaySurface
        )
        InvertedBackgroundColumnScope(this, displaySurface).content()
    }
}

class InvertedBackgroundColumnScope(scope: ColumnScope, val displaySurface: OdsDisplaySurface) : ColumnScope by scope
