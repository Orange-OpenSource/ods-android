/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import com.orange.ods.app.R
import com.orange.ods.app.ui.LocalOdsGuideline
import com.orange.ods.app.ui.components.Component
import com.orange.ods.app.ui.components.Variant
import com.orange.ods.app.ui.components.components
import com.orange.ods.app.ui.guidelines.color.DialogColor
import com.orange.ods.app.ui.guidelines.color.getValue
import com.orange.ods.app.ui.guidelines.spacing.Spacing
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsListItemIcon
import com.orange.ods.compose.component.list.OdsListItemIconType
import com.orange.ods.compose.component.list.iconType
import com.orange.ods.theme.guideline.GuidelineColor
import com.orange.ods.theme.guideline.toHexString
import com.orange.ods.utilities.extension.orElse

@Composable
fun SearchScreen(searchedText: MutableState<TextFieldValue>, onComponentClick: (Long) -> Unit) {
    val filterComponents = components.filter { component ->
        searchedText.value.text.isEmpty() || stringResource(id = component.titleRes).lowercase()
            .contains(searchedText.value.text.lowercase())
    }

    val guidelineColors = LocalOdsGuideline.current.guidelineColors
    val filterGuidelines = guidelineColors.filter { color ->
        searchedText.value.text.isEmpty() || color.getName().lowercase().contains(searchedText.value.text.lowercase()) ||
                color.lightThemeName.lowercase().contains(searchedText.value.text.lowercase()) ||
                color.darkThemeName.lowercase().contains(searchedText.value.text.lowercase())
    }

    val list = filterComponents.filter { it.variants.isNotEmpty() }
        .flatMap {
            val componentImageRes = it.smallImageRes.orElse { it.imageRes }
            it.variants.map { variant ->
                componentImageRes to variant
            }
        }

    data class OdsSearchParameter(
        val title: String,
        val route: Long,
        val image: Int?,
        val subtitle: String?,
        val color: Color?,
        val data: Any
    )

    val searchList: List<OdsSearchParameter> = filterComponents.filter { it.variants.isEmpty() }
        .map {
            val componentImageRes = it.smallImageRes.orElse { it.imageRes }
            OdsSearchParameter(stringResource(id = it.titleRes), it.id, componentImageRes, color = null, subtitle = it.composableName, data = it)
        }
        .plus(filterGuidelines.map {
            OdsSearchParameter(it.getName(), 0, image = null, color = it.getValue(), subtitle = it.getValue().toHexString(), data = it)
        })
        .plus(list.map {
            OdsSearchParameter(
                stringResource(id = it.second.titleRes),
                it.second.id,
                image = it.first,
                color = null,
                subtitle = it.second.composableName,
                data = it.second
            )
        }).plus(Spacing.values().map {
            OdsSearchParameter(
                it.tokenName,
                0,
                image = R.drawable.il_spacing,
                color = null,
                subtitle = stringResource(id = R.string.guideline_spacing_dp, it.getDp().value.toInt()) + "\n",
                data = it
            )
        }).sortedBy { it.title }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(searchList) { item ->
            val openDialog = remember { mutableStateOf(false) }
            val guideline = filterGuidelines.filter {
                it.getName() == item.title && it.getValue() == item.color
            }
            OdsListItem(
                text = item.title,
                secondaryText = item.subtitle,
                singleLineSecondaryText = false,
                modifier = Modifier
                    .iconType(OdsListItemIconType.SquareImage)
                    .clickable {
                        when (item.data) {
                            is Component, is Variant -> onComponentClick(item.route)
                            is GuidelineColor -> openDialog.value = true
                        }
                    },
                icon = {
                    OdsListItemIcon(
                        painter = if (item.image != null) {
                            painterResource(id = item.image)
                        } else if (item.color != null) {
                            ColorPainter(item.color)
                        } else {
                            painterResource(id = R.drawable.placeholder)
                        }
                    )
                }
            )
            if (openDialog.value) {
                DialogColor(color = guideline[0], openDialog)
            }
        }
    }
}

