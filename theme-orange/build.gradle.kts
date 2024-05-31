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
    id("library")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.orange.ods.theme.orange"
}

dependencies {
    implementation(project(":theme-contract"))

    implementation(platform(Dependencies.composeBom))
    implementation(Dependencies.composeMaterial3)
    implementation(Dependencies.material)
}
