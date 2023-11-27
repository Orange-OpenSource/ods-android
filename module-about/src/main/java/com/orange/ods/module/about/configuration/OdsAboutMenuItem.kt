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

import androidx.annotation.RawRes
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
internal fun mandatoryMenuItems(@RawRes privacyPolicyFileRes: Int, @RawRes termsOfServiceFileRes: Int): List<OdsAboutMenuItem> = listOf(
    OdsAboutFileMenuItem(
        painterResource(id = R.drawable.ic_dataprotection),
        stringResource(id = R.string.ods_about_menu_privacy_policy),
        100,
        privacyPolicyFileRes,
        OdsAboutFileMenuItem.FileFormat.Html
    ),
    OdsAboutFileMenuItem(
        painterResource(id = R.drawable.ic_tasklist),
        stringResource(id = R.string.ods_about_menu_terms_of_service),
        101,
        termsOfServiceFileRes,
        OdsAboutFileMenuItem.FileFormat.Html
    )
)


/**
 * Item to display in the about menu
 *
 * @property graphicsObject The leading icon of the menu item.
 * @property text The primary text of the menu item.
 * @property position Index corresponding to the item position in the menu.
 * @property secondaryText The secondary text of the menu item displayed below the primary text.
 */
@Immutable
sealed class OdsAboutMenuItem(val graphicsObject: Any, val text: String, val position: Int, val secondaryText: String? = null)

/**
 * A [OdsAboutMenuItem] linked to a file.
 */
class OdsAboutFileMenuItem private constructor(
    graphicsObject: Any,
    text: String,
    position: Int,
    @RawRes val fileRes: Int,
    val fileFormat: FileFormat,
    secondaryText: String? = null
) : OdsAboutMenuItem(graphicsObject, text, position, secondaryText) {

    /**
     * Creates an instance of [OdsAboutFileMenuItem].
     *
     * @property painter The painter to draw as menu item leading icon.
     * @property text The primary text of the menu item.
     * @property position Index corresponding to the item position in the menu.
     * @property fileRes The resource identifier of the file to display on item click.
     * @property fileFormat The format of the file.
     * @property secondaryText The secondary text of the menu item displayed below the primary text.
     */
    constructor(
        painter: Painter,
        text: String,
        position: Int,
        fileRes: Int,
        fileFormat: FileFormat,
        secondaryText: String? = null
    ) : this(graphicsObject = painter, text, position, fileRes, fileFormat, secondaryText)

    /**
     * Creates an instance of [OdsAboutFileMenuItem].
     *
     * @property imageVector The image vector to draw as menu item leading icon.
     * @property text The primary text of the menu item.
     * @property position Index corresponding to the item position in the menu.
     * @property fileRes The resource identifier of the file to display on item click.
     * @property fileFormat The format of the file.
     * @property secondaryText The secondary text of the menu item displayed below the primary text.
     */
    constructor(
        imageVector: ImageVector,
        text: String,
        position: Int,
        fileRes: Int,
        fileFormat: FileFormat,
        secondaryText: String? = null
    ) : this(graphicsObject = imageVector, text, position, fileRes, fileFormat, secondaryText)

    /**
     * Creates an instance of [OdsAboutFileMenuItem].
     *
     * @property bitmap The image bitmap to draw as menu item leading icon.
     * @property text The primary text of the menu item.
     * @property position Index corresponding to the item position in the menu.
     * @property fileRes The resource identifier of the file to display on item click.
     * @property fileFormat The format of the file.
     * @property secondaryText The secondary text of the menu item displayed below the primary text.
     */
    constructor(
        bitmap: ImageBitmap,
        text: String,
        position: Int,
        fileRes: Int,
        fileFormat: FileFormat,
        secondaryText: String? = null
    ) : this(graphicsObject = bitmap, text, position, fileRes, fileFormat, secondaryText)

    enum class FileFormat {
        Html, Markdown
    }
}

/**
 * An [OdsAboutMenuItem] linked to an URL.
 */
class OdsAboutUrlMenuItem private constructor(
    graphicsObject: Any,
    text: String,
    position: Int,
    val url: String,
    secondaryText: String? = null
) : OdsAboutMenuItem(graphicsObject, text, position, secondaryText) {

    /**
     * Creates an instance of [OdsAboutUrlMenuItem].
     *
     * @property painter The painter to draw as menu item leading icon.
     * @property text The primary text of the menu item.
     * @property position Index corresponding to the item position in the menu.
     * @property url The URL to launch on item click.
     * @property secondaryText The secondary text of the menu item displayed below the primary text.
     */
    constructor(
        painter: Painter,
        text: String,
        position: Int,
        url: String,
        secondaryText: String? = null
    ) : this(graphicsObject = painter, text, position, url, secondaryText)

    /**
     * Creates an instance of [OdsAboutUrlMenuItem].
     *
     * @property imageVector The image vector to draw as menu item leading icon.
     * @property text The primary text of the menu item.
     * @property position Index corresponding to the item position in the menu.
     * @property url The URL to launch on item click.
     * @property secondaryText The secondary text of the menu item displayed below the primary text.
     */
    constructor(
        imageVector: ImageVector,
        text: String,
        position: Int,
        url: String,
        secondaryText: String? = null
    ) : this(graphicsObject = imageVector, text, position, url, secondaryText)

    /**
     * Creates an instance of [OdsAboutUrlMenuItem].
     *
     * @property bitmap The image bitmap to draw as menu item leading icon.
     * @property text The primary text of the menu item.
     * @property position Index corresponding to the item position in the menu.
     * @property url The URL to launch on item click.
     * @property secondaryText The secondary text of the menu item displayed below the primary text.
     */
    constructor(
        bitmap: ImageBitmap,
        text: String,
        position: Int,
        url: String,
        secondaryText: String? = null
    ) : this(graphicsObject = bitmap, text, position, url, secondaryText)
}
