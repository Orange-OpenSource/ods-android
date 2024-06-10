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
    id("com.google.devtools.ksp") version "2.0.0-1.0.22"
    id("library")
    id("github")
    id("kotlin-parcelize")
    id("app.cash.paparazzi") version "1.3.4"
    id("org.jetbrains.kotlin.plugin.compose")
}

buildscript {
    repositories {
        mavenCentral()
        google()
    }
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

    androidTestImplementation(Dependencies.composeUiTestJUnit)
    androidTestImplementation(Dependencies.kotlinReflect)
    androidTestImplementation(Dependencies.mockitoAndroid)
    androidTestImplementation(Dependencies.mockitoKotlin)
    // Needed for createComposeRule(), but not for createAndroidComposeRule<YourActivity>():
    debugImplementation(Dependencies.composeUiTestManifest)
}

// TODO Remove when https://github.com/google/guava/issues/6567 is fixed.
// See also: https://github.com/google/guava/issues/6801.
dependencies.constraints {
    testImplementation("com.google.guava:guava") {
        attributes {
            attribute(
                TargetJvmEnvironment.TARGET_JVM_ENVIRONMENT_ATTRIBUTE,
                objects.named(TargetJvmEnvironment::class.java, TargetJvmEnvironment.STANDARD_JVM)
            )
        }
        because(
            "Paparazzi's layoutlib and sdk-common depend on Guava's -jre published variant." +
                    "See https://github.com/cashapp/paparazzi/issues/906."
        )
    }
}
