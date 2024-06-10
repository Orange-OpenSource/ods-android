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

import android.annotation.TargetApi
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

internal class NetworkManager(context: Context) {

    private val connectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val isOnline: Boolean
        get() {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                hasOneOfCapabilities(listOf(NetworkCapabilities.TRANSPORT_CELLULAR, NetworkCapabilities.TRANSPORT_WIFI))
            } else {
                @Suppress("DEPRECATION")
                listOf(ConnectivityManager.TYPE_MOBILE, ConnectivityManager.TYPE_WIFI).contains(connectivityManager.activeNetworkInfo?.type)
            }
        }

    @TargetApi(Build.VERSION_CODES.M)
    private fun hasOneOfCapabilities(capabilities: List<Int>): Boolean {
        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        return if (networkCapabilities != null) {
            capabilities.any { networkCapabilities.hasTransport(it) }
        } else {
            false
        }
    }

}