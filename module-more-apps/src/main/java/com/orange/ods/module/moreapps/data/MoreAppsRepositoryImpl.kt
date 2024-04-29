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
import com.orange.ods.module.moreapps.domain.MoreAppsRepository
import com.orange.ods.module.moreapps.domain.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.util.Locale


internal class MoreAppsRepositoryImpl(private val appsPlusApi: AppsPlusApi) : MoreAppsRepository {

    override fun getMoreAppsItems(apiKey: String, locale: Locale, filter: String?): Flow<Resource<List<MoreAppsItem>>> = flow {
        val response = appsPlusApi.getApps(apiKey, locale.toString(), filter)

        if (!response.isSuccessful) {
            emit(Resource.Failure(IOException("Unexpected HTTP code: ${response.code()}")))
        } else {
            emit(Resource.Success(response.body()?.items?.toModel().orEmpty()))
        }
    }
}

private fun List<ItemDto>.toModel(): List<MoreAppsItem> = this.map { itemDto -> itemDto.toModel() }

private fun ItemDto.toModel(): MoreAppsItem {
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

private fun IconsDto.toModel(): Map<Density, String> {
    val iconUrlByDensity: MutableMap<Density, String> = mutableMapOf()
    iconUrlByDensity[Density.Mdpi] = this.mdpi
    iconUrlByDensity[Density.Hdpi] = this.hdpi
    iconUrlByDensity[Density.Xhdpi] = this.xhdpi
    iconUrlByDensity[Density.Xxhdpi] = this.xxhdpi
    iconUrlByDensity[Density.Xxxhdpi] = this.xxxhdpi

    return iconUrlByDensity
}