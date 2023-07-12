/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.appbars.top

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.rememberBottomSheetScaffoldState
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
import com.orange.ods.app.R
import com.orange.ods.app.ui.LocalMainTopAppBarManager
import com.orange.ods.app.ui.MainTopAppBarState
import com.orange.ods.app.ui.TopAppBarConfiguration
import com.orange.ods.app.ui.components.Variant
import com.orange.ods.app.ui.components.utilities.ComponentCountRow
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.utilities.NavigationItem
import com.orange.ods.app.ui.utilities.composable.*
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.chip.OdsChoiceChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing
import com.orange.ods.compose.text.OdsTextBody2
import com.orange.ods.compose.text.OdsTextCaption
import com.orange.ods.compose.theme.OdsTheme
import kotlin.math.max

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentTopAppBar(variant: Variant) {
    val customizationState = rememberTopAppBarCustomizationState()
    val isLargeVariant = variant == Variant.AppBarsTopLarge

    with(customizationState) {
        val customActionCount = max(0, actionCount.value - MainTopAppBarState.DefaultConfiguration.actions.size)
        val customActions = NavigationItem.values()
            .take(customActionCount)
            .map { TopAppBarConfiguration.Action.Custom(stringResource(id = it.textResId), it.iconResId) }
        val topAppBarConfiguration = TopAppBarConfiguration.Builder()
            .large(isLargeVariant)
            .scrollBehavior(scrollBehavior.value)
            .navigationIconEnabled(isNavigationIconEnabled)
            .actions {
                addAll(MainTopAppBarState.DefaultConfiguration.actions.take(actionCount.value))
                addAll(customActions)
                if (isOverflowMenuEnabled) add(TopAppBarConfiguration.Action.OverflowMenu)
            }
            .build()

        with(LocalMainTopAppBarManager.current) {
            updateTopAppBar(topAppBarConfiguration)
            if (isLargeVariant) updateTopAppBarTitle(title.value.titleResId)
        }

        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = { CustomizationBottomSheetContent(customizationState = customizationState, isLargeVariant = isLargeVariant) }
        ) {
            val context = LocalContext.current
            Column(
                modifier = Modifier
                    .padding(vertical = dimensionResource(id = R.dimen.screen_vertical_margin))
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (isLargeVariant && isCollapsible) {
                    OdsTextBody2(text = stringResource(id = R.string.component_app_bars_top_large_scrolling_upward))
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
                            name = if (isLargeVariant) OdsComposable.OdsLargeTopAppBar.name else OdsComposable.OdsTopAppBar.name,
                            exhaustiveParameters = false,
                            parameters = {
                                title(context.getString(R.string.component_app_bars_top_regular))

                                if (isNavigationIconEnabled) {
                                    composable(name = "navigationIcon") {
                                        FunctionCallCode(
                                            name = "Icon",
                                            parameters = {
                                                simple("imageVector", "<image vector>")
                                                contentDescription(context.getString(R.string.top_app_bar_back_icon_desc))
                                            }
                                        )
                                    }
                                }

                                composable(name = "actions") {
                                    repeat(actionCount.value) {
                                        FunctionCallCode(
                                            name = OdsComposable.OdsTopAppBarActionButton.name,
                                            parameters = {
                                                onClick()
                                                painter()
                                                contentDescription("icon description")
                                            }
                                        )
                                    }
                                    if (isOverflowMenuEnabled) {
                                        FunctionCallCode(
                                            name = OdsComposable.OdsTopAppBarOverflowMenuBox.name,
                                            parameters = { string("overflowIconContentDescription", "Open overflow menu") }
                                        ) {
                                            for (i in 1..2) {
                                                FunctionCallCode(
                                                    name = OdsComposable.OdsDropdownMenuItem.name,
                                                    parameters = {
                                                        text("Menu $i")
                                                        onClick()
                                                    }
                                                )
                                            }
                                        }
                                    }
                                }

                                simple("scrollBehavior", "<scrollBehavior>")
                            }
                        )
                    }
                    if (isLargeVariant && isCollapsible) {
                        OdsTextBody2(
                            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_s), bottom = dimensionResource(id = R.dimen.spacing_xs)),
                            text = stringResource(id = R.string.component_app_bars_top_large_code_collapsing)
                        )
                        OdsTextCaption(
                            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.spacing_xs)),
                            text = stringResource(id = R.string.component_app_bars_top_large_code_collapsing_step_1)
                        )
                        CodeBackgroundColumn {
                            TechnicalText(text = "val topBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())")
                        }
                        OdsTextCaption(
                            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_s), bottom = dimensionResource(id = R.dimen.spacing_xs)),
                            text = stringResource(id = R.string.component_app_bars_top_large_code_collapsing_step_2)
                        )
                        CodeBackgroundColumn {
                            TechnicalText(text = "val nestedScrollConnection = remember { topBarScrollBehavior.nestedScrollConnection }")
                            TechnicalText(text = "Scaffold(modifier = Modifier.nestedScroll(nestedScrollConnection), ...) { ... }")
                        }
                    }
                }

                if (isLargeVariant && isCollapsible) {
                    BlinkingChevronDown(modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.spacing_s)))
                    OdsTextBody2(text = stringResource(id = R.string.component_app_bars_top_large_scrolling_downward))
                }
            }
        }
    }
}

