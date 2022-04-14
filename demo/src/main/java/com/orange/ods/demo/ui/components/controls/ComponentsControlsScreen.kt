/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.controls

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.control.OdsRadioButton
import com.orange.ods.compose.component.control.OdsSlider
import com.orange.ods.compose.component.control.OdsSwitch
import com.orange.ods.compose.text.OdsTextBody1
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.utilities.ComponentHeader
import com.orange.ods.demo.ui.utilities.LabelledCheckbox
import com.orange.ods.demo.ui.utilities.Subtitle
import com.orange.ods.demo.ui.utilities.Title

@Composable
fun ComponentsControlsScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(bottom = dimensionResource(id = R.dimen.ods_spacing_s))
    ) {
        ComponentHeader(imageRes = R.drawable.picture_component_controls, description = R.string.component_controls_description)
        Checkboxes()
        RadioButtons()
        Switches()
        Sliders()
    }
}

@Composable
fun Checkboxes() {
    Column(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.ods_spacing_s))) {
        Title(R.string.component_controls_checkboxes)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            LabelledCheckbox(
                checked = remember { mutableStateOf(true) },
                label = stringResource(id = R.string.component_state_enabled),
                enabled = true
            )
            LabelledCheckbox(
                checked = remember { mutableStateOf(false) },
                label = stringResource(id = R.string.component_state_enabled),
                enabled = true
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            LabelledCheckbox(
                checked = remember { mutableStateOf(true) },
                label = stringResource(id = R.string.component_state_disabled),
                enabled = false
            )
            LabelledCheckbox(
                checked = remember { mutableStateOf(false) },
                label = stringResource(id = R.string.component_state_disabled),
                enabled = false
            )
        }
    }
}

@Composable
fun RadioButtons() {
    val radio1 = "radio1"
    val radio2 = "radio2"

    Column(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.ods_spacing_s))) {
        Title(R.string.component_controls_radio_buttons)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val selectedRadio = remember { mutableStateOf(radio1) }
            LabelledRadioButton(
                selectedRadio = selectedRadio,
                currentRadio = radio1,
                label = stringResource(id = R.string.component_state_enabled),
                enabled = true
            )
            LabelledRadioButton(
                selectedRadio = selectedRadio,
                currentRadio = radio2,
                label = stringResource(id = R.string.component_state_enabled),
                enabled = true
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val selectedRadio = remember { mutableStateOf(radio1) }
            LabelledRadioButton(
                selectedRadio = selectedRadio,
                currentRadio = radio1,
                label = stringResource(id = R.string.component_state_disabled),
                enabled = false
            )
            LabelledRadioButton(
                selectedRadio = selectedRadio,
                currentRadio = radio2,
                label = stringResource(id = R.string.component_state_disabled),
                enabled = false
            )
        }
    }
}

@Composable
fun Switches() {
    Column(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.ods_spacing_s))) {
        Title(R.string.component_controls_switches)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            LabelledSwitch(
                label = stringResource(id = R.string.component_state_enabled),
                selected = remember { mutableStateOf(true) },
                enabled = true
            )
            LabelledSwitch(
                label = stringResource(id = R.string.component_state_enabled),
                selected = remember { mutableStateOf(false) },
                enabled = true
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            LabelledSwitch(
                label = stringResource(id = R.string.component_state_enabled),
                selected = remember { mutableStateOf(true) },
                enabled = false
            )
            LabelledSwitch(
                label = stringResource(id = R.string.component_state_disabled),
                selected = remember { mutableStateOf(false) },
                enabled = false
            )
        }
    }
}

@Composable
fun Sliders() {
    var discreteSliderPosition by remember { mutableStateOf(0f) }
    var discreteWithIconsSliderPosition by remember { mutableStateOf(0f) }
    var continuousSliderPosition by remember { mutableStateOf(0f) }
    var continuousSliderWithIconsPosition by remember { mutableStateOf(0f) }

    Column(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.ods_screen_horizontal_margin))) {
        Title(R.string.component_controls_sliders)

        Subtitle(R.string.component_controls_slider_discrete, withHorizontalPadding = false)
        OdsSlider(
            value = discreteSliderPosition,
            steps = 10,
            onValueChange = { discreteSliderPosition = it },
        )

        Subtitle(R.string.component_controls_slider_discrete_with_icons, withHorizontalPadding = false)
        OdsSlider(
            value = discreteWithIconsSliderPosition,
            steps = 10,
            onValueChange = { discreteWithIconsSliderPosition = it },
            leftIconRes = R.drawable.ic_heart,
            rightIconRes = R.drawable.ic_heart,
        )

        Subtitle(R.string.component_controls_slider_continuous, withHorizontalPadding = false)
        OdsSlider(
            value = continuousSliderPosition,
            onValueChange = { continuousSliderPosition = it }
        )

        Subtitle(R.string.component_controls_slider_continuous_with_icons, withHorizontalPadding = false)
        OdsSlider(
            value = continuousSliderWithIconsPosition,
            onValueChange = { continuousSliderWithIconsPosition = it },
            leftIconRes = R.drawable.ic_heart,
            rightIconRes = R.drawable.ic_heart,
        )
    }
}

@Composable
private fun LabelledRadioButton(
    selectedRadio: MutableState<String>,
    currentRadio: String,
    label: String,
    enabled: Boolean
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OdsRadioButton(
            selected = selectedRadio.value == currentRadio,
            onClick = {
                selectedRadio.value = currentRadio
            },
            enabled = enabled
        )
        OdsTextBody1(text = label)
    }
}

@Composable
private fun LabelledSwitch(
    selected: MutableState<Boolean>,
    label: String,
    enabled: Boolean
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OdsSwitch(
            checked = selected.value,
            onCheckedChange = { selected.value = it },
            enabled = enabled
        )
        OdsTextBody1(text = label)
    }
}