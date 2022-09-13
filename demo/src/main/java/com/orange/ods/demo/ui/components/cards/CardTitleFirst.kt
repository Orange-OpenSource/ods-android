/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.card.OdsTitleFirstCard
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.components.utilities.clickOnElement
import com.orange.ods.demo.ui.utilities.composable.SwitchListItem

@ExperimentalMaterialApi
@Composable
fun CardTitleFirst() {
    val context = LocalContext.current
    val cardCustomizationState = rememberCardCustomizationState()

    with(cardCustomizationState) {
        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {
                SwitchListItem(labelRes = R.string.component_card_clickable, checked = clickable)
                SwitchListItem(labelRes = R.string.component_element_thumbnail, checked = thumbnailChecked)
                SwitchListItem(labelRes = R.string.component_element_subtitle, checked = subtitleChecked)
                SwitchListItem(labelRes = R.string.component_element_text, checked = textChecked)
                SwitchListItem(labelRes = R.string.component_element_button1, checked = button1Checked)
                SwitchListItem(labelRes = R.string.component_element_button2, checked = button2Checked)
            }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(id = R.dimen.spacing_m))
                    .verticalScroll(state = rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_m))
            ) {
                val button1Text = stringResource(id = R.string.component_element_button1)
                val button2Text = stringResource(id = R.string.component_element_button2)
                val cardContainerText = stringResource(id = R.string.component_card_element_container)

                OdsTitleFirstCard(
                    title = stringResource(id = R.string.component_element_title),
                    image = painterResource(id = R.drawable.placeholder),
                    thumbnail = if (hasThumbnail) painterResource(id = R.drawable.placeholder) else null,
                    subtitle = if (hasSubtitle) stringResource(id = R.string.component_element_subtitle) else null,
                    text = if (hasText) stringResource(id = R.string.component_element_text_value) else null,
                    onCardClick = if (isClickable) {
                        { clickOnElement(context, cardContainerText) }
                    } else null,
                    button1Text = if (hasButton1) button1Text else null,
                    onButton1Click = { clickOnElement(context, button1Text) },
                    button2Text = if (hasButton2) button2Text else null,
                    onButton2Click = { clickOnElement(context, button2Text) }
                )
            }
        }
    }
}