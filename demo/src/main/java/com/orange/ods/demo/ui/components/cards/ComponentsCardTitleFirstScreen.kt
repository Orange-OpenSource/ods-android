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

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.component.card.OdsCardTitleFirst
import com.orange.ods.compose.text.OdsTextBody2
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.utilities.LabelledCheckbox

@ExperimentalMaterialApi
@Composable
fun ComponentsCardTitleFirstScreen() {
    val context = LocalContext.current

    val thumbnailIsChecked = remember { mutableStateOf(true) }
    val textIsChecked = remember { mutableStateOf(true) }
    val subtitleIsChecked = remember { mutableStateOf(true) }
    val button1IsChecked = remember { mutableStateOf(true) }
    val button2IsChecked = remember { mutableStateOf(true) }

    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 56.dp,
        sheetContent = {
            OdsTextBody2(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.ods_spacing_s)),
                text = stringResource(id = R.string.component_customize)
            )
            LabelledCheckbox(label = "Thumbnail", checked = thumbnailIsChecked)
            LabelledCheckbox(label = "Subtitle", checked = subtitleIsChecked)
            LabelledCheckbox(label = "Text", checked = textIsChecked)
            LabelledCheckbox(label = "Button 1", checked = button1IsChecked)
            LabelledCheckbox(label = "Button 2", checked = button2IsChecked)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.ods_spacing_s))
                .verticalScroll(state = rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.ods_spacing_s))
        ) {
            OdsCardTitleFirst(
                thumbnailRes = if (thumbnailIsChecked.value) R.drawable.picture_component_cards else null,
                imageRes = R.drawable.picture_guideline_iconography,
                title = "Title",
                subtitle = if (subtitleIsChecked.value) "SubTitle" else null,
                text = if (textIsChecked.value) {
                    "Lorem ipsum dolor sit amet, at blandit nec tristique porttitor."
                } else {
                    null
                },
                onCardClick = { displayToast(context, "Click on Card") },
                button1Text = if (button1IsChecked.value) "Button 1" else null,
                onButton1Click = { displayToast(context, "Click on Button 2") },
                button2Text = if (button2IsChecked.value) "Button 2" else null,
                onButton2Click = { displayToast(context, "Click on Button 2") }
            )
        }
    }
}

private fun displayToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}