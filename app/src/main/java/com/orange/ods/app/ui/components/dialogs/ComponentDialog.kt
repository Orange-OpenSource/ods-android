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
import com.orange.ods.app.ui.utilities.composable.CodeImplementationColumn
import com.orange.ods.app.ui.utilities.composable.ComposableCode
import com.orange.ods.app.ui.utilities.composable.SimpleParameter
import com.orange.ods.compose.component.OdsComponent
import com.orange.ods.compose.component.dialog.OdsAlertDialog
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsSwitchTrailing

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ComponentDialog() {
    val customizationState = rememberDialogCustomizationState()

    val closeDialogAction = { customizationState.openDialog.value = false }
    val context = LocalContext.current
    val recipes = LocalRecipes.current.filter { it.description.isNotBlank() }

    ComponentCustomizationBottomSheetScaffold(
        bottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
        bottomSheetContent = {
            OdsListItem(
                text = stringResource(id = R.string.component_element_title),
                trailing = OdsSwitchTrailing(checked = customizationState.titleChecked)
            )
            OdsListItem(
                text = stringResource(id = R.string.component_dialog_element_dismiss_button),
                trailing = OdsSwitchTrailing(checked = customizationState.dismissButtonChecked)
            )
        }) {
        val confirmButtonText =
            stringResource(id = if (customizationState.isDismissButtonChecked) R.string.component_dialog_action_confirm else R.string.component_dialog_action_ok)
        val dismissButtonText = stringResource(id = R.string.component_dialog_action_dismiss)
        val recipe = rememberSaveable { recipes.random() }

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            ComponentLaunchContentColumn(textRes = R.string.component_dialog_customize, buttonLabelRes = R.string.component_dialog_open) {
                customizationState.openDialog.value = true
            }

            CodeImplementationColumn(
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin))
            ) {
                ComposableCode(name = OdsComponent.OdsAlertDialog.name, parameters = mutableListOf(
                    SimpleParameter.ValueOnlyParameter("text", "<dialog text>"),
                    SimpleParameter.BetweenQuotesParameter("confirmButtonText", confirmButtonText),
                    SimpleParameter.LambdaParameter("onConfirmButtonClick")
                ).apply {
                    if (customizationState.isTitleChecked) add(SimpleParameter.BetweenQuotesParameter("titleText", recipe.title))
                    if (customizationState.isDismissButtonChecked) {
                        add(SimpleParameter.BetweenQuotesParameter("dismissButtonText", dismissButtonText))
                        add(SimpleParameter.LambdaParameter("onDismissButtonClick"))
                    }
                })
            }

            if (customizationState.shouldOpenDialog) {
                OdsAlertDialog(
                    titleText = if (customizationState.isTitleChecked) recipe.title else null,
                    text = recipe.description,
                    confirmButtonText = confirmButtonText,
                    onConfirmButtonClick = {
                        clickOnElement(context = context, clickedElement = confirmButtonText)
                        closeDialogAction()
                    },
                    dismissButtonText = if (customizationState.isDismissButtonChecked) dismissButtonText else null,
                    onDismissButtonClick = {
                        clickOnElement(context = context, clickedElement = dismissButtonText)
                        closeDialogAction()
                    },
                )
            }
        }
    }
}