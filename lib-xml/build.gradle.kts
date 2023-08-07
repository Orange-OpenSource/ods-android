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
    id("kotlin-kapt")
}

android {
    namespace = "com.orange.ods.xml"

    buildFeatures {
        compose = true
        viewBinding = true
        dataBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
}

dependencies {
    implementation(project(":lib"))

    implementation(Dependencies.accompanistDrawablePainter)
    implementation(Dependencies.appCompat)
    implementation(platform(Dependencies.composeBom))
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.composeUi)

    testImplementation(Dependencies.jUnit)
    androidTestImplementation(Dependencies.testExtJUnit)
}
