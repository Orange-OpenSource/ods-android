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
import com.orange.ods.app.ui.UiFramework
import com.orange.ods.app.ui.components.utilities.ComponentCountRow
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.app.ui.utilities.DrawableManager
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.FunctionCallCode
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.banner.OdsBanner
import com.orange.ods.compose.component.banner.OdsBannerButton
import com.orange.ods.compose.component.banner.OdsBannerImage
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsListItemTrailingSwitch
import com.orange.ods.extension.ifNotNull

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
                    modifier = Modifier.padding(start = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin)),
                    minCount = BannerCustomizationState.MinTextCount,
                    maxCount = BannerCustomizationState.MaxTextCount
                )
                ComponentCountRow(
                    title = stringResource(id = R.string.component_banner_buttons_count),
                    count = buttonsCount,
                    minusIconContentDescription = stringResource(id = R.string.component_banner_remove_action_button),
                    plusIconContentDescription = stringResource(id = R.string.component_banner_add_action_button),
                    modifier = Modifier.padding(start = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin)),
                    minCount = BannerCustomizationState.MinActionButtonCount,
                    maxCount = BannerCustomizationState.MaxActionButtonCount
                )
                OdsListItem(
                    text = stringResource(id = R.string.component_banner_image),
                    trailing = OdsListItemTrailingSwitch(checked = imageChecked.value, { imageChecked.value = it })
                )
            }
        ) {
            val context = LocalContext.current
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                val message = if (hasTwoTextLines) recipe.description else recipe.title
                val firstButtonText = stringResource(id = R.string.component_banner_dismiss)
                val onFirstButtonClickText = stringResource(id = R.string.component_element_first_button)
                val onFirstButtonClick = { clickOnElement(context, onFirstButtonClickText) }
                val secondButtonText = if (hasSecondButton) stringResource(id = R.string.component_banner_detail) else null
                val onSecondButtonClickText = stringResource(id = R.string.component_element_second_button)
                val onSecondButtonClick = if (hasSecondButton) {
                    { clickOnElement(context, onSecondButtonClickText) }
                } else null
                val placeholderResId = DrawableManager.getPlaceholderResId()
                val errorPlaceholderResId = DrawableManager.getPlaceholderResId(error = true)
                UiFramework<OdsBannerBinding>(
                    compose = {
                        OdsBanner(
                            message = message,
                            firstButton = OdsBannerButton(firstButtonText, onFirstButtonClick),
                            image = if (hasImage) {
                                val painter = rememberAsyncImagePainter(
                                    model = recipe.imageUrl,
                                    placeholder = painterResource(id = placeholderResId),
                                    error = painterResource(id = errorPlaceholderResId)
                                )
                                OdsBannerImage(painter, "")
                            } else {
                                null
                            },
                            secondButton = ifNotNull(secondButtonText, onSecondButtonClick) { text, onClick -> OdsBannerButton(text, onClick) }
                        )
                    },
                    xml = {
                        this.message = message
                        this.firstButtonText = firstButtonText
                        this.secondButtonText = secondButtonText
                        odsBanner.onFirstButtonClick = onFirstButtonClick
                        odsBanner.onSecondButtonClick = onFirstButtonClick
                        if (hasImage) {
                            odsBanner.image = AppCompatResources.getDrawable(context, placeholderResId)
                            val request = ImageRequest.Builder(context)
                                .data(recipe.imageUrl)
                                .error(errorPlaceholderResId)
                                .target { odsBanner.image = it }
                                .build()
                            context.imageLoader.enqueue(request)
                        } else {
                            odsBanner.image = null
                        }
                    }
                )

                CodeImplementationColumn(
                    modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin)),
                    xmlAvailable = true
                ) {
                    FunctionCallCode(
                        name = OdsComposable.OdsBanner.name,
                        exhaustiveParameters = false,
                        parameters = {
                            string("message", if (hasTwoTextLines) recipe.description else recipe.title)
                            classInstance<OdsBannerButton>("firstButton") {
                                text(context.getString(R.string.component_banner_dismiss))
                                onClick()
                            }
                            if (hasImage) {
                                classInstance<OdsBannerImage>("image") {
                                    painter()
                                    contentDescription("")
                                }
                            }
                            if (hasSecondButton) {
                                classInstance<OdsBannerButton>("secondButton") {
                                    text(context.getString(R.string.component_banner_detail))
                                    onClick()
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}
