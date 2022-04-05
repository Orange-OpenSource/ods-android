/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.guidelines.colors

import androidx.annotation.StringRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.orange.ods.compose.theme.Black900
import com.orange.ods.compose.theme.White100
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.utils.getStringName

@Composable
fun GuidelinesColorScreen() {
    ColorList(getColorList(isSystemInDarkTheme()))
}

@Composable
private fun ColorList(colors: List<ColorItem>) {
    LazyColumn(
        contentPadding = PaddingValues(start = 8.dp, end = 8.dp, bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(19.dp),
    ) {
        item {
            Title(textRes = R.string.guideline_colour_core, paddingTop = 26.dp)
        }
        items(colors.filter { it.colorType == ColorType.CORE }.chunked(2)) { rowColors ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                BigColorItem(color = rowColors[0])
                BigColorItem(color = rowColors[1])
            }
        }
        item {
            Title(textRes = R.string.guideline_colour_functional, paddingTop = 50.dp)
        }
        items(colors.filter { it.colorType == ColorType.FUNCTIONAL }.chunked(2)) { rowColors ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                BigColorItem(color = rowColors[0])
                BigColorItem(color = rowColors[1])
            }
        }
        item {
            Title(textRes = R.string.guideline_colour_supporting, paddingTop = 50.dp)
        }
        items(colors.filter { it.colorType == ColorType.SUPPORTING }.chunked(3)) { rowColors ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                SmallColorItem(color = rowColors[0])
                SmallColorItem(color = rowColors[1])
                SmallColorItem(color = rowColors[2])
            }
        }
    }
}

@Composable
private fun Title(@StringRes textRes: Int, paddingTop: Dp) {
    Text(
        text = stringResource(id = textRes),
        style = MaterialTheme.typography.h5,
        modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = paddingTop)
    )
}

@Composable
private fun RowScope.SmallColorItem(color: ColorItem) {
    val openDialog = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .weight(0.33f)
            .clickable {
                openDialog.value = true
            },
    ) {
        Box(
            modifier = Modifier
                .background(color = color.jetPackValue)
                .fillMaxWidth()
                .aspectRatio(1f)
        )
        Text(
            text = color.name,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(top = 3.dp)
        )
        Text(
            text = color.hexValue,
            style = MaterialTheme.typography.caption
        )
    }
    if (openDialog.value) {
        DialogColor(color = color, openDialog)
    }
}

@Composable
private fun RowScope.BigColorItem(color: ColorItem) {
    val openDialog = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .weight(0.5f)
            .clickable {
                openDialog.value = true
            },
    ) {
        val boxColorModifier = Modifier
            .background(color = color.jetPackValue)
            .fillMaxWidth()
            .aspectRatio(1f)
        Box(
            modifier = when (color.jetPackValue) {
                White100 -> {
                    boxColorModifier.border(BorderStroke(1.dp, Color(0xff979797)))
                }
                Black900 -> {
                    boxColorModifier.border(BorderStroke(1.dp, White100))
                }
                else -> {
                    boxColorModifier
                }
            }
        )
        Text(
            text = color.name,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(top = 3.dp)
        )
        Text(text = color.jetPackName, style = MaterialTheme.typography.body1)
        Text(
            modifier = Modifier.padding(top = 2.dp),
            text = color.hexValue,
            style = MaterialTheme.typography.caption
        )
        Text(
            modifier = Modifier.padding(top = 2.dp),
            text = color.rgbValue,
            style = MaterialTheme.typography.caption
        )
    }
    if (openDialog.value) {
        DialogColor(color = color, openDialog)
    }
}

@Composable
private fun DialogColor(color: ColorItem, openDialog: MutableState<Boolean>) {
    val context = LocalContext.current
    Dialog(
        onDismissRequest = { openDialog.value = false },
    ) {
        Column {
            Box(
                modifier = Modifier
                    .background(color = color.jetPackValue)
                    .fillMaxWidth()
                    .height(190.dp)
            )
            Column(
                modifier = Modifier
                    .background(color = MaterialTheme.colors.background)
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 20.dp)
            ) {
                Text(
                    text = color.name,
                    style = MaterialTheme.typography.h5,
                )
                Text(
                    modifier = Modifier.padding(top = 3.dp),
                    text = color.jetPackName,
                    style = MaterialTheme.typography.body1
                )
                Text(
                    modifier = Modifier.padding(top = 7.dp),
                    text = color.hexValue,
                    style = MaterialTheme.typography.body1
                )
                Text(
                    modifier = Modifier.padding(top = 7.dp),
                    text = color.rgbValue, style = MaterialTheme.typography.body1
                )
                Text(
                    modifier = Modifier.padding(top = 7.dp),
                    text = stringResource(
                        id = R.string.guideline_colour_xml,
                        context.getStringName(color.xmlResourceValue)
                    ),
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}

@Composable
private fun getColorList(systemInDarkTheme: Boolean): List<ColorItem> {
    return getCoreColorList(systemInDarkTheme)
        .plus(getFunctionalColorList(systemInDarkTheme))
        .plus(getSupportingColorList())
}