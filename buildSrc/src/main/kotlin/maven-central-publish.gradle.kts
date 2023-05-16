/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

import com.orange.ods.gradle.Environment
import com.orange.ods.gradle.MavenCentralPublishPluginExtension

plugins {
    id("com.android.library")
    `maven-publish`
    signing
}

val pluginExtension: MavenCentralPublishPluginExtension?
    get() = extensions.findByName(MavenCentralPublishPluginExtension.Name) as? MavenCentralPublishPluginExtension?

apply {
    if (pluginExtension == null) {
        extensions.create<MavenCentralPublishPluginExtension>(MavenCentralPublishPluginExtension.Name)
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "com.orange.ods.android"
                artifactId = pluginExtension?.artifactId ?: "ods-${project.name}"
                this.version = version
                artifact(tasks["sourcesJar"])

                pom {
                    name.set(artifactId)
                    description.set("Orange Design System for Android")
                    val gitHubUrl = "https://github.com/Orange-OpenSource/ods-android"
                    url.set(gitHubUrl)
                    licenses {
                        license {
                            name.set("MIT License")
                            url.set("https://github.com/Orange-OpenSource/ods-android/blob/main/LICENSE")
                        }
                    }
                    scm {
                        url.set(gitHubUrl)
                        connection.set("scm:git:git://github.com/Orange-OpenSource/ods-android.git")
                        developerConnection.set("scm:git:ssh://git@github.com/Orange-OpenSource/ods-android.git")
                    }
                    developers {
                        developer {
                            name.set("Pauline Auvray")
                            email.set("pauline.auvray@orange.com")
                        }
                        developer {
                            name.set("Florent Maitre")
                            email.set("florent.maitre@orange.com")
                        }
                    }
                }
            }
        }

        repositories {
            maven {
                val releasesRepoUrl = "https://oss.sonatype.org/service/local/staging/deploy/maven2/"
                val snapshotsRepoUrl = "https://oss.sonatype.org/content/repositories/snapshots/"
                url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
                credentials {
                    val (username, password) = Environment.getVariablesOrNull("SONATYPE_USERNAME", "SONATYPE_PASSWORD")
                    this.username = username
                    this.password = password
                }
            }
        }
    }

    signing {
        val (signingKeyId, signingSecretKey, signingPassword) = Environment.getVariablesOrNull(
            "GNUPG_SIGNING_KEY_ID",
            "GNUPG_SIGNING_SECRET_KEY",
            "GNUPG_SIGNING_PASSWORD"
        )
        useInMemoryPgpKeys(signingKeyId, signingSecretKey, signingPassword)
        sign(publishing.publications["release"])
    }
}

tasks.register<Jar>("sourcesJar") {
    archiveClassifier.set("sources")
    from(android.sourceSets.getByName("main").java.srcDirs)
}
