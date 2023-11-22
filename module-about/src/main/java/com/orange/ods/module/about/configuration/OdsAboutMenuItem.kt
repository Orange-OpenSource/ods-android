/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.module.about.configuration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.module.about.R

@Composable
@Stable
internal fun mandatoryMenuItems(): List<OdsAbout.MenuItem> = listOf(
    OdsAbout.FileMenuItem(
        painterResource(id = R.drawable.ic_dataprotection),
        stringResource(id = R.string.ods_about_menu_privacy_policy),
        100,
        "about_privacy_policy",
        OdsAbout.FileMenuItem.FileFormat.Html
    ),
    OdsAbout.FileMenuItem(
        painterResource(id = R.drawable.ic_tasklist),
        stringResource(id = R.string.ods_about_menu_terms_of_service),
        101,
        "about_terms_of_service",
        OdsAbout.FileMenuItem.FileFormat.Html
    )
)

/**
 * Contains classes to configure an OdsAboutModule.
 */
object OdsAbout {

    /**
     * Item to display in the about menu
     *
     * @property graphicsObject The leading icon of the menu item.
     * @property text The primary text of the menu item
     * @property position Index corresponding to the item position in the menu.
     * @property secondaryText The secondary text of the menu item displayed below the primary text.
     */
    @Immutable
    sealed class MenuItem(val graphicsObject: Any, val text: String, val position: Int, val secondaryText: String? = null)

    /**
     * A [MenuItem] linked to a file.
     */
    class FileMenuItem(
        graphicsObject: Any,
        text: String,
        position: Int,
        val fileName: String,
        val fileFormat: FileFormat,
        secondaryText: String? = null
    ) : MenuItem(graphicsObject, text, position, secondaryText) {

        /**
         * Creates an instance of [OdsAbout.FileMenuItem].
         *
         * @property painter The painter to draw as menu item leading icon.
         * @property text The primary text of the menu item
         * @property position Index corresponding to the item position in the menu.
         * @property fileName The name of the file to display on item click.
         * @property fileFormat The format of the file.
         * @property secondaryText The secondary text of the menu item displayed below the primary text.
         */
        constructor(
            painter: Painter,
            text: String,
            position: Int,
            fileName: String,
            fileFormat: FileFormat,
            secondaryText: String? = null
        ) : this(graphicsObject = painter, text, position, fileName, fileFormat, secondaryText)

        /**
         * Creates an instance of [OdsAbout.FileMenuItem].
         *
         * @property imageVector The image vector to draw as menu item leading icon.
         * @property text The primary text of the menu item
         * @property position Index corresponding to the item position in the menu.
         * @property fileName The name of the file to display on item click.
         * @property fileFormat The format of the file.
         * @property secondaryText The secondary text of the menu item displayed below the primary text.
         */
        constructor(imageVector: ImageVector, text: String, position: Int, fileName: String, fileFormat: FileFormat, secondaryText: String? = null) : this(
            graphicsObject = imageVector,
            text,
            position,
            fileName,
            fileFormat,
            secondaryText
        )

        /**
         * Creates an instance of [OdsAbout.FileMenuItem].
         *
         * @property bitmap The image bitmap to draw as menu item leading icon.
         * @property text The primary text of the menu item
         * @property position Index corresponding to the item position in the menu.
         * @property fileName The name of the file to display on item click.
         * @property fileFormat The format of the file.
         * @property secondaryText The secondary text of the menu item displayed below the primary text.
         */
        constructor(bitmap: ImageBitmap, text: String, position: Int, fileName: String, fileFormat: FileFormat, secondaryText: String? = null) : this(
            graphicsObject = bitmap,
            text,
            position,
            fileName,
            fileFormat,
            secondaryText
        )

        enum class FileFormat {
            Html, Markdown
        }
    }

    /**
     * An [MenuItem] linked to an URL.
     */
    class UrlMenuItem(
        graphicsObject: Any,
        title: String,
        position: Int,
        val url: String,
        subtitle: String? = null
    ) : MenuItem(graphicsObject, title, position, subtitle) {

        /**
         * Creates an instance of [OdsAbout.UrlMenuItem].
         *
         * @property painter The painter to draw as menu item leading icon.
         * @property text The primary text of the menu item
         * @property position Index corresponding to the item position in the menu.
         * @property url The URL to launch on item click.
         * @property secondaryText The secondary text of the menu item displayed below the primary text.
         */
        constructor(
            painter: Painter,
            title: String,
            position: Int,
            url: String,
            secondaryText: String? = null
        ) : this(graphicsObject = painter, title, position, url, secondaryText)

        /**
         * Creates an instance of [OdsAbout.UrlMenuItem].
         *
         * @property imageVector The image vector to draw as menu item leading icon.
         * @property text The primary text of the menu item
         * @property position Index corresponding to the item position in the menu.
         * @property url The URL to launch on item click.
         * @property secondaryText The secondary text of the menu item displayed below the primary text.
         */
        constructor(imageVector: ImageVector, title: String, position: Int, url: String, secondaryText: String? = null) : this(
            graphicsObject = imageVector,
            title,
            position,
            url,
            secondaryText
        )

        /**
         * Creates an instance of [OdsAbout.UrlMenuItem].
         *
         * @property bitmap The image bitmap to draw as menu item leading icon.
         * @property text The primary text of the menu item
         * @property position Index corresponding to the item position in the menu.
         * @property url The URL to launch on item click.
         * @property secondaryText The secondary text of the menu item displayed below the primary text.
         */
        constructor(bitmap: ImageBitmap, title: String, position: Int, url: String, secondaryText: String? = null) : this(
            graphicsObject = bitmap,
            title,
            position,
            url,
            secondaryText
        )
    }
}