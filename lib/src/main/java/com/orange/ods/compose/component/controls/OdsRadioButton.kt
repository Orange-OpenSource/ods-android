/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.controls

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.orange.ods.compose.theme.OdsPrimaryRippleTheme

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
fun OdsRadioButton(
    selected: Boolean,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    CompositionLocalProvider(LocalRippleTheme provides OdsPrimaryRippleTheme) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            colors = if (selected) {
                RadioButtonDefaults.colors(
                    disabledColor = MaterialTheme.colors.secondary.copy(alpha = ContentAlpha.disabled)
                )
            } else {
                RadioButtonDefaults.colors()
            }
        )
    }
}