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

import com.orange.ods.compose.component.button.OdsTextButton

/**
 * @return [OdsTextButton.Style] associated to the provided [xmlId]
 * BE CAREFUL: If the enum values change you have to update associated XML attributes in the lib-xml
 */
fun OdsTextButton.Style.Companion.fromXmlAttrValue(xmlId: Int): OdsTextButton.Style = OdsTextButton.Style.entries[xmlId]

/**
 * @return the XML enum value corresponding to this [OdsTextButton.Style]
 * BE CAREFUL: As there is no way to access XML enum names directly, if an enum name change, you have to update this method.
 */
fun OdsTextButton.Style.getXmlEnumValue() = when (this) {
    OdsTextButton.Style.Default -> "standard"
    OdsTextButton.Style.Primary -> "primary"
}