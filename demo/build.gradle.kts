/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

import com.android.build.gradle.internal.tasks.factory.dependsOn
import com.orange.ods.gradle.Dependencies
import com.orange.ods.gradle.Environment
import com.orange.ods.gradle.Versions
import com.orange.ods.gradle.findTypedProperty

plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    id("kotlin-android")
    id("com.google.firebase.appdistribution")
    id("com.google.firebase.crashlytics")
    id("firebase")
}

android {
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = "com.orange.ods.demo"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        val versionCodeProperty = project.findTypedProperty<String>("versionCode")
        versionCode = versionCodeProperty?.toInt() ?: 1
        versionName = version.toString()
        val versionNameSuffixProperty = project.findTypedProperty<String>("versionNameSuffix")
        versionNameSuffix = versionNameSuffixProperty

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    val signingConfigName = "signingConfig"
    val storeFilePath = Environment.getVariablesOrNull("SIGNING_STORE_FILE_PATH").first()
    val storeFile = file(storeFilePath ?: "./demo.keystore").takeIf { it.exists() }
    if (storeFile != null) {
        signingConfigs {
            create(signingConfigName) {
                val (storePassword, keyAlias, keyPassword) = Environment.getVariablesOrNull(
                    "SIGNING_STORE_PASSWORD",
                    "SIGNING_KEY_ALIAS",
                    "SIGNING_KEY_PASSWORD"
                )
                this.storeFile = storeFile
                this.storePassword = storePassword ?: "storePassword"
                this.keyAlias = keyAlias ?: "keyAlias"
                this.keyPassword = keyPassword ?: "keyPassword"
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), file("proguard-rules.pro"))
            if (storeFile != null) {
                signingConfig = this@android.signingConfigs.getByName(signingConfigName)
            }
        }
    }

    firebaseAppDistribution {
        releaseNotesFile = Firebase_gradle.AppDistribution.releaseNotesFilePath
        groups = "ods-android-internal-tests"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
        allWarningsAsErrors = true
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }

    packagingOptions {
        with(resources.excludes) {
            add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    implementation(project(":lib"))

    implementation(Dependencies.coreKtx)
    implementation(Dependencies.kotlinReflect)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.composeUiToolingPreview)
    implementation(Dependencies.lifecycleRuntimeKtx)
    implementation(Dependencies.activityCompose)
    implementation(Dependencies.navigationCompose)
    implementation(Dependencies.accompanistSystemUiController)
    implementation(Dependencies.accompanistPager)
    implementation(Dependencies.accompanistPagerIndicators)
    implementation(Dependencies.accompanistFlowLayout)
    implementation(platform(Dependencies.firebaseBom))
    implementation(Dependencies.firebaseCrashlytics)
    implementation(Dependencies.webkit)
    implementation(Dependencies.browser)

    debugImplementation(Dependencies.composeUiTooling)
}

tasks.register<Copy>("copyChangelog") {
    from("../changelog.md").into("src/main/res/raw")
}

gradle.projectsEvaluated {
    tasks.named("preBuild").dependsOn(tasks.named("copyChangelog"))
}
