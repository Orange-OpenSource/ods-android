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
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation("com.android.tools.build:gradle:8.4.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.0") // https://issuetracker.google.com/issues/176079157#comment14
    implementation("com.squareup:javapoet:1.13.0") // https://github.com/google/dagger/issues/3282
}
