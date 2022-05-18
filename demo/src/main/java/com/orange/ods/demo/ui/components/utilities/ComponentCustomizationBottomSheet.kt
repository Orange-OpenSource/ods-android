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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.text.OdsTextSubtitle1
import com.orange.ods.demo.R
import kotlinx.coroutines.launch

@Composable
@ExperimentalMaterialApi
fun ComponentCustomizationBottomSheetScaffold(bottomSheetContent: @Composable () -> Unit, content: @Composable BoxScope.() -> Unit) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 56.dp,
        sheetContent = {
            ComponentCustomizationBottomSheetHeader(onClick = {
                coroutineScope.launch {
                    if (bottomSheetScaffoldState.bottomSheetState.isExpanded) {
                        bottomSheetScaffoldState.bottomSheetState.collapse()
                    } else {
                        bottomSheetScaffoldState.bottomSheetState.expand()
                    }
                }
            })
            bottomSheetContent()
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding), content = content)
    }
}

@Composable
private fun ComponentCustomizationBottomSheetHeader(onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clickable(onClick = onClick)
    ) {
        OdsTextSubtitle1(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.ods_spacing_s)),
            text = stringResource(id = R.string.component_customize)
        )
    }
}