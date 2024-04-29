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
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.LocalThemeManager
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.app.ui.utilities.DrawableManager
import com.orange.ods.app.ui.utilities.code.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.code.FunctionCallCode
import com.orange.ods.app.ui.utilities.extension.buildImageRequest
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.card.OdsCard
import com.orange.ods.compose.component.card.OdsVerticalImageFirstCard
import com.orange.ods.compose.theme.OdsTheme

@Composable
fun CardVerticalImageFirst(customizationState: CardCustomizationState) {
    val context = LocalContext.current
    val darkModeEnabled = LocalThemeManager.current.darkModeEnabled
    val recipes = LocalRecipes.current
    val recipe = rememberSaveable { recipes.filter { it.description.isNotBlank() }.random() }

    with(customizationState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState())
                .padding(OdsTheme.spacings.medium.dp)
        ) {
            val firstButtonText = stringResource(id = R.string.component_element_first_button)
            val secondButtonText = stringResource(id = R.string.component_element_second_button)
            val cardText = stringResource(id = R.string.component_card_element_card)

            OdsVerticalImageFirstCard(
                title = recipe.title,
                image = OdsCard.Image(
                    rememberAsyncImagePainter(
                        model = buildImageRequest(context, recipe.imageUrl, darkModeEnabled),
                        placeholder = painterResource(id = DrawableManager.getPlaceholderResId()),
                        error = painterResource(id = DrawableManager.getPlaceholderResId(error = true))
                    ),
                    ""
                ),
                subtitle = if (hasSubtitle) recipe.subtitle else null,
                text = if (hasText) recipe.description else null,
                onClick = if (isClickable) {
                    { clickOnElement(context, cardText) }
                } else null,
                firstButton = if (hasFirstButton) OdsCard.Button(firstButtonText) { clickOnElement(context, firstButtonText) } else null,
                secondButton = if (hasSecondButton) OdsCard.Button(secondButtonText) { clickOnElement(context, secondButtonText) } else null,
            )

            CodeImplementationColumn(
                modifier = Modifier.padding(top = OdsTheme.spacings.small.dp)
            ) {
                FunctionCallCode(
                    name = OdsComposable.OdsVerticalImageFirstCard.name,
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
                    }
                )
            }
        }
    }
}