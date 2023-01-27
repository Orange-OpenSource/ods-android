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
        val version = project.gradle.startParameter.projectProperties["version"]
        if (version == null) {
            throw GradleException("Please set the \"version\" project property.")
        }

        // Get previous version from gradle.properties
        val previousVersion = execute("sed", "-En", "s/^version=(.*)/\\1/p", "gradle.properties").trim()

        // Update version in gradle.properties
        execute("sed", "-Ei", "", "s/^(version=).*/\\1$version/", "gradle.properties")

        // Update dependencies in docs/home_content.md and DEVELOP.md
        val substituteDependencyArg = "s/(com\\.orange\\.ods\\.android:ods-[^:]*:)[[:digit:]]+\\.[[:digit:]]+\\.[[:digit:]]+/\\1$version/"
        execute("sed", "-Ei", "", substituteDependencyArg, "docs/home_content.md")
        execute("sed", "-Ei", "", substituteDependencyArg, "DEVELOP.md")

        // Update unreleased section title in changelog.md
        val date = SimpleDateFormat("yyyy-MM-dd").format(Date())
        execute(
            "sed",
            "-Ei",
            "",
            "s/^## \\[Unreleased\\].*/## [$version](https:\\/\\/github.com\\/Orange-OpenSource\\/ods-android\\/compare\\/$previousVersion...$version) - $date/",
            "changelog.md"
        )
    }
}

tasks.register<DefaultTask>("tagRelease") {
    doLast {
        val tag = version.toString()
        execute("git", "tag", tag)
        execute("git", "push", "origin", tag)
    }
}
