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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
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
import com.orange.ods.compose.theme.Black900
import com.orange.ods.compose.theme.OdsMaterialTheme
import com.orange.ods.compose.theme.White100
import com.orange.ods.demo.OdsApplication
import com.orange.ods.demo.R

@Composable
fun ComponentsButtonsScreen() {
    Column(
        modifier = Modifier
            .verticalScroll(state = rememberScrollState())
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
            .padding(vertical = 16.dp)
    ) {
        val isDarkModeEnabled by remember { OdsApplication.instance.isDarkModeEnabled }
        ContainedButtons(isDarkModeEnabled)
        OutlinedButtons(isDarkModeEnabled)
        TextButtons(isDarkModeEnabled)
        ToggleButtons(isDarkModeEnabled)
    }
}

@Composable
private fun ContainedButtons(isDarkModeEnabled: Boolean) {
    ButtonTypeSubtitleText("Contained")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OdsButton(text = "Enabled", onClick = {}, isOnDarkSurface = isDarkModeEnabled)
        OdsButton(text = "Disabled", onClick = {}, enabled = false, isOnDarkSurface = isDarkModeEnabled)
    }

    ButtonTypeSubtitleText("Contained with icon")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OdsButton(text = "Enabled", onClick = {}, iconRes = R.drawable.ic_search, isOnDarkSurface = isDarkModeEnabled)
        OdsButton(text = "Disabled", onClick = {}, iconRes = R.drawable.ic_search, enabled = false, isOnDarkSurface = isDarkModeEnabled)
    }

    DarkSurface {
        OdsButton(text = "Enabled", onClick = {}, isOnDarkSurface = true)
        OdsButton(text = "Disabled", onClick = {}, enabled = false, isOnDarkSurface = true)
    }

    LightSurface {
        OdsButton(text = "Enabled", onClick = {}, isOnDarkSurface = false)
        OdsButton(text = "Disabled", onClick = {}, enabled = false, isOnDarkSurface = false)
    }
}

@Composable
private fun OutlinedButtons(isDarkModeEnabled: Boolean) {
    ButtonTypeSubtitleText("Outlined")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OdsOutlinedButton(text = "Enabled", onClick = {}, isOnDarkSurface = isDarkModeEnabled)
        OdsOutlinedButton(text = "Disabled", onClick = {}, enabled = false, isOnDarkSurface = isDarkModeEnabled)
    }

    ButtonTypeSubtitleText("Outlined with icon")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OdsOutlinedButton(text = "Enabled", onClick = {}, iconRes = R.drawable.ic_search, isOnDarkSurface = isDarkModeEnabled)
        OdsOutlinedButton(text = "Disabled", onClick = {}, iconRes = R.drawable.ic_search, enabled = false, isOnDarkSurface = isDarkModeEnabled)
    }

    DarkSurface {
        OdsOutlinedButton(text = "Enabled", onClick = {}, isOnDarkSurface = true)
        OdsOutlinedButton(text = "Disabled", onClick = {}, enabled = false, isOnDarkSurface = true)
    }

    LightSurface {
        OdsOutlinedButton(text = "Enabled", onClick = {}, isOnDarkSurface = false)
        OdsOutlinedButton(text = "Disabled", onClick = {}, enabled = false, isOnDarkSurface = false)
    }
}

