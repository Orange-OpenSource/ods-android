/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.banners

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.OdsComponent
import com.orange.ods.compose.component.banner.OdsBanner
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.utilities.ComponentCountRow
import com.orange.ods.demo.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.demo.ui.components.utilities.clickOnElement
import com.orange.ods.demo.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.demo.ui.utilities.composable.CommonTechnicalTextColumn
import com.orange.ods.demo.ui.utilities.composable.TechnicalText

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentBanners() {

    val bannerCustomizationState = rememberBannerCustomizationState()

    with(bannerCustomizationState) {
        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {
                ComponentCountRow(
                    title = stringResource(id = R.string.component_banner_text_lines_count),
                    count = textLinesCount,
                    minusIconContentDescription = stringResource(id = R.string.component_remove_action_button),
                    plusIconContentDescription = stringResource(id = R.string.component_add_action_button),
                    modifier = Modifier.padding(start = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                    minCount = BannerCustomizationState.MinTextCount,
                    maxCount = BannerCustomizationState.MaxTextCount
                )
                ComponentCountRow(
                    title = stringResource(id = R.string.component_banner_buttons_count),
                    count = buttonsCount,
                    minusIconContentDescription = stringResource(id = R.string.component_banner_remove_action_button),
                    plusIconContentDescription = stringResource(id = R.string.component_banner_add_action_button),
                    modifier = Modifier.padding(start = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                    minCount = BannerCustomizationState.MinActionButtonCount,
                    maxCount = BannerCustomizationState.MaxActionButtonCount
                )
                OdsListItem(
                    text = stringResource(id = R.string.component_banner_image),
                    trailing = OdsSwitchTrailing(checked = iconChecked)
                )
            }
        ) {
            val context = LocalContext.current
            val button1Text = stringResource(id = R.string.component_element_button1)
            val button2Text = stringResource(id = R.string.component_element_button2)
            Column {
                OdsBanner(
                    message = if (hasTwoTextLines) stringResource(id = R.string.component_banner_two_line_text) else stringResource(id = R.string.component_banner_one_line_text),
                    button1Text = stringResource(id = R.string.component_snackbar_action_label),
                    button2Text = if (hasButton2) stringResource(id = R.string.component_snackbar_action_label) else null,
                    image = if (hasIcon) painterResource(id = com.orange.ods.R.drawable.placeholder) else null,
                    onButton1Click = { clickOnElement(context, button1Text) },
                    onButton2Click = { clickOnElement(context, button2Text) },
                )

                CodeImplementationColumn {
                    CommonTechnicalTextColumn(
                        componentName = OdsComponent.OdsBanner.name
                    ) {
                        if (hasTwoTextLines) TechnicalText(text = " message = \"${stringResource(id = R.string.component_banner_two_line_text)}\"")
                        else TechnicalText(text = " message = \"${stringResource(id = R.string.component_banner_one_line_text)}\"")
                        if (hasIcon) TechnicalText(text = " image = painterResource(id = R.drawable.placeholder)")
                        TechnicalText(text = " button1Text = \"${stringResource(id = R.string.component_snackbar_action_label)}\"")
                        if (hasButton2) TechnicalText(text = " button2Text = \"${stringResource(id = R.string.component_snackbar_action_label)}\"")
                    }
                }
            }
        }
    }
}

