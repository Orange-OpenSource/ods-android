/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.listitem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
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
import com.orange.ods.app.ui.components.utilities.ComponentCountRow
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.app.ui.utilities.DrawableManager
import com.orange.ods.app.ui.utilities.code.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.code.FunctionCallCode
import com.orange.ods.app.ui.utilities.composable.Subtitle
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.chip.OdsChoiceChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.listitem.OdsListItem
import com.orange.ods.extension.ifNotNull

@OptIn(ExperimentalMaterialApi::class)
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
    ComponentCountRow(
        modifier = Modifier.padding(start = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin)),
        title = stringResource(id = R.string.component_list_item_line_count),
        count = listItemCustomizationState.lineCount,
        minusIconContentDescription = stringResource(id = R.string.component_list_item_remove_line),
        plusIconContentDescription = stringResource(id = R.string.component_list_item_add_line),
        minCount = ListItemCustomizationState.MinLineCount,
        maxCount = ListItemCustomizationState.MaxLineCount
    )

    Subtitle(textRes = R.string.component_list_leading, horizontalPadding = true)
    OdsChoiceChipsFlowRow(
        value = listItemCustomizationState.selectedLeadingIconType.value,
        onValueChange = { value -> listItemCustomizationState.selectedLeadingIconType.value = value },
        modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)),
        chips = listOf(
            OdsChoiceChip(text = stringResource(id = R.string.component_list_leading_none), value = null),
            OdsChoiceChip(text = stringResource(id = R.string.component_list_leading_icon), value = OdsListItem.LeadingIcon.Type.Icon),
            OdsChoiceChip(text = stringResource(id = R.string.component_list_leading_circular_image), value = OdsListItem.LeadingIcon.Type.CircularImage),
            OdsChoiceChip(text = stringResource(id = R.string.component_list_leading_square_image), value = OdsListItem.LeadingIcon.Type.SquareImage),
            OdsChoiceChip(text = stringResource(id = R.string.component_list_leading_wide_image), value = OdsListItem.LeadingIcon.Type.WideImage),
        )
    )

    Subtitle(textRes = R.string.component_list_trailing, horizontalPadding = true)
    OdsChoiceChipsFlowRow(
        value = listItemCustomizationState.selectedTrailing.value,
        onValueChange = { value -> listItemCustomizationState.selectedTrailing.value = value },
        modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)),
        chips = listItemCustomizationState.trailings.map { trailing ->
            OdsChoiceChip(text = stringResource(id = trailing.textResId), value = trailing)
        }
    )
}

@Composable
private fun ComponentListItemContent(listItemCustomizationState: ListItemCustomizationState) {
    val recipe = LocalRecipes.current.first { it.description.isNotBlank() }
    with(listItemCustomizationState) {
        Column {
            if (!trailings.contains(selectedTrailing.value)) {
                resetTrailing()
            }

            val singleLineSecondaryText = lineCount.intValue == 2

            val text = recipe.title
            val secondaryText = if (lineCount.intValue > 1) recipe.description else null
            val leadingIcon = ifNotNull(getIconPainter(recipe), selectedLeadingIconType.value) { painter, type ->
                OdsListItem.LeadingIcon(type, painter, "")
            }

            val context = LocalContext.current
            OdsListItem(
                text = text,
                secondaryText = secondaryText,
                singleLineSecondaryText = singleLineSecondaryText,
                leadingIcon = leadingIcon,
                trailing = trailing
            ) {
                clickOnElement(context = context, context.getString(R.string.component_list_item))
            }

            CodeImplementationColumn(modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin))) {
                FunctionCallCode(
                    name = OdsComposable.OdsListItem.name,
                    parameters = {
                        string("text", text)
                        if (secondaryText != null) {
                            string("secondaryText", secondaryText)
                            if (!singleLineSecondaryText) stringRepresentation("singleLineSecondaryText", false)
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
        OdsListItem.TrailingIcon::class.java -> R.string.component_list_trailing_icon
        OdsListItem.TrailingCaption::class.java -> R.string.component_list_trailing_caption
        else -> R.string.component_list_trailing_none
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
            rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(recipe.imageUrl)
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
