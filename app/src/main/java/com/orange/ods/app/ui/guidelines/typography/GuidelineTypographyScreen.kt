/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import com.orange.ods.app.R
import com.orange.ods.app.ui.utilities.DrawableManager
import com.orange.ods.app.ui.utilities.composable.DetailScreenHeader
import com.orange.ods.compose.component.divider.OdsDivider
import com.orange.ods.compose.text.OdsText
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.theme.OdsToken
import com.orange.ods.theme.description
import com.orange.ods.theme.typography.OdsTextStyle
import com.orange.ods.theme.typography.OdsTypography

@Composable
fun GuidelineTypographyScreen() {
    val typographyTokens = OdsTheme.typography.tokens.entries

    LazyColumn(
        contentPadding = PaddingValues(bottom = dimensionResource(id = com.orange.ods.R.dimen.screen_vertical_margin)),
        verticalArrangement = Arrangement.spacedBy(OdsTheme.spacings.medium.dp)
    ) {
        item {
            DetailScreenHeader(
                imageRes = DrawableManager.getDrawableResIdForCurrentTheme(resId = R.drawable.il_typography),
                descriptionRes = R.string.guideline_typography_description
            )
        }

        itemsIndexed(typographyTokens) { index, textStyle ->
            TextStyleRow(textStyle)
            if (index < typographyTokens.lastIndex) {
                OdsDivider(
                    modifier = Modifier
                        .padding(top = OdsTheme.spacings.medium.dp)
                        .padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin))
                )
            }
        }
    }
}

@Composable
private fun TextStyleRow(textStyleToken: OdsToken<OdsTextStyle>) {
    val textColor = OdsTheme.colors.onBackground
    Column(modifier = Modifier
        .padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin))
        .semantics(mergeDescendants = true) {}) {
        OdsText(
            text = textStyleToken.description,
            style = textStyleToken.value,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        textStyleToken.composeTextStyle?.let { composeTextStyle ->
            Text(
                text = buildAnnotatedString {
                    append("Compose: ")
                    withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                        append(composeTextStyle)
                    }
                },
                color = textColor
            )
        }
        Text(
            text = buildAnnotatedString {
                append("Token: ")
                withStyle(style = SpanStyle(fontStyle = FontStyle.Italic)) {
                    append(textStyleToken.name)
                }
            },
            color = textColor
        )
    }
}

val OdsToken<OdsTextStyle>.composeTextStyle: String?
    get() {
        val textStyleProperty = when (name) {
            OdsToken.TextStyle.HeadlineLarge -> OdsTypography::headlineLarge
            OdsToken.TextStyle.HeadlineSmall -> OdsTypography::headlineSmall
            OdsToken.TextStyle.TitleLarge -> OdsTypography::titleLarge
            OdsToken.TextStyle.TitleMedium -> OdsTypography::titleMedium
            OdsToken.TextStyle.TitleSmall -> OdsTypography::titleSmall
            OdsToken.TextStyle.BodyLarge -> OdsTypography::bodyLarge
            OdsToken.TextStyle.BodyMedium -> OdsTypography::bodyMedium
            OdsToken.TextStyle.BodySmall -> OdsTypography::bodySmall
            OdsToken.TextStyle.LabelLarge -> OdsTypography::labelLarge
            OdsToken.TextStyle.LabelSmall -> OdsTypography::labelSmall
            else -> null
        }

        return textStyleProperty?.let { "OdsTheme.typography.${it.name}" }
    }
