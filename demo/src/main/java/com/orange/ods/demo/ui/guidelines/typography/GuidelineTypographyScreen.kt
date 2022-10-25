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
import com.orange.ods.compose.component.divider.OdsDivider
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.LocalMainTopAppBarManager
import com.orange.ods.demo.ui.LocalOdsDemoGuideline
import com.orange.ods.demo.ui.utilities.getStringName
import com.orange.ods.theme.guideline.GuidelineTextStyle

@Composable
fun GuidelineTypographyScreen() {
    LocalMainTopAppBarManager.current.updateTopAppBarTitle(R.string.guideline_typography)

    val guidelineTypography = LocalOdsDemoGuideline.current.guidelineTypography

    LazyColumn(
        contentPadding = PaddingValues(horizontal = dimensionResource(id = R.dimen.spacing_m), vertical = dimensionResource(id = R.dimen.spacing_s)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_m)),
    ) {
        items(guidelineTypography) { guidelineTextStyle ->
            TextStyleRow(guidelineTextStyle)
            OdsDivider(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_m)))
        }
    }
}

@Composable
private fun TextStyleRow(guidelineTextStyle: GuidelineTextStyle) {
    val context = LocalContext.current
    val textColor = OdsTheme.colors.onBackground
    Column(modifier = Modifier.semantics(mergeDescendants = true) {}) {
        Text(
            text = guidelineTextStyle.name,
            style = guidelineTextStyle.textStyle,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = textColor
        )
        Text(
            text = buildAnnotatedString {
                append("Compose: ")
                withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                    append(guidelineTextStyle.composeStyle)
                }
            },
            color = textColor
        )
        Text(
            text = buildAnnotatedString {
                append("Resource: ")
                withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                    append("?attr/${context.getStringName(guidelineTextStyle.xmlResource)}")
                }
            },
            color = textColor
        )
    }
}