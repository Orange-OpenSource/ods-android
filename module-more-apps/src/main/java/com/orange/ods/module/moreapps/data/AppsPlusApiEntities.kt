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

package com.orange.ods.module.moreapps.data


internal data class AppsPlusResponseDto(
    val items: List<ItemDto>
)

internal data class ItemDto(
    val type: String,
    val description: String?,
    val children: List<ItemDto>?,
    val published: String?,
    val title: String?,
    val mid: String?,
    val iconUrl: String?,
    val icons: IconsDto?,
    val link: String?,
    val providerId: String?
)

internal data class IconsDto(
    val mdpi: String,
    val hdpi: String,
    val xhdpi: String,
    val xxhdpi: String,
    val xxxhdpi: String
)

internal enum class ItemType {
    CAROUSEL, LIST, SECTION, APP
}