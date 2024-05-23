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

package com.orange.ods.gradle

object Dependencies {

    const val accompanistDrawablePainter = "com.google.accompanist:accompanist-drawablepainter:${Versions.accompanist}"
    const val accompanistSystemUiController = "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}"
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activity}"
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val browser = "androidx.browser:browser:${Versions.browser}"
    const val coil = "io.coil-kt:coil:${Versions.coil}"
    const val coilCompose = "io.coil-kt:coil-compose:${Versions.coil}"
    const val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}"
    const val composeCompilerGradlePlugin = "org.jetbrains.kotlin:compose-compiler-gradle-plugin:${Versions.kotlin}"
    const val composeMaterial = "androidx.compose.material:material"
    const val composeMaterial3 = "androidx.compose.material3:material3"
    const val composeUi = "androidx.compose.ui:ui"
    const val composeUiTestJUnit = "androidx.compose.ui:ui-test-junit4"
    const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val composeUiViewBinding = "androidx.compose.ui:ui-viewbinding"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val constraintLayoutCompose = "androidx.constraintlayout:constraintlayout-compose:${Versions.constraintLayoutCompose}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.core}"
    const val customViewPoolingContainer = "androidx.customview:customview-poolingcontainer:${Versions.customViewPoolingContainer}"
    const val dataStorePreferences = "androidx.datastore:datastore-preferences:${Versions.dataStorePreferences}"
    const val firebaseAppDistributionGradlePlugin = "com.google.firebase:firebase-appdistribution-gradle:${Versions.firebaseAppDistributionGradlePlugin}"
    const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
    const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics-ktx"
    const val firebaseCrashlyticsGradlePlugin = "com.google.firebase:firebase-crashlytics-gradle:${Versions.firebaseCrashlyticsGradlePlugin}"
    const val googleServicesGradlePlugin = "com.google.gms:google-services:${Versions.googleServicesGradlePlugin}"
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    const val hiltAndroidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    const val kotlinReflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val kotlinPoet = "com.squareup:kotlinpoet:${Versions.kotlinPoet}"
    const val kotlinPoetKsp = "com.squareup:kotlinpoet-ksp:${Versions.kotlinPoet}"
    const val ksp = "com.google.devtools.ksp:symbol-processing-api:${Versions.ksp}"
    const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val mockitoAndroid = "org.mockito:mockito-android:${Versions.mockitoAndroid}"
    const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlin}"
    const val navigationCompose = "androidx.navigation:navigation-compose:${Versions.navigation}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val webkit = "androidx.webkit:webkit:${Versions.webkit}"
}
