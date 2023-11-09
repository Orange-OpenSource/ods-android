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
 * Contains classes to build an [com.orange.ods.compose.component.chip.OdsChip] or an [com.orange.ods.compose.component.chip.OdsFilterChip].
 */
class OdsChip {

    /**
     * A leading icon in an [OdsChip].
     */
    class LeadingIcon : OdsComponentIcon<Nothing> {

        /**
         * Creates an instance of [OdsChip.LeadingIcon].
         *
         * @param painter The painter to draw.
         * @param contentDescription The content description associated to this [OdsChip.LeadingIcon].
         */
        constructor(painter: Painter, contentDescription: String) : super(painter, contentDescription)

        /**
         * Creates an instance of [OdsChip.LeadingIcon].
         *
         * @param imageVector The image vector to draw.
         * @param contentDescription The content description associated to this [OdsChip.LeadingIcon].
         */
        constructor(imageVector: ImageVector, contentDescription: String) : super(imageVector, contentDescription)

        /**
         * Creates an instance of [OdsChip.LeadingIcon].
         *
         * @param bitmap The image bitmap to draw.
         * @param contentDescription The content description associated to this [OdsChip.LeadingIcon].
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
    class LeadingAvatar : OdsComponentImage<Nothing> {

        /**
         * Creates an instance of [OdsChip.LeadingAvatar].
         *
         * @param painter The painter to draw.
         * @param contentDescription The content description associated to this [OdsChip.LeadingAvatar].
         */
        constructor(painter: Painter, contentDescription: String) : super(painter, contentDescription, contentScale = ContentScale.Crop)

        /**
         * Creates an instance of [OdsChip.LeadingAvatar].
         *
         * @param imageVector The image vector to draw.
         * @param contentDescription The content description associated to this [OdsChip.LeadingAvatar].
         */
        constructor(imageVector: ImageVector, contentDescription: String) : super(imageVector, contentDescription, contentScale = ContentScale.Crop)

        /**
         * Creates an instance of [OdsChip.LeadingAvatar].
         *
         * @param bitmap The image bitmap to draw.
         * @param contentDescription The content description associated to this [OdsChip.LeadingAvatar].
         */
        constructor(bitmap: ImageBitmap, contentDescription: String) : super(bitmap, contentDescription, contentScale = ContentScale.Crop)

        @Composable
        fun Content(enabled: Boolean) {
            this.Content(modifier = Modifier.odsChipAvatar(enabled))
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
internal fun Modifier.odsChipAvatar(enabled: Boolean): Modifier = composed {
    this
        .size(dimensionResource(R.dimen.icon_size))
        .clip(CircleShape)
        .alpha(if (enabled) 1f else ChipDefaults.LeadingIconOpacity)
}