/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.bottomsheet

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldDefaults
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.theme.OdsTheme

@Composable
@ExperimentalMaterialApi
@OdsComponentApi
fun OdsBottomSheetScaffold(
    sheetContent: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier,
    scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    topBar: (@Composable () -> Unit)? = null,
    snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    floatingActionButton: (@Composable () -> Unit)? = null,
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    sheetGesturesEnabled: Boolean = true,
    sheetPeekHeight: Dp = BottomSheetScaffoldDefaults.SheetPeekHeight,
    content: @Composable (PaddingValues) -> Unit

) {
    BottomSheetScaffold(
        sheetContent = sheetContent,
        modifier = modifier,
        scaffoldState = scaffoldState,
        topBar = topBar,
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        sheetGesturesEnabled = sheetGesturesEnabled,
        sheetShape = OdsTheme.shapes.large,
        sheetBackgroundColor = OdsTheme.colors.surface,
        sheetContentColor = OdsTheme.colors.onSurface,
        sheetPeekHeight = sheetPeekHeight,
        backgroundColor = OdsTheme.colors.surface,
        contentColor = OdsTheme.colors.onSurface,
        content = content
    )
}