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
    id("library")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.hilt)
    id(libs.plugins.kotlin.parcelize.get().pluginId) // https://github.com/gradle/gradle/issues/20084#issuecomment-1060822638
    id(libs.plugins.kotlin.kapt.get().pluginId) // This must be the last statement in the plugins {} to avoid "options not recognized" warning
}

android {
    namespace = "com.orange.ods.module.about"

    buildFeatures {
        compose = true
        viewBinding = true
        dataBinding = true
    }

    kotlin {
        compilerOptions {
            // Suppresses an expected warning that triggers a build failure because allWarningsAsErrors is true
            // See https://youtrack.jetbrains.com/issue/KT-68400/K2-w-Kapt-currently-doesnt-support-language-version-2.0.-Falling-back-to-1.9.
            freeCompilerArgs.add("-Xsuppress-version-warnings")
        }
    }
}

dependencies {
    implementation(project(":accessibility-statement"))
    implementation(project(":lib"))

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.browser)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.ui.viewbinding)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.timber)
}