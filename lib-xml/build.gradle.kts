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
    id(libs.plugins.kotlin.kapt.get().pluginId) // https://github.com/gradle/gradle/issues/20084#issuecomment-1060822638
}

android {
    namespace = "com.orange.ods.xml"

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
    implementation(project(":lib"))

    implementation(libs.accompanist.drawablepainter)
    implementation(libs.androidx.appcompat)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.ui)
}

mavenCentralPublish {
    enabled = false
}
