/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
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
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.app.ui.utilities.DrawableManager
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.FunctionCallCode
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.card.OdsCardButton
import com.orange.ods.compose.component.card.OdsCardImage
import com.orange.ods.compose.component.card.OdsHorizontalCard

@Composable
fun CardHorizontal(customizationState: CardCustomizationState) {
    val context = LocalContext.current
    val recipes = LocalRecipes.current
    val recipe = rememberSaveable { recipes.filter { it.description.isNotBlank() }.random() }

    with(customizationState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState())
                .padding(dimensionResource(id = com.orange.ods.R.dimen.spacing_m))
        ) {
            val firstButtonText = stringResource(id = R.string.component_element_first_button)
            val secondButtonText = stringResource(id = R.string.component_element_second_button)
            val cardText = stringResource(id = R.string.component_card_element_card)

            OdsHorizontalCard(
                title = recipe.title,
                image = OdsCardImage(
                    rememberAsyncImagePainter(
                        model = recipe.imageUrl,
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
                firstButton = if (hasFirstButton) OdsCardButton(firstButtonText) { clickOnElement(context, firstButtonText) } else null,
                secondButton = if (hasSecondButton) OdsCardButton(secondButtonText) { clickOnElement(context, secondButtonText) } else null,
                imagePosition = imagePosition.value,
                divider = hasDivider
            )

            CodeImplementationColumn(
                modifier = Modifier.padding(top = dimensionResource(com.orange.ods.R.dimen.spacing_s))
            ) {
                FunctionCallCode(
                    name = OdsComposable.OdsHorizontalCard.name,
                    exhaustiveParameters = false,
                    parameters = {
                        enum("imagePosition", imagePosition.value)
                        title(recipe.title)
                        classInstance("image", OdsCardImage::class.java) {
                            painter()
                            contentDescription("")
                        }
                        if (hasSubtitle) subtitle(recipe.subtitle)
                        if (hasText) cardText()
                        if (isClickable) onClick()
                        if (hasFirstButton) {
                            classInstance("firstButton", OdsCardButton::class.java) {
                                text(firstButtonText)
                                onClick()
                            }
                        }
                        if (hasSecondButton) {
                            classInstance("secondButton", OdsCardButton::class.java) {
                                text(secondButtonText)
                                onClick()
                            }
                        }
                        simple("imagePosition", imagePosition.value.name)
                        if (!hasDivider && (hasFirstButton || hasSecondButton)) stringRepresentation("divider", hasDivider)
                    }
                )
            }
        }
    }
}