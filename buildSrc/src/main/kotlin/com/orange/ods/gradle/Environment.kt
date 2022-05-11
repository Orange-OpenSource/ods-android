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

import org.gradle.api.GradleException

object Environment {

    /**
     * Returns the values for the given environment variables names.
     * Throws an exception if at least one environment variable is missing.
     *
     * @param variables The list of environment variables.
     * @return The values associated with these environment variables.
     */
    fun getVariables(vararg variables: String): List<String> {
        val missingVariable = variables.firstOrNull { !System.getenv().containsKey(it) }
        // At least one environment variable is missing
        if (missingVariable != null) {
            throw GradleException("$missingVariable environment variable is missing.")
        }

        return variables.map { System.getenv(it) }
    }

    /**
     * Returns the values for the given environment variables names.
     * A value can be null if the corresponding environment variable is missing.
     *
     * @param variables The list of environment variables.
     * @return The values associated with these environment variables.
     */
    fun getVariablesOrNull(vararg variables: String): List<String?> {
        return variables.map { System.getenv(it) }
    }
}
