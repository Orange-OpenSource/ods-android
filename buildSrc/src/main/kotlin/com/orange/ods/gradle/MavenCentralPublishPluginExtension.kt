/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.gradle

abstract class MavenCentralPublishPluginExtension {

    companion object {
        const val NAME = "mavenCentralPublish"
        const val VARIANT = "prodRelease"
    }

    var artifactId: String? = null
}
