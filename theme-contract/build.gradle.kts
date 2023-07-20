/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

import com.orange.ods.gradle.Versions

plugins {
    id("library")
}

android {
    namespace = "com.orange.ods.theme"
    
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }
}

dependencies {
    implementation(com.orange.ods.gradle.Dependencies.material)
    implementation(com.orange.ods.gradle.Dependencies.composeMaterial)
    implementation(com.orange.ods.gradle.Dependencies.kotlinReflect)
}