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

import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.orange.ods.app.databinding.OdsHorizontalCardBinding
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.LocalThemeManager
import com.orange.ods.app.ui.UiFramework
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.app.ui.utilities.DrawableManager
import com.orange.ods.app.ui.utilities.code.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.code.FunctionCallCode
import com.orange.ods.app.ui.utilities.extension.buildImageRequest
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.card.OdsCard
import com.orange.ods.compose.component.card.OdsHorizontalCard

@Composable
fun CardHorizontal(customizationState: CardCustomizationState) {
    val context = LocalContext.current
    val darkModeEnabled = LocalThemeManager.current.darkModeEnabled
    val recipes = LocalRecipes.current
    val recipe = rememberSaveable { recipes.filter { it.description.isNotBlank() }.random() }

    with(customizationState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState())
                .padding(dimensionResource(id = com.orange.ods.R.dimen.spacing_m))
        ) {
            val title = recipe.title
            val subtitle = if (hasSubtitle) recipe.subtitle else null
            val text = if (hasText) recipe.description else null
            val firstButtonText = stringResource(id = R.string.component_element_first_button)
            val onFirstButtonClick = if (hasFirstButton) {
                { clickOnElement(context, firstButtonText) }
            } else null
            val secondButtonText = stringResource(id = R.string.component_element_second_button)
            val onSecondButtonClick = if (hasSecondButton) {
                { clickOnElement(context, secondButtonText) }
            } else null
            val cardText = stringResource(id = R.string.component_card_element_card)

            val placeholderResId = DrawableManager.getPlaceholderResId()
            val errorPlaceholderResId = DrawableManager.getPlaceholderResId(error = true)

            UiFramework<OdsHorizontalCardBinding>(
                compose = {
                    OdsHorizontalCard(
                        title = title,
                        image = OdsCard.Image(
                            rememberAsyncImagePainter(
                                model = buildImageRequest(context, recipe.imageUrl, darkModeEnabled),
                                placeholder = painterResource(id = placeholderResId),
                                error = painterResource(id = errorPlaceholderResId)
                            ),
                            ""
                        ),
                        subtitle = subtitle,
                        text = text,
                        onClick = if (isClickable) {
                            { clickOnElement(context, cardText) }
                        } else null,
                        firstButton = onFirstButtonClick?.let { OdsCard.Button(firstButtonText, it) },
                        secondButton = onSecondButtonClick?.let { OdsCard.Button(secondButtonText, it) },
                        imagePosition = imagePosition.value,
                        divider = hasDivider
                    )
                },
                xml = {
                    this.title = title
                    this.subtitle = subtitle
                    this.text = text

                    this.firstButtonText = firstButtonText
                    this.secondButtonText = secondButtonText
                    odsHorizontalCard.onFirstButtonClick = onFirstButtonClick
                    odsHorizontalCard.onSecondButtonClick = onSecondButtonClick

                    odsHorizontalCard.image = AppCompatResources.getDrawable(context, placeholderResId)
                    val onDrawable: (Drawable?) -> Unit = { odsHorizontalCard.image = it }
                    val request = ImageRequest.Builder(buildImageRequest(context, recipe.imageUrl, darkModeEnabled))
                        .error(errorPlaceholderResId)
                        .target(onError = onDrawable, onSuccess = onDrawable)
                        .build()
                    context.imageLoader.enqueue(request)
                    this.imagePosition = this@with.imagePosition.value
                }
            )

            CodeImplementationColumn(
                modifier = Modifier.padding(top = dimensionResource(com.orange.ods.R.dimen.spacing_s))
            ) {
                FunctionCallCode(
                    name = OdsComposable.OdsHorizontalCard.name,
                    exhaustiveParameters = false,
                    parameters = {
                        title(recipe.title)
                        classInstance("image", OdsCard.Image::class.java) {
                            painter()
                            contentDescription("")
                        }
                        if (hasSubtitle) subtitle(recipe.subtitle)
                        if (hasText) cardText()
                        if (isClickable) onClick()
                        if (hasFirstButton) {
                            classInstance("firstButton", OdsCard.Button::class.java) {
                                text(firstButtonText)
                                onClick()
                            }
                        }
                        if (hasSecondButton) {
                            classInstance("secondButton", OdsCard.Button::class.java) {
                                text(secondButtonText)
                                onClick()
                            }
                        }
                        enum("imagePosition", imagePosition.value)
                        if (!hasDivider && (hasFirstButton || hasSecondButton)) stringRepresentation("divider", hasDivider)
                    }
                )
            }
        }
    }
}