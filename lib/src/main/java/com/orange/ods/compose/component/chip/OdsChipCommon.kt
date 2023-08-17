/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.chip

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import com.orange.ods.compose.component.content.OdsComponentIcon
import com.orange.ods.compose.component.content.OdsComponentImage

/**
 * A leading icon in an [OdsChip].
 */
class OdsChipLeadingIcon : OdsComponentIcon {

    /**
     * Creates an instance of [OdsChipLeadingIcon].
     *
     * @param painter The painter to draw.
     * @param contentDescription The content description associated to this [OdsChipLeadingIcon].
     */
    constructor(painter: Painter, contentDescription: String) : super(painter as Any, contentDescription)

    /**
     * Creates an instance of [OdsChipLeadingIcon].
     *
     * @param imageVector The image vector to draw.
     * @param contentDescription The content description associated to this [OdsChipLeadingIcon].
     */
    constructor(imageVector: ImageVector, contentDescription: String) : super(imageVector as Any, contentDescription)

    /**
     * Creates an instance of [OdsChipLeadingIcon].
     *
     * @param bitmap The image bitmap to draw.
     * @param contentDescription The content description associated to this [OdsChipLeadingIcon].
     */
    constructor(bitmap: ImageBitmap, contentDescription: String) : super(bitmap as Any, contentDescription)
}

/**
 * A leading icon in an [OdsChip].
 */
class OdsChipLeadingAvatar : OdsComponentImage {

    /**
     * Creates an instance of [OdsChipLeadingAvatar].
     *
     * @param painter The painter to draw.
     * @param contentDescription The content description associated to this [OdsChipLeadingAvatar].
     */
    constructor(painter: Painter, contentDescription: String) : super(painter as Any, contentDescription, ContentScale.Crop)

    /**
     * Creates an instance of [OdsChipLeadingAvatar].
     *
     * @param imageVector The image vector to draw.
     * @param contentDescription The content description associated to this [OdsChipLeadingAvatar].
     */
    constructor(imageVector: ImageVector, contentDescription: String) : super(imageVector as Any, contentDescription, ContentScale.Crop)

    /**
     * Creates an instance of [OdsChipLeadingAvatar].
     *
     * @param bitmap The image bitmap to draw.
     * @param contentDescription The content description associated to this [OdsChipLeadingAvatar].
     */
    constructor(bitmap: ImageBitmap, contentDescription: String) : super(bitmap as Any, contentDescription, ContentScale.Crop)

}
