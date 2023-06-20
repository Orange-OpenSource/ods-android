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

import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import coil.imageLoader
import coil.request.ImageRequest
import com.orange.ods.app.R
import com.orange.ods.app.databinding.OdsBannerBinding
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.LocalUiFramework
import com.orange.ods.app.ui.UiFramework
import com.orange.ods.app.ui.components.utilities.ComponentCountRow
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.app.ui.utilities.DrawableManager
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.FunctionCallCode
import com.orange.ods.app.ui.utilities.composable.TechnicalText
import com.orange.ods.compose.OdsComposable
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
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                val message = if (hasTwoTextLines) recipe.description else recipe.title
                val button1Text = stringResource(id = R.string.component_banner_dismiss)
                val onButton1ClickText = stringResource(id = R.string.component_element_button1)
                val onButton1Click = { clickOnElement(context, onButton1ClickText) }
                val button2Text = if (hasButton2) stringResource(id = R.string.component_banner_detail) else null
                val onButton2ClickText = stringResource(id = R.string.component_element_button2)
                val onButton2Click = { clickOnElement(context, onButton2ClickText) }
                val placeholderResId = DrawableManager.getPlaceholderResId()
                val errorPlaceholderResId = DrawableManager.getPlaceholderResId(error = true)
                UiFramework<OdsBannerBinding>(
                    compose = {
                        OdsBanner(
                            message = message,
                            button1Text = button1Text,
                            button2Text = button2Text,
                            image = if (hasImage) rememberAsyncImagePainter(
                                model = recipe.imageUrl,
                                placeholder = painterResource(id = placeholderResId),
                                error = painterResource(id = errorPlaceholderResId)
                            ) else null,
                            onButton1Click = onButton1Click,
                            onButton2Click = onButton2Click,
                        )
                    },
                    xml = {
                        this.message = message
                        this.button1Text = button1Text
                        this.button2Text = button2Text
                        banner.onButton1Click = onButton1Click
                        banner.onButton2Click = onButton1Click
                        if (hasImage) {
                            banner.image = AppCompatResources.getDrawable(context, placeholderResId)
                            val request = ImageRequest.Builder(context)
                                .data(recipe.imageUrl)
                                .error(errorPlaceholderResId)
                                .target { banner.image = it }
                                .build()
                            context.imageLoader.enqueue(request)
                        } else {
                            banner.image = null
                        }
                    }
                )

                CodeImplementationColumn(
                    modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                    xmlAvailable = true
                ) {
                    FunctionCallCode(
                        name = OdsComposable.OdsBanner.name,
                        exhaustiveParameters = false,
                        parameters = {
                            string("message", if (hasTwoTextLines) recipe.description else recipe.title)
                            button1Text(context.getString(R.string.component_banner_dismiss))
                            if (hasImage) image()
                            if (hasButton2) button2Text(context.getString(R.string.component_banner_detail))
                        }
                    )
                }

            }
        }
    }
}
