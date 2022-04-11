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

import androidx.annotation.AttrRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.theme.ObsGrey700
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.utilities.getStringName

data class TypographyItem(
    val name: String,
    val style: TextStyle,
    val jetpackUsage: String,
    @AttrRes
    val xmlResource: Int
)

@Composable
fun GuidelinesTypographyScreen() {
    TypographyList(
        listOf(
            TypographyItem(
                name = "Headline 1",
                style = MaterialTheme.typography.h1,
                jetpackUsage = "MaterialTheme.typography.h1",
                xmlResource = R.attr.textAppearanceHeadline1,
            ),
            TypographyItem(
                name = "Headline 2",
                style = MaterialTheme.typography.h2,
                jetpackUsage = "MaterialTheme.typography.h2",
                xmlResource = R.attr.textAppearanceHeadline2,
            ),
            TypographyItem(
                name = "Headline 3",
                style = MaterialTheme.typography.h3,
                jetpackUsage = "MaterialTheme.typography.h3",
                xmlResource = R.attr.textAppearanceHeadline3,
            ),
            TypographyItem(
                name = "Headline 4",
                style = MaterialTheme.typography.h4,
                jetpackUsage = "MaterialTheme.typography.h4",
                xmlResource = R.attr.textAppearanceHeadline4,
            ),
            TypographyItem(
                name = "Headline 5",
                style = MaterialTheme.typography.h5,
                jetpackUsage = "MaterialTheme.typography.h5",
                xmlResource = R.attr.textAppearanceHeadline5,
            ),
            TypographyItem(
                name = "Headline 6",
                style = MaterialTheme.typography.h6,
                jetpackUsage = "MaterialTheme.typography.h6",
                xmlResource = R.attr.textAppearanceHeadline6,
            ),
            TypographyItem(
                name = "Subtitle1",
                style = MaterialTheme.typography.subtitle1,
                jetpackUsage = "MaterialTheme.typography.subtitle1",
                xmlResource = R.attr.textAppearanceSubtitle1,
            ),
            TypographyItem(
                name = "Subtitle2",
                style = MaterialTheme.typography.subtitle2,
                jetpackUsage = "MaterialTheme.typography.subtitle2",
                xmlResource = R.attr.textAppearanceSubtitle2,
            ),
            TypographyItem(
                name = "Body1",
                style = MaterialTheme.typography.body1,
                jetpackUsage = "MaterialTheme.typography.body1",
                xmlResource = R.attr.textAppearanceBody1,
            ),
            TypographyItem(
                name = "Body2",
                style = MaterialTheme.typography.body2,
                jetpackUsage = "MaterialTheme.typography.body2",
                xmlResource = R.attr.textAppearanceBody2,
            ),
            TypographyItem(
                name = "Button",
                style = MaterialTheme.typography.button,
                jetpackUsage = "MaterialTheme.typography.button",
                xmlResource = R.attr.textAppearanceButton,
            ),
            TypographyItem(
                name = "Caption",
                style = MaterialTheme.typography.caption,
                jetpackUsage = "MaterialTheme.typography.caption",
                xmlResource = R.attr.textAppearanceCaption,
            ),
            TypographyItem(
                name = "Overline",
                style = MaterialTheme.typography.overline,
                jetpackUsage = "MaterialTheme.typography.overline",
                xmlResource = R.attr.textAppearanceOverline,
            )
        )
    )
}

@Composable
private fun TypographyRow(typo: TypographyItem) {
    val context = LocalContext.current
    Column() {
        Text(
            text = typo.name,
            style = typo.style
        )
        Text(
            buildAnnotatedString {
                append("Compose: ")
                withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                    append(typo.jetpackUsage)
                }
            }
        )
        Text(
            buildAnnotatedString {
                append("Resource: ")
                withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                    append("?attr/${context.getStringName(typo.xmlResource)}")
                }
            }
        )
    }
}

@Composable
private fun TypographyList(typos: List<TypographyItem>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(typos) { typo ->
            TypographyRow(typo)
            Divider(modifier = Modifier.padding(top = 16.dp), color = ObsGrey700, thickness = 1.dp)
        }
    }
}