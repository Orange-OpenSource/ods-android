/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.control

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsPrimaryRippleTheme
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.utilities.extension.enable

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/14638a-selection-controls/b/352c00" class="external" target="_blank">ODS radio button</a>.
 *
 * Radio buttons allow users to select one option from a set.
 *
 * [RadioButton]s can be combined together with [Text] in the desired layout (e.g. [Column] or
 * [Row]) to achieve radio group-like behaviour, where the entire layout is selectable.
 *
 * @param selected whether this radio button is selected or not
 * @param onClick callback to be invoked when the RadioButton is clicked. If null, then this
 * RadioButton will not handle input events, and only act as a visual indicator of [selected] state
 * @param modifier Modifier to be applied to the radio button
 * @param enabled Controls the enabled state of the [RadioButton]. When `false`, this button will
 * not be selectable and appears disabled
 */
@Composable
@OdsComponentApi
fun OdsRadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    CompositionLocalProvider(LocalRippleTheme provides OdsPrimaryRippleTheme) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            colors = if (selected) {
                OdsRadioButtonDefaults.colors(
                    disabledColor = OdsTheme.colors.secondary.enable(enabled = false)
                )
            } else {
                OdsRadioButtonDefaults.colors()
            }
        )
    }
}

private object OdsRadioButtonDefaults {

    @Composable
    fun colors(
        selectedColor: Color = OdsTheme.colors.secondary,
        unselectedColor: Color = OdsTheme.colors.onSurface.copy(alpha = 0.6f),
        disabledColor: Color = OdsTheme.colors.onSurface.enable(enabled = false)
    ) = RadioButtonDefaults.colors(selectedColor, unselectedColor, disabledColor)

}

@UiModePreviews.Default
@Composable
private fun PreviewOdsRadioButton() = Preview {
    var selected by remember { mutableStateOf(false) }
    OdsRadioButton(
        selected = selected,
        onClick = { selected = !selected }
    )
}
