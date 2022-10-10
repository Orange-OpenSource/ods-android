/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.guidelines.color

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.orange.ods.compose.text.OdsTextBody1
import com.orange.ods.compose.text.OdsTextCaption
import com.orange.ods.compose.text.OdsTextH5
import com.orange.ods.compose.text.OdsTextH6
import com.orange.ods.compose.theme.Black900
import com.orange.ods.compose.theme.White100
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.LocalMainTopAppBarManager
import com.orange.ods.demo.ui.utilities.composable.Title
import com.orange.ods.demo.ui.utilities.getStringName

@Composable
fun GuidelineColorScreen() {
    LocalMainTopAppBarManager.current.updateTopAppBarTitle(R.string.guideline_color)
    ColorList(getColorList(isSystemInDarkTheme()))
}

@Composable
private fun ColorList(colors: List<GuidelineColorItem>) {
    LazyColumn(
        contentPadding = PaddingValues(
            start = dimensionResource(id = R.dimen.spacing_m),
            end = dimensionResource(id = R.dimen.spacing_m),
            bottom = dimensionResource(id = R.dimen.ods_screen_vertical_margin)
        ),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_m)),
    ) {
        item {
            Title(textRes = R.string.guideline_colour_core, modifier = Modifier.semantics { heading() })
        }
        items(colors.filter { it.colorType == ColorType.CORE }.chunked(2)) { rowColors ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_m)),
            ) {
                BigColorItem(color = rowColors[0])
                BigColorItem(color = rowColors[1])
            }
        }
        item {
            Title(textRes = R.string.guideline_colour_functional, modifier = Modifier.semantics { heading() })
        }
        items(colors.filter { it.colorType == ColorType.FUNCTIONAL }.chunked(2)) { rowColors ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_m)),
            ) {
                BigColorItem(color = rowColors[0])
                BigColorItem(color = rowColors[1])
            }
        }
        item {
            Title(textRes = R.string.guideline_colour_supporting, modifier = Modifier.semantics { heading() })
        }
        items(colors.filter { it.colorType == ColorType.SUPPORTING }.chunked(3)) { rowColors ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_m)),
            ) {
                SmallColorItem(color = rowColors[0])
                SmallColorItem(color = rowColors[1])
                SmallColorItem(color = rowColors[2])
            }
        }
    }
}

@Composable
private fun RowScope.SmallColorItem(color: GuidelineColorItem) {
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
        OdsTextH6(
            text = color.name,
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_xs))
        )
        OdsTextCaption(text = color.hexValue)
    }
    if (openDialog.value) {
        DialogColor(color = color, openDialog)
    }
}

@Composable
private fun RowScope.BigColorItem(color: GuidelineColorItem) {
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
        OdsTextH6(
            text = color.name,
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_xs))
        )
        OdsTextBody1(text = color.jetPackName)
        OdsTextCaption(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_xs)),
            text = color.hexValue
        )
        OdsTextCaption(
            text = color.rgbValue
        )
    }
    if (openDialog.value) {
        DialogColor(color = color, openDialog)
    }
}

@Composable
private fun DialogColor(color: GuidelineColorItem, openDialog: MutableState<Boolean>) {
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
                    .padding(dimensionResource(id = R.dimen.spacing_m))
            ) {
                OdsTextH5(text = color.name)
                OdsTextBody1(
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_xs)),
                    text = color.jetPackName
                )
                OdsTextBody1(
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_s)),
                    text = color.hexValue
                )
                OdsTextBody1(
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_s)),
                    text = color.rgbValue
                )
                OdsTextBody1(
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_s)),
                    text = stringResource(
                        id = R.string.guideline_colour_xml,
                        context.getStringName(color.xmlResourceValue)
                    )
                )
            }
        }
    }
}

@Composable
private fun getColorList(systemInDarkTheme: Boolean): List<GuidelineColorItem> {
    return getCoreColors(systemInDarkTheme)
        .plus(getFunctionalColors(systemInDarkTheme))
        .plus(getSupportingColors())
}