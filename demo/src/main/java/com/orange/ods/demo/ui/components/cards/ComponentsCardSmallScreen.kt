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

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.component.OdsCardSmall
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.utilities.LabelledCheckbox

@ExperimentalMaterialApi
@Composable
fun ComponentsCardSmallScreen() {
    val context = LocalContext.current

    val subtitleIsChecked = remember { mutableStateOf(true) }

    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 56.dp,
        sheetContent = {
            Text(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.ods_spacing_s)),
                text = stringResource(id = R.string.component_customize)
            )
            LabelledCheckbox("Subtitle", subtitleIsChecked)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.ods_spacing_s)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.ods_spacing_s)),
        ) {
            OdsCardSmall(
                modifier = Modifier.weight(0.5f),
                imageRes = R.drawable.picture_component_cards,
                title = "Title",
                subtitle = if (subtitleIsChecked.value) {
                    "SubTitle"
                } else {
                    null
                },
                onCardClick = {
                    Toast.makeText(context, "Click on Card", Toast.LENGTH_LONG).show()
                }
            )
            Box(modifier = Modifier.weight(0.5f)) {}
        }
    }
}