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
    id("library")
    id(libs.plugins.kotlin.parcelize.get().pluginId) // https://github.com/gradle/gradle/issues/20084#issuecomment-1060822638
}

android {
    namespace = "com.orange.ods.theme.innovationcup"
}

dependencies {
    implementation(project(":theme-contract"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material)
}
