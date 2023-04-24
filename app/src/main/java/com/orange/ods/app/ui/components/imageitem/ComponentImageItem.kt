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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Alignment
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
    val imageItemHeight = 250.dp

    with(imageItemCustomizationState) {
        if (!hasText) {
            iconDisplayed.value = false
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
                    trailing = OdsSwitchTrailing(checked = iconDisplayed, enabled = hasText)
                )
                Column(modifier = Modifier.padding(start = dimensionResource(id = R.dimen.spacing_m), end = dimensionResource(id = R.dimen.spacing_m))) {
                    Subtitle(textRes = R.string.component_image_item_sizes)
                    OdsSlider(
                        value = sliderPosition,
                        steps = 1,
                        valueRange = 0f..2f,
                        onValueChange = { sliderPosition = it },
                        leftIcon = leftIcon,
                        leftIconContentDescription = leftIconContentDescription,
                        rightIcon = rightIcon,
                        rightIconContentDescription = rightIconContentDescription
                    )
                }
            }) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AnimatedVisibility(
                    visible = true, enter = fadeIn(),
                    exit = fadeOut()
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
                            .run {
                                when (sliderPosition.toInt()) {
                                    0 -> size(175.dp, 175.dp)
                                    2 -> fillMaxWidth().height(imageItemHeight)
                                    else -> size(imageItemHeight, dimensionResource(id = R.dimen.card_big_image_height))
                                }
                            }
                            .padding(dimensionResource(id = R.dimen.spacing_m)),
                        iconChecked = iconCheckedState.value,
                        iconContentDescription = stringResource(id = R.string.component_button_icon_toggle_favorite_icon_desc),
                        onIconCheckedChange = { checked -> iconCheckedState.value = checked }
                    )
                }
            }
        }
    }
}