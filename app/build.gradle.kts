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
import com.android.build.gradle.internal.tasks.factory.dependsOn
import com.orange.ods.gradle.Dependencies
import com.orange.ods.gradle.Environment
import com.orange.ods.gradle.Versions
import com.orange.ods.gradle.findTypedProperty

plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("com.google.firebase.appdistribution")
    id("com.google.firebase.crashlytics")
    id("firebase")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.plugin.compose")
    id("kotlin-kapt") // This must be the last statement in the plugins {} to avoid "options not recognized" warning
}

android {
    namespace = "com.orange.ods.app"

    compileSdk = Versions.compileSdk

    defaultConfig {
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        val versionCodeProperty = project.findTypedProperty<String>("versionCode")
        versionCode = versionCodeProperty?.toInt() ?: 11
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
    val storeFile = file(storeFilePath ?: "./app.keystore").takeIf { it.exists() }
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

    val versionFlavorDimension = "version"
    flavorDimensions.add(versionFlavorDimension)
    productFlavors {
        create("qualif") {
            dimension = versionFlavorDimension
            applicationId = "com.orange.ods.test.app"
        }

        create("prod") {
            dimension = versionFlavorDimension
            applicationId = "com.orange.ods.app"
        }
    }

    firebaseAppDistribution {
        appId = "1:212698857200:android:67d1403d02a72f4d5ecc35"
        releaseNotesFile = Firebase_gradle.AppDistribution.releaseNotesFilePath
        groups = project.findTypedProperty("appDistributionGroup")
    }

    kotlin {
        jvmToolchain(17)
        compilerOptions {
            allWarningsAsErrors = true
            freeCompilerArgs.add("-opt-in=kotlin.RequiresOptIn")
            // Suppresses an expected warning that triggers a build failure because allWarningsAsErrors is true
            // See https://youtrack.jetbrains.com/issue/KT-68400/K2-w-Kapt-currently-doesnt-support-language-version-2.0.-Falling-back-to-1.9.
            freeCompilerArgs.add("-Xsuppress-version-warnings")
        }
    }

    buildFeatures {
        buildConfig = true
        compose = true
        viewBinding = true
        dataBinding = true
    }

    packaging {
        with(resources.excludes) {
            add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    implementation(project(":lib"))
    implementation(project(":lib-xml"))
    implementation(project(":theme-innovation-cup"))
    implementation(project(":module-about"))

    implementation(Dependencies.accompanistSystemUiController)
    implementation(Dependencies.activityCompose)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.browser)
    implementation(Dependencies.coil)
    implementation(Dependencies.coilCompose)
    implementation(platform(Dependencies.composeBom))
    implementation(Dependencies.composeMaterial3)
    implementation(Dependencies.composeUi)
    debugImplementation(Dependencies.composeUiTooling)
    implementation(Dependencies.composeUiToolingPreview)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.dataStorePreferences)
    implementation(platform(Dependencies.firebaseBom))
    implementation(Dependencies.firebaseCrashlytics)
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltCompiler)
    implementation(Dependencies.kotlinReflect)
    implementation(Dependencies.lifecycleRuntimeKtx)
    implementation(Dependencies.lifecycleViewModelKtx)
    implementation(Dependencies.material)
    implementation(Dependencies.navigationCompose)
    implementation(Dependencies.timber)
    implementation(Dependencies.webkit)
}

tasks.register<Copy>("copyChangelog") {
    from("../changelog.md").into("src/main/res/raw")
}

tasks.register<Copy>("copyThirdParty") {
    from("../THIRD_PARTY.md").into("src/main/res/raw").rename { it.lowercase() }
}

gradle.projectsEvaluated {
    tasks.named("preBuild").dependsOn(tasks.named("copyChangelog"), tasks.named("copyThirdParty"))
}
