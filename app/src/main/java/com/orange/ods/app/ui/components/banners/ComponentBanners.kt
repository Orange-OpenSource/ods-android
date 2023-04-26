/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.banners

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.components.utilities.ComponentCountRow
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.CodeParameter
import com.orange.ods.app.ui.utilities.composable.FunctionCallCode
import com.orange.ods.app.ui.utilities.composable.PredefinedParameter
import com.orange.ods.app.ui.utilities.composable.StringParameter
import com.orange.ods.compose.component.OdsComponent
import com.orange.ods.compose.component.banner.OdsBanner
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentBanners() {

    val bannerCustomizationState = rememberBannerCustomizationState()
    val recipes = LocalRecipes.current
    val recipe = rememberSaveable { recipes.filter { it.description.isNotBlank() }.random() }

    with(bannerCustomizationState) {
        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {
                ComponentCountRow(
                    title = stringResource(id = R.string.component_banner_text_lines_count),
                    count = textLinesCount,
                    minusIconContentDescription = stringResource(id = R.string.component_remove_action_button),
                    plusIconContentDescription = stringResource(id = R.string.component_add_action_button),
                    modifier = Modifier.padding(start = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                    minCount = BannerCustomizationState.MinTextCount,
                    maxCount = BannerCustomizationState.MaxTextCount
                )
                ComponentCountRow(
                    title = stringResource(id = R.string.component_banner_buttons_count),
                    count = buttonsCount,
                    minusIconContentDescription = stringResource(id = R.string.component_banner_remove_action_button),
                    plusIconContentDescription = stringResource(id = R.string.component_banner_add_action_button),
                    modifier = Modifier.padding(start = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                    minCount = BannerCustomizationState.MinActionButtonCount,
                    maxCount = BannerCustomizationState.MaxActionButtonCount
                )
                OdsListItem(
                    text = stringResource(id = R.string.component_banner_image),
                    trailing = OdsSwitchTrailing(checked = imageChecked)
                )
            }
        ) {
            val context = LocalContext.current
            val button1Text = stringResource(id = R.string.component_element_button1)
            val button2Text = stringResource(id = R.string.component_element_button2)
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                OdsBanner(
                    message = if (hasTwoTextLines) recipe.description else recipe.title,
                    button1Text = stringResource(id = R.string.component_banner_dismiss),
                    button2Text = if (hasButton2) stringResource(id = R.string.component_banner_detail) else null,
                    image = if (hasImage) rememberAsyncImagePainter(
                        model = recipe.imageUrl,
                        placeholder = painterResource(id = R.drawable.placeholder),
                        error = painterResource(id = R.drawable.placeholder)
                    ) else null,
                    onButton1Click = { clickOnElement(context, button1Text) },
                    onButton2Click = { clickOnElement(context, button2Text) }
                )

                CodeImplementationColumn(
                    modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin))
                ) {
                    FunctionCallCode(name = OdsComponent.OdsBanner.name, exhaustiveParameters = false, parameters = mutableListOf<CodeParameter>(
                        StringParameter("message", if (hasTwoTextLines) recipe.description else recipe.title),
                        PredefinedParameter.Button1Text(stringResource(id = R.string.component_banner_dismiss)),
                    ).apply {
                        if (hasImage) add(PredefinedParameter.Image)
                        if (hasButton2) add(PredefinedParameter.Button2Text(stringResource(id = R.string.component_banner_detail)))
                    })
                }
            }
        }
    }
}

