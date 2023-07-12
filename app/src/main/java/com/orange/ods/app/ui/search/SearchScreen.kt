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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import com.orange.ods.app.R
import com.orange.ods.app.ui.LocalOdsGuideline
import com.orange.ods.app.ui.MainDestinations
import com.orange.ods.app.ui.components.Component
import com.orange.ods.app.ui.components.Variant
import com.orange.ods.app.ui.components.components
import com.orange.ods.app.ui.guidelines.color.DialogColor
import com.orange.ods.app.ui.guidelines.color.getValue
import com.orange.ods.app.ui.guidelines.spacing.Spacing
import com.orange.ods.app.ui.utilities.DrawableManager
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsListItemIcon
import com.orange.ods.compose.component.list.OdsListItemIconType
import com.orange.ods.compose.component.list.iconType
import com.orange.ods.theme.guideline.GuidelineColor
import com.orange.ods.theme.guideline.toHexString
import com.orange.ods.utilities.extension.orElse

@Composable
fun SearchScreen(
    searchedText: MutableState<TextFieldValue>,
    onResultItemClick: (String, Long?) -> Unit
) {

    val context = LocalContext.current

    val filteredComponents = components.filter { component ->
        searchedText.value.text.isEmpty() || stringResource(id = component.titleRes).lowercase()
            .contains(searchedText.value.text.lowercase())
    }.asSequence()

    val filteredSpacings = Spacing.values().filter { spacing ->
        searchedText.value.text.isEmpty() || spacing.tokenName.lowercase()
            .contains(searchedText.value.text.lowercase())
    }

    val filteredGuidelineColors = LocalOdsGuideline.current.guidelineColors.filter { guidelineColor ->
        searchedText.value.text.isEmpty() || guidelineColor.getName().lowercase().contains(searchedText.value.text.lowercase()) ||
                guidelineColor.lightThemeName.lowercase().contains(searchedText.value.text.lowercase()) ||
                guidelineColor.darkThemeName.lowercase().contains(searchedText.value.text.lowercase())
    }

    val filteredVariants = components.filter { it.variants.isNotEmpty() }
        .flatMap { component ->
            val componentImageRes = component.smallImageRes.orElse { component.imageRes }
            component.variants.filter { variant ->
                searchedText.value.text.isEmpty() || context.getString(variant.titleRes).lowercase()
                    .contains(searchedText.value.text.lowercase())
            }.map { variant ->
                componentImageRes to variant
            }
        }

    data class SearchResult(
        val title: String,
        val id: Long,
        val image: Int?,
        val subtitle: String?,
        val color: Color?,
        val data: Any
    )

    val searchResults: List<SearchResult> = filteredComponents.filter { it.variants.isEmpty() }
        .map { component ->
            SearchResult(
                context.getString(component.titleRes),
                component.id,
                component.smallImageRes.orElse { component.imageRes },
                color = null,
                subtitle = component.composableName,
                data = component
            )
        }
        .plus(filteredGuidelineColors.map { guidelineColor ->
            SearchResult(
                guidelineColor.getName(),
                0,
                image = null,
                color = guidelineColor.getValue(),
                subtitle = guidelineColor.getValue().toHexString(),
                data = guidelineColor
            )
        })
        .plus(filteredVariants.map { variant ->
            SearchResult(
                stringResource(id = variant.second.titleRes),
                id = variant.second.id,
                image = variant.first,
                color = null,
                subtitle = variant.second.composableName,
                data = variant.second
            )
        }).plus(filteredSpacings.map { spacing ->
            SearchResult(
                spacing.tokenName,
                0,
                image = R.drawable.il_spacing,
                color = null,
                subtitle = stringResource(id = R.string.guideline_spacing_dp, spacing.getDp().value.toInt()),
                data = spacing
            )
        }).sortedBy { it.title }.toList()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(searchResults) { item ->
            val openDialog = remember { mutableStateOf(false) }
            val guidelineColor = filteredGuidelineColors.firstOrNull { guidelineColor ->
                guidelineColor.getName() == item.title && guidelineColor.getValue() == item.color
            }
            OdsListItem(
                text = item.title,
                secondaryText = item.subtitle,
                singleLineSecondaryText = true,
                modifier = Modifier
                    .iconType(OdsListItemIconType.SquareImage)
                    .clickable {
                        when (item.data) {
                            is Component -> onResultItemClick(MainDestinations.ComponentDetailRoute, item.id)
                            is Variant -> onResultItemClick(MainDestinations.ComponentVariantDemoRoute, item.id)
                            is Spacing -> onResultItemClick(MainDestinations.GuidelineSpacing, null)
                            is GuidelineColor -> openDialog.value = true
                        }
                    },
                icon = {
                    print(item)
                    OdsListItemIcon(
                        painter = when {
                            item.image != null -> painterResource(id = DrawableManager.getDrawableResIdForCurrentTheme(resId = item.image))
                            item.color != null -> ColorPainter(item.color)
                            else -> painterResource(id = DrawableManager.getPlaceholderResId())
                        }
                    )
                }
            )
            if (openDialog.value && guidelineColor != null) {
                DialogColor(color = guidelineColor, openDialog)
            }
        }
    }
}

