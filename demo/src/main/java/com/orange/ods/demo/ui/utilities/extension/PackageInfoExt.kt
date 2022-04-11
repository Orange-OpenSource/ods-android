/*
 * Software Name: MyOrange
 * SPDX-FileCopyrightText: Copyright (c) 2019-2021 Orange
 *
 * This software is confidential and proprietary information of Orange.
 * You shall not disclose such Confidential Information and shall not copy, use or distribute it in whole or in part without the prior written consent of Orange.
 *
 * Software description: My Orange is a multi-services mobile application for Orange services.
 */

package com.orange.ods.demo.ui.utilities

import android.content.pm.PackageInfo
import android.os.Build

@Suppress("DEPRECATION")
fun PackageInfo.versionCode(): Long =
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
        versionCode.toLong()
    } else {
        longVersionCode
    }
