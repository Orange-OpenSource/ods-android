/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.cards

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.text.OdsTextBody2
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.SubComponent

@ExperimentalMaterialApi
@Composable
fun SubComponentCard(subComponent: SubComponent) {
    when (subComponent) {
        SubComponent.CardImageFirst -> CardImageFirstContent()
        SubComponent.CardSmall -> CardSmallContent()
        SubComponent.CardTitleFirst -> CardTitleFirstContent()
        else -> {}
    }
}

@Composable
fun CardBottomSheetContentHeader() {
    OdsTextBody2(
        modifier = Modifier.padding(dimensionResource(id = R.dimen.ods_spacing_s)),
        text = stringResource(id = R.string.component_customize)
    )
}