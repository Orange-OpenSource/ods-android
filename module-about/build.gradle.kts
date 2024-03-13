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
import com.orange.ods.gradle.Versions

plugins {
    id("library")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt") // This must be the last statement in the plugins {} to avoid "options not recognized" warning
}

android {
    namespace = "com.orange.ods.module.about"

    buildFeatures {
        compose = true
        viewBinding = true
        dataBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
}

dependencies {
    implementation(project(":accessibility-statement-lib-android"))
    implementation(project(":lib"))
    implementation(Dependencies.appCompat)
    implementation(Dependencies.browser)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.composeUiViewBinding)
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltCompiler)
    implementation(Dependencies.navigationCompose)
    implementation(Dependencies.timber)
}