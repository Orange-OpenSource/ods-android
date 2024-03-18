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
}

android {
    namespace = "com.orange.accessibilitystatementlibrary"

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    sourceSets {
        named("main") {
            manifest.srcFile("../vendor/accessibility-statement-lib-android/AccessibilityStatementLibrary/src/main/AndroidManifest.xml")
            java.srcDir("../vendor/accessibility-statement-lib-android/AccessibilityStatementLibrary/src/main/java")
            res.srcDir("../vendor/accessibility-statement-lib-android/AccessibilityStatementLibrary/src/main/res")
        }
    }
}

dependencies {
    api(Dependencies.constraintLayout)
    implementation(Dependencies.coreKtx)
}

mavenCentralPublish {
    artifactId = "accessibility-statement"
}
