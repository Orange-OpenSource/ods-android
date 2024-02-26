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

package com.orange.ods.xml.utilities.extension

import com.orange.ods.compose.theme.OdsDisplaySurface

/**
 * @return [OdsDisplaySurface] associated to the provided [xmlId]
 * BE CAREFUL: If the enum values change you have to update associated XML attributes in the lib-xml
 */
fun OdsDisplaySurface.Companion.fromXmlAttrValue(xmlId: Int): OdsDisplaySurface = OdsDisplaySurface.entries[xmlId]