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

import com.orange.ods.compose.component.button.OdsButton

/**
 * @return [OdsButton.Style] associated to the provided [xmlId]
 * BE CAREFUL: If the enum values change you have to update associated XML attributes in the lib-xml.
 */
fun OdsButton.Style.Companion.fromXmlAttrValue(xmlId: Int): OdsButton.Style = OdsButton.Style.entries[xmlId]

/**
 * XML enum value corresponding to this [OdsButton.Style]
 * BE CAREFUL: As there is no way to access XML enum names directly, if an enum name change, you have to update this method.
 */
val OdsButton.Style.xmlEnumValue
    get() = when (this) {
        OdsButton.Style.Default -> "standard"
        OdsButton.Style.Primary -> "primary"
        OdsButton.Style.FunctionalPositive -> "functional_positive"
        OdsButton.Style.FunctionalNegative -> "functional_negative"
    }