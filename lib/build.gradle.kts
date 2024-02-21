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
    id("com.google.devtools.ksp") version "1.9.21-1.0.15"
    id("library")
    id("github")
    id("kotlin-parcelize")
}

/**
 * The OdsThemeConfigurationContract implementation used by Android Studio previews for the ODS library.
 * Please change this value if you want to have a custom theme preview  for the ODS library and
 * don't forget to add a dependency to your custom theme in this case.
 */
val previewThemeConfigurationClass = "com.orange.ods.theme.orange.OrangeThemeConfiguration"

android {
    namespace = "com.orange.ods"

    defaultConfig {
        buildConfigField("com.orange.ods.theme.OdsThemeConfigurationContract", "PREVIEW_THEME_CONFIGURATION", "new $previewThemeConfigurationClass()")
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }

    sourceSets.configureEach {
        java.srcDir("${layout.buildDirectory}/generated/ksp/$name/kotlin/")
    }
}

dependencies {
    compileOnly(project(":composable-processor"))
    ksp(project(":composable-processor"))
    api(project(":theme-contract"))
    api(project(":theme-orange"))

    implementation(Dependencies.appCompat)
    implementation(platform(Dependencies.composeBom))
    api(Dependencies.composeMaterial)
    implementation(Dependencies.composeMaterial3)
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeUiTooling)
    implementation(Dependencies.composeUiToolingPreview)
    implementation(Dependencies.constraintLayoutCompose)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.customViewPoolingContainer) // This dependency is needed otherwise the compose preview does not work properly
    implementation(Dependencies.lifecycleRuntimeKtx)

    testImplementation(Dependencies.jUnit)
    androidTestImplementation(Dependencies.testExtJUnit)
}
