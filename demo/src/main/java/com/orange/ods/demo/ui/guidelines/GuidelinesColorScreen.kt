/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.guidelines

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
import com.orange.ods.compose.theme.*
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.utils.getStringName

data class ColorItem(
    val name: String,
    val jetPackName: String,
    val jetPackValue: Color,
    val hexValue: String,
    val rgbValue: String,
    val xmlResourceValue: Int,
    val colorType: ColorType
)

enum class ColorType {
    CORE,
    FUNCTIONAL,
    SUPPORTING
}

@Composable
fun GuidelinesColorScreen() {
    ColorList(buildColorList(isSystemInDarkTheme()))
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
        ) { }
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
        ) { }
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
            ) { }
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
private fun buildColorList(systemInDarkTheme: Boolean): List<ColorItem> {
    return buildCoreColorList(systemInDarkTheme)
        .plus(buildFunctionalColorList(systemInDarkTheme))
        .plus(buildSupportingColorList())
}

@Composable
private fun buildCoreColorList(systemInDarkTheme: Boolean): List<ColorItem> = listOf(
    //Orange
    if (systemInDarkTheme) {
        ColorItem(
            name = "Orange 100",
            jetPackName = "primary",
            jetPackValue = MaterialTheme.colors.primary,
            hexValue = "#FF7900",
            rgbValue = "rgb(255, 121, 0)",
            xmlResourceValue = R.attr.colorPrimary,
            colorType = ColorType.CORE
        )
    } else {
        ColorItem(
            name = "Orange 200",
            jetPackName = "primary",
            jetPackValue = MaterialTheme.colors.primary,
            hexValue = "#F16E00",
            rgbValue = "rgb(241, 110, 0)",
            xmlResourceValue = R.attr.colorPrimary,
            colorType = ColorType.CORE
        )
    },
    //Background
    if (systemInDarkTheme) {
        ColorItem(
            name = "Black 900",
            jetPackName = "background",
            jetPackValue = MaterialTheme.colors.background,
            hexValue = "#000000",
            rgbValue = "rgb(0, 0, 0)",
            xmlResourceValue = R.attr.backgroundColor,
            colorType = ColorType.CORE
        )
    } else {
        ColorItem(
            name = "White 100",
            jetPackName = "background",
            jetPackValue = MaterialTheme.colors.background,
            hexValue = "#FFFFFF",
            rgbValue = "rgb(255, 255, 255)",
            xmlResourceValue = R.attr.backgroundColor,
            colorType = ColorType.CORE
        )
    },
    //Secondary Background
    if (systemInDarkTheme) {
        ColorItem(
            name = "Secondary Background",
            jetPackName = "surface",
            jetPackValue = MaterialTheme.colors.surface,
            hexValue = "#121212",
            rgbValue = "rgb(18, 18, 18)",
            xmlResourceValue = R.attr.colorSurface,
            colorType = ColorType.CORE
        )
    } else {
        ColorItem(
            name = "White 100",
            jetPackName = "surface",
            jetPackValue = MaterialTheme.colors.surface,
            hexValue = "#FFFFFF",
            rgbValue = "rgb(255, 255, 255)",
            xmlResourceValue = R.attr.colorSurface,
            colorType = ColorType.CORE
        )
    },
    ColorItem(
        name = "OBS Grey 700",
        jetPackName = "ObsGrey700",
        jetPackValue = ObsGrey700,
        hexValue = "#595959",
        rgbValue = "rgb(89, 89, 89)",
        xmlResourceValue = R.color.ods_color_core_obsgrey_700,
        colorType = ColorType.CORE
    ),
)

