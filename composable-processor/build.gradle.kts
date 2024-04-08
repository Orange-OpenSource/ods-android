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

import com.orange.ods.gradle.Dependencies

plugins {
    kotlin("jvm")
    id("com.google.devtools.ksp") version "1.9.23-1.0.20"
}

sourceSets.main {
    java.srcDirs("src/main/kotlin")
}

dependencies {
    implementation(Dependencies.ksp)
    implementation(Dependencies.kotlinPoet)
    implementation(Dependencies.kotlinPoetKsp)
}
