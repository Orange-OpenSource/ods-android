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
import org.gradle.api.Project
import java.io.ByteArrayOutputStream

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

fun Project.execute(executable: String, vararg args: String): String {
    val output = ByteArrayOutputStream()
    exec {
        standardOutput = output
        this.executable = executable
        this.args = args.toList()
    }

    return output.toString()
}

/**
 * Launches cURL with the specified arguments and throws an exception if the received HTTP status code is invalid.
 * When an invalid HTTP status code is received, cURL exits with code 0 and the task succeeds.
 * This method is used to make the task fail when HTTP status code is invalid.
 *
 * @param args The cURL arguments.
 */
fun Project.curl(vararg args: String) {
    // Add an argument to write the HTTP status code at the end of the output
    val output = execute("curl", *args, "-w", "\n%{http_code}")

    // Retrieve the HTTP status code
    val splitOutput = output.split("\n")
    val httpStatusCode = splitOutput.last().toInt()
    // Reconstruct output without the status code and print it
    println(splitOutput.dropLast(1).joinToString("\n"))
    if (httpStatusCode < 200 || httpStatusCode >= 300) {
        throw GradleException("Received HTTP error code $httpStatusCode.")
    }
}

/**
 * Generates and returns the release notes starting from the specified Git revision.
 *
 * @param since The Git revision where the release notes should start from.
 * @return The release notes.
 */
fun Project.generateReleaseNotes(since: String?): String {
    val args = mutableListOf("log", "--pretty=format:- %b", "--merges", "--grep=Merge pull request")
    if (since != null && since.isNotEmpty()) {
        args += "${since}..HEAD"
    }
    val log = execute("git", *args.toTypedArray())

    // Remove useless lines
    val emptyLineRegex = Regex("(?m)^[\t]*\r?\n")

    return log.replace(emptyLineRegex, "").trim()
}

/**
 * Finds and returns the last Git tag matching a given pattern.
 *
 * @param pattern The pattern which the tag should match.
 * @param before If not null, the research will only take into account tags before this one.
 * @param isAnnotated Indicates if search should be performed through annotated or lightweight tags.
 * @return
 */
fun Project.findLastTag(pattern: String, before: String?, isAnnotated: Boolean): String? {
    val tags = execute("git", "tag", if (isAnnotated) "--sort=-*committerdate" else "--sort=-committerdate")

    // Add multiline option to pattern
    val regex = Regex(pattern, RegexOption.MULTILINE)
    var results = regex.findAll(tags).map { it.value }.toList()

    if (before != null) {
        val index = results.indexOf(before)
        if (index >= 0) {
            results = results.drop(index + 1)
        }
    }

    return results.firstOrNull()
}

fun Project.createTag(tag: String, sha: String) {
    val (token, repository) = Environment.getVariables("GITHUB_TOKEN", "GITHUB_REPOSITORY")
    curl(
        "-X", "POST",
        "-H", "Authorization: token $token",
        "-H", "Accept: application/vnd.github.v3+json",
        "-d", "{\"ref\":\"refs/tags/$tag\",\"sha\":\"$sha\"}",
        "https://api.github.com/repos/$repository/git/refs"
    )
}
