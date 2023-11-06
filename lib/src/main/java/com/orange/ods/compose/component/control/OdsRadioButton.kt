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
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsPrimaryRippleTheme
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.compose.utilities.extension.enable

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/14638a-selection-controls/b/352c00" class="external" target="_blank">ODS radio button</a>.
 *
 * Radio buttons allow users to select one option from a set.
 *
 * [RadioButton]s can be combined together with [Text] in the desired layout (e.g. [Column] or
 * [Row]) to achieve radio group-like behaviour, where the entire layout is selectable.
 *
 * @param selected Controls the selected state of the radio button.
 * @param onClick Callback invoked on radio button click. If `null`, then the radio button will not handle input events, and only act as
 * a visual indicator of [selected] state.
 * @param modifier [Modifier] applied to the radio button.
 * @param enabled Controls the enabled state of the radio button. When `false`, the button will not be selectable and appears disabled.
 */
@Composable
@OdsComposable
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
                RadioButtonDefaults.colors(
                    disabledColor = OdsTheme.colors.secondary.enable(enabled = false)
                )
            } else {
                RadioButtonDefaults.colors()
            }
        )
    }
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
