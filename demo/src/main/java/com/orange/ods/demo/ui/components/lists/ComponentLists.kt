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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.component.chip.OdsChoiceChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.control.OdsCheckbox
import com.orange.ods.compose.component.control.OdsSwitch
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsListItemIcon
import com.orange.ods.compose.component.list.OdsListItemIconType
import com.orange.ods.compose.component.list.divider
import com.orange.ods.compose.component.list.iconType
import com.orange.ods.compose.text.OdsTextCaption
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.utilities.ComponentCountRow
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.components.utilities.clickOnElement
import com.orange.ods.demo.ui.utilities.composable.Subtitle
import com.orange.ods.demo.ui.utilities.composable.SwitchListItem
import com.orange.ods.utilities.extension.orElse

@ExperimentalMaterialApi
@Composable
fun ComponentLists() {
    val listItemCustomizationState = rememberListItemCustomizationState()
    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = listItemCustomizationState.bottomSheetScaffoldState,
        bottomSheetContent = { ComponentListsBottomSheetContent(listItemCustomizationState) },
        content = { ComponentListsContent(listItemCustomizationState) }
    )
}

@ExperimentalMaterialApi
@Composable
private fun ComponentListsBottomSheetContent(listItemCustomizationState: ListItemCustomizationState) {
    ComponentCountRow(
        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
        title = stringResource(id = R.string.component_list_item_size),
        count = listItemCustomizationState.lineCount,
        minusIconContentDescription = stringResource(id = R.string.component_list_item_remove_line),
        plusIconContentDescription = stringResource(id = R.string.component_list_item_add_line),
        minCount = ComponentListItem.MinLineCount,
        maxCount = ComponentListItem.MaxLineCount
    )

    Subtitle(textRes = R.string.component_list_leading, withHorizontalPadding = true)
    OdsChoiceChipsFlowRow(
        selectedChip = listItemCustomizationState.selectedLeading,
        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
        outlinedChips = true
    ) {
        OdsChoiceChip(textRes = R.string.component_list_leading_none, value = ListItemCustomizationState.Leading.None)
        OdsChoiceChip(textRes = R.string.component_list_leading_icon, value = ListItemCustomizationState.Leading.Icon)
        OdsChoiceChip(textRes = R.string.component_list_leading_circular_image, value = ListItemCustomizationState.Leading.CircularImage)
        OdsChoiceChip(textRes = R.string.component_list_leading_square_image, value = ListItemCustomizationState.Leading.SquareImage)
        OdsChoiceChip(textRes = R.string.component_list_leading_wide_image, value = ListItemCustomizationState.Leading.WideImage)
    }

    Subtitle(textRes = R.string.component_list_trailing, withHorizontalPadding = true)
    OdsChoiceChipsFlowRow(
        selectedChip = listItemCustomizationState.selectedTrailing,
        modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
        outlinedChips = true
    ) {
        listItemCustomizationState.trailings.forEach { trailing ->
            OdsChoiceChip(textRes = trailing.textResId, value = trailing)
        }
    }

    SwitchListItem(R.string.component_list_divider, listItemCustomizationState.dividerEnabled)
}

@ExperimentalMaterialApi
@Composable
private fun ComponentListsContent(listItemCustomizationState: ListItemCustomizationState) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        if (!listItemCustomizationState.trailings.contains(listItemCustomizationState.selectedTrailing.value)) {
            listItemCustomizationState.resetTrailing()
        }

        repeat(4) {
            OdsListItem(
                modifier = Modifier.clickable {}
                    .let { modifier ->
                        listItemCustomizationState.iconType?.let { modifier.iconType(it) }.orElse { modifier }
                    }
                    .let { if (listItemCustomizationState.dividerEnabled.value) it.divider() else it },
                text = stringResource(id = R.string.component_element_title),
                secondaryText = listItemCustomizationState.secondaryTextResId?.let { stringResource(id = it) },
                singleLineSecondaryText = listItemCustomizationState.lineCount.value == 2,
                icon = listItemCustomizationState.iconPainterResId?.let { { OdsListItemIcon(painter = painterResource(it)) } },
                trailing = listItemCustomizationState.trailing
            )
        }
    }
}

@ExperimentalMaterialApi
private val ListItemCustomizationState.Trailing.textResId: Int
    get() = when (this) {
        ListItemCustomizationState.Trailing.None -> R.string.component_list_trailing_none
        ListItemCustomizationState.Trailing.Checkbox -> R.string.component_list_trailing_checkbox
        ListItemCustomizationState.Trailing.Switch -> R.string.component_list_trailing_switch
        ListItemCustomizationState.Trailing.Icon -> R.string.component_list_trailing_icon
        ListItemCustomizationState.Trailing.Caption -> R.string.component_list_trailing_caption
    }

@ExperimentalMaterialApi
private val ListItemCustomizationState.secondaryTextResId: Int?
    get() = when (lineCount.value) {
        2 -> R.string.component_element_subtitle
        3 -> R.string.component_element_lorem_ipsum
        else -> null
    }

@ExperimentalMaterialApi
private val ListItemCustomizationState.iconType: OdsListItemIconType?
    get() = when (selectedLeading.value) {
        ListItemCustomizationState.Leading.None -> null
        ListItemCustomizationState.Leading.Icon -> OdsListItemIconType.Icon
        ListItemCustomizationState.Leading.CircularImage -> OdsListItemIconType.CircularImage
        ListItemCustomizationState.Leading.SquareImage -> OdsListItemIconType.SquareImage
        ListItemCustomizationState.Leading.WideImage -> OdsListItemIconType.WideImage
    }

@ExperimentalMaterialApi
private val ListItemCustomizationState.iconPainterResId: Int?
    get() = when (selectedLeading.value) {
        ListItemCustomizationState.Leading.None -> null
        ListItemCustomizationState.Leading.Icon -> R.drawable.ic_address_book
        ListItemCustomizationState.Leading.CircularImage,
        ListItemCustomizationState.Leading.SquareImage,
        ListItemCustomizationState.Leading.WideImage -> R.drawable.placeholder
    }

@ExperimentalMaterialApi
private val ListItemCustomizationState.trailing: (@Composable () -> Unit)?
    get() = when (selectedTrailing.value) {
        ListItemCustomizationState.Trailing.None -> null
        ListItemCustomizationState.Trailing.Checkbox -> { ->
            var checked by remember { mutableStateOf(true) }
            OdsCheckbox(checked = checked, onCheckedChange = { checked = it })
        }
        ListItemCustomizationState.Trailing.Switch -> { ->
            var checked by remember { mutableStateOf(true) }
            OdsSwitch(checked = checked, onCheckedChange = { checked = it })
        }
        ListItemCustomizationState.Trailing.Icon -> { ->
            val context = LocalContext.current
            val iconText = stringResource(id = R.string.component_element_icon)
            Icon(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .clickable {
                        clickOnElement(context, iconText)
                    },
                painter = painterResource(id = R.drawable.ic_info),
                contentDescription = null
            )
        }
        ListItemCustomizationState.Trailing.Caption -> { ->
            OdsTextCaption(text = stringResource(id = R.string.component_element_caption))
        }
    }
