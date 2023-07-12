/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.utilities

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import com.orange.ods.app.R
import com.orange.ods.app.ui.utilities.composable.OnResumeEffect
import com.orange.ods.compose.component.bottomsheet.OdsBottomSheetScaffold
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.theme.OdsTheme
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentCustomizationBottomSheetScaffold(
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(hostState = it) },
    titleResId: Int = R.string.component_customize,
    floatingActionButton: (@Composable () -> Unit)? = null,
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    bottomSheetContent: @Composable () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetHeaderStateDescription = when (bottomSheetScaffoldState.bottomSheetState.currentValue) {
        BottomSheetValue.Collapsed -> stringResource(R.string.component_state_collapsed)
        BottomSheetValue.Expanded -> stringResource(R.string.component_state_expanded)
    }
    BackHandler(bottomSheetScaffoldState.bottomSheetState.isExpanded) {
        coroutineScope.launch {
            bottomSheetScaffoldState.bottomSheetState.collapse()
        }
    }
    OdsBottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        snackbarHost = snackbarHost,
        sheetPeekHeight = 56.dp,
        sheetContent = {
            OdsListItem(
                modifier = Modifier
                    .clickable {
                        coroutineScope.launch {
                            if (bottomSheetScaffoldState.bottomSheetState.isExpanded) {
                                bottomSheetScaffoldState.bottomSheetState.collapse()
                            } else {
                                bottomSheetScaffoldState.bottomSheetState.expand()
                            }
                        }
                    }
                    .semantics {
                        stateDescription = bottomSheetHeaderStateDescription
                    },
                text = stringResource(id = titleResId),
                icon = {
                    val degrees = if (bottomSheetScaffoldState.bottomSheetState.isExpanded) 0f else -180f
                    val angle by animateFloatAsState(targetValue = degrees)
                    Icon(
                        modifier = Modifier.rotate(angle),
                        painter = painterResource(id = R.drawable.ic_chevron_down),
                        contentDescription = null,
                        tint = OdsTheme.colors.onSurface
                    )
                })

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

@OptIn(ExperimentalMaterialApi::class)
private fun tryExpandBottomSheet(coroutineScope: CoroutineScope, bottomSheetState: BottomSheetState, retryCount: Int = 0) {
    coroutineScope.launch {
        try {
            bottomSheetState.expand()
        } catch (exception: CancellationException) {
            // Retry up to 3 times if animation was interrupted by a composition
            if (retryCount < 3) {
                tryExpandBottomSheet(coroutineScope, bottomSheetState, retryCount + 1)
            }
        }
    }
}
