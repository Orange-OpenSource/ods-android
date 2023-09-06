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

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import com.orange.ods.R
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
 * A leading avatar in an [OdsChip].
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

@OptIn(ExperimentalMaterialApi::class)
internal fun Modifier.odsChipAvatar(enabled: Boolean): Modifier = composed {
    this
        .size(dimensionResource(R.dimen.icon_size))
        .clip(CircleShape)
        .alpha(if (enabled) 1f else ChipDefaults.LeadingIconOpacity)
}