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

import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
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
 * <a href="https://system.design.orange.com/0c1af118d/p/14638a-selection-controls/b/352c00" class="external" target="_blank">ODS Checkbox</a>.
 *
 * Checkboxes allow users to select one or more items from a set. Checkboxes can turn an option on
 * or off.
 *
 *
 * @param checked whether Checkbox is checked or unchecked
 * @param onCheckedChange callback to be invoked when checkbox is being clicked,
 * therefore the change of checked state in requested.  If null, then this is passive
 * and relies entirely on a higher-level component to control the "checked" state.
 * @param modifier Modifier to be applied to the layout of the checkbox
 * @param enabled whether the component is enabled or grayed out
 */
@Composable
@OdsComponentApi
fun OdsCheckbox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    CompositionLocalProvider(LocalRippleTheme provides OdsPrimaryRippleTheme) {
        Checkbox(
            modifier = modifier,
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
            colors = if (checked) {
                OdsCheckboxDefault.colors(disabledColor = OdsTheme.colors.secondary.enable(enabled = false))
            } else {
                OdsCheckboxDefault.colors()
            }
        )
    }
}

private object OdsCheckboxDefault {

    @Composable
    fun colors(
        checkedColor: Color = OdsTheme.colors.secondary,
        uncheckedColor: Color = OdsTheme.colors.onSurface.copy(alpha = 0.6f),
        checkmarkColor: Color = OdsTheme.colors.surface,
        disabledColor: Color = OdsTheme.colors.onSurface.enable(enabled = false),
        disabledIndeterminateColor: Color = checkedColor.enable(enabled = false)
    ) = CheckboxDefaults.colors(checkedColor, uncheckedColor, checkmarkColor, disabledColor, disabledIndeterminateColor)

}

@UiModePreviews.Default
@Composable
private fun PreviewOdsCheckbox() = Preview {
    var checked by remember { mutableStateOf(false) }
    OdsCheckbox(
        checked = checked,
        onCheckedChange = { checked = it }
    )
}
