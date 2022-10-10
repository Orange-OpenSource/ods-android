/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.utilities

object Markdown {

    private const val SpecialCharacters = "\\`*_{}[]()#+-.!"

    fun toHtml(markdown: String): String {
        val html = markdown.replaceEscapedCharacters()
            .replaceLinks()
            .replaceCode()
            .replaceHeadings()
            .replaceNewlines()

        return """
            <html>
                <head>
                <meta
                    name="color-scheme"
                    content="dark light">    
                <link
                    rel="stylesheet"
                    type="text/css"
                    href="file:///android_res/raw/base_style.css">
            </head>
            $html
        """.trimIndent()
    }

    private fun CharSequence.replaceEscapedCharacters(): String {
        val escapedCharacters = SpecialCharacters.map { "\\$it" }.joinToString("")
        return replace("\\\\([$escapedCharacters])".toRegex(), "$1")
    }

    private fun CharSequence.replaceLinks(): String {
        return replace("<([^>]*)>".toRegex(), "<a href=\"$1\">$1</a>")
            .replace("\\[(.[^]]*)]\\(([^)]*)\\)".toRegex(), "<a href=\"$2\">$1</a>")
    }

    private fun CharSequence.replaceCode() = replace("`([^`]*)`".toRegex(), "<code>$1</code>")

    private fun CharSequence.replaceHeadings(): String {
        var result = toString()
        for (level in 1..6) {
            val markdownHeading = "#".repeat(level)
            result = result.replace("(?m)^$markdownHeading (.*)$".toRegex(), "<h$level>$1</h$level>")
        }

        return result
    }

    // This method must be called after replaceHeadings
    private fun CharSequence.replaceNewlines(): String {
        var result = toString().replace("\n", "<br>")
        for (level in 1..6) {
            // Remove useless newlines before and after headings
            result = result.replace("(<br>)*<h$level>".toRegex(), "<h$level>")
                .replace("</h$level>(<br>)*".toRegex(), "</h$level>")
        }

        return result
    }
}
