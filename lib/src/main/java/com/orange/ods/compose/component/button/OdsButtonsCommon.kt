/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.button

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.orange.ods.compose.component.content.OdsComponentIcon
import com.orange.ods.compose.theme.OdsDisplaySurface


/**
 * A button icon in an [OdsButton].
 * It is non-clickable and no content description is needed cause a button label is always present.
 */
class OdsButtonIcon : OdsComponentIcon {

    /**
     * Creates an instance of [OdsButtonIcon].
     *
     * @param painter Painter of the icon.
     */
    constructor(painter: Painter) : super(painter as Any, "")

    /**
     * Creates an instance of [OdsButtonIcon].
     *
     * @param imageVector Image vector of the icon.
     */
    constructor(imageVector: ImageVector) : super(imageVector as Any, "")

    /**
     * Creates an instance of [OdsButtonIcon].
     *
     * @param bitmap Image bitmap of the icon.
     */
    constructor(bitmap: ImageBitmap) : super(bitmap as Any, "")

    @Composable
    override fun Content(modifier: Modifier) {
        super.Content(modifier = modifier.size(ButtonDefaults.IconSize))
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
    }
}

@Composable
internal fun iconButtonTintColor(displaySurface: OdsDisplaySurface) = displaySurface.themeColors.onSurface
