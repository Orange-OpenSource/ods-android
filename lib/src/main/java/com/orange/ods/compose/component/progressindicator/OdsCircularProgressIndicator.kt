/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT licence,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components 
 */

package com.orange.ods.compose.component.progressindicator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.text.OdsTextCaption
import com.orange.ods.extension.orElse


/**
 * Progress indicators express an unspecified wait time or display the length of a process.
 *
 * @see androidx.compose.material.CircularProgressIndicator
 *
 * @param modifier [Modifier] applied to the progress indicator.
 * @param progress Progress indicator value where 0.0 represents no progress and 1.0 represents full progress. Values outside of this range are coerced
 * into the range. If set to `null`, the progress indicator is indeterminate.
 * @param label Label displayed below the circular progress.
 */
@Composable
@OdsComposable
fun OdsCircularProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float? = null,
    label: String? = null
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier)
    {
        progress?.let {
            CircularProgressIndicator(
                progress = progress,
                modifier = modifier
            )

        }.orElse {
            CircularProgressIndicator(modifier = modifier)
        }
        if (label != null) {
            OdsTextCaption(
                text = label,
                modifier = Modifier
                    .padding(top = dimensionResource(id = R.dimen.spacing_s))
            )
        }
    }
}


@UiModePreviews.Default
@Composable
private fun PreviewOdsCircularProgressIndicator(@PreviewParameter(OdsCircularProgressIndicatorPreviewParameterProvider::class) progress: Float?) = Preview {
    OdsCircularProgressIndicator(progress = progress, label = "Downloading …")
}

private class OdsCircularProgressIndicatorPreviewParameterProvider : BasicPreviewParameterProvider<Float?>(0.75f, null)
