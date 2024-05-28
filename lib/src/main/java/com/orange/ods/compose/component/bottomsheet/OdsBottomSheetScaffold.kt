/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ods.compose.component.bottomsheet

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberBottomSheetScaffoldState
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
 * @param sheetPeekHeight Height of the bottom sheet when it is collapsed.
 * @param sheetSwipeEnabled Whether the sheet swiping is enabled and should react to the user's input.
 * @param topBar Top app bar displayed in the scaffold.
 * @param snackbarHost Composable hosting the snackbars shown inside the scaffold.
 * @param content Content of the screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@OdsComposable
fun OdsBottomSheetScaffold(
    sheetContent: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier,
    scaffoldState: BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    sheetPeekHeight: Dp = BottomSheetDefaults.SheetPeekHeight,
    sheetSwipeEnabled: Boolean = true,
    topBar: (@Composable () -> Unit)? = null,
    snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    content: @Composable (PaddingValues) -> Unit
) {
    //TODO Take a look at the new API to manage all available parameters
    BottomSheetScaffold(
        sheetContent = sheetContent,
        modifier = modifier,
        scaffoldState = scaffoldState,
        sheetPeekHeight = sheetPeekHeight,
        sheetContainerColor = OdsTheme.colors.surface,
        sheetContentColor = OdsTheme.colors.onSurface,
        sheetDragHandle = null, //TODO Add this parameter in the API and manage the drag display
        sheetSwipeEnabled = sheetSwipeEnabled,
        topBar = topBar,
        snackbarHost = snackbarHost,
        sheetShape = OdsTheme.shapes.large,

        content = content
    )
}
