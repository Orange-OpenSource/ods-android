/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import com.orange.ods.compose.theme.OdsCustomTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
@ExperimentalMaterialApi // Used for BottomSheetScaffold in the ComponentsCardScreen
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var odsCustomThemes: Set<@JvmSuppressWildcards OdsCustomTheme>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OdsDemoApp(odsCustomThemes)
        }
    }
}