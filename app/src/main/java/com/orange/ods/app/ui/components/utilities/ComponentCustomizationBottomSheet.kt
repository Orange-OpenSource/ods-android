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

package com.orange.ods.app.ui.components.utilities

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import com.orange.ods.app.R
import com.orange.ods.app.ui.utilities.composable.OnResumeEffect
import com.orange.ods.compose.component.bottomsheet.OdsBottomSheetScaffold
import com.orange.ods.compose.text.OdsText
import com.orange.ods.compose.theme.OdsTheme
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentCustomizationBottomSheetScaffold(
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(hostState = it) },
    titleResId: Int = R.string.component_customize,
    bottomSheetContent: @Composable () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetHeaderStateDescription = when (bottomSheetScaffoldState.bottomSheetState.currentValue) {
        SheetValue.Hidden, SheetValue.PartiallyExpanded -> stringResource(R.string.component_state_bottom_sheet_collapsed)
        SheetValue.Expanded -> stringResource(R.string.component_state_bottom_sheet_expanded)
    }
    BackHandler(bottomSheetScaffoldState.bottomSheetState.currentValue == SheetValue.Expanded) {
        coroutineScope.launch {
            bottomSheetScaffoldState.bottomSheetState.partialExpand()
        }
    }
    OdsBottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        snackbarHost = snackbarHost,
        sheetSwipeEnabled = false,
        sheetContent = {
            Row(
                modifier = Modifier
                    .clickable {
                        coroutineScope.launch {
                            if (bottomSheetScaffoldState.bottomSheetState.currentValue == SheetValue.Expanded) {
                                bottomSheetScaffoldState.bottomSheetState.partialExpand()
                            } else {
                                bottomSheetScaffoldState.bottomSheetState.expand()
                            }
                        }
                    }
                    .semantics {
                        stateDescription = bottomSheetHeaderStateDescription
                    }
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val degrees = if (bottomSheetScaffoldState.bottomSheetState.currentValue == SheetValue.Expanded) 0f else -180f
                val angle by animateFloatAsState(targetValue = degrees, label = "ComponentCustomizationBottomSheetScaffoldIconRotation")
                Icon(
                    modifier = Modifier.rotate(angle),
                    painter = painterResource(id = R.drawable.ic_chevron_down),
                    contentDescription = null,
                    tint = OdsTheme.colors.onSurface
                )
                OdsText(
                    modifier = Modifier.padding(start = OdsTheme.spacings.medium.dp),
                    text = stringResource(id = titleResId),
                    style = OdsTheme.typography.titleMedium
                )
            }

            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                bottomSheetContent()
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(OdsTheme.colors.background),
            content = content
        )
    }

    OnResumeEffect {
        tryExpandBottomSheet(coroutineScope, bottomSheetScaffoldState.bottomSheetState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
private fun tryExpandBottomSheet(coroutineScope: CoroutineScope, sheetState: SheetState, retryCount: Int = 0) {
    coroutineScope.launch {
        try {
            sheetState.expand()
        } catch (exception: CancellationException) {
            // Retry up to 3 times if animation was interrupted by a composition
            if (retryCount < 3) {
                tryExpandBottomSheet(coroutineScope, sheetState, retryCount + 1)
            }
        }
    }
}
