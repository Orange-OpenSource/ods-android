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
import com.orange.ods.compose.component.content.OdsIconBuilder
import com.orange.ods.compose.component.content.OdsImageBuilder

/**
 * A leading icon in an [OdsChip].
 */
class OdsChipLeadingIconBuilder : OdsIconBuilder<Nothing> {

    /**
     * Creates an instance of [OdsChipLeadingIconBuilder].
     *
     * @param painter The painter to draw.
     * @param contentDescription The content description associated to this [OdsChipLeadingIconBuilder].
     */
    constructor(painter: Painter, contentDescription: String) : super(painter, contentDescription)

    /**
     * Creates an instance of [OdsChipLeadingIconBuilder].
     *
     * @param imageVector The image vector to draw.
     * @param contentDescription The content description associated to this [OdsChipLeadingIconBuilder].
     */
    constructor(imageVector: ImageVector, contentDescription: String) : super(imageVector, contentDescription)

    /**
     * Creates an instance of [OdsChipLeadingIconBuilder].
     *
     * @param bitmap The image bitmap to draw.
     * @param contentDescription The content description associated to this [OdsChipLeadingIconBuilder].
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
class OdsChipLeadingAvatarBuilder : OdsImageBuilder<Nothing> {

    /**
     * Creates an instance of [OdsChipLeadingAvatarBuilder].
     *
     * @param painter The painter to draw.
     * @param contentDescription The content description associated to this [OdsChipLeadingAvatarBuilder].
     */
    constructor(painter: Painter, contentDescription: String) : super(painter, contentDescription, contentScale = ContentScale.Crop)

    /**
     * Creates an instance of [OdsChipLeadingAvatarBuilder].
     *
     * @param imageVector The image vector to draw.
     * @param contentDescription The content description associated to this [OdsChipLeadingAvatarBuilder].
     */
    constructor(imageVector: ImageVector, contentDescription: String) : super(imageVector, contentDescription, contentScale = ContentScale.Crop)

    /**
     * Creates an instance of [OdsChipLeadingAvatarBuilder].
     *
     * @param bitmap The image bitmap to draw.
     * @param contentDescription The content description associated to this [OdsChipLeadingAvatarBuilder].
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