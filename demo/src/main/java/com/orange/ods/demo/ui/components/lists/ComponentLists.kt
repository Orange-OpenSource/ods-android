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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.control.OdsCheckbox
import com.orange.ods.compose.component.control.OdsSwitch
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsListItemIcon
import com.orange.ods.compose.component.list.OdsListItemIconType
import com.orange.ods.compose.component.list.OdsListItemScope
import com.orange.ods.compose.component.list.divider
import com.orange.ods.compose.component.list.iconType
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationChip
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationChipRow
import com.orange.ods.demo.ui.utilities.composable.Subtitle
import com.orange.ods.demo.ui.utilities.composable.SwitchListItem
import com.orange.ods.utilities.extension.orElse

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
        variantListsState.trailings.forEach { trailing ->
            ComponentCustomizationChip(textRes = trailing.textResId, value = trailing)
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

        repeat(4) {
            OdsListItem(
                modifier = Modifier.clickable {}
                    .let { modifier ->
                        variantListsState.iconType?.let { modifier.iconType(it) }.orElse { modifier }
                    }
                    .let { if (variantListsState.dividerEnabled.value) it.divider() else it },
                text = stringResource(id = R.string.component_element_title),
                secondaryText = variantListsState.secondaryTextResId?.let { stringResource(id = it) },
                singleLineSecondaryText = variantListsState.selectedSize.value == VariantListsState.Size.TwoLine,
                icon = variantListsState.iconPainterResId?.let { { OdsListItemIcon(painter = painterResource(it)) } },
                trailing = variantListsState.trailing
            )
        }
    }
}

@ExperimentalMaterialApi
private val VariantListsState.Trailing.textResId: Int
    get() = when (this) {
        VariantListsState.Trailing.None -> R.string.component_list_trailing_none
        VariantListsState.Trailing.Checkbox -> R.string.component_list_trailing_checkbox
        VariantListsState.Trailing.Switch -> R.string.component_list_trailing_switch
        VariantListsState.Trailing.Icon -> R.string.component_list_trailing_icon
        VariantListsState.Trailing.Caption -> R.string.component_list_trailing_caption
    }

@ExperimentalMaterialApi
private val VariantListsState.secondaryTextResId: Int?
    get() = when (selectedSize.value) {
        VariantListsState.Size.SingleLine -> null
        VariantListsState.Size.TwoLine -> R.string.component_element_subtitle
        VariantListsState.Size.ThreeLine -> R.string.component_element_lorem_ipsum
    }

@ExperimentalMaterialApi
private val VariantListsState.iconType: OdsListItemIconType?
    get() = when (selectedLeading.value) {
        VariantListsState.Leading.None -> null
        VariantListsState.Leading.Icon -> OdsListItemIconType.Icon
        VariantListsState.Leading.CircularImage -> OdsListItemIconType.CircularImage
        VariantListsState.Leading.SquareImage -> OdsListItemIconType.SquareImage
        VariantListsState.Leading.WideImage -> OdsListItemIconType.WideImage
    }

@ExperimentalMaterialApi
private val VariantListsState.iconPainterResId: Int?
    get() = when (selectedLeading.value) {
        VariantListsState.Leading.None -> null
        VariantListsState.Leading.Icon -> R.drawable.ic_address_book
        VariantListsState.Leading.CircularImage,
        VariantListsState.Leading.SquareImage,
        VariantListsState.Leading.WideImage -> R.drawable.placeholder
    }

@ExperimentalMaterialApi
private val VariantListsState.trailing: (@Composable OdsListItemScope.() -> Unit)?
    get() = when (selectedTrailing.value) {
        VariantListsState.Trailing.None -> null
        VariantListsState.Trailing.Checkbox -> { ->
            var checked by remember { mutableStateOf(true) }
            OdsCheckbox(checked = checked, onCheckedChange = { checked = it })
        }
        VariantListsState.Trailing.Switch -> { ->
            var checked by remember { mutableStateOf(true) }
            OdsSwitch(checked = checked, onCheckedChange = { checked = it })
        }
        VariantListsState.Trailing.Icon -> { ->
            Icon(painter = painterResource(id = R.drawable.ic_info), contentDescription = null)
        }
        VariantListsState.Trailing.Caption -> { ->
            Text(text = stringResource(id = R.string.component_element_caption), style = MaterialTheme.typography.caption)
        }
    }
