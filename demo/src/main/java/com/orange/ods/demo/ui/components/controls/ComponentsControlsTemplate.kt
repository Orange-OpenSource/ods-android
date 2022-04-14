/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.controls

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.utilities.ComponentDescription

@Composable
fun ComponentsControlsTemplate(@StringRes descriptionRes: Int, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.ods_spacing_s), vertical = dimensionResource(id = R.dimen.ods_spacing_xs))
            .verticalScroll(rememberScrollState())
    ) {
        ComponentDescription(description = descriptionRes)
        content.invoke()
    }
}