@Composable
private fun CustomizationBottomSheetContent(customizationState: TopAppBarCustomizationState, isLargeVariant: Boolean) {
    with(customizationState) {
        Subtitle(textRes = R.string.component_app_bars_top_large_scroll_behavior, horizontalPadding = true)
        OdsChoiceChipsFlowRow(
            selectedChip = scrollBehavior,
            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.spacing_m)),
            outlinedChips = true
        ) {
            OdsChoiceChip(textRes = R.string.component_app_bars_top_large_scroll_behavior_none, value = TopAppBarCustomizationState.ScrollBehavior.None)
            OdsChoiceChip(
                textRes = R.string.component_app_bars_top_large_scroll_behavior_collapsible,
                value = TopAppBarCustomizationState.ScrollBehavior.Collapsible
            )
        }
        OdsListItem(
            text = stringResource(id = R.string.component_app_bars_top_element_navigation_icon),
            trailing = OdsSwitchTrailing(
                checked = navigationIconEnabled
            )
        )
        ComponentCountRow(
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.screen_horizontal_margin)),
            title = stringResource(id = R.string.component_app_bars_top_actions_count),
            count = actionCount,
            minusIconContentDescription = stringResource(id = R.string.component_app_bars_top_remove_action),
            plusIconContentDescription = stringResource(id = R.string.component_app_bars_top_add_action),
            minCount = minActionCount,
            maxCount = maxActionCountSelectable
        )
        OdsListItem(
            text = stringResource(id = R.string.component_app_bars_top_element_overflow_menu),
            trailing = OdsSwitchTrailing(
                checked = overflowMenuEnabled,
                enabled = isOverflowMenuSwitchEnabled
            )
        )
        if (isLargeVariant) {
            Subtitle(textRes = R.string.component_element_title, horizontalPadding = true)
            OdsChoiceChipsFlowRow(
                selectedChip = title,
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.spacing_m))
                    .padding(bottom = dimensionResource(id = R.dimen.spacing_s)),
                outlinedChips = true
            ) {
                OdsChoiceChip(textRes = R.string.component_app_bars_top_large_title_one_line, value = TopAppBarCustomizationState.Title.Short)
                OdsChoiceChip(textRes = R.string.component_app_bars_top_large_title_two_lines, value = TopAppBarCustomizationState.Title.TwoLines)
                OdsChoiceChip(
                    textRes = R.string.component_app_bars_top_large_title_truncated,
                    value = TopAppBarCustomizationState.Title.Long
                )
            }
        }
    }
}

@Composable
private fun BlinkingChevronDown(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition()

    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        )
    )

    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        )
    )

    Icon(
        modifier = modifier
            .size(30.dp)
            .scale(scale)
            .alpha(alpha),
        painter = painterResource(id = R.drawable.ic_chevron_down),
        contentDescription = null,
        tint = OdsTheme.colors.onSurface
    )
}