/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components 
 */

package com.orange.ods.xml.utilities.extension

import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.xml.theme.OdsXml

private val Xml = OdsXml()

val OdsTheme.xml: OdsXml
    // Kotlin extensions do not allow backing fields
    // We can return the Xml global variable because OdsTheme is a singleton
    get() = Xml
