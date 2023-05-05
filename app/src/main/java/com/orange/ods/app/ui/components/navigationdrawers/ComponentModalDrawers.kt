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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalCategories
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.components.utilities.ComponentLaunchContentColumn
import com.orange.ods.app.ui.utilities.DrawableManager
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.FunctionCallCode
import com.orange.ods.app.ui.utilities.composable.Subtitle
import com.orange.ods.compose.component.OdsComponent
import com.orange.ods.compose.component.chip.OdsChoiceChip
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing
import com.orange.ods.compose.component.navigationdrawer.OdsModalDrawer
import com.orange.ods.compose.component.navigationdrawer.OdsModalDrawerDivider
import com.orange.ods.compose.component.navigationdrawer.OdsModalDrawerHeader
import com.orange.ods.compose.component.navigationdrawer.OdsModalDrawerHeaderImageDisplayType
import com.orange.ods.compose.component.navigationdrawer.OdsModalDrawerItem
import com.orange.ods.compose.component.navigationdrawer.OdsModalDrawerListItem
import com.orange.ods.compose.component.navigationdrawer.OdsModalDrawerSectionLabel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentModalDrawers() {
    val customizationState = rememberNavigationDrawersCustomizationState()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val recipes = LocalRecipes.current
    val categories = LocalCategories.current

    with(customizationState) {
        val modalDrawerItems: MutableList<OdsModalDrawerItem> = categories.subList(1, categories.size).map {
            OdsModalDrawerListItem(if (isListIconChecked) it.iconResId else null, it.name)
        }.toMutableList()

        val sectionListCategory = categories.first()
        val sectionListRecipes = recipes.filter { it.category.id == sectionListCategory.id }

        if (hasLabel || hasDivider) {
            if (hasDivider) modalDrawerItems.add(OdsModalDrawerDivider)
            if (hasLabel) modalDrawerItems.add(OdsModalDrawerSectionLabel(sectionListCategory.name))
            sectionListRecipes.forEach { recipe ->
                val item = OdsModalDrawerListItem(if (isListIconChecked) recipe.iconResId else null, recipe.title)
                modalDrawerItems.add(item)
            }
        }

        val title = stringResource(id = R.string.component_modal_drawer_side)
        val subtitle = if (isSubTitleChecked) stringResource(id = R.string.component_element_example) else null
        val imageDisplayType = when {
            hasAvatar -> OdsModalDrawerHeaderImageDisplayType.Avatar
            hasBackground -> OdsModalDrawerHeaderImageDisplayType.Background
            else -> OdsModalDrawerHeaderImageDisplayType.None
        }
        val selectedItemState = remember { mutableStateOf(modalDrawerItems.firstOrNull { it is OdsModalDrawerListItem }) }
        OdsModalDrawer(
            drawerHeader = OdsModalDrawerHeader(
                title = title,
                image = if (hasBackground || hasAvatar) {
                    rememberAsyncImagePainter(
                        model = rememberSaveable { recipes.random() }.imageUrl,
                        placeholder = painterResource(id = DrawableManager.getPlaceholderResId()),
                        error = painterResource(id = DrawableManager.getPlaceholderResId(error = true))
                    )
                } else null,
                subtitle = subtitle,
                imageDisplayType = imageDisplayType
            ),
            drawerContentList = if (isContentExampleChecked) modalDrawerItems else emptyList(),
            drawerState = drawerState,
            selectedItem = selectedItemState.value,
            onItemClick = { item ->
                selectedItemState.value = item
            },
            content = {
                if (!isContentExampleChecked) {
                    listIconChecked.value = false
                    subtitleChecked.value = false
                    content.value = ComponentNavigationDrawersContentState.SectionListExample.None
                }
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
                            OdsChoiceChip(textRes = R.string.component_element_avatar, value = ComponentNavigationDrawersContentState.HeaderImage.Avatar)
                            OdsChoiceChip(
                                textRes = R.string.component_modal_drawer_background,
                                value = ComponentNavigationDrawersContentState.HeaderImage.Background
                            )
                            OdsChoiceChip(textRes = R.string.component_element_none, value = ComponentNavigationDrawersContentState.HeaderImage.None)
                        }
                        OdsListItem(
                            text = stringResource(id = R.string.component_modal_drawer_subtitle),
                            trailing = OdsSwitchTrailing(
                                checked = subtitleChecked,
                                enabled = isContentExampleChecked
                            )
                        )
                        OdsListItem(
                            text = stringResource(id = R.string.component_modal_drawer_list_icon),
                            trailing = OdsSwitchTrailing(
                                checked = listIconChecked,
                                enabled = isContentExampleChecked
                            ),
                        )
                        OdsChoiceChipsFlowRow(
                            selectedChip = content,
                            outlinedChips = true,
                            modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin)),
                        ) {
                            Subtitle(textRes = R.string.component_modal_drawer_list_example)
                            OdsChoiceChip(
                                textRes = R.string.component_element_divider,
                                value = ComponentNavigationDrawersContentState.SectionListExample.Divider,
                                enabled = isContentExampleChecked
                            )
                            OdsChoiceChip(
                                textRes = R.string.component_element_label,
                                value = ComponentNavigationDrawersContentState.SectionListExample.Label,
                                enabled = isContentExampleChecked
                            )
                            OdsChoiceChip(
                                textRes = R.string.component_element_none,
                                value = ComponentNavigationDrawersContentState.SectionListExample.None,
                                enabled = isContentExampleChecked
                            )
                        }
                    }) {
                    Column {
                        ComponentLaunchContentColumn(
                            textRes = R.string.component_modal_drawer_content,
                            buttonLabelRes = R.string.component_modal_drawer_open,
                            onButtonClick = { scope.launch { drawerState.open() } }
                        )

                        CodeImplementationColumn(modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin))) {
                            FunctionCallCode(
                                name = OdsComponent.OdsModalDrawer.name,
                                exhaustiveParameters = false,
                                parameters = {
                                    classInstance("drawerHeader", OdsModalDrawerHeader::class.java) {
                                        title(title)
                                        image()
                                        stringRepresentation("imageDisplayType", imageDisplayType)
                                        subtitle?.let { subtitle(it) }
                                    }
                                    list("drawerContentList") {
                                        if (isContentExampleChecked) {
                                            if (hasLabel) {
                                                classInstance(OdsModalDrawerSectionLabel::class.java) {
                                                    label("Section")
                                                }
                                            }
                                            classInstance(OdsModalDrawerListItem::class.java) {
                                                icon()
                                                simple("text", "<item label>")
                                            }
                                            if (hasDivider) classInstance(OdsModalDrawerDivider::class.java)
                                        }
                                    }
                                    simple("selectedItem", "<OdsModalDrawerItem>")
                                    lambda("onItemClick")
                                    lambda("content")
                                }
                            )
                        }
                    }
                }
            }
        )
    }
}