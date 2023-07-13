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

plugins {
    id("library")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
}

dependencies {
    implementation(project(":theme-contract"))

    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltCompiler)
}
