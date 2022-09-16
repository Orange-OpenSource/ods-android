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
    kotlin("jvm")
    id("com.google.devtools.ksp").version(com.orange.ods.gradle.Versions.ksp)
}

sourceSets.main {
    java.srcDirs("src/main/kotlin")
}

dependencies {
    implementation(Dependencies.ksp)
    implementation(Dependencies.kotlinPoet)
    implementation(Dependencies.kotlinPoetKsp)
}
