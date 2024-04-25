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
import com.orange.ods.module.moreapps.domain.AppsSection
import com.orange.ods.module.moreapps.domain.AppsSectionType
import com.orange.ods.module.moreapps.domain.Density
import com.orange.ods.module.moreapps.domain.MoreAppsRepository
import com.orange.ods.module.moreapps.domain.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import java.util.Locale


internal class MoreAppsRepositoryImpl(private val appsPlusApi: AppsPlusApi) : MoreAppsRepository {

    override fun getAppsSections(apiKey: String, locale: Locale, filter: String?): Flow<Resource<List<AppsSection>>> = flow {
        val response = appsPlusApi.getApps(apiKey, locale.toString(), filter)

        if (!response.isSuccessful) {
            emit(Resource.Failure(IOException("Unexpected HTTP code: ${response.code()}")))
        } else {
            emit(Resource.Success(response.body()?.items?.map { appSectionDto ->
                appSectionDto.toModel()
            }.orEmpty()))
        }
    }
}

private fun AppsSectionDto.toModel() = AppsSection(
    type = try {
        AppsSectionType.valueOf(this.type)
    } catch (_: Exception) {
        AppsSectionType.Section
    },
    name = this.description,
    apps = this.children.map { appDto -> appDto.toModel() }
)

private fun AppDto.toModel() = App(
    type = this.type,
    name = this.title,
    description = this.description,
    url = this.link,
    iconUrlByDensity = this.icons?.toModel().orEmpty()
)

private fun IconsDto.toModel(): Map<Density, String> {
    val iconUrlByDensity: MutableMap<Density, String> = mutableMapOf()
    iconUrlByDensity[Density.Mdpi] = this.mdpi
    iconUrlByDensity[Density.Hdpi] = this.hdpi
    iconUrlByDensity[Density.Xhdpi] = this.xhdpi
    iconUrlByDensity[Density.Xxhdpi] = this.xxhdpi
    iconUrlByDensity[Density.Xxxhdpi] = this.xxxhdpi

    return iconUrlByDensity
}