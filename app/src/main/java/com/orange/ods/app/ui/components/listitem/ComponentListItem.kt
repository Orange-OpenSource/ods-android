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

package com.orange.ods.app.ui.components.listitem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import coil.size.Size
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.domain.recipes.Recipe
import com.orange.ods.app.ui.LocalThemeManager
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.app.ui.utilities.DrawableManager
import com.orange.ods.app.ui.utilities.code.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.code.FunctionCallCode
import com.orange.ods.app.ui.utilities.composable.Subtitle
import com.orange.ods.app.ui.utilities.extension.buildImageRequest
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.listitem.OdsListItem
import com.orange.ods.compose.extension.ifNotNull
import com.orange.ods.compose.extension.orElse
import com.orange.ods.compose.theme.OdsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentListItem() {
    val listItemCustomizationState = rememberListItemCustomizationState()
    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = listItemCustomizationState.bottomSheetScaffoldState,
        bottomSheetContent = { ComponentListItemBottomSheetContent(listItemCustomizationState) },
        content = { ComponentListItemContent(listItemCustomizationState) }
    )
}

@Composable
private fun ComponentListItemBottomSheetContent(listItemCustomizationState: ListItemCustomizationState) {
    Subtitle(textRes = R.string.component_list_item_secondary_text, horizontalPadding = true)
    val secondaryTextLineCountValues = listOf(null) + OdsListItem.SecondaryTextLineCount.entries
    OdsChoiceChipsFlowRow(
        selectedChoiceChipIndex = secondaryTextLineCountValues.indexOf(listItemCustomizationState.secondaryTextLineCount.value),
        modifier = Modifier.padding(horizontal = OdsTheme.spacings.medium.dp),
        choiceChips = secondaryTextLineCountValues.map { secondaryTextLineCount ->
            val textResId = when (secondaryTextLineCount) {
                OdsListItem.SecondaryTextLineCount.One -> R.string.component_list_item_secondary_text_one_line
                OdsListItem.SecondaryTextLineCount.Two -> R.string.component_list_item_secondary_text_two_lines
                null -> R.string.component_element_none
            }
            OdsChoiceChipsFlowRow.ChoiceChip(
                stringResource(id = textResId),
                { listItemCustomizationState.secondaryTextLineCount.value = secondaryTextLineCount })
        }
    )

    Subtitle(textRes = R.string.component_list_leading, horizontalPadding = true)
    val leadingIconTypes = listOf(null) + OdsListItem.LeadingIcon.Type.entries
    OdsChoiceChipsFlowRow(
        selectedChoiceChipIndex = leadingIconTypes.indexOf(listItemCustomizationState.selectedLeadingIconType.value),
        modifier = Modifier.padding(horizontal = OdsTheme.spacings.medium.dp),
        choiceChips = leadingIconTypes.map { leadingIconType ->
            val textResId = when (leadingIconType) {
                OdsListItem.LeadingIcon.Type.Icon -> R.string.component_element_icon
                OdsListItem.LeadingIcon.Type.CircularImage -> R.string.component_list_leading_circular_image
                OdsListItem.LeadingIcon.Type.SquareImage -> R.string.component_list_leading_square_image
                OdsListItem.LeadingIcon.Type.WideImage -> R.string.component_list_leading_wide_image
                null -> R.string.component_element_none
            }
            OdsChoiceChipsFlowRow.ChoiceChip(stringResource(id = textResId), { listItemCustomizationState.selectedLeadingIconType.value = leadingIconType })
        }
    )

    Subtitle(textRes = R.string.component_list_trailing, horizontalPadding = true)
    OdsChoiceChipsFlowRow(
        selectedChoiceChipIndex = listItemCustomizationState.trailings.indexOf(listItemCustomizationState.selectedTrailing.value),
        modifier = Modifier.padding(horizontal = OdsTheme.spacings.medium.dp),
        choiceChips = listItemCustomizationState.trailings.map { trailing ->
            OdsChoiceChipsFlowRow.ChoiceChip(stringResource(id = trailing.textResId), { listItemCustomizationState.selectedTrailing.value = trailing })
        }
    )
}

