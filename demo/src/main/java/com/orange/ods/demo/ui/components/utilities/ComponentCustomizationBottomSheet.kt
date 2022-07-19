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

import androidx.activity.compose.BackHandler
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.orange.ods.compose.component.chip.OdsChip
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.demo.R
import kotlinx.coroutines.launch

@Composable
@ExperimentalMaterialApi
fun ComponentCustomizationBottomSheetScaffold(
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    bottomSheetContent: @Composable () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetHeaderIconRes = when (bottomSheetScaffoldState.bottomSheetState.currentValue) {
        BottomSheetValue.Collapsed -> R.drawable.ic_chevron_up
        BottomSheetValue.Expanded -> R.drawable.ic_chevron_down
    }
    BackHandler(bottomSheetScaffoldState.bottomSheetState.isExpanded) {
        coroutineScope.launch {
            bottomSheetScaffoldState.bottomSheetState.collapse()
        }
    }
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 56.dp,
        sheetContent = {
            OdsListItem(
                modifier = Modifier.clickable {
                    coroutineScope.launch {
                        if (bottomSheetScaffoldState.bottomSheetState.isExpanded) {
                            bottomSheetScaffoldState.bottomSheetState.collapse()
                        } else {
                            bottomSheetScaffoldState.bottomSheetState.expand()
                        }
                    }
                },
                text = stringResource(id = R.string.component_customize),
                icon = { Icon(painter = painterResource(id = bottomSheetHeaderIconRes), contentDescription = null) })

            bottomSheetContent()
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding), content = content)
    }
}

@Composable
fun <T> ComponentCustomizationChipRow(selectedChip: MutableState<T>, content: @Composable ChipRowScope<T>.() -> Unit) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .selectableGroup()
            .padding(horizontal = dimensionResource(id = R.dimen.ods_screen_horizontal_margin)),
        mainAxisSpacing = dimensionResource(id = R.dimen.spacing_s),
        content = { ChipRowScope(selectedChip).content() }
    )
}

@ExperimentalMaterialApi
@Composable
fun <T> ChipRowScope<T>.ComponentCustomizationChip(@StringRes textRes: Int, value: T) {
    OdsChip(text = stringResource(id = textRes), selected = selectedChip.value == value, onClick = { selectedChip.value = value })
}

data class ChipRowScope<T>(val selectedChip: MutableState<T>)
