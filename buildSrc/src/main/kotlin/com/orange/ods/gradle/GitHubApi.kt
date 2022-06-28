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

import org.gradle.api.Project

class GitHubApi(private val token: String, private val repository: String) {

    fun Project.createTag(tag: String, sha: String) {
        launchRequest(
            "git/refs",
            "POST",
            "{\"ref\":\"refs/tags/$tag\",\"sha\":\"$sha\"}"
        )
    }

    fun Project.publishRelease(tag: String, draft: Boolean, prerelease: Boolean) {
        launchRequest(
            "releases",
            "POST",
            "{\"tag_name\":\"$tag\",\"name\":\"$tag\",\"draft\":$draft,\"prerelease\":$prerelease}"
        )
    }

    private fun Project.launchRequest(path: String, method: String, postData: String? = null) {
        val args = mutableListOf(
            "-X", method,
            "-H", "Authorization: token $token",
            "-H", "Accept: application/vnd.github.v3+json"
        ).apply {
            if (method == "POST" && postData != null) {
                add("-d")
                add(postData)
            }
            add("https://api.github.com/repos/$repository/$path")
        }
        curl(*args.toTypedArray())
    }
}
