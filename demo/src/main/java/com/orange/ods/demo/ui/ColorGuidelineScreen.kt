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

import android.content.Context
import androidx.annotation.ColorRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.orange.ods.compose.theme.*
import com.orange.ods.demo.R

data class ColorItem(
    val jetPackColor: Color,
    val colorName: String,
    @ColorRes
    val xmlResource: Int
)

@Composable
fun ColorGuidelineScreen() {
    ColorList(buildColorList())
}

@Composable
fun ColorRow(color: ColorItem) {
    val context = LocalContext.current
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .background(color = color.jetPackColor)
                .width(64.dp)
                .height(64.dp),
        ) { }
        Column(
            modifier = Modifier.padding(start = 8.dp),
        ) {
            Text(text = "Hex : ${context.getColorHex(color.xmlResource)}")
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_xml_logo),
                    modifier = Modifier.width(32.dp),
                    contentDescription = null
                )
                Text(text = context.getStringName(color.xmlResource), fontStyle = FontStyle.Italic)
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image_jetpack_compose),
                    modifier = Modifier.width(32.dp),
                    contentDescription = null
                )
                Text(text = color.colorName, fontStyle = FontStyle.Italic)
            }
        }
    }
}

@Composable
private fun ColorList(colors: List<ColorItem>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(colors) { color ->
            ColorRow(color)
            Divider(modifier = Modifier.padding(top = 16.dp), color = ObsGrey700, thickness = 1.dp)
        }
    }
}

private fun buildColorList(): List<ColorItem> = listOf(
    ColorItem(
        jetPackColor = Orange200,
        colorName = "Orange200",
        xmlResource = R.color.ods_color_core_orange_200
    ),
    ColorItem(
        jetPackColor = Orange100,
        colorName = "Orange100",
        xmlResource = R.color.ods_color_core_orange_100
    ),
    ColorItem(
        jetPackColor = White100,
        colorName = "White100",
        xmlResource = R.color.ods_color_core_white_100
    ),
    ColorItem(
        jetPackColor = Black900,
        colorName = "Black900",
        xmlResource = R.color.ods_color_core_black_900
    ),
    ColorItem(
        jetPackColor = ObsGrey700,
        colorName = "ObsGrey700",
        xmlResource = R.color.ods_color_core_obsgrey_700
    ),
    ColorItem(
        jetPackColor = Blue100,
        colorName = "Blue100",
        xmlResource = R.color.ods_color_supporting_blue_100
    ),
    ColorItem(
        jetPackColor = Blue200,
        colorName = "Blue200",
        xmlResource = R.color.ods_color_supporting_blue_200
    ),
    ColorItem(
        jetPackColor = Blue300,
        colorName = "Blue300",
        xmlResource = R.color.ods_color_supporting_blue_300
    ),
    ColorItem(
        jetPackColor = Yellow100,
        colorName = "Yellow100",
        xmlResource = R.color.ods_color_supporting_yellow_100
    ),
    ColorItem(
        jetPackColor = Yellow200,
        colorName = "Yellow200",
        xmlResource = R.color.ods_color_supporting_yellow_200
    ),
    ColorItem(
        jetPackColor = Yellow300,
        colorName = "Yellow300",
        xmlResource = R.color.ods_color_supporting_yellow_300
    ),
    ColorItem(
        jetPackColor = Green100,
        colorName = "Green100",
        xmlResource = R.color.ods_color_supporting_green_100
    ),
    ColorItem(
        jetPackColor = Green200,
        colorName = "Green200",
        xmlResource = R.color.ods_color_supporting_green_200
    ),
    ColorItem(
        jetPackColor = Green300,
        colorName = "Green300",
        xmlResource = R.color.ods_color_supporting_green_300
    ),
    ColorItem(
        jetPackColor = Purple100,
        colorName = "Purple100",
        xmlResource = R.color.ods_color_supporting_purple_100
    ),
    ColorItem(
        jetPackColor = Purple200,
        colorName = "Purple200",
        xmlResource = R.color.ods_color_supporting_purple_200
    ),
    ColorItem(
        jetPackColor = Purple300,
        colorName = "Purple300",
        xmlResource = R.color.ods_color_supporting_purple_300
    ),
    ColorItem(
        jetPackColor = Pink100,
        colorName = "Pink100",
        xmlResource = R.color.ods_color_supporting_pink_100
    ),
    ColorItem(
        jetPackColor = Pink200,
        colorName = "Pink200",
        xmlResource = R.color.ods_color_supporting_pink_200
    ),
    ColorItem(
        jetPackColor = Pink300,
        colorName = "Pink300",
        xmlResource = R.color.ods_color_supporting_pink_300
    ),
    ColorItem(
        jetPackColor = Info200,
        colorName = "Info200",
        xmlResource = R.color.ods_color_functional_info_200
    ),
    ColorItem(
        jetPackColor = Alert200,
        colorName = "Alert200",
        xmlResource = R.color.ods_color_functional_alert_200
    ),
    ColorItem(
        jetPackColor = Positive200,
        colorName = "Positive200",
        xmlResource = R.color.ods_color_functional_positive_200
    ),
    ColorItem(
        jetPackColor = Negative200,
        colorName = "Negative200",
        xmlResource = R.color.ods_color_functional_negative_200
    ),
    ColorItem(
        jetPackColor = Grey200,
        colorName = "Grey200",
        xmlResource = R.color.ods_color_grey_200
    ),
    ColorItem(
        jetPackColor = Grey300,
        colorName = "Grey300",
        xmlResource = R.color.ods_color_grey_300
    ),
    ColorItem(
        jetPackColor = Grey400,
        colorName = "Grey400",
        xmlResource = R.color.ods_color_grey_400
    ),
    ColorItem(
        jetPackColor = Grey500,
        colorName = "Grey500",
        xmlResource = R.color.ods_color_grey_500
    ),
    ColorItem(
        jetPackColor = Grey600,
        colorName = "Grey600",
        xmlResource = R.color.ods_color_grey_600
    ),
    ColorItem(
        jetPackColor = Grey800,
        colorName = "Grey800",
        xmlResource = R.color.ods_color_grey_800
    ),
    ColorItem(
        jetPackColor = DarkSurfaceDefault,
        colorName = "DarkSurfaceDefault",
        xmlResource = R.color.ods_dark_default_color_surface
    ),
)

//Method to get the resource name with the color id
private fun Context.getStringName(@ColorRes res: Int): String =
    this.resources.getResourceName(res).split('/').last()

//Method to get the color hexadecimal with the color id
private fun Context.getColorHex(@ColorRes res: Int): String {
    var number = Integer.toHexString(ContextCompat.getColor(this, res))
    if (number.length == 8 && number.startsWith("ff")) {
        number = number.removePrefix("ff")
    }
    return "#" + number.uppercase()
}