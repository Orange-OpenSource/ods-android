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
import androidx.compose.foundation.layout.Spacer
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
import coil.request.ImageRequest
import coil.size.Size
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.app.ui.utilities.DrawableManager
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.FunctionCallCode
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.card.OdsVerticalHeaderFirstCard

@Composable
fun CardVerticalHeaderFirst(customizationState: CardCustomizationState) {
    val context = LocalContext.current
    val recipes = LocalRecipes.current
    val recipe = rememberSaveable { recipes.filter { it.description.isNotBlank() }.random() }

    with(customizationState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = com.orange.ods.R.dimen.spacing_m))
                .verticalScroll(state = rememberScrollState()),
        ) {
            val button1Text = stringResource(id = R.string.component_element_button1)
            val button2Text = stringResource(id = R.string.component_element_button2)
            val cardText = stringResource(id = R.string.component_card_element_card)
            val imagePainter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(context)
                    .data(recipe.imageUrl)
                    .size(Size.ORIGINAL)
                    .build(),
                placeholder = painterResource(id = DrawableManager.getPlaceholderResId()),
                error = painterResource(id = DrawableManager.getPlaceholderResId(error = true))
            )

            OdsVerticalHeaderFirstCard(
                title = recipe.title,
                image = imagePainter,
                thumbnail = if (hasThumbnail) imagePainter else null,
                subtitle = if (hasSubtitle) recipe.subtitle else null,
                text = if (hasText) recipe.description else null,
                onCardClick = if (isClickable) {
                    { clickOnElement(context, cardText) }
                } else null,
                button1Text = if (hasButton1) button1Text else null,
                onButton1Click = { clickOnElement(context, button1Text) },
                button2Text = if (hasButton2) button2Text else null,
                onButton2Click = { clickOnElement(context, button2Text) }
            )

            Spacer(modifier = Modifier.padding(top = dimensionResource(com.orange.ods.R.dimen.spacing_s)))

            CodeImplementationColumn {
                FunctionCallCode(
                    name = OdsComposable.OdsVerticalHeaderFirstCard.name,
                    exhaustiveParameters = false,
                    parameters = {
                        title(recipe.title)
                        image()
                        if (hasThumbnail) simple("thumbnail", "<thumbnail painter>")
                        if (hasSubtitle) subtitle(recipe.subtitle)
                        if (hasText) cardText()
                        if (isClickable) onCardClick()
                        if (hasButton1) {
                            button1Text(button1Text)
                            onButton1Click()
                        }
                        if (hasButton2) {
                            button2Text(button2Text)
                            onButton2Click()
                        }
                    }
                )
            }
        }
    }
}