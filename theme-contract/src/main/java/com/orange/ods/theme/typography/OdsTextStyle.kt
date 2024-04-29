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

package com.orange.ods.theme.typography

import androidx.compose.ui.text.TextStyle

/**
 * An Orange Design System text style.
 *
 * @property textStyle The underlying Compose text style.
 * @property isAllCaps Indicates if characters formatted with this text style are capitalized or not.
 */
data class OdsTextStyle(val textStyle: TextStyle, val isAllCaps: Boolean = false)
