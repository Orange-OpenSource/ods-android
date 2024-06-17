/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ods.theme.innovationcup

import com.orange.ods.theme.tokens.OdsChipStyle
import com.orange.ods.theme.tokens.OdsChipTokens
import com.orange.ods.theme.tokens.OdsComponentsTokens
import com.orange.ods.theme.tokens.OdsSemanticColorToken
import com.orange.ods.theme.tokens.OdsSystemBarsTokens
import com.orange.ods.theme.tokens.OdsTextFieldStyle
import com.orange.ods.theme.tokens.OdsTextFieldTokens

internal val InnovationCupComponentsTokens = OdsComponentsTokens(
    chip = OdsChipTokens(
        style = OdsChipStyle.Filled
    ),
    systemBar = OdsSystemBarsTokens(
        containerColor = OdsSemanticColorToken.Primary,
    ),
    textField = OdsTextFieldTokens(
        style = OdsTextFieldStyle.Outlined
    )
)