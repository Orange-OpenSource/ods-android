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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.component.button.OdsButton
import com.orange.ods.compose.component.button.OdsOutlinedButton
import com.orange.ods.compose.component.button.OdsTextButton
import com.orange.ods.compose.component.button.OdsToggleButton
import com.orange.ods.compose.theme.OdsMaterialTheme
import com.orange.ods.demo.R

@Composable
fun ComponentsButtonsScreen() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(state = scrollState)
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .padding(16.dp)
    ) {
        ContainedButtons()
        OutlinedButtons()
        TextButtons()
        ToggleButtons()
    }
}

@Composable
private fun ContainedButtons() {
    ButtonTypeSubtitleText("Contained")
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OdsButton(text = "Enabled", onClick = {})
        OdsButton(text = "Disabled", onClick = {}, enabled = false)
    }

    ButtonTypeSubtitleText("Contained with icon")
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OdsButton(text = "Enabled", onClick = {}, iconRes = R.drawable.ic_search)
        OdsButton(text = "Disabled", onClick = {}, iconRes = R.drawable.ic_search, enabled = false)
    }
}

@Composable
private fun OutlinedButtons() {
    ButtonTypeSubtitleText("Outlined")
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OdsOutlinedButton(text = "Enabled", onClick = {})
        OdsOutlinedButton(text = "Disabled", onClick = {}, enabled = false)
    }

    ButtonTypeSubtitleText("Outlined with icon")
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OdsOutlinedButton(text = "Enabled", onClick = {}, iconRes = R.drawable.ic_search)
        OdsOutlinedButton(text = "Disabled", onClick = {}, iconRes = R.drawable.ic_search, enabled = false)
    }
}

@Composable
private fun TextButtons() {
    ButtonTypeSubtitleText("Text")
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OdsTextButton(text = "Enabled", onClick = {})
        OdsTextButton(text = "Disabled", onClick = {}, enabled = false)
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OdsTextButton(text = "Enabled", onClick = {}, hasPrimaryColor = true)
        OdsTextButton(text = "Disabled", onClick = {}, enabled = false, hasPrimaryColor = true)
    }

    ButtonTypeSubtitleText("Text with icon")
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OdsTextButton(text = "Enabled", onClick = {}, iconRes = R.drawable.ic_search)
        OdsTextButton(text = "Disabled", onClick = {}, iconRes = R.drawable.ic_search, enabled = false)
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OdsTextButton(text = "Enabled", onClick = {}, iconRes = R.drawable.ic_search, hasPrimaryColor = true)
        OdsTextButton(text = "Disabled", onClick = {}, iconRes = R.drawable.ic_search, enabled = false, hasPrimaryColor = true)
    }
}

@Composable
fun ToggleButtons() {
    var checked by remember { mutableStateOf(false) }
    ButtonTypeSubtitleText("Toggle")
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ToggleGroup()
        OdsToggleButton(checked = checked, onCheckedChange = { checked = it }, iconRes = R.drawable.ic_search, contentDescription = "Search")
    }
}

@Composable
private fun ToggleGroup() {
    val iconsRes = listOf(R.drawable.ic_info, R.drawable.ic_search, R.drawable.ic_guideline_dna)
    var checkedIcon by remember { mutableStateOf(R.drawable.ic_info) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        iconsRes.forEach { iconRes ->
            OdsToggleButton(checked = checkedIcon == iconRes, onCheckedChange = { checkedIcon = iconRes }, iconRes = iconRes, contentDescription = "")
        }
    }
}

@Composable
private fun ButtonTypeSubtitleText(type: String) {
    Text(
        text = type,
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun ComponentsButtonsScreenPreview() {
    OdsMaterialTheme {
        ComponentsButtonsScreen()
    }
}
