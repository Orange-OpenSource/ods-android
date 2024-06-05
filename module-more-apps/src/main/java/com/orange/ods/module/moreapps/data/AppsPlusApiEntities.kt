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
import com.orange.ods.module.moreapps.domain.AppsSection
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

internal fun List<ItemDto>.toModel(): List<MoreAppsItem> = this.map { itemDto -> itemDto.toModel() }

internal fun ItemDto.toModel(): MoreAppsItem {
    val type = try {
        ItemType.valueOf(this.type.uppercase())
    } catch (_: Exception) {
        ItemType.LIST
    }

    return when (type) {
        ItemType.CAROUSEL, ItemType.LIST -> AppsList(
            this.children?.toModel().orEmpty()
        ) // For the moment Carousel is managed like a List because we are waiting developments on Apps+ side
        ItemType.SECTION -> AppsSection(this.description, this.children?.toModel().orEmpty())
        ItemType.APP -> App(
            type = this.type,
            name = this.title,
            description = this.description,
            url = this.link,
            iconUrlByDensity = this.icons?.toModel().orEmpty()
        )
    }
}

internal fun IconsDto.toModel(): Map<Density, String> {
    val iconUrlByDensity: MutableMap<Density, String> = mutableMapOf()
    iconUrlByDensity[Density.Mdpi] = this.mdpi
    iconUrlByDensity[Density.Hdpi] = this.hdpi
    iconUrlByDensity[Density.Xhdpi] = this.xhdpi
    iconUrlByDensity[Density.Xxhdpi] = this.xxhdpi
    iconUrlByDensity[Density.Xxxhdpi] = this.xxxhdpi

    return iconUrlByDensity
}