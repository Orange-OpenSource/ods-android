/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

import com.orange.ods.gradle.Environment
import com.orange.ods.gradle.gitHubApi

tasks.register<DefaultTask>("publishToGitHub") {
    doLast {
        val ref = Environment.getVariables("GITHUB_REF").first()
        val tag = ref.substringAfter("refs/tags/")
        gitHubApi {
            publishRelease(tag, draft = true, prerelease = true)
        }
    }
}
