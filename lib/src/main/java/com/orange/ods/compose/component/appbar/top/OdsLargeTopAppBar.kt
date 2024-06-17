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

package com.orange.ods.compose.component.appbar.top

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.menu.OdsDropdownMenu
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.OdsPreview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.text.OdsText
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.theme.colors.fromToken
import kotlinx.coroutines.delay

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/23e0e6-app-bars/b/620966" class="external" target="_blank">ODS Top App Bar</a>.
 *
 * The top app bar displays information and actions relating to the current screen.
 *
 * @param title Title displayed in the center of the top app bar.
 * @param modifier [Modifier] applied to the top app bar.
 * @param navigationIcon Icon displayed at the start of the top app bar.
 * @param actions Actions displayed at the end of the top app bar. The default layout here is a [androidx.compose.foundation.layout.Row], so icons inside will be placed horizontally.
 * @param overflowMenuItems List of items displayed in the overflow menu. The top app bar uses `OdsDropdownMenu` to display its overflow menu.
 * @param scrollBehavior [TopAppBarScrollBehavior] attached to the top app bar.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@OdsComposable
fun OdsLargeTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: OdsTopAppBar.NavigationIcon? = null,
    actions: List<OdsTopAppBar.ActionButton> = emptyList(),
    overflowMenuItems: List<OdsDropdownMenu.Item> = emptyList(),
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    val expandedTitleStartPadding = 48.dp
    val collapsedTitleStartPadding = 8.dp
    val expandedTitleAlpha = 1f
    val expandedTitleMaxLines = 2
    val collapsedTitleMaxLines = 1
    val stateChangeFraction = 0.7

    val titleStartPadding by remember(scrollBehavior) {
        derivedStateOf {
            if (scrollBehavior != null && scrollBehavior.state.collapsedFraction >= 0.85) collapsedTitleStartPadding else expandedTitleStartPadding
        }
    }

    val titleAlpha by remember(scrollBehavior) {
        derivedStateOf {
            if (scrollBehavior != null) {
                when (scrollBehavior.state.collapsedFraction) {
                    in 0.0..stateChangeFraction -> 1 - (scrollBehavior.state.collapsedFraction * (1f / stateChangeFraction))
                    in (stateChangeFraction + 0.15)..1.0 -> 0 + scrollBehavior.state.collapsedFraction
                    else -> 0
                }.toFloat()
            } else {
                expandedTitleAlpha
            }
        }
    }
    val titleMaxLines by remember(scrollBehavior) {
        derivedStateOf {
            if (scrollBehavior != null && scrollBehavior.state.collapsedFraction >= stateChangeFraction) collapsedTitleMaxLines else expandedTitleMaxLines
        }
    }

    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(title) {
        delay(100L)
        focusRequester.requestFocus()
    }

    LargeTopAppBar(
        title = {
            OdsText(
                modifier = Modifier
                    .padding(
                        start = titleStartPadding,
                        end = OdsTheme.spacings.medium.dp
                    )
                    .alpha(titleAlpha)
                    .semantics { traversalIndex = -1f }
                    .focusRequester(focusRequester)
                    .focusable(),
                text = title,
                style = OdsTheme.typography.titleLarge,
                overflow = TextOverflow.Ellipsis,
                maxLines = titleMaxLines,
            )
        },
        modifier = modifier.semantics { isTraversalGroup = true },
        navigationIcon = { navigationIcon?.Content() },
        actions = { OdsTopAppBarActions(actions = actions, overflowMenuItems = overflowMenuItems) },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = OdsTheme.colors.fromToken(OdsTheme.componentsTokens.topAppBar.containerColor),
            navigationIconContentColor = OdsTheme.colors.fromToken(OdsTheme.componentsTokens.topAppBar.leadingIconColor),
            titleContentColor = OdsTheme.colors.fromToken(OdsTheme.componentsTokens.topAppBar.headlineColor),
            actionIconContentColor = OdsTheme.colors.fromToken(OdsTheme.componentsTokens.topAppBar.trailingIconColor)
        ),
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@UiModePreviews.Default
@Composable
private fun PreviewOdsLargeTopAppBar(@PreviewParameter(OdsLargeTopAppBarPreviewParameterProvider::class) parameter: OdsLargeTopAppBarPreviewParameter) =
    OdsPreview {
        val actions = listOf(OdsTopAppBar.ActionButton(painterResource(id = android.R.drawable.ic_dialog_info), "Info") {})
        val overflowMenuItems = listOf(
            OdsDropdownMenu.Item("Settings") { },
            OdsDropdownMenu.Item("Account") { }
        )
        OdsLargeTopAppBar(
            title = parameter.title,
            navigationIcon = parameter.navigationIcon,
            actions = actions,
            overflowMenuItems = overflowMenuItems
        )
    }

internal data class OdsLargeTopAppBarPreviewParameter(
    val title: String,
    val navigationIcon: OdsTopAppBar.NavigationIcon?
)

private class OdsLargeTopAppBarPreviewParameterProvider :
    BasicPreviewParameterProvider<OdsLargeTopAppBarPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OdsLargeTopAppBarPreviewParameter>
    get() {
        val navigationIcon = OdsTopAppBar.NavigationIcon(Icons.AutoMirrored.Filled.ArrowBack, "") {}
        return listOf(
            OdsLargeTopAppBarPreviewParameter("One line title", navigationIcon),
            OdsLargeTopAppBarPreviewParameter("Two lines title is allowed in large top app bar", navigationIcon),
            OdsLargeTopAppBarPreviewParameter("The title will be truncated if it is too long to fit in the large top app bar like this one", navigationIcon),
            OdsLargeTopAppBarPreviewParameter("One line title", null)
        )
    }