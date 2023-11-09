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

rootProject.name = "Orange Design System Android"

include(":app")
include(":composable-processor")
include(":lib")
include(":lib-xml")
include(":theme-orange")
include(":theme-innovation-cup")
include(":theme-contract")
include(":module-about")
include(":accessibility-statement-lib-android")
project(":accessibility-statement-lib-android").projectDir = File("vendor/accessibility-statement-lib-android/AccessibilityStatementLibrary")
