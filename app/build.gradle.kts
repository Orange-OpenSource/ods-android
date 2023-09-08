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
    id("kotlin-parcelize")
    id("com.google.firebase.appdistribution")
    id("com.google.firebase.crashlytics")
    id("firebase")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt") // This must be the last statement in the plugins {} to avoid "options not recognized" warning
}

android {
    namespace = "com.orange.ods.app"

    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = "com.orange.ods.app"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        val versionCodeProperty = project.findTypedProperty<String>("versionCode")
        versionCode = versionCodeProperty?.toInt() ?: 5
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
        debug {
            applicationIdSuffix = ".debug"
        }
    }

    firebaseAppDistribution {
        releaseNotesFile = Firebase_gradle.AppDistribution.releaseNotesFilePath
        groups = project.findTypedProperty("appDistributionGroup")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
        allWarningsAsErrors = true
        freeCompilerArgs = freeCompilerArgs + "-opt-in=kotlin.RequiresOptIn"
    }

    buildFeatures {
        buildConfig = true
        compose = true
        viewBinding = true
        dataBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
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

    implementation(Dependencies.accompanistFlowLayout)
    implementation(Dependencies.accompanistSystemUiController)
    implementation(Dependencies.activityCompose)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.browser)
    implementation(Dependencies.coil)
    implementation(Dependencies.coilCompose)
    implementation(platform(Dependencies.composeBom))
    implementation(Dependencies.composeMaterial3)
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeUiTooling)
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

gradle.projectsEvaluated {
    tasks.named("preBuild").dependsOn(tasks.named("copyChangelog"))
}
