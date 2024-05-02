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

import android.content.Context
import com.orange.ods.module.moreapps.domain.MoreAppsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object OdsMoreAppsDataModule {

    @Singleton
    @Provides
    fun provideNetworkManager(@ApplicationContext context: Context): NetworkManager {
        return NetworkManager(context)
    }

    @Singleton
    @Provides
    fun provideOkHttp(@ApplicationContext context: Context, networkManager: NetworkManager): OkHttpClient {
        return HttpClient(context, networkManager).build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(com.orange.ods.module.moreapps.BuildConfig.APPS_PLUS_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideAppsPlusApi(retrofit: Retrofit): AppsPlusApi {
        return retrofit.create(AppsPlusApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMoreAppsRepository(appsPlusApi: AppsPlusApi): MoreAppsRepository = MoreAppsRepositoryImpl(appsPlusApi)
}
