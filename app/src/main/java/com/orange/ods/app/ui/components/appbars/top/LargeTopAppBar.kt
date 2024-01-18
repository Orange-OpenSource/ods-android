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
import com.orange.ods.compose.component.chip.OdsChoiceChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.menu.OdsDropdownMenu
import com.orange.ods.compose.text.OdsTextBodyM
import com.orange.ods.compose.text.OdsTextBodyS
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
                OdsTextBodyM(text = stringResource(id = com.orange.ods.app.R.string.component_app_bars_top_large_scrolling_upward))
                BlinkingChevronDown(
                    modifier = Modifier
                        .rotate(180f)
                        .padding(vertical = dimensionResource(id = R.dimen.spacing_s))
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
                    OdsTextBodyM(
                        modifier = Modifier.padding(
                            top = dimensionResource(id = R.dimen.spacing_s),
                            bottom = dimensionResource(id = R.dimen.spacing_xs)
                        ),
                        text = stringResource(id = com.orange.ods.app.R.string.component_app_bars_top_large_code_collapsing)
                    )
                    OdsTextBodyS(
                        modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.spacing_xs)),
                        text = stringResource(id = com.orange.ods.app.R.string.component_app_bars_top_large_code_collapsing_step_1)
                    )
                    CodeBackgroundColumn {
                        TechnicalText(text = "val topBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())")
                    }
                    OdsTextBodyS(
                        modifier = Modifier.padding(
                            top = dimensionResource(id = R.dimen.spacing_s),
                            bottom = dimensionResource(id = R.dimen.spacing_xs)
                        ),
                        text = stringResource(id = com.orange.ods.app.R.string.component_app_bars_top_large_code_collapsing_step_2)
                    )
                    CodeBackgroundColumn {
                        TechnicalText(text = "val nestedScrollConnection = remember { topBarScrollBehavior.nestedScrollConnection }")
                        TechnicalText(text = "Scaffold(modifier = Modifier.nestedScroll(nestedScrollConnection), ...) { ... }")
                    }
                }
            }

            if (isCollapsible) {
                BlinkingChevronDown(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.spacing_s)))
                OdsTextBodyM(text = stringResource(id = com.orange.ods.app.R.string.component_app_bars_top_large_scrolling_downward))
            }
        }
    }
}

@Composable
fun LargeTopAppBarBottomSheetContent() {
    with(LocalTopAppBarCustomizationState.current) {
        Subtitle(textRes = com.orange.ods.app.R.string.component_app_bars_top_large_scroll_behavior, horizontalPadding = true)
        OdsChoiceChipsFlowRow(
            value = scrollBehavior.value,
            onValueChange = { value -> scrollBehavior.value = value },
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.spacing_m)),
            chips = listOf(
                OdsChoiceChip(
                    text = stringResource(id = com.orange.ods.app.R.string.component_app_bars_top_large_scroll_behavior_none),
                    value = TopAppBarCustomizationState.ScrollBehavior.None
                ),
                OdsChoiceChip(
                    text = stringResource(com.orange.ods.app.R.string.component_app_bars_top_large_scroll_behavior_collapsible),
                    value = TopAppBarCustomizationState.ScrollBehavior.Collapsible
                )
            )
        )

        RegularTopAppBarBottomSheetContent()

        Subtitle(textRes = com.orange.ods.app.R.string.component_element_title, horizontalPadding = true)
        OdsChoiceChipsFlowRow(
            value = title.value,
            onValueChange = { value -> title.value = value },
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.spacing_m)),
            chips = listOf(
                OdsChoiceChip(
                    text = stringResource(id = com.orange.ods.app.R.string.component_app_bars_top_large_title_one_line),
                    value = TopAppBarCustomizationState.Title.Short
                ),
                OdsChoiceChip(
                    text = stringResource(id = com.orange.ods.app.R.string.component_app_bars_top_large_title_two_lines),
                    value = TopAppBarCustomizationState.Title.TwoLines
                ),
                OdsChoiceChip(
                    text = stringResource(id = com.orange.ods.app.R.string.component_app_bars_top_large_title_truncated),
                    value = TopAppBarCustomizationState.Title.Long
                )
            )
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