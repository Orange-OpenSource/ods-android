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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.component.controls.OdsCheckbox
import com.orange.ods.compose.component.controls.OdsRadioButton
import com.orange.ods.compose.component.controls.OdsSwitch
import com.orange.ods.compose.theme.SliderActiveTickColor

private const val ENABLED = "Enabled"
private const val DISABLED = "Disabled"
private const val ON = "On"
private const val OFF = "Off"

@Composable
fun ComponentsControlsScreen() {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Checkboxes()
        RadioButtons()
        Switches()
        Sliders()
    }
}

@Composable
fun ControlTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier.padding(16.dp),
        style = MaterialTheme.typography.h5
    )
}

@Composable
fun Checkboxes() {
    Column {
        ControlTitle("Checkboxes")
        Row {
            LabelledCheckbox(
                modifier = Modifier.weight(0.5f),
                isChecked = true,
                label = ENABLED,
                enabled = true
            )
            LabelledCheckbox(
                modifier = Modifier.weight(0.5f),
                isChecked = false,
                label = ENABLED,
                enabled = true
            )
        }
        Row {
            LabelledCheckbox(
                modifier = Modifier.weight(0.5f),
                isChecked = true,
                label = DISABLED,
                enabled = false
            )
            LabelledCheckbox(
                modifier = Modifier.weight(0.5f),
                isChecked = false,
                label = DISABLED,
                enabled = false
            )
        }
    }
}

@Composable
fun RadioButtons() {
    Column {
        ControlTitle("Radio Buttons")
        Row {
            val selectedRadio = remember { mutableStateOf(ON) }
            LabelledRadioButton(
                modifier = Modifier.weight(0.5F),
                selectedRadio = selectedRadio,
                currentRadio = ON,
                label = ENABLED,
                enabled = true
            )
            LabelledRadioButton(
                modifier = Modifier.weight(0.5F),
                selectedRadio = selectedRadio,
                currentRadio = OFF,
                label = ENABLED,
                enabled = true
            )
        }
        Row {
            val selectedRadio = remember { mutableStateOf(ON) }
            LabelledRadioButton(
                modifier = Modifier.weight(0.5F),
                selectedRadio = selectedRadio,
                currentRadio = ON,
                label = DISABLED,
                enabled = false
            )
            LabelledRadioButton(
                modifier = Modifier.weight(0.5F),
                selectedRadio = selectedRadio,
                currentRadio = OFF,
                label = DISABLED,
                enabled = false
            )
        }
    }
}

@Composable
fun Switches() {
    Column {
        ControlTitle("Switches")
        Row {
            LabelledSwitch(
                modifier = Modifier.weight(0.5F),
                label = ENABLED,
                isSelected = true,
                enabled = true
            )
            LabelledSwitch(
                modifier = Modifier.weight(0.5F),
                label = ENABLED,
                isSelected = false,
                enabled = true
            )
        }
        Row {
            LabelledSwitch(
                modifier = Modifier.weight(0.5F),
                label = DISABLED,
                isSelected = true,
                enabled = false
            )
            LabelledSwitch(
                modifier = Modifier.weight(0.5F),
                label = DISABLED,
                isSelected = false,
                enabled = false
            )
        }
    }
}

@Composable
fun Sliders() {
    Column {
        ControlTitle(title = "Sliders")
        var sliderPosition by remember { mutableStateOf(0f) }
        var sliderPosition2 by remember { mutableStateOf(0f) }
        Text(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            text = "Discrete"
        )
        Slider(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            value = sliderPosition2,
            steps = 10,
            onValueChange = { sliderPosition2 = it },
            colors = SliderDefaults.colors(
                activeTickColor = SliderActiveTickColor //Cannot use primary alpha color, it will not be visible, need to use plain color
            )
        )
        Text(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            text = "Continuous"
        )
        Slider(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            value = sliderPosition,
            onValueChange = { sliderPosition = it }
        )
        Box(modifier = Modifier.padding(bottom = 64.dp)) {}
    }
}

@Composable
fun LabelledCheckbox(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    label: String,
    enabled: Boolean
) {
    Row(
        modifier = modifier.padding(start = 24.dp, end = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val isCheckedRemember = remember { mutableStateOf(isChecked) }
        OdsCheckbox(
            checked = isCheckedRemember.value,
            onCheckedChange = { isCheckedRemember.value = it },
            enabled = enabled,
        )
        Text(text = label)
    }
}

@Composable
fun LabelledRadioButton(
    modifier: Modifier = Modifier,
    selectedRadio: MutableState<String>,
    currentRadio: String,
    label: String,
    enabled: Boolean
) {
    Row(
        modifier = modifier.padding(start = 24.dp, end = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OdsRadioButton(
            selected = selectedRadio.value == currentRadio,
            onClick = {
                selectedRadio.value = currentRadio
            },
            enabled = enabled
        )
        Text(text = label)
    }
}

@Composable
fun LabelledSwitch(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    label: String,
    enabled: Boolean
) {
    Row(
        modifier = modifier.padding(start = 24.dp, end = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val isSelectedRemember = remember { mutableStateOf(isSelected) }
        OdsSwitch(
            checked = isSelectedRemember.value,
            onCheckedChange = { isSelectedRemember.value = it },
            enabled = enabled
        )
        Text(text = label)
    }
}