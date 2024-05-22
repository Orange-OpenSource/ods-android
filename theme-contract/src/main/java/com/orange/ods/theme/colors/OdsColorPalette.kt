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

package com.orange.ods.theme.colors

import androidx.compose.ui.graphics.Color
import com.orange.ods.theme.OdsThemeConfigurationItem

interface OdsColorSet : OdsThemeConfigurationItem.Catalog<Color>

interface OdsColorPalette : OdsThemeConfigurationItem.Catalog<OdsColorSet>
