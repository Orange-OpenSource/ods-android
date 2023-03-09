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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
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
import com.orange.ods.compose.component.navigationdrawer.OdsModalDrawer
import com.orange.ods.compose.component.navigationdrawer.OdsModalDrawerHeaderParametersProvider
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentModalDrawers() {
    val customizationState = rememberNavigationDrawersCustomizationState()
    val scope = rememberCoroutineScope()
    var drawerState = rememberDrawerState(DrawerValue.Closed)
    OdsModalDrawer(
        headerParametersProvider = OdsModalDrawerHeaderParametersProvider(
            title = "Headline 6",
            backgroundImage = if (customizationState.hasBackground) painterResource(id = R.drawable.placeholder) else null,
            subtitle = if (customizationState.isSubTitleChecked) stringResource(id = R.string.component_modal_drawer_list_icon) else null,
            avatar = if (customizationState.hasAvatar) painterResource(id = R.drawable.placeholder) else null
        ),
        /*hasIcon = customizationState.isListIcon,
        secondList = if (customizationState.isListDivider) recipes else null,*/
        drawerState = drawerState,
        content = {
            with(customizationState) {
                ComponentCustomizationBottomSheetScaffold(
                    bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
                    bottomSheetContent = {
                        OdsListItem(
                            text = stringResource(id = R.string.component_modal_drawer_content_example),
                            trailing = OdsSwitchTrailing(checked = contentExampleChecked)
                        )
                        OdsChoiceChipsFlowRow(
                            selectedChip = header,
                            outlinedChips = true,
                            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                        ) {
                            Subtitle(textRes = R.string.component_modal_drawer_header_image)
                            OdsChoiceChip(textRes = R.string.component_element_avatar, value = ComponentNavigationDrawersContentState.Header.Avatar)
                            OdsChoiceChip(
                                textRes = R.string.component_modal_drawer_background,
                                value = ComponentNavigationDrawersContentState.Header.Background
                            )
                            OdsChoiceChip(textRes = R.string.component_element_none, value = ComponentNavigationDrawersContentState.Header.None)
                        }
                        OdsListItem(
                            text = stringResource(id = R.string.component_modal_drawer_subtitle),
                            trailing = OdsSwitchTrailing(
                                checked = subTitleChecked,
                                enabled = isContentExample
                            )
                        )
                        OdsListItem(
                            text = stringResource(id = R.string.component_modal_drawer_list_icon),
                            trailing = OdsSwitchTrailing(
                                checked = listIconChecked,
                                enabled = isContentExample
                            ),
                        )
                        OdsChoiceChipsFlowRow(
                            selectedChip = content,
                            outlinedChips = true,
                            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                        ) {
                            Subtitle(textRes = R.string.component_modal_drawer_list_example)
                            OdsChoiceChip(textRes = R.string.component_element_divider, value = ComponentNavigationDrawersContentState.Content.Divider)
                            OdsChoiceChip(
                                textRes = R.string.component_element_label,
                                value = ComponentNavigationDrawersContentState.Content.Label
                            )
                            OdsChoiceChip(textRes = R.string.component_element_none, value = ComponentNavigationDrawersContentState.Content.None)
                        }
                    }) {
                    Column {
                        ComponentLaunchContentColumn(
                            textRes = R.string.component_modal_drawer_content,
                            buttonLabelRes = R.string.component_modal_drawer_open,
                            onButtonClick = { scope.launch { drawerState.open() } }
                        )
                        CodeImplementationColumn {
                            CommonTechnicalTextColumn(
                                componentName = OdsComponent.OdsModalDrawer.name
                            ) {
                            }
                        }
                    }
                }
            }
        }
    )

}