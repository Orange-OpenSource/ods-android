/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.lists

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.orange.ods.compose.component.control.OdsCheckbox
import com.orange.ods.compose.component.control.OdsSwitch
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsListItemIcon
import com.orange.ods.compose.component.list.OdsListItemIconType
import com.orange.ods.compose.component.list.iconType
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationChip
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationChipRow
import com.orange.ods.demo.ui.utilities.composable.Subtitle
import com.orange.ods.demo.ui.utilities.composable.SwitchListItem

@ExperimentalMaterialApi
@Composable
fun ComponentLists() {
    val variantListsState = rememberVariantListsState()
    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = variantListsState.bottomSheetScaffoldState,
        bottomSheetContent = { ComponentListsBottomSheetContent(variantListsState) },
        content = { ComponentListsContent(variantListsState) }
    )
}

@ExperimentalMaterialApi
@Composable
private fun ComponentListsBottomSheetContent(variantListsState: VariantListsState) {
    Subtitle(textRes = R.string.component_list_size, withHorizontalPadding = true)
    ComponentCustomizationChipRow(variantListsState.selectedSize) {
        ComponentCustomizationChip(textRes = R.string.component_list_size_single_line, value = VariantListsState.Size.SingleLine)
        ComponentCustomizationChip(textRes = R.string.component_list_size_two_line, value = VariantListsState.Size.TwoLine)
        ComponentCustomizationChip(textRes = R.string.component_list_size_three_line, value = VariantListsState.Size.ThreeLine)
    }

    Subtitle(textRes = R.string.component_list_leading, withHorizontalPadding = true)
    ComponentCustomizationChipRow(variantListsState.selectedLeading) {
        ComponentCustomizationChip(textRes = R.string.component_list_leading_none, value = VariantListsState.Leading.None)
        ComponentCustomizationChip(textRes = R.string.component_list_leading_icon, value = VariantListsState.Leading.Icon)
        ComponentCustomizationChip(textRes = R.string.component_list_leading_circular_image, value = VariantListsState.Leading.CircularImage)
        ComponentCustomizationChip(textRes = R.string.component_list_leading_square_image, value = VariantListsState.Leading.SquareImage)
        ComponentCustomizationChip(textRes = R.string.component_list_leading_wide_image, value = VariantListsState.Leading.WideImage)
    }

    Subtitle(textRes = R.string.component_list_trailing, withHorizontalPadding = true)
    ComponentCustomizationChipRow(variantListsState.selectedTrailing) {
        variantListsState.trailings.forEach { trailingValue ->
            val textRes = when (trailingValue) {
                VariantListsState.Trailing.None -> R.string.component_list_trailing_none
                VariantListsState.Trailing.Checkbox -> R.string.component_list_trailing_checkbox
                VariantListsState.Trailing.Switch -> R.string.component_list_trailing_switch
                VariantListsState.Trailing.Icon -> R.string.component_list_trailing_icon
                VariantListsState.Trailing.Caption -> R.string.component_list_trailing_caption
            }
            ComponentCustomizationChip(textRes = textRes, value = trailingValue)
        }
    }

    SwitchListItem(R.string.component_list_divider, variantListsState.dividerEnabled)
}

@ExperimentalMaterialApi
@Composable
private fun ComponentListsContent(variantListsState: VariantListsState) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        if (!variantListsState.trailings.contains(variantListsState.selectedTrailing.value)) {
            variantListsState.resetTrailing()
        }

        val iconType = when (variantListsState.selectedLeading.value) {
            VariantListsState.Leading.None -> null
            VariantListsState.Leading.Icon -> OdsListItemIconType.Icon
            VariantListsState.Leading.CircularImage -> OdsListItemIconType.CircularImage
            VariantListsState.Leading.SquareImage -> OdsListItemIconType.SquareImage
            VariantListsState.Leading.WideImage -> OdsListItemIconType.WideImage
        }
        val modifier = with(Modifier.clickable {}) {
            if (iconType != null) iconType(iconType) else this
        }
        val text = stringResource(id = R.string.component_element_title)
        val secondaryText = when (variantListsState.selectedSize.value) {
            VariantListsState.Size.SingleLine -> null
            VariantListsState.Size.TwoLine -> stringResource(id = R.string.component_element_subtitle)
            VariantListsState.Size.ThreeLine -> stringResource(id = R.string.component_element_lorem_ipsum)
        }
        val singleLineSecondaryText = variantListsState.selectedSize.value == VariantListsState.Size.TwoLine
        val painter = when (variantListsState.selectedLeading.value) {
            VariantListsState.Leading.None -> null
            VariantListsState.Leading.Icon -> painterResource(id = R.drawable.ic_address_book)
            VariantListsState.Leading.CircularImage,
            VariantListsState.Leading.SquareImage,
            VariantListsState.Leading.WideImage -> painterResource(id = R.drawable.placeholder)
        }
        val trailing = getTrailing(variantListsState)
        repeat(4) {
            OdsListItem(
                modifier = modifier,
                text = text,
                secondaryText = secondaryText,
                singleLineSecondaryText = singleLineSecondaryText,
                icon = painter?.let { { OdsListItemIcon(painter = painter) } },
                trailing = trailing
            )

            if (variantListsState.dividerEnabled.value) {
                Divider(startIndent = getStartIndent(variantListsState = variantListsState))
            }
        }
    }
}

@ExperimentalMaterialApi
private fun getTrailing(variantListsState: VariantListsState): (@Composable () -> Unit)? {
    return if (variantListsState.selectedTrailing.value != VariantListsState.Trailing.None) {
        @Composable {
            var checked by remember { mutableStateOf(true) }
            when (variantListsState.selectedTrailing.value) {
                VariantListsState.Trailing.None -> {}
                VariantListsState.Trailing.Checkbox -> OdsCheckbox(checked = checked, onCheckedChange = { checked = it })
                VariantListsState.Trailing.Switch -> OdsSwitch(checked = checked, onCheckedChange = { checked = it })
                VariantListsState.Trailing.Icon -> Icon(painter = painterResource(id = R.drawable.ic_info), contentDescription = null)
                VariantListsState.Trailing.Caption -> Text(
                    text = stringResource(id = R.string.component_element_caption),
                    style = MaterialTheme.typography.caption
                )
            }
        }
    } else {
        null
    }
}

@ExperimentalMaterialApi
@Composable
private fun getStartIndent(variantListsState: VariantListsState): Dp {
    return when (variantListsState.selectedLeading.value) {
        VariantListsState.Leading.None -> dimensionResource(id = R.dimen.spacing_m)
        VariantListsState.Leading.Icon,
        VariantListsState.Leading.CircularImage -> dimensionResource(id = R.dimen.avatar_size) + dimensionResource(id = R.dimen.spacing_m).times(2)
        VariantListsState.Leading.SquareImage -> {
            dimensionResource(id = R.dimen.list_square_image_size) + dimensionResource(id = R.dimen.spacing_m).times(2)
        }
        VariantListsState.Leading.WideImage -> dimensionResource(id = R.dimen.list_wide_image_width) + dimensionResource(id = R.dimen.spacing_m)
    }
}
