/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.textfield.password

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.R
import com.orange.ods.compose.component.textfield.OdsTextFieldIcon
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider

@Composable
internal fun OdsPasswordVisualisationIcon(odsPasswordTextFieldState: OdsPasswordTextFieldState) {
    with(odsPasswordTextFieldState) {
        OdsTextFieldIcon(
            painter = if (isPasswordVisible) painterResource(id = R.drawable.ic_crosset_out_eye) else painterResource(id = R.drawable.ic_eye),
            contentDescription = if (isPasswordVisible) stringResource(id = R.string.text_field_password_hide) else stringResource(id = R.string.text_field_password_show),
            onClick = if (enabled.value) {
                { passwordVisible.value = !isPasswordVisible }
            } else null,
        )
    }
}

internal class OdsPasswordTextFieldPreviewParameterProvider : BasicPreviewParameterProvider<Boolean>(false, true)