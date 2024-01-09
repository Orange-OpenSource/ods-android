/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

plugins {
    id("release")
}

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
    delete(rootProject.layout.buildDirectory)
}
