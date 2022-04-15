/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.about

sealed class AboutNavigationItem(
    var route: String
) {
    companion object {
        const val FILE_NAME_KEY = "fileName"
        const val TITLE_RES_KEY = "titleRes"
    }

    object HtmlFile : AboutNavigationItem("about/html_file")
}