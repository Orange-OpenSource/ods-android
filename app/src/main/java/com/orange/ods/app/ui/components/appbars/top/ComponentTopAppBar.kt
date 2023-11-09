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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.orange.ods.app.ui.AppBarConfiguration
import com.orange.ods.app.ui.LocalAppBarManager
import com.orange.ods.app.ui.components.Variant
import com.orange.ods.app.ui.components.utilities.ComponentCountRow
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.utilities.code.CodeBackgroundColumn
import com.orange.ods.app.ui.utilities.code.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.code.FunctionCallCode
import com.orange.ods.app.ui.utilities.composable.*
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.appbar.top.OdsTopAppBarActionButtonBuilder
import com.orange.ods.compose.component.appbar.top.OdsTopAppBarNavigationIconBuilder
import com.orange.ods.compose.component.appbar.top.OdsTopAppBarOverflowMenuActionItemBuilder
import com.orange.ods.compose.component.chip.OdsChoiceChipBuilder
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsListItemTrailingSwitchBuilder
import com.orange.ods.compose.text.OdsTextBody2
import com.orange.ods.compose.text.OdsTextCaption
import com.orange.ods.compose.theme.OdsTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentTopAppBar(variant: Variant) {
    val customizationState = rememberTopAppBarCustomizationState(large = remember { mutableStateOf(variant == Variant.AppBarsTopLarge) })

    with(customizationState) {
        val appBarConfiguration = AppBarConfiguration(
            isLarge = isLarge,
            largeTitleRes = if (isLarge) title.value.titleRes else R.string.component_app_bars_top_regular,
            scrollBehavior = scrollBehavior.value,
            isNavigationIconEnabled = isNavigationIconEnabled,
            actionCount = actionCount.value,
            isOverflowMenuEnabled = isOverflowMenuEnabled
        )

        LocalAppBarManager.current.setCustomAppBar(appBarConfiguration)

        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = { CustomizationBottomSheetContent(customizationState = customizationState, isLarge = isLarge) }
        ) {
            val context = LocalContext.current
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = dimensionResource(id = com.orange.ods.R.dimen.screen_vertical_margin)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (isLargeCollapsible) {
                    OdsTextBody2(text = stringResource(id = R.string.component_app_bars_top_large_scrolling_upward))
                    BlinkingChevronDown(
                        modifier = Modifier
                            .rotate(180f)
                            .padding(vertical = dimensionResource(id = com.orange.ods.R.dimen.spacing_s))
                    )
                }
                CodeImplementationColumn(
                    modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin)),
                    contentBackground = false
                ) {
                    CodeBackgroundColumn {
                        FunctionCallCode(
                            name = if (isLarge) OdsComposable.OdsLargeTopAppBar.name else OdsComposable.OdsTopAppBar.name,
                            exhaustiveParameters = false,
                            parameters = {
                                title(context.getString(R.string.component_app_bars_top_regular))

                                if (isNavigationIconEnabled) {
                                    classInstance<OdsTopAppBarNavigationIconBuilder>("navigationIcon") {
                                        imageVector()
                                        contentDescription(context.getString(R.string.top_app_bar_back_icon_desc))
                                    }
                                }

                                list("actions") {
                                    repeat(actionCount.value) {
                                        classInstance<OdsTopAppBarActionButtonBuilder> {
                                            onClick()
                                            painter()
                                            contentDescription("icon description")
                                        }
                                    }
                                }

                                if (isOverflowMenuEnabled) {
                                    list("overflowMenuActions") {
                                        for (i in 1..2) {
                                            // The classInstance method displays the original type of type aliases, that's why function is used instead
                                            function(OdsTopAppBarOverflowMenuActionItemBuilder::class.java.simpleName) {
                                                text("Menu $i")
                                                onClick()
                                            }
                                        }
                                    }
                                }

                                if (isLargeCollapsible) {
                                    simple("scrollBehavior", "<scrollBehavior>")
                                }
                            }
                        )
                    }
                    if (isLargeCollapsible) {
                        OdsTextBody2(
                            modifier = Modifier.padding(
                                top = dimensionResource(id = com.orange.ods.R.dimen.spacing_s),
                                bottom = dimensionResource(id = com.orange.ods.R.dimen.spacing_xs)
                            ),
                            text = stringResource(id = R.string.component_app_bars_top_large_code_collapsing)
                        )
                        OdsTextCaption(
                            modifier = Modifier.padding(bottom = dimensionResource(id = com.orange.ods.R.dimen.spacing_xs)),
                            text = stringResource(id = R.string.component_app_bars_top_large_code_collapsing_step_1)
                        )
                        CodeBackgroundColumn {
                            TechnicalText(text = "val topBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())")
                        }
                        OdsTextCaption(
                            modifier = Modifier.padding(
                                top = dimensionResource(id = com.orange.ods.R.dimen.spacing_s),
                                bottom = dimensionResource(id = com.orange.ods.R.dimen.spacing_xs)
                            ),
                            text = stringResource(id = R.string.component_app_bars_top_large_code_collapsing_step_2)
                        )
                        CodeBackgroundColumn {
                            TechnicalText(text = "val nestedScrollConnection = remember { topBarScrollBehavior.nestedScrollConnection }")
                            TechnicalText(text = "Scaffold(modifier = Modifier.nestedScroll(nestedScrollConnection), ...) { ... }")
                        }
                    }
                }

                if (isLargeCollapsible) {
                    BlinkingChevronDown(modifier = Modifier.padding(vertical = dimensionResource(id = com.orange.ods.R.dimen.spacing_s)))
                    OdsTextBody2(text = stringResource(id = R.string.component_app_bars_top_large_scrolling_downward))
                }
            }
        }
    }
}

