/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.app.ui.utilities

import androidx.annotation.DrawableRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.orange.ods.app.R
import com.orange.ods.app.ui.LocalThemeManager
import com.orange.ods.app.ui.utilities.extension.isOrange
import com.orange.ods.extension.orElse

object DrawableManager {

    private val orangeResIdByGenericResId = mapOf(
        // About
        R.drawable.il_about_generic to R.drawable.il_about,
        // Guidelines
        R.drawable.il_color_generic to R.drawable.il_color,
        R.drawable.il_spacing_generic to R.drawable.il_spacing,
        R.drawable.il_typography_generic to R.drawable.il_typography,
        // Components
        R.drawable.il_app_bars_top_generic to R.drawable.il_app_bars_top,
        R.drawable.il_app_bars_top_small_generic to R.drawable.il_app_bars_top_small,
        R.drawable.il_banners_generic to R.drawable.il_banners,
        R.drawable.il_bottom_navigation_generic to R.drawable.il_bottom_navigation,
        R.drawable.il_buttons_generic to R.drawable.il_buttons,
        R.drawable.il_buttons_small_generic to R.drawable.il_buttons_small,
        R.drawable.il_buttons_icons_generic to R.drawable.il_buttons_icons,
        R.drawable.il_cards_generic to R.drawable.il_cards,
        R.drawable.il_checkboxes_generic to R.drawable.il_checkboxes,
        R.drawable.il_chips_generic to R.drawable.il_chips,
        R.drawable.il_chips_small_generic to R.drawable.il_chips_small,
        R.drawable.il_dialogs_generic to R.drawable.il_dialogs,
        R.drawable.il_fab_generic to R.drawable.il_fab,
        R.drawable.il_image_item_generic to R.drawable.il_image_item,
        R.drawable.il_list_item_generic to R.drawable.il_list_item,
        R.drawable.il_lists_generic to R.drawable.il_lists,
        R.drawable.il_menus_generic to R.drawable.il_menus,
        R.drawable.il_navigation_drawers_generic to R.drawable.il_navigation_drawers,
        R.drawable.il_progress_generic to R.drawable.il_progress,
        R.drawable.il_radio_buttons_generic to R.drawable.il_radio_buttons,
        R.drawable.il_sliders_generic to R.drawable.il_sliders,
        R.drawable.il_snackbars_generic to R.drawable.il_snackbars,
        R.drawable.il_snackbars_small_generic to R.drawable.il_snackbars_small,
        R.drawable.il_switches_generic to R.drawable.il_switches,
        R.drawable.il_switches_small_generic to R.drawable.il_switches_small,
        R.drawable.il_tabs_generic to R.drawable.il_tabs,
        R.drawable.il_tabs_small_generic to R.drawable.il_tabs_small,
        R.drawable.il_text_fields_generic to R.drawable.il_text_fields,
        R.drawable.il_text_fields_small_generic to R.drawable.il_text_fields_small,
    )

    private val genericResIdByOrangeResId = orangeResIdByGenericResId.entries.associate { it.value to it.key }

    @Composable
    fun getDrawableResIdForCurrentTheme(@DrawableRes resId: Int): Int {
        val isOrangeTheme = LocalThemeManager.current.currentThemeConfiguration.isOrange
        val currentThemeResId = if (isOrangeTheme) orangeResIdByGenericResId[resId] else genericResIdByOrangeResId[resId]

        return currentThemeResId.orElse { resId }
    }

    @Composable
    fun getPlaceholderResId(error: Boolean = false): Int {
        val darkTheme = isSystemInDarkTheme()
        return when {
            !darkTheme && !error -> R.drawable.placeholder
            !darkTheme && error -> R.drawable.placeholder_error
            darkTheme && !error -> R.drawable.placeholder_dark
            else -> R.drawable.placeholder_error_dark
        }
    }

    @Composable
    fun getPlaceholderSmallResId(error: Boolean = false): Int {
        val darkTheme = isSystemInDarkTheme()
        return when {
            !darkTheme && !error -> R.drawable.placeholder_small
            !darkTheme && error -> R.drawable.placeholder_error_small
            darkTheme && !error -> R.drawable.placeholder_small_dark
            else -> R.drawable.placeholder_error_small_dark
        }
    }
}