@Composable
private fun ComponentListItemContent(listItemCustomizationState: ListItemCustomizationState) {
    val recipe = LocalRecipes.current.first { it.description.isNotBlank() }
    with(listItemCustomizationState) {
        Column(modifier = Modifier.padding(vertical = dimensionResource(id = com.orange.ods.R.dimen.screen_vertical_margin))) {
            if (!trailings.contains(selectedTrailing.value)) {
                resetTrailing()
            }

            val text = recipe.title
            val secondaryTextLineCountValue = secondaryTextLineCount.value
            val secondaryText = if (secondaryTextLineCountValue != null) recipe.description else null
            val leadingIcon = ifNotNull(getIconPainter(recipe), selectedLeadingIconType.value) { painter, type ->
                OdsListItem.LeadingIcon(type, painter, "")
            }

            val context = LocalContext.current
            OdsListItem(
                text = text,
                secondaryText = secondaryText,
                secondaryTextLineCount = secondaryTextLineCountValue.orElse { OdsListItem.SecondaryTextLineCount.One },
                leadingIcon = leadingIcon,
                trailing = trailing
            ) {
                clickOnElement(context = context, context.getString(R.string.component_list_item))
            }

            CodeImplementationColumn(
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin))
                    .padding(top = OdsTheme.spacings.small.dp)
            ) {
                FunctionCallCode(
                    name = OdsComposable.OdsListItem.name,
                    parameters = {
                        string("text", text)
                        if (secondaryTextLineCountValue != null && secondaryText != null) {
                            string("secondaryText", secondaryText)
                            enum("secondaryTextLineCount", secondaryTextLineCountValue)
                        }
                        selectedLeadingIconType.value?.let { iconType ->
                            classInstance<OdsListItem.LeadingIcon>("leadingIcon") {
                                enum("type", iconType)
                                painter()
                                contentDescription("")
                            }
                        }
                        selectedTrailing.value?.let { trailingClass ->
                            classInstance("trailing", trailingClass) {
                                when (trailingClass) {
                                    OdsListItem.TrailingCheckbox::class.java,
                                    OdsListItem.TrailingSwitch::class.java -> {
                                        simple("checked", "checked")
                                        lambda("onCheckedChange", "checked = it")
                                    }
                                    OdsListItem.TrailingRadioButton::class.java -> {
                                        simple("selected", "selected")
                                        lambda("onClick", "selected = it")
                                    }
                                    OdsListItem.TrailingIcon::class.java -> {
                                        painter()
                                        contentDescription("")
                                        onClick()
                                    }
                                    OdsListItem.TrailingCaption::class.java -> {
                                        text(context.getString(R.string.component_element_caption))
                                    }
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}

private val Class<out OdsListItem.Trailing>?.textResId: Int
    get() = when (this) {
        OdsListItem.TrailingCheckbox::class.java -> R.string.component_list_trailing_checkbox
        OdsListItem.TrailingSwitch::class.java -> R.string.component_list_trailing_switch
        OdsListItem.TrailingRadioButton::class.java -> R.string.component_list_trailing_radio_button
        OdsListItem.TrailingIcon::class.java -> R.string.component_element_icon
        OdsListItem.TrailingCaption::class.java -> R.string.component_element_caption
        else -> R.string.component_element_none
    }

@Composable
private fun ListItemCustomizationState.getIconPainter(recipe: Recipe): Painter? {
    return when (selectedLeadingIconType.value) {
        OdsListItem.LeadingIcon.Type.Icon -> recipe.iconResId?.let { painterResource(id = it) }
        OdsListItem.LeadingIcon.Type.CircularImage,
        OdsListItem.LeadingIcon.Type.SquareImage,
        OdsListItem.LeadingIcon.Type.WideImage -> {
            val wideImageSizeWidthPx = with(LocalDensity.current) { dimensionResource(id = com.orange.ods.R.dimen.list_wide_image_width).toPx() }
            val wideImageSizeHeightPx = with(LocalDensity.current) { dimensionResource(id = com.orange.ods.R.dimen.list_wide_image_height).toPx() }
            val darkModeEnabled = LocalThemeManager.current.darkModeEnabled
            rememberAsyncImagePainter(
                model = ImageRequest.Builder(buildImageRequest(LocalContext.current, recipe.imageUrl, darkModeEnabled))
                    .scale(Scale.FILL)
                    .size(Size(wideImageSizeWidthPx.toInt(), wideImageSizeHeightPx.toInt()))
                    .build(),
                placeholder = painterResource(id = DrawableManager.getPlaceholderSmallResId()),
                error = painterResource(id = DrawableManager.getPlaceholderSmallResId(error = true))
            )
        }
        null -> null
    }
}

private val ListItemCustomizationState.trailing: OdsListItem.Trailing?
    @Composable
    get() = when (selectedTrailing.value) {
        OdsListItem.TrailingCheckbox::class.java -> {
            var checked by remember { mutableStateOf(true) }
            OdsListItem.TrailingCheckbox(checked, { checked = it })
        }
        OdsListItem.TrailingSwitch::class.java -> {
            var checked by remember { mutableStateOf(true) }
            OdsListItem.TrailingSwitch(checked, { checked = it })
        }
        OdsListItem.TrailingRadioButton::class.java -> {
            var selected by remember { mutableStateOf(true) }
            OdsListItem.TrailingRadioButton(selected, { selected = !selected })
        }
        OdsListItem.TrailingIcon::class.java -> {
            val context = LocalContext.current
            val iconText = stringResource(id = R.string.component_element_icon)
            OdsListItem.TrailingIcon(painterResource(id = R.drawable.ic_info), stringResource(id = R.string.component_list_information)) {
                clickOnElement(context, iconText)
            }
        }
        OdsListItem.TrailingCaption::class.java -> {
            OdsListItem.TrailingCaption(stringResource(id = R.string.component_element_caption))
        }
        else -> null
    }
