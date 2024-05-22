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

package com.orange.ods.app.ui.utilities.extension

import com.orange.ods.theme.OdsThemeConfigurationItem
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.KVisibility
import kotlin.reflect.full.memberProperties

class CatalogEntry<T> @PublishedApi internal constructor(
    private val catalog: OdsThemeConfigurationItem.Catalog<T>,
    private val property: KProperty1<OdsThemeConfigurationItem.Catalog<T>, T>
) {
    
    internal companion object {

        internal val DescriptionRegex = Regex("((?<=.)(?=\\p{Upper}))|((?<=\\D)(?=\\d))")
    }

    val name = property.name

    val description = property.name
        .split(DescriptionRegex)
        .joinToString(" ") { string ->
            string.replaceFirstChar { it.uppercase() }
        }

    val value: T
        get() = property.get(catalog)
}

@PublishedApi
@Suppress("UNCHECKED_CAST")
internal inline fun <reified T> OdsThemeConfigurationItem.Catalog<T>.getProperties(): List<KProperty1<OdsThemeConfigurationItem.Catalog<T>, T>> {
    val clazz = this::class as KClass<OdsThemeConfigurationItem.Catalog<T>>
    return clazz.memberProperties.mapNotNull {
        it.takeIf { it.visibility == KVisibility.PUBLIC && it.returnType.classifier == T::class } as? KProperty1<OdsThemeConfigurationItem.Catalog<T>, T>
    }
}

inline val <reified T> OdsThemeConfigurationItem.Catalog<T>.entries: List<CatalogEntry<T>>
    get() = getProperties<T>().map { CatalogEntry(this, it) }
