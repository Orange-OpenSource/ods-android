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

import com.orange.ods.gradle.execute
import com.orange.ods.gradle.findTypedProperty
import java.text.SimpleDateFormat
import java.util.Date

tasks.register<DefaultTask>("prepareRelease") {
    doLast {
        val newVersion = project.gradle.startParameter.projectProperties["version"]
        if (newVersion == null) {
            throw GradleException("Please set the \"version\" project property.")
        }

        updateVersion(newVersion)
        updateDependencies(newVersion)
        updateChangelog(newVersion)
        archiveDocumentation(newVersion)
        updateVersionCode()
    }
}

tasks.register<DefaultTask>("tagRelease") {
    doLast {
        val tag = version.toString()
        execute("git", "tag", tag)
        execute("git", "push", "origin", tag)
    }
}

fun updateVersion(version: String) {
    File("gradle.properties").replace("(version=).*".toRegex()) { matchResult ->
        "${matchResult.groupValues[1]}$version"
    }
}

fun updateDependencies(version: String) {
    val regex = "(com\\.orange\\.ods\\.android:ods-[^:]*:)\\d+\\.\\d+\\.\\d+".toRegex()
    val transform: (MatchResult) -> CharSequence = { matchResult ->
        "${matchResult.groupValues[1]}$version"
    }
    File("docs/home_content.md").replace(regex, transform)
    File("docs/modules/About.md").replace(regex, transform)
    File("DEVELOP.md").replace(regex, transform)
}

fun updateChangelog(version: String) {
    val versionRegex = "## \\[(\\d+\\.\\d+\\.\\d+)\\]".toRegex()
    val previousVersion = File("changelog.md").readLines()
        .firstOrNull { versionRegex.find(it) != null }
        ?.run { versionRegex.find(this)?.groupValues?.get(1) }
        ?.substringBefore("\n")
        .orEmpty()
    val date = SimpleDateFormat("yyyy-MM-dd").format(Date())
    File("changelog.md").replace(
        "## \\[Unreleased\\].*".toRegex(),
        "## [$version](https://github.com/Orange-OpenSource/ods-android/compare/$previousVersion...$version) - $date"
    )
}

fun archiveDocumentation(version: String) {
    // Copy all files to a new directory named with the version
    copy {
        from("docs")
        into("docs/$version")
        exclude("_*", "Gemfile*", "404.html")
        val versionRegex = "^\\d+.\\d+.\\d+$".toRegex()
        exclude { versionRegex.matches(it.name) }
    }

    // Copy and update data_menu.yml and team.yml files
    val dataPath = "docs/_data"
    val dataFileSuffix = "_${version.replace(".", "_")}"
    val dataFilenames = listOf("data_menu", "team")
    copy {
        from(dataFilenames.map { "$dataPath/$it.yml" })
        into(dataPath)
        rename { it.removeSuffix(".yml") + "$dataFileSuffix.yml" }
    }
    dataFilenames.forEach { filename ->
        File("$dataPath/$filename$dataFileSuffix.yml").replace("version: \"\"".toRegex(), "version: \"$version\"")
    }

    // Update Jekyll configuration files
    val configText = """
            |  - scope:
            |      path: "$version"
            |    values:
            |      version: "$version"
            |
            """.trimMargin()
    File("docs/_config.yml").appendText(configText)
    File("docs/_config_netlify.yml").appendText(configText)
}

fun updateVersionCode() {
    val versionCodeRegex = "(versionCode = versionCodeProperty\\?\\.toInt\\(\\) \\?: )(\\d+)".toRegex()
    File("app/build.gradle.kts").replace(versionCodeRegex) { matchResult ->
        val versionCode = matchResult.groupValues[2].toInt() + 1
        "${matchResult.groupValues[1]}$versionCode"
    }
}

tasks.register<DefaultTask>("testSonatypeRepository") {
    doLast {
        val sonatypeRepositoryId = project.findTypedProperty<String>("sonatypeRepositoryId")
        if (sonatypeRepositoryId == null) {
            throw GradleException("Please set the \"sonatypeRepositoryId\" project property.")
        }

        // Add Sonatype Maven repository
        File("build.gradle.kts").replace("(\\s*)mavenCentral\\(\\)".toRegex()) { matchResult ->
            val indent = matchResult.groupValues[1]
            "${matchResult.value}${indent}maven(url = \"https://oss.sonatype.org/content/repositories/comorange-$sonatypeRepositoryId\")"
        }

        // Replace project dependencies with module dependencies in app
        File("app/build.gradle.kts").replace("implementation\\(project\\(\":(.*)\"\\)\\)".toRegex()) { matchResult ->
            "implementation(\"com.orange.ods.android:ods-${matchResult.groupValues[1]}:$version\")"
        }

        // Remove all Android Studio modules except app
        File("settings.gradle.kts").replace("(include\\(.*\\)(\\n)?)+".toRegex(), "include(\":app\")\n")
    }
}
