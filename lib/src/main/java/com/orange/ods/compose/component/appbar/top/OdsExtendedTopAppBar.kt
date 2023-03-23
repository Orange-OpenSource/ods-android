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

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.orange.ods.R
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsTheme

private val AppBarTopExtendedMaxHeight = 150.dp
private val AppBarTopExtendedMinHeight = 56.dp
private val TitleStartPadding = 72.dp

private val TitleOffsetMin = 0.dp
private val TitleOffsetMax = AppBarTopExtendedMaxHeight - AppBarTopExtendedMinHeight

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/23e0e6-app-bars/b/620966" class="external" target="_blank">Material ODS Top App Bar</a>.
 *
 * The top app bar displays information and actions relating to the current screen.
 * Extended top app bars can be used for longer titles or to provide a stronger presence to the top app bar.
 *
 * @param modifier The [Modifier] to be applied to the OdsExtendedTopAppBar
 * @param title The title to be displayed in the OdsExtendedTopAppBar
 * @param navigationIcon Optional navigation icon displayed at the start of the OdsExtendedTopAppBar. This should be an [Icon].
 * @param onNavigationIconClick Optional action executed on the navigation icon click.
 * @param actions The actions displayed at the end of the OdsExtendedTopAppBar. This should be [OdsTopAppBarActionButton]s.
 * The default layout here is a [Row], so icons inside will be placed horizontally.
 */
@Composable
fun OdsExtendedTopAppBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    navigationIcon: @Composable (() -> Unit)? = null,
    onNavigationIconClick: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    screenContent: @Composable () -> Unit
) {
    val scrollState = rememberScrollState(0)
    Box(Modifier.fillMaxSize()) {
        ScreenContent(scrollState, screenContent)
        AppBarSurface { scrollState.value }
        OdsTopAppBar(
            modifier = modifier,
            navigationIcon = navigationIcon,
            onNavigationIconClick = onNavigationIconClick,
            actions = actions,
            elevated = false
        )
        title?.let {
            AppBarTitle(title) { scrollState.value }
        }
    }
}


@Composable
private fun AppBarSurface(scrollProvider: () -> Int) {
    val surfaceOffsetMin = with(LocalDensity.current) { (AppBarTopExtendedMinHeight - AppBarTopExtendedMaxHeight).toPx() }
    val surfaceOffsetMax = 0f

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(bottom = dimensionResource(id = R.dimen.spacing_m))
            .offset {
                val scroll = scrollProvider()
                val offset = (surfaceOffsetMax - scroll).coerceAtLeast(surfaceOffsetMin)
                IntOffset(x = 0, y = offset.toInt())
            }
    ) {
        Surface(
            modifier = Modifier
                .height(AppBarTopExtendedMaxHeight)
                .fillMaxWidth()
                .background(OdsTheme.colors.component.topAppBar.barBackground),
            elevation = if (isSystemInDarkTheme()) 0.dp else AppBarDefaults.TopAppBarElevation
        ) {}
    }
}

@Composable
private fun AppBarTitle(title: String, scrollProvider: () -> Int) {
    val titleOffsetMin = with(LocalDensity.current) { TitleOffsetMin.toPx() }
    val titleOffsetMax = with(LocalDensity.current) { TitleOffsetMax.toPx() }

    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .heightIn(min = AppBarTopExtendedMinHeight)
            .statusBarsPadding()
            .fillMaxWidth()
            .offset {
                val scroll = scrollProvider()
                val offset = (titleOffsetMax - scroll).coerceAtLeast(titleOffsetMin)
                IntOffset(x = 0, y = offset.toInt())
            }
    ) {
        Text(
            text = title,
            style = OdsTheme.typography.h6,
            color = OdsTheme.colors.component.topAppBar.barContent,
            modifier = Modifier
                .padding(
                    start = TitleStartPadding,
                    end = dimensionResource(id = R.dimen.screen_horizontal_margin),
                    bottom = dimensionResource(id = R.dimen.spacing_m)
                )
        )
    }
}

@Composable
private fun ScreenContent(scrollState: ScrollState, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(top = AppBarTopExtendedMaxHeight + dimensionResource(id = R.dimen.spacing_m))
    ) {
        content()
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsExtendedTopAppBar() = Preview {

}
