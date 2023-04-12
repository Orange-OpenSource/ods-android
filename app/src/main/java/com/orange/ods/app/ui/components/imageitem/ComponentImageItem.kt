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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.utilities.composable.Subtitle
import com.orange.ods.compose.component.control.OdsSlider
import com.orange.ods.compose.component.imageitem.OdsImageItem
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentImageItem() {
    val imageItemCustomizationState = rememberImageItemCustomizationState()
    val iconCheckedState = rememberSaveable { mutableStateOf(false) }
    val recipes = LocalRecipes.current
    val recipe = rememberSaveable { recipes.random() }
    var sliderPosition by remember { mutableStateOf(0f) }
    val leftIcon = painterResource(id = R.drawable.ic_display_standard_small)
    val leftIconContentDescription = stringResource(id = R.string.component_image_item_small)
    val rightIcon = painterResource(id = R.drawable.ic_display_standard)
    val rightIconContentDescription = stringResource(id = R.string.component_image_item_large)

    with(imageItemCustomizationState) {
        if (!hasText) {
            sideIcons.value = false
        }
        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {
                OdsListItem(
                    text = stringResource(id = R.string.component_element_text),
                    trailing = OdsSwitchTrailing(checked = textDisplayed)
                )
                OdsListItem(
                    text = stringResource(id = R.string.component_element_icon),
                    trailing = OdsSwitchTrailing(checked = sideIcons, enabled = hasText)
                )
                Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                    Subtitle(textRes = R.string.component_image_item_sizes)
                    OdsSlider(
                        value = sliderPosition,
                        steps = 1,
                        valueRange = 0f..20f,
                        onValueChange = { sliderPosition = it },
                        leftIcon = leftIcon,
                        leftIconContentDescription = leftIconContentDescription,
                        rightIcon = rightIcon,
                        rightIconContentDescription = rightIconContentDescription
                    )
                }
            }) {
            Column(
                modifier = if (sliderPosition.toInt() == 0)
                    Modifier
                        .padding(start = 112.dp, end = 112.dp, top = dimensionResource(id = R.dimen.spacing_m))
                        .size(250.dp, 150.dp)
                else if (sliderPosition.toInt() == 10)
                    Modifier
                        .padding(start = 93.dp, end = 93.dp, top = dimensionResource(id = R.dimen.spacing_m))
                        .size(300.dp, 250.dp)
                else
                    Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen.spacing_m),
                            end = dimensionResource(id = R.dimen.spacing_m),
                            top = dimensionResource(id = R.dimen.spacing_m)
                        )
                        .size(400.dp, 250.dp)
            ) {
                OdsImageItem(
                    image = rememberAsyncImagePainter(
                        model = recipe.imageUrl,
                        placeholder = painterResource(id = R.drawable.placeholder),
                        error = painterResource(id = R.drawable.placeholder)
                    ),
                    icon = if (hasSideIcons && !iconCheckedState.value) painterResource(id = R.drawable.ic_heart_outlined)
                    else if (hasSideIcons && iconCheckedState.value) painterResource(id = R.drawable.ic_heart) else null,
                    title = if (hasText) recipe.title else null,
                    checkedIcon = iconCheckedState.value,
                    iconContentDescription = stringResource(id = R.string.component_button_icon_toggle_favorite_icon_desc),
                    onCheckedChange = { checked -> iconCheckedState.value = checked }
                )
            }


        }
    }
}