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
import com.orange.ods.compose.extension.orElse
import com.orange.ods.module.moreapps.BuildConfig
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

internal class HttpClient(context: Context, private val networkManager: NetworkManager) {

    companion object {
        private const val CACHE_SIZE: Long = 5 * 1024 * 1024 // 5 MB
    }

    private val cache = Cache(context.cacheDir, CACHE_SIZE)

    fun build() = OkHttpClient.Builder()
        .apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                )
            }
        }
        .addInterceptor(CacheLoggingInterceptor())
        .addInterceptor(OfflineInterceptor())
        .addNetworkInterceptor(OnlineInterceptor())
        .cache(cache)
        .build()

    /**
     * Read from cache for 60 seconds even if there is Internet connection.
     */
    private inner class OnlineInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val response = chain.proceed(chain.request())
            val maxAge = 60 // 60 sec
            return response.newBuilder()
                .header("Cache-Control", "public, max-age=$maxAge")
                .build()
        }
    }

    /**
     *  If available, use cache when the device is offline.
     *  The 'max-stale' attribute defines the period the cache can be used. If the cache is older, then it is discarded.
     *  The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.
     */
    private inner class OfflineInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().takeIf { networkManager.isOnline }.orElse {
                // No Internet
                val offlineCacheValidity: Int = 60 * 60 * 24 * 30 // Offline cache available for 30 days
                chain.request().newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=$offlineCacheValidity")
                    .removeHeader("Pragma")
                    .build()
            }
            return chain.proceed(request)
        }
    }

    private inner class CacheLoggingInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            if (request.cacheControl.onlyIfCached) {
                Timber.i("--> REQUESTING FROM CACHE ${request.url})")
            }

            val response = chain.proceed(request)
            if (response.networkResponse == null && response.cacheResponse != null) {
                Timber.i("<-- GOT RESPONSE FROM CACHE ${response.request.url}")
            }

            return response
        }
    }
}