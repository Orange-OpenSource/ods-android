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

package com.orange.ods.app.ui.components.cards

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.ui.components.Variant
import com.orange.ods.app.ui.components.utilities.ComponentCountRow
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.utilities.composable.Subtitle
import com.orange.ods.compose.component.card.OdsCard
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.listitem.OdsListItem

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentCard(variant: Variant) {
    val cardCustomizationState = rememberCardCustomizationState()

    with(cardCustomizationState) {
        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {
                OdsListItem(
                    text = stringResource(id = R.string.component_card_clickable),
                    trailing = OdsListItem.TrailingSwitch(clickable.value, { clickable.value = it })
                )
                if (variant == Variant.CardVerticalHeaderFirst) {
                    OdsListItem(
                        text = stringResource(id = R.string.component_element_thumbnail),
                        trailing = OdsListItem.TrailingSwitch(thumbnailChecked.value, { thumbnailChecked.value = it })
                    )
                } else if (variant == Variant.CardHorizontal) {
                    Subtitle(textRes = R.string.component_card_horizontal_image_position, horizontalPadding = true)
                    OdsChoiceChipsFlowRow(
                        selectedChoiceChipIndex = OdsCard.Image.Position.entries.indexOf(imagePosition.value),
                        modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)),
                        choiceChips = OdsCard.Image.Position.entries.map { imagePosition ->
                            val textResId = when (imagePosition) {
                                OdsCard.Image.Position.Start -> R.string.component_card_horizontal_image_position_start
                                OdsCard.Image.Position.End -> R.string.component_card_horizontal_image_position_end
                            }
                            OdsChoiceChipsFlowRow.ChoiceChip(stringResource(id = textResId), { this.imagePosition.value = imagePosition })
                        }
                    )
                }
                OdsListItem(
                    text = stringResource(id = R.string.component_element_subtitle),
                    trailing = OdsListItem.TrailingSwitch(subtitleChecked.value, { subtitleChecked.value = it })
                )
                if (variant in listOf(Variant.CardVerticalHeaderFirst, Variant.CardVerticalImageFirst, Variant.CardHorizontal)) {
                    OdsListItem(
                        text = stringResource(id = R.string.component_element_text),
                        trailing = OdsListItem.TrailingSwitch(textChecked.value, { textChecked.value = it })
                    )
                    ComponentCountRow(
                        title = stringResource(id = R.string.component_card_action_button_count),
                        count = buttonCount,
                        minusIconContentDescription = stringResource(id = R.string.component_remove_action_button),
                        plusIconContentDescription = stringResource(id = R.string.component_add_action_button),
                        modifier = Modifier.padding(start = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin)),
                        minCount = CardCustomizationState.MinButtonCount,
                        maxCount = CardCustomizationState.MaxButtonCount
                    )
                }
                if (variant == Variant.CardHorizontal) {
                    if (!hasFirstButton) dividerChecked.value = false
                    OdsListItem(
                        text = stringResource(id = R.string.component_element_divider),
                        trailing = OdsListItem.TrailingSwitch(dividerChecked.value, { dividerChecked.value = it }, hasFirstButton)
                    )
                }
            }) {
            when (variant) {
                Variant.CardVerticalImageFirst -> CardVerticalImageFirst(customizationState = cardCustomizationState)
                Variant.CardSmall -> CardSmall(customizationState = cardCustomizationState)
                Variant.CardVerticalHeaderFirst -> CardVerticalHeaderFirst(customizationState = cardCustomizationState)
                Variant.CardHorizontal -> CardHorizontal(customizationState = cardCustomizationState)
                else -> {}
            }
        }
    }
}