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
    id("library")
}

android {
    namespace = "com.orange.ods.module.about"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
}

dependencies {
    implementation(project(":lib"))
    implementation(Dependencies.appCompat)
    implementation(Dependencies.browser)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.hiltAndroid)
    implementation(Dependencies.navigationCompose)
}