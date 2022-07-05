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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.card.OdsCardSmall
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.components.utilities.clickOnElement
import com.orange.ods.demo.ui.utilities.composable.SwitchListItem

@ExperimentalMaterialApi
@Composable
fun VariantCardSmall() {
    val context = LocalContext.current
    val subtitleIsChecked = rememberSaveable { mutableStateOf(true) }

    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            SwitchListItem(labelRes = R.string.component_element_subtitle, checked = subtitleIsChecked)
        }) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.spacing_m)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_m)),
        ) {
            val cardContainerText = stringResource(id = R.string.component_card_element_container)

            OdsCardSmall(
                modifier = Modifier.weight(0.5f),
                imageRes = R.drawable.placeholder,
                title = stringResource(id = R.string.component_element_title),
                subtitle = if (subtitleIsChecked.value) stringResource(id = R.string.component_element_subtitle) else null,
                onCardClick = { clickOnElement(context, cardContainerText) }
            )
            Box(modifier = Modifier.weight(0.5f))
        }
    }
}