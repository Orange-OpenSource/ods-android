/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.textfields

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.SubComponent

@ExperimentalMaterialApi
@Composable
fun SubComponentTextField(subComponent: SubComponent) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = dimensionResource(id = R.dimen.ods_screen_horizontal_margin), vertical = dimensionResource(id = R.dimen.ods_screen_vertical_margin))
    ) {
        SubComponentTextFieldContent(subComponent)
    }
}

@ExperimentalMaterialApi
@Composable
private fun SubComponentTextFieldContent(subComponent: SubComponent) {
    when (subComponent) {
        SubComponent.TextFieldsFilled -> TextFieldFilledContent()
        SubComponent.TextFieldsOutline -> TextFieldOutlinedContent()
        else -> {}
    }
}