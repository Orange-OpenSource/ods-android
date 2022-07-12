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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.utilities.composable.Title

@ExperimentalMaterialApi
@Composable
fun ListThreeLinesContent() {
    Title(textRes = R.string.component_lists_without_icon, withHorizontalPadding = true)
    ThreeLineList()

    Title(textRes = R.string.component_lists_with_icon_to_the_left, withHorizontalPadding = true)
    ThreeLineList(iconType = ListIconType.Default)

    Title(textRes = R.string.component_lists_with_avatar, withHorizontalPadding = true)
    ThreeLineList(iconType = ListIconType.Avatar)

    Title(textRes = R.string.component_lists_with_small_image, withHorizontalPadding = true)
    ThreeLineList(iconType = ListIconType.SmallImage)

    Title(textRes = R.string.component_lists_with_larger_image, withHorizontalPadding = true)
    ThreeLineList(iconType = ListIconType.WideImage)

    Spacer(modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.ods_screen_vertical_margin)))
}

@ExperimentalMaterialApi
@Composable
private fun ThreeLineList(iconType: ListIconType = ListIconType.None) {
    val text = stringResource(id = R.string.component_element_title)
    val secondaryText = stringResource(id = R.string.component_element_secondary_text_value)
    List(size = 2, text = text, secondaryText = secondaryText, singleLineSecondaryText = false, iconType = iconType) { index ->
        if (index == 0) {
            @Composable {
                Text(text = stringResource(id = R.string.component_element_caption), style = MaterialTheme.typography.caption)
            }
        } else {
            null
        }
    }
}
