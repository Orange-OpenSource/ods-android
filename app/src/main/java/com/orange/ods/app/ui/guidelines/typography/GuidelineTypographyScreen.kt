/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.guidelines.typography

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import com.orange.ods.app.R
import com.orange.ods.app.ui.LocalMainTopAppBarManager
import com.orange.ods.app.ui.LocalOdsGuideline
import com.orange.ods.app.ui.utilities.DrawableManager
import com.orange.ods.app.ui.utilities.composable.DetailScreenHeader
import com.orange.ods.app.ui.utilities.getStringName
import com.orange.ods.compose.component.divider.OdsDivider
import com.orange.ods.compose.text.OdsTextBody1
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.theme.guideline.GuidelineTextStyle

@Composable
fun GuidelineTypographyScreen() {
    LocalMainTopAppBarManager.current.updateTopAppBarTitle(R.string.guideline_typography)

    val guidelineTypography = LocalOdsGuideline.current.guidelineTypography

    LazyColumn(
        contentPadding = PaddingValues(bottom = dimensionResource(id = com.orange.ods.R.dimen.screen_vertical_margin)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = com.orange.ods.R.dimen.spacing_m))
    ) {
        item {
            DetailScreenHeader(
                imageRes = DrawableManager.getDrawableResIdForCurrentTheme(resId = R.drawable.il_typography),
                descriptionRes = R.string.guideline_typography_description
            )
        }
        if (guidelineTypography.isEmpty()) {
            item {
                OdsTextBody1(
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin),
                        vertical = dimensionResource(id = com.orange.ods.R.dimen.screen_vertical_margin)
                    ),
                    text = stringResource(id = R.string.guideline_typography_no_typography_defined)
                )
            }
        } else {
            itemsIndexed(guidelineTypography) { index, textStyle ->
                TextStyleRow(textStyle)
                if (index < guidelineTypography.lastIndex) {
                    OdsDivider(
                        modifier = Modifier
                            .padding(top = dimensionResource(id = com.orange.ods.R.dimen.spacing_m))
                            .padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin))
                    )
                }
            }
        }
    }
}

@Composable
private fun TextStyleRow(guidelineTextStyle: GuidelineTextStyle) {
    val context = LocalContext.current
    val textColor = OdsTheme.colors.onBackground
    Column(modifier = Modifier
        .padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin))
        .semantics(mergeDescendants = true) {}) {
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