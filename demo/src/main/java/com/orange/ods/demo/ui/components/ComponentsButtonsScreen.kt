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
import com.orange.ods.compose.theme.OdsDisplayAppearance
import com.orange.ods.compose.theme.OdsMaterialTheme
import com.orange.ods.compose.theme.White100
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OdsButton(text = "Enabled", onClick = {})
        OdsButton(text = "Disabled", onClick = {}, enabled = false)
    }

    ButtonTypeSubtitleText("Contained with icon")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OdsButton(text = "Enabled", onClick = {}, iconRes = R.drawable.ic_search)
        OdsButton(text = "Disabled", onClick = {}, iconRes = R.drawable.ic_search, enabled = false)
    }

    DarkSurface {
        OdsButton(text = "Enabled", onClick = {}, displayAppearance = OdsDisplayAppearance.ON_DARK)
        OdsButton(text = "Disabled", onClick = {}, enabled = false, displayAppearance = OdsDisplayAppearance.ON_DARK)
    }

    LightSurface {
        OdsButton(text = "Enabled", onClick = {}, displayAppearance = OdsDisplayAppearance.ON_LIGHT)
        OdsButton(text = "Disabled", onClick = {}, enabled = false, displayAppearance = OdsDisplayAppearance.ON_LIGHT)
    }
}

@Composable
private fun OutlinedButtons() {
    ButtonTypeSubtitleText("Outlined")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OdsOutlinedButton(text = "Enabled", onClick = {})
        OdsOutlinedButton(text = "Disabled", onClick = {}, enabled = false)
    }

    ButtonTypeSubtitleText("Outlined with icon")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OdsOutlinedButton(text = "Enabled", onClick = {}, iconRes = R.drawable.ic_search)
        OdsOutlinedButton(text = "Disabled", onClick = {}, iconRes = R.drawable.ic_search, enabled = false)
    }

    DarkSurface {
        OdsOutlinedButton(text = "Enabled", onClick = {}, displayAppearance = OdsDisplayAppearance.ON_DARK)
        OdsOutlinedButton(text = "Disabled", onClick = {}, enabled = false, displayAppearance = OdsDisplayAppearance.ON_DARK)
    }

    LightSurface {
        OdsOutlinedButton(text = "Enabled", onClick = {}, displayAppearance = OdsDisplayAppearance.ON_LIGHT)
        OdsOutlinedButton(text = "Disabled", onClick = {}, enabled = false, displayAppearance = OdsDisplayAppearance.ON_LIGHT)
    }
}

@Composable
private fun TextButtons() {
    ButtonTypeSubtitleText("Text")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OdsTextButton(text = "Enabled", onClick = {})
        OdsTextButton(text = "Disabled", onClick = {}, enabled = false)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OdsTextButton(text = "Enabled", onClick = {}, hasPrimaryColor = true)
        OdsTextButton(text = "Disabled", onClick = {}, enabled = false, hasPrimaryColor = true)
    }

    ButtonTypeSubtitleText("Text with icon")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OdsTextButton(text = "Enabled", onClick = {}, iconRes = R.drawable.ic_search)
        OdsTextButton(text = "Disabled", onClick = {}, iconRes = R.drawable.ic_search, enabled = false)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        OdsTextButton(text = "Enabled", onClick = {}, iconRes = R.drawable.ic_search, hasPrimaryColor = true)
        OdsTextButton(text = "Disabled", onClick = {}, iconRes = R.drawable.ic_search, enabled = false, hasPrimaryColor = true)
    }

    DarkSurface {
        OdsTextButton(text = "Enabled", onClick = {}, displayAppearance = OdsDisplayAppearance.ON_DARK)
        OdsTextButton(text = "Disabled", onClick = {}, enabled = false, displayAppearance = OdsDisplayAppearance.ON_DARK)
    }

    LightSurface {
        OdsTextButton(text = "Enabled", onClick = {}, displayAppearance = OdsDisplayAppearance.ON_LIGHT)
        OdsTextButton(text = "Disabled", onClick = {}, enabled = false, displayAppearance = OdsDisplayAppearance.ON_LIGHT)
    }
}

@Composable
fun ToggleButtons() {
    ButtonTypeSubtitleText("Toggle")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "group:")
        ToggleGroup()
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
                .padding(top = 4.dp)
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
            displayAppearance = OdsDisplayAppearance.ON_DARK
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
            displayAppearance = OdsDisplayAppearance.ON_LIGHT
        )
    }
}

@Composable
private fun RowScope.ToggleGroup() {
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
                contentDescription = ""
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
