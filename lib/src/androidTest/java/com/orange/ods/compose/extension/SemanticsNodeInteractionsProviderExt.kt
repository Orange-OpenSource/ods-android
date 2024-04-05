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

package com.orange.ods.compose.extension

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.orange.ods.compose.component.banner.OdsBanner
import com.orange.ods.compose.component.content.OdsComponentContent
import com.orange.ods.compose.component.content.OdsComponentImage
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

fun <T> SemanticsNodeInteractionsProvider.onNode(image: OdsComponentImage<T>): SemanticsNodeInteraction where T : OdsComponentContent.ExtraParameters {
    val contentDescription = image.getPrivateProperty<OdsComponentImage<T>, String>("contentDescription")
    return onNodeWithContentDescription(contentDescription)
}

fun SemanticsNodeInteractionsProvider.onNode(button: OdsBanner.Button) = onNodeWithText(button.text.uppercase())

@Suppress("UNCHECKED_CAST")
private inline fun <reified T : Any, R> T.getPrivateProperty(name: String): R {
    return T::class.memberProperties
        .first { it.name == name }
        .apply { isAccessible = true }
        .get(this) as R
}
