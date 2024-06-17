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
 * By default, the chips are outlined. If your theme needs to use filled chips, set [style] to `OdsChipStyle.Filled`.
 */
open class OdsChipTokens(
    val style: OdsChipStyle = OdsChipStyle.Outlined
)

enum class OdsChipStyle {
    Filled, Outlined
}
