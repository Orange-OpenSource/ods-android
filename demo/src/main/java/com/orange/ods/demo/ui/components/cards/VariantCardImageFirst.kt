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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.card.OdsCardImageFirst
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.components.utilities.clickOnElement
import com.orange.ods.demo.ui.utilities.composable.SwitchListItem

@ExperimentalMaterialApi
@Composable
fun VariantCardImageFirst() {
    val context = LocalContext.current
    val textIsChecked = rememberSaveable { mutableStateOf(true) }
    val subtitleIsChecked = rememberSaveable { mutableStateOf(true) }
    val button1IsChecked = rememberSaveable { mutableStateOf(true) }
    val button2IsChecked = rememberSaveable { mutableStateOf(true) }

    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            SwitchListItem(labelRes = R.string.component_element_subtitle, checked = subtitleIsChecked)
            SwitchListItem(labelRes = R.string.component_element_text, checked = textIsChecked)
            SwitchListItem(labelRes = R.string.component_element_button1, checked = button1IsChecked)
            SwitchListItem(labelRes = R.string.component_element_button2, checked = button2IsChecked)
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

            OdsCardImageFirst(
                imageRes = R.drawable.placeholder,
                title = stringResource(id = R.string.component_element_title),
                subtitle = if (subtitleIsChecked.value) stringResource(id = R.string.component_element_subtitle) else null,
                text = if (textIsChecked.value) stringResource(id = R.string.component_element_text_value) else null,
                onCardClick = { clickOnElement(context, cardContainerText) },
                button1Text = if (button1IsChecked.value) button1Text else null,
                onButton1Click = { clickOnElement(context, button1Text) },
                button2Text = if (button2IsChecked.value) button2Text else null,
                onButton2Click = { clickOnElement(context, button2Text) }
            )
        }
    }
}