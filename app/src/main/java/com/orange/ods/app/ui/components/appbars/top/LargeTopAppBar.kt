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

package com.orange.ods.app.ui.components.appbars.top

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.orange.ods.R
import com.orange.ods.app.ui.utilities.code.CodeBackgroundColumn
import com.orange.ods.app.ui.utilities.code.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.code.FunctionCallCode
import com.orange.ods.app.ui.utilities.composable.Subtitle
import com.orange.ods.app.ui.utilities.composable.TechnicalText
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.appbar.top.OdsTopAppBar
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.menu.OdsDropdownMenu
import com.orange.ods.compose.text.OdsText
import com.orange.ods.compose.theme.OdsTheme

@Composable
fun LargeTopAppBarContent() {
    val context = LocalContext.current

    with(LocalTopAppBarCustomizationState.current) {
        val isCollapsible = scrollBehavior.value == TopAppBarCustomizationState.ScrollBehavior.Collapsible

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(vertical = dimensionResource(id = R.dimen.screen_vertical_margin)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isCollapsible) {
                OdsText(
                    text = stringResource(id = com.orange.ods.app.R.string.component_app_bars_top_large_scrolling_upward),
                    style = OdsTheme.typography.bodyMedium
                )
                BlinkingChevronDown(
                    modifier = Modifier
                        .rotate(180f)
                        .padding(vertical = OdsTheme.spacings.small.dp)
                )
            }
            CodeImplementationColumn(
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                contentBackground = false
            ) {
                CodeBackgroundColumn {
                    FunctionCallCode(
                        name = OdsComposable.OdsLargeTopAppBar.name,
                        exhaustiveParameters = false,
                        parameters = {
                            title(context.getString(com.orange.ods.app.R.string.component_app_bars_top_large))

                            if (isNavigationIconEnabled) {
                                classInstance<OdsTopAppBar.NavigationIcon>("navigationIcon") {
                                    imageVector()
                                    contentDescription(context.getString(com.orange.ods.app.R.string.top_app_bar_back_icon_desc))
                                }
                            }

                            list("actions") {
                                repeat(actionCount.intValue) {
                                    classInstance<OdsTopAppBar.ActionButton> {
                                        onClick()
                                        painter()
                                        contentDescription("icon description")
                                    }
                                }
                            }

                            if (isOverflowMenuEnabled) {
                                list("overflowMenuItems") {
                                    for (i in 1..2) {
                                        classInstance<OdsDropdownMenu.Item> {
                                            text("Menu $i")
                                            onClick()
                                        }
                                    }
                                }
                            }

                            if (isCollapsible) {
                                simple("scrollBehavior", "<scrollBehavior>")
                            }
                        }
                    )
                }
                if (isCollapsible) {
                    OdsText(
                        modifier = Modifier.padding(
                            top = OdsTheme.spacings.small.dp,
                            bottom = OdsTheme.spacings.extraSmall.dp
                        ),
                        text = stringResource(id = com.orange.ods.app.R.string.component_app_bars_top_large_code_collapsing),
                        style = OdsTheme.typography.bodyMedium
                    )
                    OdsText(
                        modifier = Modifier.padding(bottom = OdsTheme.spacings.extraSmall.dp),
                        text = stringResource(id = com.orange.ods.app.R.string.component_app_bars_top_large_code_collapsing_step_1),
                        style = OdsTheme.typography.bodySmall
                    )
                    CodeBackgroundColumn {
                        TechnicalText(text = "val topBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())")
                    }
                    OdsText(
                        modifier = Modifier.padding(
                            top = OdsTheme.spacings.small.dp,
                            bottom = OdsTheme.spacings.extraSmall.dp
                        ),
                        text = stringResource(id = com.orange.ods.app.R.string.component_app_bars_top_large_code_collapsing_step_2),
                        style = OdsTheme.typography.bodySmall
                    )
                    CodeBackgroundColumn {
                        TechnicalText(text = "val nestedScrollConnection = remember { topBarScrollBehavior.nestedScrollConnection }")
                        TechnicalText(text = "Scaffold(modifier = Modifier.nestedScroll(nestedScrollConnection), ...) { ... }")
                    }
                }
            }

            if (isCollapsible) {
                BlinkingChevronDown(modifier = Modifier.padding(vertical = OdsTheme.spacings.small.dp))
                OdsText(
                    text = stringResource(id = com.orange.ods.app.R.string.component_app_bars_top_large_scrolling_downward),
                    style = OdsTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun LargeTopAppBarBottomSheetContent() {
    with(LocalTopAppBarCustomizationState.current) {
        Subtitle(textRes = com.orange.ods.app.R.string.component_app_bars_top_large_scroll_behavior, horizontalPadding = true)
        OdsChoiceChipsFlowRow(
            modifier = Modifier.padding(horizontal = OdsTheme.spacings.medium.dp),
            selectedChoiceChipIndex = TopAppBarCustomizationState.ScrollBehavior.entries.indexOf(scrollBehavior.value),
            choiceChips = TopAppBarCustomizationState.ScrollBehavior.entries.map { scrollBehavior ->
                val textResId = when (scrollBehavior) {
                    TopAppBarCustomizationState.ScrollBehavior.None -> com.orange.ods.app.R.string.component_app_bars_top_large_scroll_behavior_none
                    TopAppBarCustomizationState.ScrollBehavior.Collapsible -> com.orange.ods.app.R.string.component_app_bars_top_large_scroll_behavior_collapsible
                }
                OdsChoiceChipsFlowRow.ChoiceChip(stringResource(id = textResId), { this.scrollBehavior.value = scrollBehavior })
            }
        )

        RegularTopAppBarBottomSheetContent()

        Subtitle(textRes = com.orange.ods.app.R.string.component_element_title, horizontalPadding = true)
        OdsChoiceChipsFlowRow(
            modifier = Modifier.padding(horizontal = OdsTheme.spacings.medium.dp),
            selectedChoiceChipIndex = TopAppBarCustomizationState.Title.entries.indexOf(title.value),
            choiceChips = TopAppBarCustomizationState.Title.entries.map { title ->
                val textResId = when (title) {
                    TopAppBarCustomizationState.Title.Short -> com.orange.ods.app.R.string.component_app_bars_top_large_title_one_line
                    TopAppBarCustomizationState.Title.TwoLines -> com.orange.ods.app.R.string.component_app_bars_top_large_title_two_lines
                    TopAppBarCustomizationState.Title.Long -> com.orange.ods.app.R.string.component_app_bars_top_large_title_truncated
                }
                OdsChoiceChipsFlowRow.ChoiceChip(stringResource(id = textResId), { this.title.value = title })
            }
        )
    }
}

@Composable
private fun BlinkingChevronDown(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val animationSpec = infiniteRepeatable<Float>(
        animation = tween(1000),
        repeatMode = RepeatMode.Reverse
    )

    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = animationSpec,
        label = ""
    )


    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1f,
        animationSpec = animationSpec,
        label = ""
    )

    Icon(
        modifier = modifier
            .size(30.dp)
            .scale(scale)
            .alpha(alpha),
        painter = painterResource(id = com.orange.ods.app.R.drawable.ic_chevron_down),
        contentDescription = null,
        tint = OdsTheme.colors.onSurface
    )
}