/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.navigationdrawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.orange.ods.R
import com.orange.ods.compose.component.utilities.OdsImageCircleShape
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.text.OdsTextH6
import com.orange.ods.compose.text.OdsTextSubtitle2

@Composable
fun OdsModalDrawerHeader(
    modifier: Modifier = Modifier,
    title: String,
    imageContentDescription: String? = null,
    backgroundImage: Painter? = null,
    subtitle: String? = null,
    avatar: Painter? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(167.dp)
    ) {
        backgroundImage?.let {
            Image(
                painter = it,
                contentDescription = imageContentDescription,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxWidth()
            )
        }
        Column(
            Modifier.padding(start = dimensionResource(id = R.dimen.spacing_m))
        ) {
            avatar?.let { OdsImageCircleShape(painter = it, Modifier.padding(top = 40.dp)) }
            OdsTextH6(text = title, Modifier.padding(top = 30.dp))
            subtitle?.let {
                OdsTextSubtitle2(text = it)
            }
        }
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsVertical() = Preview {
    OdsModalDrawerHeader(
        title = "Title",
        backgroundImage = painterResource(id = R.drawable.placeholder),
        subtitle = "Subtitle"
    )
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsModalDrawerHeader() = Preview {

    OdsModalDrawerHeader(
        title = "Title",
        avatar = painterResource(id = R.drawable.placeholder_small),
        subtitle = "Subtitle"
    )
}