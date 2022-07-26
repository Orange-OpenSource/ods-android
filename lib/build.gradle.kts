/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

import com.orange.ods.gradle.Dependencies
import com.orange.ods.gradle.Environment
import com.orange.ods.gradle.Versions
import com.orange.ods.gradle.execute

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("github")
    `maven-publish`
    signing
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        minSdk = Versions.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFile("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), file("proguard-rules.pro"))
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
        allWarningsAsErrors = true
        freeCompilerArgs = freeCompilerArgs + "-opt-in=kotlin.RequiresOptIn"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {
    implementation(Dependencies.kotlinStdlibJdk8)

    api(Dependencies.material)

    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.composeUiToolingPreview)
    implementation(Dependencies.lifecycleRuntimeKtx)

    testImplementation(Dependencies.jUnit)
    androidTestImplementation(Dependencies.testExtJUnit)
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                val artifactId = "ods-lib"
                groupId = "com.orange.ods.android"
                this.artifactId = artifactId
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
                            url.set("https://github.com/Orange-OpenSource/ods-android/blob/master/LICENSE")
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

tasks.register<DefaultTask>("tagRelease") {
    doLast {
        val tag = version.toString()
        execute("git", "tag", tag)
        execute("git", "push", "origin", tag)
    }
}
