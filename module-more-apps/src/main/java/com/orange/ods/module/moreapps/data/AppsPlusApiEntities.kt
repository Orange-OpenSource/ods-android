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

import com.orange.ods.module.moreapps.domain.App
import com.orange.ods.module.moreapps.domain.AppsList
import com.orange.ods.module.moreapps.domain.Density
import com.orange.ods.module.moreapps.domain.MoreAppsItem


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

internal fun List<ItemDto>.toModel(): List<MoreAppsItem?> = this.map { itemDto -> itemDto.toModel() }

internal fun ItemDto.toModel(): MoreAppsItem? {
    val type = try {
        ItemType.valueOf(this.type.uppercase())
    } catch (_: Exception) {
        null
    }

    return when (type) {
        ItemType.CAROUSEL, ItemType.LIST -> AppsList(null, this.children?.toModel().orEmpty()) // For the moment Carousel is managed like a List because we are waiting developments on Apps+ side
        ItemType.SECTION -> AppsList(this.description, this.children?.toModel().orEmpty())
        ItemType.APP -> App(
            name = this.title,
            description = this.description,
            url = this.link,
            iconUrlByDensity = this.icons?.toModel().orEmpty()
        )
        else -> null
    }
}


internal fun IconsDto.toModel() = mapOf(
    Density.Mdpi to mdpi,
    Density.Hdpi to hdpi,
    Density.Xhdpi to xhdpi,
    Density.Xxhdpi to xxhdpi,
    Density.Xxxhdpi to xxxhdpi
)