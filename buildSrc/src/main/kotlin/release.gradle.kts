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
import com.orange.ods.gradle.findTypedProperty
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
    File("changelog.md").replace("## \\[Unreleased\\].*".toRegex()) { matchResult ->
        "## [$version](https://github.com/Orange-OpenSource/ods-android/compare/$previousVersion...$version) - $date"
    }
}

fun archiveDocumentation(version: String) {
    copy {
        from("docs")
        into("docs/$version")
        exclude("_*", "Gemfile*")
        val versionRegex = "^\\d+.\\d+.\\d+$".toRegex()
        exclude { versionRegex.matches(it.name) }
    }

    val text = """
            |  - scope:
            |      path: "$version"
            |    values:
            |      version: "$version"
            |
            """.trimMargin()
    File("docs/_config.yml").appendText(text)
    File("docs/_config_netlify.yml").appendText(text)
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

        // Replace project dependencies with module dependencies in demo
        File("demo/build.gradle.kts").replace("implementation\\(project\\(\":(.*)\"\\)\\)".toRegex()) { matchResult ->
            "implementation(\"com.orange.ods.android:ods-${matchResult.groupValues[1]}:$version\")"
        }

        // Remove all Android Studio modules except demo
        File("settings.gradle.kts").replace("(include\\(.*\\)(\\n)?)+".toRegex(), "include(\":demo\")\n")
    }
}