@Composable
private fun buildFunctionalColorList(systemInDarkTheme: Boolean): List<ColorItem> = listOf(
    //Positive
    if (systemInDarkTheme) {
        ColorItem(
            name = "Positive 100",
            jetPackName = "functionalPositive",
            jetPackValue = MaterialTheme.colors.functionalPositive,
            hexValue = "#32C832",
            rgbValue = "rgb(50, 200, 50)",
            xmlResourceValue = R.attr.functionalPositive,
            colorType = ColorType.FUNCTIONAL
        )
    } else {
        ColorItem(
            name = "Positive 200",
            jetPackName = "functionalPositive",
            jetPackValue = MaterialTheme.colors.functionalPositive,
            hexValue = "#228722",
            rgbValue = "rgb(34, 135, 34)",
            xmlResourceValue = R.attr.functionalPositive,
            colorType = ColorType.FUNCTIONAL
        )
    },
    //Negative
    if (systemInDarkTheme) {
        ColorItem(
            name = "Negative 100",
            jetPackName = "error",
            jetPackValue = MaterialTheme.colors.error,
            hexValue = "#D53F15",
            rgbValue = "rgb(213, 63, 21)",
            xmlResourceValue = R.attr.colorError,
            colorType = ColorType.FUNCTIONAL
        )
    } else {
        ColorItem(
            name = "Negative 200",
            jetPackName = "error",
            jetPackValue = MaterialTheme.colors.error,
            hexValue = "#CD3C14",
            rgbValue = "rgb(205, 60, 20)",
            xmlResourceValue = R.attr.colorError,
            colorType = ColorType.FUNCTIONAL
        )
    },
    //Info
    if (systemInDarkTheme) {
        ColorItem(
            name = "Info 100",
            jetPackName = "functionalInfo",
            jetPackValue = MaterialTheme.colors.functionalInfo,
            hexValue = "#527EDB",
            rgbValue = "rgb(82, 126, 219)",
            xmlResourceValue = R.attr.functionalInfo,
            colorType = ColorType.FUNCTIONAL
        )
    } else {
        ColorItem(
            name = "Info 200",
            jetPackName = "functionalInfo",
            jetPackValue = MaterialTheme.colors.functionalInfo,
            hexValue = "#4170D8",
            rgbValue = "rgb(65, 112, 216)",
            xmlResourceValue = R.attr.functionalInfo,
            colorType = ColorType.FUNCTIONAL
        )
    },
    //Alert
    if (systemInDarkTheme) {
        ColorItem(
            name = "Alert 100",
            jetPackName = "functionalAlert",
            jetPackValue = MaterialTheme.colors.functionalAlert,
            hexValue = "#FFCC00",
            rgbValue = "rgb(255, 204, 0)",
            xmlResourceValue = R.attr.functionalAlert,
            colorType = ColorType.FUNCTIONAL
        )
    } else {
        ColorItem(
            name = "Alert 200",
            jetPackName = "functionalAlert",
            jetPackValue = MaterialTheme.colors.functionalAlert,
            hexValue = "#8F7200",
            rgbValue = "rgb(143, 114, 0)",
            xmlResourceValue = R.attr.functionalAlert,
            colorType = ColorType.FUNCTIONAL
        )
    },
)

