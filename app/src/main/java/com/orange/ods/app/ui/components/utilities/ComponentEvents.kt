/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ods.app.ui.components.utilities

import android.content.Context
import android.widget.Toast
import com.orange.ods.app.R

fun clickOnElement(context: Context, clickedElement: String) {
    Toast.makeText(context, String.format(context.getString(R.string.component_on_click_toast), clickedElement), Toast.LENGTH_SHORT).show()
}