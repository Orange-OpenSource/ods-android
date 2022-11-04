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

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(com.orange.ods.gradle.Dependencies.androidGradlePlugin)
        classpath(com.orange.ods.gradle.Dependencies.kotlinGradlePlugin)
        classpath(com.orange.ods.gradle.Dependencies.firebaseAppDistributionGradlePlugin)
        classpath(com.orange.ods.gradle.Dependencies.firebaseCrashlyticsGradlePlugin)
        classpath(com.orange.ods.gradle.Dependencies.googleServicesGradlePlugin)
        classpath(com.orange.ods.gradle.Dependencies.hiltAndroidGradlePlugin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register<Delete>("clean") {
    group = "cleanup"
    delete(rootProject.buildDir)
}

tasks.register<DefaultTask>("tagRelease") {
    doLast {
        val tag = version.toString()
        execute("git", "tag", tag)
        execute("git", "push", "origin", tag)
    }
}
