/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
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
 * @param sheetContent Content of the bottom sheet.
 * @param modifier [Modifier] applied to this bottom sheet scaffold.
 * @param scaffoldState State of the scaffold.
 * @param topBar Top app bar displayed in the scaffold.
 * @param snackbarHost Composable hosting the snackbars shown inside the scaffold.
 * @param floatingActionButton Floating action button displayed in the scaffold.
 * @param floatingActionButtonPosition Position of the floating action button.
 * @param sheetGesturesEnabled Whether the bottom sheet can be interacted with by gestures.
 * @param sheetPeekHeight Height of the bottom sheet when it is collapsed.
 * @param content Content of the screen.
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
