/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.navigationdrawers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DrawerValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.components.utilities.ComponentLaunchContentColumn
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.CommonTechnicalTextColumn
import com.orange.ods.app.ui.utilities.composable.Subtitle
import com.orange.ods.compose.component.OdsComponent
import com.orange.ods.compose.component.chip.OdsChoiceChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing
import com.orange.ods.compose.component.navigationdrawer.OdsNavigationDrawer
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentNavigationDrawers() {
    val customizationState = rememberNavigationDrawersCustomizationState()
    val scope = rememberCoroutineScope()
    var drawerState = rememberDrawerState(DrawerValue.Closed)
    val recipes = LocalRecipes.current
    OdsNavigationDrawer(
        firstList = recipes,
        drawerState = drawerState,
        content = {
            with(customizationState) {
                ComponentCustomizationBottomSheetScaffold(
                    bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
                    bottomSheetContent = {
                        OdsListItem(
                            text = stringResource(id = R.string.component_navigation_drawer_content_example),
                            trailing = OdsSwitchTrailing(checked = customizationState.subTitleChecked)
                        )
                        OdsChoiceChipsFlowRow(
                            selectedChip = header,
                            outlinedChips = true,
                            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                        ) {
                            Subtitle(textRes = R.string.component_navigation_drawer_header_image)
                            OdsChoiceChip(textRes = R.string.component_element_avatar, value = ComponentNavigationDrawersContentState.Header.Avatar)
                            OdsChoiceChip(
                                textRes = R.string.component_navigation_drawer_background,
                                value = ComponentNavigationDrawersContentState.Header.Background
                            )
                            OdsChoiceChip(textRes = R.string.component_element_none, value = ComponentNavigationDrawersContentState.Header.None)
                        }
                        OdsListItem(
                            text = stringResource(id = R.string.component_navigation_drawer_subtitle),
                            trailing = OdsSwitchTrailing(checked = customizationState.subTitleChecked)
                        )
                        OdsListItem(
                            text = stringResource(id = R.string.component_navigation_drawer_list_icon),
                            trailing = OdsSwitchTrailing(checked = customizationState.listIconChecked)
                        )
                        OdsListItem(
                            text = stringResource(id = R.string.component_navigation_drawer_list_divider),
                            trailing = OdsSwitchTrailing(checked = customizationState.listDividerChecked)
                        )
                        OdsListItem(
                            text = stringResource(id = R.string.component_navigation_drawer_label),
                            trailing = OdsSwitchTrailing(checked = customizationState.sectionLabelChecked)
                        )
                    }) {
                    Column {
                        ComponentLaunchContentColumn(
                            textRes = R.string.component_navigation_drawer_content,
                            buttonLabelRes = R.string.component_navigation_drawer_open,
                            onButtonClick = { scope.launch { drawerState.open() } }
                        )

                        CodeImplementationColumn {
                            CommonTechnicalTextColumn(
                                componentName = OdsComponent.OdsNavigationDrawer.name
                            ) {
                            }
                        }
                    }
                }
            }
        }
    )
}