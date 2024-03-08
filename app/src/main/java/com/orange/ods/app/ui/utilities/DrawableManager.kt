/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ods.app.ui.utilities

import androidx.annotation.DrawableRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.orange.ods.app.R
import com.orange.ods.app.ui.LocalThemeManager
import com.orange.ods.app.ui.utilities.extension.isOrange
import com.orange.ods.compose.extension.orElse

object DrawableManager {
    const val ImageBackgroundColor = 0xff1b1b1b

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
        R.drawable.il_bottom_sheet_generic to R.drawable.il_bottom_sheet,
        R.drawable.il_buttons_generic to R.drawable.il_buttons,
        R.drawable.il_buttons_small_generic to R.drawable.il_buttons_small,
        R.drawable.il_buttons_icons_generic to R.drawable.il_buttons_icons,
        R.drawable.il_cards_generic to R.drawable.il_cards,
        R.drawable.il_checkboxes_generic to R.drawable.il_checkboxes,
        R.drawable.il_chips_generic to R.drawable.il_chips,
        R.drawable.il_chips_small_generic to R.drawable.il_chips_small,
        R.drawable.il_dialogs_generic to R.drawable.il_dialogs,
        R.drawable.il_empty_state_generic to R.drawable.il_empty_state,
        R.drawable.il_empty_state_error_generic to R.drawable.il_empty_state_error,
        R.drawable.il_empty_state_first_use_generic to R.drawable.il_empty_state_first_use,
        R.drawable.il_empty_state_no_data_generic to R.drawable.il_empty_state_no_data,
        R.drawable.il_empty_state_user_clear_generic to R.drawable.il_empty_state_user_clear,
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
            !darkTheme && !error -> com.orange.ods.R.drawable.placeholder
            !darkTheme && error -> com.orange.ods.R.drawable.placeholder_error
            darkTheme && !error -> com.orange.ods.R.drawable.placeholder_dark
            else -> com.orange.ods.R.drawable.placeholder_error_dark
        }
    }

    @Composable
    fun getPlaceholderSmallResId(error: Boolean = false): Int {
        val darkTheme = isSystemInDarkTheme()
        return when {
            !darkTheme && !error -> com.orange.ods.R.drawable.placeholder_small
            !darkTheme && error -> com.orange.ods.R.drawable.placeholder_error_small
            darkTheme && !error -> com.orange.ods.R.drawable.placeholder_small_dark
            else -> com.orange.ods.R.drawable.placeholder_error_small_dark
        }
    }
}
