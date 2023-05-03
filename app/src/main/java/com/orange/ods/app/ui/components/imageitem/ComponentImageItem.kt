/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.imageitem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.utilities.composable.Subtitle
import com.orange.ods.compose.component.chip.OdsChoiceChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.imageitem.OdsImageItem
import com.orange.ods.compose.component.imageitem.OdsImageItemDisplayTitle
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentImageItem() {
    val imageItemCustomizationState = rememberImageItemCustomizationState()
    val iconCheckedState = rememberSaveable { mutableStateOf(false) }
    val recipes = LocalRecipes.current
    val recipe = rememberSaveable { recipes.random() }

    with(imageItemCustomizationState) {
        if (!hasText) {
            iconDisplayed.value = false
        }
        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {
                OdsChoiceChipsFlowRow(
                    selectedChip = type,
                    modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.spacing_m)),
                    outlinedChips = true
                ) {
                    Subtitle(textRes = R.string.component_element_type)
                    OdsChoiceChip(textRes = R.string.component_image_item_overlay_text, value = ImageItemCustomizationState.Type.Overlay)
                    OdsChoiceChip(textRes = R.string.component_image_item_below_text, value = ImageItemCustomizationState.Type.Below)
                    OdsChoiceChip(textRes = R.string.component_element_none, value = ImageItemCustomizationState.Type.None)
                }
                OdsListItem(
                    text = stringResource(id = R.string.component_element_icon),
                    trailing = OdsSwitchTrailing(checked = iconDisplayed, enabled = hasText)
                )
            }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = dimensionResource(id = com.orange.ods.R.dimen.spacing_m),
                        start = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)
                    ),
                horizontalAlignment = Alignment.Start,
            ) {
                OdsImageItem(
                    image = rememberAsyncImagePainter(
                        model = recipe.imageUrl,
                        placeholder = painterResource(id = R.drawable.placeholder),
                        error = painterResource(id = R.drawable.placeholder)
                    ),
                    uncheckedIcon = painterResource(id = R.drawable.ic_heart_outlined),
                    checkedIcon = painterResource(id = R.drawable.ic_heart),
                    iconSelected = hasIcon,
                    title = if (hasText) recipe.title else null,
                    modifier = Modifier
                        .width(dimensionResource(id = com.orange.ods.R.dimen.card_big_image_height)),
                    iconChecked = iconCheckedState.value,
                    iconContentDescription = stringResource(id = R.string.component_button_icon_toggle_favorite_icon_desc),
                    onIconCheckedChange = { checked -> iconCheckedState.value = checked },
                    displayTitle = if (isOverlay) OdsImageItemDisplayTitle.Overlay else if (isBelow) OdsImageItemDisplayTitle.Below else OdsImageItemDisplayTitle.None,
                )
            }
        }
    }
}