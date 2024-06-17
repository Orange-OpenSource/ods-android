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

package com.orange.ods.theme.tokens

/**
 * By default, the text fields are outlined and more accessible in term of contrast like that. If your theme needs to use filled text fields,
 * set [style] to `OdsTextFieldStyle.Filled`.
 */
open class OdsTextFieldTokens(
    val style: OdsTextFieldStyle = OdsTextFieldStyle.Outlined
)

enum class OdsTextFieldStyle {
    Filled, Outlined
}