private fun buildSupportingColorList(): List<ColorItem> = listOf(
    ColorItem(
        name = "Blue 100",
        jetPackName = "Blue100",
        jetPackValue = Blue100,
        hexValue = "#B5E8F7",
        rgbValue = "rgb(181, 232, 247)",
        xmlResourceValue = R.color.ods_color_supporting_blue_100,
        colorType = ColorType.SUPPORTING
    ),
    ColorItem(
        name = "Blue 200",
        jetPackName = "Blue200",
        jetPackValue = Blue200,
        hexValue = "#4BB4E6",
        rgbValue = "rgb(75, 180, 230)",
        xmlResourceValue = R.color.ods_color_supporting_blue_200,
        colorType = ColorType.SUPPORTING
    ),
    ColorItem(
        name = "Blue 300",
        jetPackName = "Blue300",
        jetPackValue = Blue300,
        hexValue = "#085EBD",
        rgbValue = "rgb(8, 94, 189)",
        xmlResourceValue = R.color.ods_color_supporting_blue_300,
        colorType = ColorType.SUPPORTING
    ),
    ColorItem(
        name = "Green 100",
        jetPackName = "Green100",
        jetPackValue = Green100,
        hexValue = "#B8EBD6",
        rgbValue = "rgb(184, 235, 214)",
        xmlResourceValue = R.color.ods_color_supporting_green_100,
        colorType = ColorType.SUPPORTING
    ),
    ColorItem(
        name = "Green 200",
        jetPackName = "Green200",
        jetPackValue = Green200,
        hexValue = "#50BE87",
        rgbValue = "rgb(80, 190, 135)",
        xmlResourceValue = R.color.ods_color_supporting_green_200,
        colorType = ColorType.SUPPORTING
    ),
    ColorItem(
        name = "Green 300",
        jetPackName = "Green300",
        jetPackValue = Green300,
        hexValue = "#0A6E31",
        rgbValue = "rgb(10, 110, 49)",
        xmlResourceValue = R.color.ods_color_supporting_green_300,
        colorType = ColorType.SUPPORTING
    ),
    ColorItem(
        name = "Pink 100",
        jetPackName = "Pink100",
        jetPackValue = Pink100,
        hexValue = "#FFE8F7",
        rgbValue = "rgb(255, 232, 247)",
        xmlResourceValue = R.color.ods_color_supporting_pink_100,
        colorType = ColorType.SUPPORTING
    ),
    ColorItem(
        name = "Pink 200",
        jetPackName = "Pink200",
        jetPackValue = Pink200,
        hexValue = "#FFB4E6",
        rgbValue = "rgb(255, 180, 230)",
        xmlResourceValue = R.color.ods_color_supporting_pink_200,
        colorType = ColorType.SUPPORTING
    ),
    ColorItem(
        name = "Pink 300",
        jetPackName = "Pink300",
        jetPackValue = Pink300,
        hexValue = "#FF8AD4",
        rgbValue = "rgb(255, 138, 212)",
        xmlResourceValue = R.color.ods_color_supporting_pink_300,
        colorType = ColorType.SUPPORTING
    ),
    ColorItem(
        name = "Purple 100",
        jetPackName = "Purple100",
        jetPackValue = Purple100,
        hexValue = "#D9C2F0",
        rgbValue = "rgb(217, 194, 240)",
        xmlResourceValue = R.color.ods_color_supporting_purple_100,
        colorType = ColorType.SUPPORTING
    ),
    ColorItem(
        name = "Purple 200",
        jetPackName = "Purple200",
        jetPackValue = Purple200,
        hexValue = "#A885D8",
        rgbValue = "rgb(168, 133, 216)",
        xmlResourceValue = R.color.ods_color_supporting_purple_200,
        colorType = ColorType.SUPPORTING
    ),
    ColorItem(
        name = "Purple 300",
        jetPackName = "Purple300",
        jetPackValue = Purple300,
        hexValue = "#492191",
        rgbValue = "rgb(73, 33, 145)",
        xmlResourceValue = R.color.ods_color_supporting_purple_300,
        colorType = ColorType.SUPPORTING
    ),
    ColorItem(
        name = "Yellow 100",
        jetPackName = "Yellow100",
        jetPackValue = Yellow100,
        hexValue = "#FFF6B6",
        rgbValue = "rgb(255, 246, 182)",
        xmlResourceValue = R.color.ods_color_supporting_yellow_100,
        colorType = ColorType.SUPPORTING
    ),
    ColorItem(
        name = "Yellow 200",
        jetPackName = "Yellow200",
        jetPackValue = Yellow200,
        hexValue = "#FFD200",
        rgbValue = "rgb(255, 210, 0)",
        xmlResourceValue = R.color.ods_color_supporting_yellow_200,
        colorType = ColorType.SUPPORTING
    ),
    ColorItem(
        name = "Yellow 300",
        jetPackName = "Yellow300",
        jetPackValue = Yellow300,
        hexValue = "#FFB400",
        rgbValue = "rgb(255, 180, 0)",
        xmlResourceValue = R.color.ods_color_supporting_yellow_300,
        colorType = ColorType.SUPPORTING
    ),
)