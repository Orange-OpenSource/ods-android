/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.utilities

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.google.accompanist.flowlayout.FlowRow
import com.orange.ods.compose.component.chip.OdsChip
import com.orange.ods.demo.R

@Composable
fun <T> ComponentChipRow(selectedChip: MutableState<T>, content: @Composable ComponentChipRowScope<T>.() -> Unit) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .selectableGroup()
            .padding(horizontal = dimensionResource(id = R.dimen.ods_screen_horizontal_margin)),
        mainAxisSpacing = dimensionResource(id = R.dimen.spacing_s),
        content = { ComponentChipRowScope(selectedChip).content() }
    )
}

@ExperimentalMaterialApi
@Composable
fun <T> ComponentChipRowScope<T>.ComponentChip(@StringRes textRes: Int, value: T) {
    OdsChip(
        text = stringResource(id = textRes),
        selected = selectedChip.value == value,
        onClick = { selectedChip.value = value }
    )
}

data class ComponentChipRowScope<T>(val selectedChip: MutableState<T>)
