/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.components.utilities

import android.content.Context
import android.widget.Toast
import com.orange.ods.demo.R

fun clickOnElement(context: Context, clickedElement: String) {
    Toast.makeText(context, String.format(context.getString(R.string.component_on_click_toast), clickedElement), Toast.LENGTH_SHORT).show()
}