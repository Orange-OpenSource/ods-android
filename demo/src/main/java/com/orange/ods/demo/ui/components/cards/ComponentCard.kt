/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.cards

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.Variant
import com.orange.ods.demo.ui.components.utilities.ComponentCountRow
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold

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
                    trailing = OdsSwitchTrailing(checked = clickable)
                )
                if (variant == Variant.CardTitleFirst) {
                    OdsListItem(
                        text = stringResource(id = R.string.component_element_thumbnail),
                        trailing = OdsSwitchTrailing(checked = thumbnailChecked)
                    )
                }
                OdsListItem(
                    text = stringResource(id = R.string.component_element_subtitle),
                    trailing = OdsSwitchTrailing(checked = subtitleChecked)
                )
                if (variant in listOf(Variant.CardTitleFirst, Variant.CardImageFirst)) {
                    OdsListItem(
                        text = stringResource(id = R.string.component_element_text),
                        trailing = OdsSwitchTrailing(checked = textChecked)
                    )
                    ComponentCountRow(
                        title = stringResource(id = R.string.component_card_action_button_count),
                        count = actionButtonCount,
                        minusIconContentDescription = stringResource(id = R.string.component_card_remove_action_button),
                        plusIconContentDescription = stringResource(id = R.string.component_card_add_action_button),
                        modifier = Modifier.padding(start = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                        minCount = CardCustomizationState.MinActionButtonCount,
                        maxCount = CardCustomizationState.MaxActionButtonCount
                    )
                }
            }) {
            when (variant) {
                Variant.CardImageFirst -> CardImageFirst(customizationState = cardCustomizationState)
                Variant.CardSmall -> CardSmall(customizationState = cardCustomizationState)
                Variant.CardTitleFirst -> CardTitleFirst(customizationState = cardCustomizationState)
                else -> {}
            }
        }
    }
}