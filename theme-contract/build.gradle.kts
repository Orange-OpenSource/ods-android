/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = com.orange.ods.gradle.Versions.compileSdk

    defaultConfig {
        minSdk = com.orange.ods.gradle.Versions.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions.jvmTarget = "11"
}

dependencies {
    implementation(com.orange.ods.gradle.Dependencies.coreKtx)
    implementation(com.orange.ods.gradle.Dependencies.appCompat)
    implementation(com.orange.ods.gradle.Dependencies.composeMaterial)
    implementation(com.orange.ods.gradle.Dependencies.kotlinReflect)
}