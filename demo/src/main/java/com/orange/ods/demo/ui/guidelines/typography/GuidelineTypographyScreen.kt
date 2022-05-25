/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.guidelines.typography

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.utilities.getStringName
import com.orange.ods.theme.orange.ObsGrey700

@Composable
fun GuidelineTypographyScreen(updateTopBarTitle: (Int) -> Unit) {
    updateTopBarTitle(R.string.guideline_typography)
    val typographyItems = getTypographyItems()

    LazyColumn(
        contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.ods_spacing_s), vertical = dimensionResource(id = R.dimen.ods_spacing_xs)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.ods_spacing_s)),
    ) {
        items(typographyItems) { typo ->
            TypographyRow(typo)
            Divider(
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.ods_spacing_s)),
                color = ObsGrey700,
                thickness = 1.dp
            )
        }
    }
}

@Composable
private fun TypographyRow(typography: TypographyItem) {
    val context = LocalContext.current
    Column(modifier = Modifier.semantics(mergeDescendants = true) {}) {
        Text(
            text = typography.name,
            style = typography.style,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            buildAnnotatedString {
                append("Compose: ")
                withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                    append(typography.jetpackUsage)
                }
            }
        )
        Text(
            buildAnnotatedString {
                append("Resource: ")
                withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                    append("?attr/${context.getStringName(typography.xmlResource)}")
                }
            }
        )
    }
}