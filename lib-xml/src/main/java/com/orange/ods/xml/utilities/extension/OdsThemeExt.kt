/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.xml.utilities.extension

import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.xml.theme.OdsXml

private val Xml = OdsXml()

val OdsTheme.xml: OdsXml
    // Kotlin extensions do not allow backing fields
    // We can return the Xml global variable because OdsTheme is a singleton
    get() = Xml
