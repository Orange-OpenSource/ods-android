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

class OdsComponentsTokens(
    val chip: OdsChipTokens = OdsChipTokens(),
    val navigationBar: OdsNavigationBarTokens = OdsNavigationBarTokens(),
    val primaryNavigationTab: OdsPrimaryNavigationTabTokens = OdsPrimaryNavigationTabTokens(),
    val switch: OdsSwitchTokens = OdsSwitchTokens(),
    val systemBar: OdsSystemBarsTokens = OdsSystemBarsTokens(),
    val textField: OdsTextFieldTokens = OdsTextFieldTokens(),
    val topAppBar: OdsTopAppBarTokens = OdsTopAppBarTokens(),
)