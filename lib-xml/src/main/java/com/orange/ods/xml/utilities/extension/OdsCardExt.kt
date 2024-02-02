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

import com.orange.ods.compose.component.card.OdsCard

/**
 * @return [OdsCard.Image.Position] associated to the provided [xmlId]
 * BE CAREFUL: If the enum values change you have to update associated XML attributes in the lib-xml.
 */
fun OdsCard.Image.Position.Companion.fromXmlAttrValue(xmlId: Int): OdsCard.Image.Position = OdsCard.Image.Position.entries[xmlId]

/**
 * XML enum value corresponding to this [OdsCard.Image.Position]
 * BE CAREFUL: As there is no way to access XML enum names directly, if an enum name change, you have to update this method.
 */
val OdsCard.Image.Position.xmlEnumValue
    get() = when (this) {
        OdsCard.Image.Position.Start -> "start"
        OdsCard.Image.Position.End -> "end"
    }