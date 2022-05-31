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
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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