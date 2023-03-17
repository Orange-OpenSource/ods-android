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
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import com.orange.ods.app.ui.LocalOdsGuideline
import com.orange.ods.app.ui.components.components
import com.orange.ods.app.ui.guidelines.color.DialogColor
import com.orange.ods.app.ui.guidelines.color.getValue
import com.orange.ods.app.ui.guidelines.spacing.GuidelineSpacingImage
import com.orange.ods.app.ui.guidelines.spacing.Spacing
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsListItemIcon
import com.orange.ods.compose.component.list.OdsListItemIconType
import com.orange.ods.compose.component.list.iconType
import com.orange.ods.utilities.extension.orElse

@Composable
fun SearchScreen(searchedText: MutableState<TextFieldValue>, onComponentClick: (Long) -> Unit) {
    val filterComponents = components.filter { component ->
        searchedText.value.text.isEmpty() || stringResource(id = component.titleRes).lowercase().contains(searchedText.value.text.lowercase())
    }
    val guidelineColors = LocalOdsGuideline.current.guidelineColors
    val filterGuidelines = guidelineColors.filter { color ->
        searchedText.value.text.isEmpty() || color.getName().lowercase().contains(searchedText.value.text.lowercase()) ||
                color.lightThemeName.lowercase().contains(searchedText.value.text.lowercase()) ||
                color.darkThemeName.lowercase().contains(searchedText.value.text.lowercase()
        )
    }


    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(filterComponents) { component ->
            val componentImageRes = component.smallImageRes.orElse { component.imageRes }
            OdsListItem(
                text = stringResource(id = component.titleRes),
                secondaryText = null,
                singleLineSecondaryText = false,
                modifier = Modifier
                    .iconType(OdsListItemIconType.SquareImage)
                    .clickable { onComponentClick(component.id) },
                icon = {
                    OdsListItemIcon(
                        painterResource(id = componentImageRes)
                    )
                },
            )
        }
        items(filterGuidelines) { guideline ->
            val openDialog = remember { mutableStateOf(false) }
            OdsListItem(
                text = guideline.getName(),
                secondaryText = null,
                singleLineSecondaryText = false,
                modifier = Modifier
                    .iconType(OdsListItemIconType.SquareImage)
                    .clickable { openDialog.value = true },
                icon = {
                    OdsListItemIcon(
                        painter = ColorPainter(guideline.getValue())
                    )
                },
            )
            if (openDialog.value) {
                DialogColor(color = guideline, openDialog)
            }
        }
        items(Spacing.values()) { spacing ->
            OdsListItem(
                text = spacing.tokenName,
                secondaryText = null,
                singleLineSecondaryText = false,
                modifier = Modifier
                    .iconType(OdsListItemIconType.SquareImage)
                    .clickable { },
                icon = { GuidelineSpacingImage(spacing = spacing) },
            )
        }
    }
}
