/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.appbar.top

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.theme.OdsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@OdsComposable
fun OdsLargeTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    onNavigationIconClick: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior? = null
) {
    val contentColor = OdsTheme.colors.component.topAppBar.barContent
    val expandedTitleStartPadding = 48.dp
    val collapsedTitleStartPadding = 8.dp
    val expandedTitleAlpha = 1f
    val expandedTitleMaxLines = 2
    val collapsedTitleMaxLines = 1
    val stateChangeFraction = 0.7

    val titleStartPadding by remember {
        derivedStateOf {
            if (scrollBehavior != null && scrollBehavior.state.collapsedFraction >= 0.85) collapsedTitleStartPadding else expandedTitleStartPadding
        }
    }

    val titleAlpha by remember {
        derivedStateOf {
            if (scrollBehavior != null) {
                when (scrollBehavior.state.collapsedFraction) {
                    in 0.0..stateChangeFraction -> 1 - (scrollBehavior.state.collapsedFraction * 1.5)
                    in (stateChangeFraction + 0.15)..1.0 -> 0 + scrollBehavior.state.collapsedFraction
                    else -> 0
                }.toFloat()
            } else {
                expandedTitleAlpha
            }
        }
    }
    val titleMaxLines by remember {
        derivedStateOf {
            if (scrollBehavior != null && scrollBehavior.state.collapsedFraction >= stateChangeFraction) collapsedTitleMaxLines else expandedTitleMaxLines
        }
    }

    LargeTopAppBar(
        title = {
            Text(
                modifier = Modifier
                    .padding(
                        start = if (scrollBehavior != null) titleStartPadding else expandedTitleStartPadding,
                        end = dimensionResource(id = R.dimen.spacing_m)
                    )
                    .alpha(if (scrollBehavior != null) titleAlpha else expandedTitleAlpha),
                text = title,
                style = OdsTheme.typography.h6,
                overflow = TextOverflow.Ellipsis,
                maxLines = if (scrollBehavior != null) titleMaxLines else expandedTitleMaxLines,
            )
        },
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                navigationIcon()
            }
        },
        actions = actions,
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = OdsTheme.colors.component.topAppBar.barBackground,
            navigationIconContentColor = contentColor,
            titleContentColor = contentColor,
            actionIconContentColor = contentColor
        ),
        scrollBehavior = scrollBehavior
    )
}