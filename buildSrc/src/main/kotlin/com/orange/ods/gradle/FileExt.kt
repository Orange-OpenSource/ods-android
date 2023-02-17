/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

import java.io.File

fun File.replace(regex: Regex, replacement: String) {
    val text = readText()
    writeText(text.replace(regex, replacement))
}

fun File.replace(regex: Regex, transform: (MatchResult) -> CharSequence) {
    val text = readText()
    writeText(text.replace(regex, transform))
}
