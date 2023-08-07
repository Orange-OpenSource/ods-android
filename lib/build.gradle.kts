/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

import com.orange.ods.gradle.Dependencies
import com.orange.ods.gradle.Versions

plugins {
    id("com.google.devtools.ksp") version "1.9.0-1.0.13"
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
        java.srcDir("$buildDir/generated/ksp/$name/kotlin/")
    }
}

dependencies {
    compileOnly(project(":composable-processor"))
    ksp(project(":composable-processor"))
    api(project(":theme-contract"))
    api(project(":theme-orange"))

    implementation(Dependencies.accompanistFlowLayout)
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
    implementation(Dependencies.kotlinReflect)
    implementation(Dependencies.lifecycleRuntimeKtx)

    testImplementation(Dependencies.jUnit)
    androidTestImplementation(Dependencies.testExtJUnit)
}
