/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.other

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.orange.ods.R

/**
 * Displays in a disc a 40x40 icon
 *
 * @param modifier Modifier applied to the image
 * @param imageRes Resource identifier for the image
 * @param contentDescription Content description of the image
 */
@Composable
fun OdsImageCircleShape(modifier: Modifier = Modifier, @DrawableRes imageRes: Int, contentDescription: String? = null) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(dimensionResource(id = R.dimen.image_circle_shape_size))
            .clip(CircleShape),
    )
}