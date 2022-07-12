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

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.control.OdsCheckbox
import com.orange.ods.compose.component.control.OdsSwitch
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.utilities.composable.Title

@ExperimentalMaterialApi
@Composable
fun ListOneLineContent() {
    Title(textRes = R.string.component_lists_with_label_text, withHorizontalPadding = true)
    SingleLineList(text = null, secondaryText = stringResource(id = R.string.component_element_label))

    Title(textRes = R.string.component_lists_with_normal_text, withHorizontalPadding = true)
    SingleLineList()

    Title(textRes = R.string.component_lists_with_icon_to_the_left, withHorizontalPadding = true)
    SingleLineList(iconType = ListIconType.Default)

    Title(textRes = R.string.component_lists_with_avatar, withHorizontalPadding = true)
    SingleLineList(iconType = ListIconType.Avatar)

    Title(textRes = R.string.component_lists_with_small_image, withHorizontalPadding = true)
    SingleLineList(iconType = ListIconType.SmallImage)

    Title(textRes = R.string.component_lists_with_larger_image, withHorizontalPadding = true)
    SingleLineList(iconType = ListIconType.WideImage)

    Spacer(modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.ods_screen_vertical_margin)))
}

@ExperimentalMaterialApi
@Composable
private fun SingleLineList(
    text: String? = stringResource(id = R.string.component_element_title),
    secondaryText: String? = null,
    iconType: ListIconType = ListIconType.None,
) {
    List(size = 4, text = text, secondaryText = secondaryText, iconType = iconType) { index ->
        if (index > 0) {
            @Composable {
                var checked by remember { mutableStateOf(true) }
                when (index) {
                    1 -> OdsCheckbox(checked = checked, onCheckedChange = { checked = it })
                    2 -> OdsSwitch(checked = checked, onCheckedChange = { checked = it })
                    3 -> Icon(painter = painterResource(id = R.drawable.ic_info), contentDescription = null)
                }
            }
        } else {
            null
        }
    }
}
