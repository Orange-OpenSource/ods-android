/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.component.OdsCardImageFirst
import com.orange.ods.demo.R

@ExperimentalMaterialApi
@Composable
fun ComponentsCardScreen() {
    val context = LocalContext.current

    val textIsChecked = remember { mutableStateOf(true) }
    val subtitleIsChecked = remember { mutableStateOf(true) }
    val button1IsChecked = remember { mutableStateOf(true) }
    val button2IsChecked = remember { mutableStateOf(true) }

    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 56.dp,
        sheetContent = {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Swipe up to Customize"
            )
            LabelledCheckbox(subtitleIsChecked, "Subtitle")
            LabelledCheckbox(textIsChecked, "Text")
            LabelledCheckbox(button1IsChecked, "Button 1")
            LabelledCheckbox(button2IsChecked, "Button 2")
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(state = rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OdsCardImageFirst(
                imageRes = R.drawable.picture_guideline_iconography,
                title = "Title",
                subtitle = if (subtitleIsChecked.value) {
                    "SubTitle"
                } else {
                    null
                },
                text = if (textIsChecked.value) {
                    "Lorem ipsum dolor sit amet, at blandit nec tristique porttitor."
                } else {
                    null
                },
                onCardClick = {
                    Toast.makeText(context, "Click on Card", Toast.LENGTH_LONG).show()
                },
                button1Text = if (button1IsChecked.value) {
                    "Button 1"
                } else {
                    null
                },
                onButton1Click = {
                    Toast.makeText(context, "Click on Button 1", Toast.LENGTH_LONG).show()
                },
                button2Text = if (button2IsChecked.value) {
                    "Button 2"
                } else {
                    null
                },
                onButton2Click = {
                    Toast.makeText(context, "Click on Button 2", Toast.LENGTH_LONG).show()
                }
            )
        }
    }
}

@Composable
private fun LabelledCheckbox(isChecked: MutableState<Boolean>, label: String) {
    Row(
        modifier = Modifier.padding(0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked.value,
            onCheckedChange = { isChecked.value = it },
            enabled = true,
        )
        Text(text = label)
    }
}