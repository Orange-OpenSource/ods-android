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
import androidx.compose.runtime.Composable
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
class OdsChipLeadingIcon : OdsComponentIcon<Nothing> {

    /**
     * Creates an instance of [OdsChipLeadingIcon].
     *
     * @param painter The painter to draw.
     * @param contentDescription The content description associated to this [OdsChipLeadingIcon].
     */
    constructor(painter: Painter, contentDescription: String) : super(painter, contentDescription)

    /**
     * Creates an instance of [OdsChipLeadingIcon].
     *
     * @param imageVector The image vector to draw.
     * @param contentDescription The content description associated to this [OdsChipLeadingIcon].
     */
    constructor(imageVector: ImageVector, contentDescription: String) : super(imageVector, contentDescription)

    /**
     * Creates an instance of [OdsChipLeadingIcon].
     *
     * @param bitmap The image bitmap to draw.
     * @param contentDescription The content description associated to this [OdsChipLeadingIcon].
     */
    constructor(bitmap: ImageBitmap, contentDescription: String) : super(bitmap, contentDescription)

    @Composable
    override fun Content(modifier: Modifier) {
        super.Content(modifier.size(dimensionResource(id = R.dimen.chip_icon_size)))
    }
}

/**
 * A leading avatar in an [OdsChip].
 */
class OdsChipLeadingAvatar : OdsComponentImage<Nothing> {

    /**
     * Creates an instance of [OdsChipLeadingAvatar].
     *
     * @param painter The painter to draw.
     * @param contentDescription The content description associated to this [OdsChipLeadingAvatar].
     */
    constructor(painter: Painter, contentDescription: String) : super(painter, contentDescription, contentScale = ContentScale.Crop)

    /**
     * Creates an instance of [OdsChipLeadingAvatar].
     *
     * @param imageVector The image vector to draw.
     * @param contentDescription The content description associated to this [OdsChipLeadingAvatar].
     */
    constructor(imageVector: ImageVector, contentDescription: String) : super(imageVector, contentDescription, contentScale = ContentScale.Crop)

    /**
     * Creates an instance of [OdsChipLeadingAvatar].
     *
     * @param bitmap The image bitmap to draw.
     * @param contentDescription The content description associated to this [OdsChipLeadingAvatar].
     */
    constructor(bitmap: ImageBitmap, contentDescription: String) : super(bitmap, contentDescription, contentScale = ContentScale.Crop)

    @Composable
    fun Content(enabled: Boolean) {
        this.Content(modifier = Modifier.odsChipAvatar(enabled))
    }
    
}

@OptIn(ExperimentalMaterialApi::class)
internal fun Modifier.odsChipAvatar(enabled: Boolean): Modifier = composed {
    this
        .size(dimensionResource(R.dimen.icon_size))
        .clip(CircleShape)
        .alpha(if (enabled) 1f else ChipDefaults.LeadingIconOpacity)
}