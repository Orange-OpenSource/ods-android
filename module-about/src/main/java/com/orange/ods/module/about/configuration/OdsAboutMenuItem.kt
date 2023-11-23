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

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.orange.ods.module.about.R

private enum class AboutMenuItem(@DrawableRes private val iconRes: Int, @StringRes private val textRes: Int, private val position: Int) {
    PrivacyPolicy(R.drawable.ic_dataprotection, R.string.ods_about_menu_privacy_policy, 100),
    TermsOfService(R.drawable.ic_tasklist, R.string.ods_about_menu_terms_of_service, 101),
    AppNews(R.drawable.ic_calendareventinfo, R.string.ods_about_menu_app_news, 102),
    LegalInformation(R.drawable.ic_legal, R.string.ods_about_menu_legal_information, 103),
    RateTheApp(R.drawable.ic_review, R.string.ods_about_menu_rate_the_app, 104);

    @Composable
    fun linkedToFile(@RawRes fileRes: Int) = OdsAbout.FileMenuItem(
        painterResource(id = iconRes),
        stringResource(id = textRes),
        position,
        fileRes,
        OdsAbout.FileMenuItem.FileFormat.Html
    )

    @Composable
    fun linkedToUrl(url: String) = OdsAbout.UrlMenuItem(
        painterResource(id = iconRes),
        stringResource(id = textRes),
        position,
        url
    )
}


@Composable
@Stable
internal fun mandatoryMenuItems(@RawRes privacyPolicyFileRes: Int, @RawRes termsOfServiceFileRes: Int): List<OdsAboutMenuItem> = listOf(
    AboutMenuItem.PrivacyPolicy.linkedToFile(privacyPolicyFileRes),
    AboutMenuItem.TermsOfService.linkedToFile(fileRes = termsOfServiceFileRes)
)

@Composable
internal fun optionalMenuItems(@RawRes appNewsFileRes: Int?, @RawRes legalInformationFileRes: Int?, rateTheAppUrl: String?): List<OdsAbout.MenuItem> {
    return mutableListOf<OdsAboutMenuItem>().apply {
        appNewsFileRes?.let {
            add(AboutMenuItem.AppNews.linkedToFile(fileRes = appNewsFileRes))
        }
        legalInformationFileRes?.let {
            add(AboutMenuItem.LegalInformation.linkedToFile(fileRes = legalInformationFileRes))
        }
        rateTheAppUrl?.let {
            add(AboutMenuItem.RateTheApp.linkedToUrl(url = rateTheAppUrl))
        }
    }.toList()
}

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
