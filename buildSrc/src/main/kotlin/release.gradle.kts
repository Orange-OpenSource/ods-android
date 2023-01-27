/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

import com.orange.ods.gradle.execute
import java.text.SimpleDateFormat
import java.util.*

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
    execute("sed", "-Ei", "", "s/^(version=).*/\\1$version/", "gradle.properties")
}

fun updateDependencies(version: String) {
    val substituteDependencyArg = "s/(com\\.orange\\.ods\\.android:ods-[^:]*:)[[:digit:]]+\\.[[:digit:]]+\\.[[:digit:]]+/\\1$version/"
    execute("sed", "-Ei", "", substituteDependencyArg, "docs/home_content.md")
    execute("sed", "-Ei", "", substituteDependencyArg, "DEVELOP.md")
}

fun updateChangelog(version: String) {
    val previousVersion = execute(
        "sed",
        "-En",
        "s/^## \\[([[:digit:]]+\\.[[:digit:]]+\\.[[:digit:]])\\].*/\\1/p",
        "changelog.md"
    ).substringBefore("\n")
    val date = SimpleDateFormat("yyyy-MM-dd").format(Date())
    execute(
        "sed",
        "-Ei",
        "",
        "s/^## \\[Unreleased\\].*/## [$version](https:\\/\\/github.com\\/Orange-OpenSource\\/ods-android\\/compare\\/$previousVersion...$version) - $date/",
        "changelog.md"
    )
}

fun archiveDocumentation(version: String) {
    copy {
        from("docs")
        into("docs/$version")
        exclude("_*", "Gemfile*")
    }

    val jekyllConfigFile = File("docs/_config.yml")
    jekyllConfigFile.appendText(
        """
            |  - scope:
            |      path: "$version"
            |    values:
            |      version: "$version"
            |
            """.trimMargin()
    )
}
