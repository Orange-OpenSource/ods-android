/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.gradle

import org.gradle.api.Project

/**
 * Returns the value of the given property or null if not found.
 * This is a typed version of the findProperty method on Project instances.
 *
 * @param T The type of the property.
 * @param propertyName The name of the property.
 * @return The value of the property, possibly null or null if not found.
 */
fun <T> Project.findTypedProperty(propertyName: String): T? {
    @Suppress("UNCHECKED_CAST")
    return findProperty(propertyName) as? T
}
