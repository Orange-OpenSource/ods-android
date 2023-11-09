/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.components.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.app.R
import com.orange.ods.app.domain.recipes.LocalRecipes
import com.orange.ods.app.ui.components.utilities.ComponentCustomizationBottomSheetScaffold
import com.orange.ods.app.ui.components.utilities.ComponentLaunchContentColumn
import com.orange.ods.app.ui.components.utilities.clickOnElement
import com.orange.ods.app.ui.utilities.code.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.code.FunctionCallCode
import com.orange.ods.compose.OdsComposable
import com.orange.ods.compose.component.dialog.OdsAlertDialog
import com.orange.ods.compose.component.dialog.OdsAlertDialogButtonBuilder
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsListItemTrailingSwitchBuilder

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentDialog() {
    val customizationState = rememberDialogCustomizationState()

    with(customizationState) {
        val closeDialogAction = { openDialog.value = false }
        val context = LocalContext.current
        val recipes = LocalRecipes.current.filter { it.description.isNotBlank() }

        ComponentCustomizationBottomSheetScaffold(
            bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
            bottomSheetContent = {
                OdsListItem(
                    text = stringResource(id = R.string.component_element_title),
                    trailing = OdsListItemTrailingSwitchBuilder(titleChecked.value, { titleChecked.value = it })
                )
                OdsListItem(
                    text = stringResource(id = R.string.component_dialog_element_dismiss_button),
                    trailing = OdsListItemTrailingSwitchBuilder(dismissButtonChecked.value, { dismissButtonChecked.value = it })
                )
            }) {
            val confirmButtonText =
                stringResource(id = if (isDismissButtonChecked) R.string.component_dialog_action_confirm else R.string.component_dialog_action_ok)
            val dismissButtonText = stringResource(id = R.string.component_dialog_action_dismiss)
            val recipe = rememberSaveable { recipes.random() }

            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
            ) {
                ComponentLaunchContentColumn(textRes = R.string.component_dialog_customize, buttonLabelRes = R.string.component_dialog_open) {
                    openDialog.value = true
                }

                CodeImplementationColumn(
                    modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin))
                ) {
                    FunctionCallCode(
                        name = OdsComposable.OdsAlertDialog.name,
                        exhaustiveParameters = false,
                        parameters = {
                            simple("text", "<dialog text>")
                            classInstance<OdsAlertDialogButtonBuilder>("confirmButton") {
                                text(confirmButtonText)
                                onClick()
                            }
                            if (isTitleChecked) string("title", recipe.title)
                            if (isDismissButtonChecked) {
                                classInstance<OdsAlertDialogButtonBuilder>("dismissButton") {
                                    text(dismissButtonText)
                                    onClick()
                                }
                            }
                        }
                    )
                }

                if (shouldOpenDialog) {
                    OdsAlertDialog(
                        title = if (isTitleChecked) recipe.title else null,
                        text = recipe.description,
                        confirmButton = OdsAlertDialogButtonBuilder(confirmButtonText) {
                            clickOnElement(context = context, clickedElement = confirmButtonText)
                            closeDialogAction()
                        },
                        dismissButton = if (isDismissButtonChecked) {
                            OdsAlertDialogButtonBuilder(dismissButtonText) {
                                clickOnElement(context = context, clickedElement = dismissButtonText)
                                closeDialogAction()
                            }
                        } else null,
                    )
                }
            }
        }
    }
}