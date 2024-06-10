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

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface AppsPlusApi {

    @GET("get")
    suspend fun getApps(
        @Query("apikey") apiKey: String,
        @Query("lang") language: String,
        @Query("filter") filter: String?
    ): Response<AppsPlusResponseDto>
}