@Composable
private fun CustomizationBottomSheetContent(customizationState: TopAppBarCustomizationState, isLarge: Boolean) {
    with(customizationState) {
        if (isLarge) {
            Subtitle(textRes = R.string.component_app_bars_top_large_scroll_behavior, horizontalPadding = true)
            OdsChoiceChipsFlowRow(
                value = scrollBehavior.value,
                onValueChange = { value -> scrollBehavior.value = value },
                modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)),
                chips = listOf(
                    OdsChoiceChipBuilder(
                        text = stringResource(id = R.string.component_app_bars_top_large_scroll_behavior_none),
                        value = TopAppBarCustomizationState.ScrollBehavior.None
                    ),
                    OdsChoiceChipBuilder(
                        text = stringResource(R.string.component_app_bars_top_large_scroll_behavior_collapsible),
                        value = TopAppBarCustomizationState.ScrollBehavior.Collapsible
                    )
                )
            )
        }
        OdsListItem(
            text = stringResource(id = R.string.component_app_bars_top_element_navigation_icon),
            trailing = OdsListItemTrailingSwitchBuilder(navigationIconEnabled.value, { navigationIconEnabled.value = it })
        )
        ComponentCountRow(
            modifier = Modifier.padding(start = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin)),
            title = stringResource(id = R.string.component_app_bars_top_actions_count),
            count = actionCount,
            minusIconContentDescription = stringResource(id = R.string.component_app_bars_top_remove_action),
            plusIconContentDescription = stringResource(id = R.string.component_app_bars_top_add_action),
            minCount = minActionCount,
            maxCount = maxActionCountSelectable
        )
        OdsListItem(
            text = stringResource(id = R.string.component_app_bars_top_element_overflow_menu),
            trailing = OdsListItemTrailingSwitchBuilder(overflowMenuEnabled.value, { overflowMenuEnabled.value = it }, isOverflowMenuSwitchEnabled)
        )
        if (isLarge) {
            Subtitle(textRes = R.string.component_element_title, horizontalPadding = true)
            OdsChoiceChipsFlowRow(
                value = title.value,
                onValueChange = { value -> title.value = value },
                modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)),
                chips = listOf(
                    OdsChoiceChipBuilder(
                        text = stringResource(id = R.string.component_app_bars_top_large_title_one_line),
                        value = TopAppBarCustomizationState.Title.Short
                    ),
                    OdsChoiceChipBuilder(
                        text = stringResource(id = R.string.component_app_bars_top_large_title_two_lines),
                        value = TopAppBarCustomizationState.Title.TwoLines
                    ),
                    OdsChoiceChipBuilder(
                        text = stringResource(id = R.string.component_app_bars_top_large_title_truncated),
                        value = TopAppBarCustomizationState.Title.Long
                    )
                )
            )
        }
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
        painter = painterResource(id = R.drawable.ic_chevron_down),
        contentDescription = null,
        tint = OdsTheme.colors.onSurface
    )
}