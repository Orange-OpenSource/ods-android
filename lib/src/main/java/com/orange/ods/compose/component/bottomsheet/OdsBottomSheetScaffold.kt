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
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.theme.OdsTheme


/**
 * <a href="https://system.design.orange.com/0c1af118d/p/81f927-sheets-bottom/b/27195f" target="_blank">ODS Sheets bottom</a>.
 *
 * Bottom Sheets are surfaces anchored to the bottom of the screen that present users supplement content.
 *
 * @param sheetContent The content of the bottom sheet.
 * @param modifier optional [Modifier] for this OdsBottomSheetScaffold.
 * @param scaffoldState The state of the scaffold.
 * @param topBar An optional top app bar.
 * @param snackbarHost The composable hosting the snackbars shown inside the scaffold.
 * @param floatingActionButton An optional floating action button.
 * @param floatingActionButtonPosition The position of the floating action button.
 * @param sheetGesturesEnabled Whether the bottom sheet can be interacted with by gestures.
 * @param sheetPeekHeight The height of the bottom sheet when it is collapsed.
 * @param content destinations inside this OdsBottomSheetScaffold.
 */
@Composable
@ExperimentalMaterialApi
@OdsComposable
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
        sheetPeekHeight = sheetPeekHeight,
        backgroundColor = OdsTheme.colors.surface,
        contentColor = OdsTheme.colors.onSurface,
        content = content
    )
}