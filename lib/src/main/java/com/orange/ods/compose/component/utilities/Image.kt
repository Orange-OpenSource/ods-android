/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.utilities

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.orange.ods.R

/**
 * Displays an image in a disc
 *
 * @param painter to draw
 * @param modifier Modifier applied to the image
 * @param contentDescription Content description of the image
 * @param circleSize The size of the final image, 40x40 by default
 * @param alpha Optional opacity to be applied to the Painter when it is rendered onscreen the default renders the Painter completely opaque
 */
@Composable
fun OdsImageCircleShape(
    painter: Painter,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    circleSize: Dp = dimensionResource(id = R.dimen.avatar_size),
    alpha: Float = DefaultAlpha
) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(circleSize)
            .clip(CircleShape),
        alpha = alpha
    )
}