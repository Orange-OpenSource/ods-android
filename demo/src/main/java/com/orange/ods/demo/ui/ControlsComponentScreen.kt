/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private const val ENABLED = "Enabled"
private const val DISABLED = "Disabled"
private const val ON = "On"
private const val OFF = "Off"

@Composable
fun ControlsComponentScreen() {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Checkboxes()
        RadioButtons()
        Switches()
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
        CompositionLocalProvider(LocalRippleTheme provides RippleCustomTheme) {
            Checkbox(
                checked = isCheckedRemember.value,
                onCheckedChange = { isCheckedRemember.value = it },
                enabled = enabled,
            )
            Text(text = label)
        }
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
        CompositionLocalProvider(LocalRippleTheme provides RippleCustomTheme) {
            RadioButton(
                selected = selectedRadio.value == currentRadio,
                onClick = {
                    selectedRadio.value = currentRadio
                },
                enabled = enabled
            )
            Text(text = label)
        }
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
        CompositionLocalProvider(LocalRippleTheme provides RippleCustomTheme) {
            Switch(
                checked = isSelectedRemember.value,
                onCheckedChange = { isSelectedRemember.value = it },
                enabled = enabled
            )
            Text(text = label)
        }
    }
}

private object RippleCustomTheme : RippleTheme {

    @Composable
    override fun defaultColor() =
        RippleTheme.defaultRippleColor(
            MaterialTheme.colors.primary,
            lightTheme = true
        )

    @Composable
    override fun rippleAlpha(): RippleAlpha =
        RippleTheme.defaultRippleAlpha(
            Color.Black,
            lightTheme = true
        )
}