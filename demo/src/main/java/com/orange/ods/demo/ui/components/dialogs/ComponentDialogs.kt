/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.orange.ods.compose.component.button.OdsButton
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.components.buttons.fullWidthButton
import com.orange.ods.demo.ui.utilities.composable.LabelledCheckbox
import com.orange.ods.demo.ui.utilities.composable.Title

@Composable
fun ComponentDialogsContent() {
    val customizationState = rememberDialogCustomizationState()

    Title(textRes = R.string.component_dialogs_customize, withHorizontalPadding = true)
    LabelledCheckbox(
        checked = customizationState.titleChecked,
        label = stringResource(id = R.string.component_element_title)
    )
    LabelledCheckbox(
        checked = customizationState.iconChecked,
        label = stringResource(id = R.string.component_element_icon)
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        LabelledCheckbox(
            checked = customizationState.button1Checked,
            label = stringResource(id = R.string.component_element_button1)
        )
        LabelledCheckbox(
            checked = customizationState.button2Checked,
            label = stringResource(id = R.string.component_element_button2)
        )
    }

    OdsButton(modifier = Modifier.fullWidthButton(), text = stringResource(id = R.string.component_dialogs_open_dialog), hasPrimaryColor = true, onClick = { /*TODO*/ })
}