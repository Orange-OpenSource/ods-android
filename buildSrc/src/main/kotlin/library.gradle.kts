/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

import com.orange.ods.gradle.MavenCentralPublishPluginExtension
import com.orange.ods.gradle.Versions
import gradle.kotlin.dsl.accessors._ecf15b363eddec123ebdbce713433fa8.android

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-central-publish")
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        minSdk = Versions.minSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFile("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), file("proguard-rules.pro"))
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
        allWarningsAsErrors = true
    }

    publishing {
        singleVariant(MavenCentralPublishPluginExtension.RELEASE_COMPONENT_NAME) {
            withSourcesJar()
            withJavadocJar()
        }
    }
}
