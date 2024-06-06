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

import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("library")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.hilt)
    id(libs.plugins.kotlin.parcelize.get().pluginId) // https://github.com/gradle/gradle/issues/20084#issuecomment-1060822638
    id(libs.plugins.kotlin.kapt.get().pluginId) // This must be the last statement in the plugins {} to avoid "options not recognized" warning
}

android {
    namespace = "com.orange.ods.module.moreapps"

    defaultConfig {
        gradleLocalProperties(rootDir, providers).getProperty("APPS_PLUS_URL")?.let {
            buildConfigField("String", "APPS_PLUS_URL", "\"$it\"")
        } ?: run {
            throw GradleException("Required APPS_PLUS_URL property in local.properties not set.")
        }
    }

    buildFeatures {
        buildConfig = true
        compose = true
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(project(":lib"))
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.browser)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.coil)
    implementation(libs.coil.compose)
    implementation(libs.gson)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.timber)
}