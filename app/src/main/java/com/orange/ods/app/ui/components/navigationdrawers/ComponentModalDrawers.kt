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

package com.orange.ods.app.ui.components.navigationdrawers

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DrawerValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalCategories
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.LocalThemeManager
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.components.utilities.ComponentLaunchContentColumn
import com.orange.ods.app.ui.utilities.DrawableManager
import com.orange.ods.app.ui.utilities.code.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.code.FunctionCallCode
import com.orange.ods.app.ui.utilities.code.IconPainterValue
import com.orange.ods.app.ui.utilities.composable.Subtitle
import com.orange.ods.app.ui.utilities.extension.buildImageRequest
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.chip.OdsChoiceChipsFlowRow
import com.orange.ods.compose.component.listitem.OdsListItem
import com.orange.ods.compose.component.navigationdrawer.OdsModalDrawer
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentModalDrawers() {
    val customizationState = rememberNavigationDrawersCustomizationState()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val context = LocalContext.current
    val darkModeEnabled = LocalThemeManager.current.darkModeEnabled
    val recipes = LocalRecipes.current
    val categories = LocalCategories.current
    val recipe = rememberSaveable { recipes.random() }

    with(customizationState) {
        var selectedItem: OdsModalDrawer.ListItem? by remember { mutableStateOf(null) }

        val modalDrawerItems: MutableList<OdsModalDrawer.Item> = categories.subList(1, categories.size).map { category ->
            OdsModalDrawer.ListItem(
                category.name,
                if (isListIconChecked && category.iconResId != null) painterResource(id = category.iconResId) else null
            ) { item -> selectedItem = item }
        }.toMutableList()

        val sectionListCategory = categories.first()
        val sectionListRecipes = recipes.filter { it.category.id == sectionListCategory.id }

        if (hasLabel || hasDivider) {
            if (hasDivider) modalDrawerItems.add(OdsModalDrawer.Divider)
            if (hasLabel) modalDrawerItems.add(OdsModalDrawer.SectionHeader(sectionListCategory.name))
            sectionListRecipes.forEach { recipe ->
                val item =
                    OdsModalDrawer.ListItem(
                        recipe.title,
                        if (isListIconChecked && recipe.iconResId != null) painterResource(id = recipe.iconResId) else null
                    ) { item -> selectedItem = item }
                modalDrawerItems.add(item)
            }
        }

        val title = stringResource(id = R.string.component_modal_drawer_side)
        val subtitle = if (isSubTitleChecked) stringResource(id = R.string.component_element_example) else null
        val imagePainter = rememberAsyncImagePainter(
            model = buildImageRequest(context, recipe.imageUrl, darkModeEnabled),
            placeholder = painterResource(id = DrawableManager.getPlaceholderResId()),
            error = painterResource(id = DrawableManager.getPlaceholderResId(error = true))
        )

        LaunchedEffect(key1 = Unit) {
            selectedItem = modalDrawerItems.firstOrNull { it is OdsModalDrawer.ListItem } as? OdsModalDrawer.ListItem
        }
        OdsModalDrawer(
            header = OdsModalDrawer.Header(
                title = title,
                image = when {
                    hasAvatar -> OdsModalDrawer.Header.Avatar(imagePainter, "")
                    hasBackground -> OdsModalDrawer.Header.Background(imagePainter)
                    else -> null
                },
                subtitle = subtitle,
            ),
            items = if (isContentExampleChecked) modalDrawerItems else emptyList(),
            state = drawerState,
            selectedItem = selectedItem,
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
                            trailing = OdsListItem.TrailingSwitch(contentExampleChecked.value, { contentExampleChecked.value = it })
                        )
                        Subtitle(textRes = R.string.component_modal_drawer_header_image, horizontalPadding = true)
                        OdsChoiceChipsFlowRow(
                            selectedChoiceChipIndex = ComponentNavigationDrawersContentState.HeaderImage.entries.indexOf(headerImage.value),
                            modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)),
                            choiceChips = ComponentNavigationDrawersContentState.HeaderImage.entries.map { headerImage ->
                                val text = when (headerImage) {
                                    ComponentNavigationDrawersContentState.HeaderImage.Avatar -> stringResource(id = R.string.component_element_avatar)
                                    ComponentNavigationDrawersContentState.HeaderImage.Background -> stringResource(id = R.string.component_modal_drawer_background)
                                    ComponentNavigationDrawersContentState.HeaderImage.None -> stringResource(id = R.string.component_element_none)
                                }
                                OdsChoiceChipsFlowRow.ChoiceChip(text, { this.headerImage.value = headerImage })
                            }
                        )
                        OdsListItem(
                            text = stringResource(id = R.string.component_modal_drawer_subtitle),
                            trailing = OdsListItem.TrailingSwitch(subtitleChecked.value, { subtitleChecked.value = it }, isContentExampleChecked)
                        )
                        OdsListItem(
                            text = stringResource(id = R.string.component_modal_drawer_list_icon),
                            trailing = OdsListItem.TrailingSwitch(listIconChecked.value, { listIconChecked.value = it }, isContentExampleChecked)
                        )
                        Subtitle(textRes = R.string.component_modal_drawer_list_example, horizontalPadding = true)
                        OdsChoiceChipsFlowRow(
                            selectedChoiceChipIndex = ComponentNavigationDrawersContentState.SectionListExample.entries.indexOf(content.value),
                            modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)),
                            choiceChips = ComponentNavigationDrawersContentState.SectionListExample.entries.map { content ->
                                val text = when (content) {
                                    ComponentNavigationDrawersContentState.SectionListExample.Divider -> stringResource(id = R.string.component_element_divider)
                                    ComponentNavigationDrawersContentState.SectionListExample.Label -> stringResource(id = R.string.component_element_label)
                                    ComponentNavigationDrawersContentState.SectionListExample.None -> stringResource(id = R.string.component_element_none)
                                }
                                OdsChoiceChipsFlowRow.ChoiceChip(text, { this.content.value = content })
                            }
                        )
                    }) {
                    Column {
                        ComponentLaunchContentColumn(
                            textRes = R.string.component_modal_drawer_content,
                            buttonLabelRes = R.string.component_modal_drawer_open,
                            onButtonClick = { scope.launch { drawerState.open() } }
                        )

                        CodeImplementationColumn(modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin))) {
                            FunctionCallCode(
                                name = OdsComposable.OdsModalDrawer.name,
                                exhaustiveParameters = false,
                                parameters = {
                                    classInstance<OdsModalDrawer.Header>("header") {
                                        title(title)
                                        when {
                                            hasBackground -> classInstance<OdsModalDrawer.Header.Background>("image") { painter() }
                                            hasAvatar -> classInstance<OdsModalDrawer.Header.Avatar>("image") {
                                                painter()
                                                contentDescription("")
                                            }
                                        }
                                        subtitle?.let { subtitle(it) }
                                    }
                                    list("items") {
                                        if (isContentExampleChecked) {
                                            if (hasLabel) {
                                                classInstance<OdsModalDrawer.SectionHeader> {
                                                    label("Section")
                                                }
                                            }
                                            classInstance<OdsModalDrawer.ListItem> {
                                                simple("leadingIcon", IconPainterValue)
                                                simple("text", "<item label>")
                                            }
                                            if (hasDivider) classInstance<OdsModalDrawer.Divider>()
                                        }
                                    }
                                    simple("selectedItem", "<OdsModalDrawer.ListItem>")
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