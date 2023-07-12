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
    id("com.google.devtools.ksp") version "1.7.10-1.0.6"
    id("com.android.library")
    id("kotlin-android")
    id("github")
    id("maven-central-publish")
    id("kotlin-parcelize")
}

/**
 * The OdsThemeConfigurationContract implementation used by Android Studio previews for the ODS library.
 * Please change this value if you want to have a custom theme preview  for the ODS library and
 * don't forget to add a dependency to your custom theme in this case.
 */
val previewThemeConfigurationClass = "com.orange.ods.theme.orange.OrangeThemeConfiguration"

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        minSdk = Versions.minSdk

        buildConfigField("com.orange.ods.theme.OdsThemeConfigurationContract", "PREVIEW_THEME_CONFIGURATION", "new $previewThemeConfigurationClass()")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFile("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), file("proguard-rules.pro"))
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
        allWarningsAsErrors = true
        freeCompilerArgs = freeCompilerArgs + "-opt-in=kotlin.RequiresOptIn"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }

    sourceSets.configureEach {
        java.srcDir("$buildDir/generated/ksp/$name/kotlin/")
    }
}

dependencies {
    api(project(":theme-contract"))
    api(project(":theme-orange"))
    api(Dependencies.composeMaterial)
    
    implementation(Dependencies.composeMaterial3)

    implementation(Dependencies.kotlinStdlibJdk8)
    implementation(Dependencies.kotlinReflect)
    compileOnly(project(":composable-processor"))

    implementation(Dependencies.accompanistFlowLayout)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeUiTooling)
    implementation(Dependencies.composeUiToolingPreview)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.customViewPoolingContainer) // This dependency is needed otherwise the compose preview does not work properly
    implementation(Dependencies.kotlinReflect)
    implementation(Dependencies.lifecycleRuntimeKtx)
    implementation(Dependencies.constraintLayoutCompose)

    testImplementation(Dependencies.jUnit)
    androidTestImplementation(Dependencies.testExtJUnit)

    ksp(project(":composable-processor"))
}
