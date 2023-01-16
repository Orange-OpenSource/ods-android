/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.progressindicator

import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ods.compose.component.OdsComponentApi
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.utilities.extension.orElse

/**
 * Progress indicators express an unspecified wait time or display the length of a process.
 *
 * @see androidx.compose.material.LinearProgressIndicator
 *
 * @param modifier The modifier applied to this progress indicator
 * @param progress The progress of this progress indicator, where 0.0 represents no progress and 1.0
 * represents full progress. Values outside of this range are coerced into the range. If set to `null`,
 * the progress indicator is indeterminate.
 */
@Composable
@OdsComponentApi
fun OdsLinearProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float? = null,
) {
    val progressIndicatorColor = OdsTheme.colors.primary
    progress?.let {
        LinearProgressIndicator(progress = progress, modifier = modifier, color = progressIndicatorColor)
    }.orElse {
        LinearProgressIndicator(modifier = modifier, color = progressIndicatorColor)
    }
}

@UiModePreviews.Default
@Composable
fun PreviewOdsLinearProgressIndicator(@PreviewParameter(OdsLinearProgressIndicatorPreviewParameterProvider::class) progress: Float?) = Preview {
    OdsLinearProgressIndicator(progress = progress)
}

private class OdsLinearProgressIndicatorPreviewParameterProvider : BasicPreviewParameterProvider<Float?>(0.75f, null)