@Composable
private fun TextButtons(isDarkModeEnabled: Boolean) {
    ButtonTypeSubtitleText("Text")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OdsTextButton(text = "Enabled", onClick = {}, isOnDarkSurface = isDarkModeEnabled)
        OdsTextButton(text = "Disabled", onClick = {}, enabled = false, isOnDarkSurface = isDarkModeEnabled)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OdsTextButton(text = "Enabled", onClick = {}, hasPrimaryColor = true, isOnDarkSurface = isDarkModeEnabled)
        OdsTextButton(text = "Disabled", onClick = {}, enabled = false, hasPrimaryColor = true, isOnDarkSurface = isDarkModeEnabled)
    }

    ButtonTypeSubtitleText("Text with icon")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OdsTextButton(text = "Enabled", onClick = {}, iconRes = R.drawable.ic_search, isOnDarkSurface = isDarkModeEnabled)
        OdsTextButton(text = "Disabled", onClick = {}, iconRes = R.drawable.ic_search, enabled = false, isOnDarkSurface = isDarkModeEnabled)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OdsTextButton(text = "Enabled", onClick = {}, iconRes = R.drawable.ic_search, hasPrimaryColor = true, isOnDarkSurface = isDarkModeEnabled)
        OdsTextButton(text = "Disabled", onClick = {}, iconRes = R.drawable.ic_search, enabled = false, hasPrimaryColor = true, isOnDarkSurface = isDarkModeEnabled)
    }

    DarkSurface {
        OdsTextButton(text = "Enabled", onClick = {}, isOnDarkSurface = true)
        OdsTextButton(text = "Disabled", onClick = {}, enabled = false, isOnDarkSurface = true)
    }

    LightSurface {
        OdsTextButton(text = "Enabled", onClick = {}, isOnDarkSurface = false)
        OdsTextButton(text = "Disabled", onClick = {}, enabled = false, isOnDarkSurface = false)
    }
}

@Composable
fun ToggleButtons(isDarkModeEnabled: Boolean) {
    ButtonTypeSubtitleText("Toggle")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "group:")
        ToggleGroup(isDarkModeEnabled)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "single:")
        var toggleChecked by remember { mutableStateOf(false) }
        OdsToggleButton(
            checked = toggleChecked,
            onCheckedChange = { toggleChecked = it },
            iconRes = R.drawable.ic_module_molecule,
            contentDescription = "Search",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            isOnDarkSurface = isDarkModeEnabled
        )
    }

    DarkSurface {
        var toggleChecked by remember { mutableStateOf(false) }
        OdsToggleButton(
            checked = toggleChecked,
            onCheckedChange = { toggleChecked = it },
            iconRes = R.drawable.ic_module_molecule,
            contentDescription = "Search",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            isOnDarkSurface = true
        )
    }

    LightSurface {
        var toggleChecked by remember { mutableStateOf(false) }
        OdsToggleButton(
            checked = toggleChecked,
            onCheckedChange = { toggleChecked = it },
            iconRes = R.drawable.ic_module_molecule,
            contentDescription = "Search",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            isOnDarkSurface = false
        )
    }
}

@Composable
private fun RowScope.ToggleGroup(isDarkModeEnabled: Boolean) {
    val iconsRes = listOf(R.drawable.ic_info, R.drawable.ic_search, R.drawable.ic_guideline_dna)
    var checkedIcon by remember { mutableStateOf(R.drawable.ic_info) }

    Row(
        modifier = Modifier
            .weight(1f)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        iconsRes.forEach { iconRes ->
            OdsToggleButton(
                checked = checkedIcon == iconRes,
                onCheckedChange = { checkedIcon = iconRes },
                iconRes = iconRes,
                contentDescription = "",
                isOnDarkSurface = isDarkModeEnabled
            )
        }
    }
}

@Composable
private fun ButtonTypeSubtitleText(type: String) {
    Text(
        text = type,
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
    )
}

@Composable
private fun DarkSurface(content: @Composable RowScope.() -> Unit) {
    Text(
        "forced on dark background",
        color = White100,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Black900)
            .padding(vertical = 4.dp, horizontal = 16.dp)
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Black900)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        content = content
    )
}

@Composable
private fun LightSurface(content: @Composable RowScope.() -> Unit) {
    Text(
        "forced on light background",
        color = Black900,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = White100)
            .padding(vertical = 4.dp, horizontal = 16.dp)
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = White100)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        content = content
    )
}

@Preview(showBackground = true)
@Composable
private fun ComponentsButtonsScreenPreview() {
    OdsMaterialTheme {
        ComponentsButtonsScreen()
    }
}
