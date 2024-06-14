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

plugins {
    id(libs.plugins.kotlin.jvm.get().pluginId) // https://github.com/gradle/gradle/issues/20084#issuecomment-1060822638
    alias(libs.plugins.ksp)
}

sourceSets.main {
    java.srcDirs("src/main/kotlin")
}

dependencies {
    implementation(libs.kotlinpoet)
    implementation(libs.kotlinpoet.ksp)
    implementation(libs.ksp)
}
