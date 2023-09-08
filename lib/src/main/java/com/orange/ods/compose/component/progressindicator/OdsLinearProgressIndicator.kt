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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.invisibleToUser
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.content.OdsComponentIcon
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.text.OdsTextCaption
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.extension.orElse

/**
 * Progress indicators express an unspecified wait time or display the length of a process.
 *
 * @see androidx.compose.material.LinearProgressIndicator
 *
 * @param modifier The modifier applied to this progress indicator
 * @param progress The value of this progress indicator, where 0.0 represents no progress and 1.0
 * represents full progress. Values outside of this range are coerced into the range. If set to `null`,
 * the progress indicator is indeterminate.
 * @param label The label displayed above the linear progress
 * @param icon The icon displayed above the linear progress
 * @param showCurrentValue Indicates whether the current value is displayed
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
@OdsComposable
fun OdsLinearProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float? = null,
    label: String? = null,
    icon: OdsLinearProgressIndicatorIcon? = null,
    showCurrentValue: Boolean = false
) {
    Column(
        modifier = modifier
            .padding(horizontal = dimensionResource(id = R.dimen.spacing_m))
            .padding(top = dimensionResource(id = R.dimen.spacing_m))
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen.spacing_xs)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_s))
        ) {
            icon?.let { icon.Content() }
            if (label != null) {
                Text(
                    text = label,
                    style = OdsTheme.typography.caption,
                    color = OdsTheme.colors.onSurface,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
        progress?.let {
            LinearProgressIndicator(progress = progress, modifier = Modifier.fillMaxWidth())

            if (showCurrentValue) {
                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                    OdsTextCaption(
                        modifier = Modifier
                            .padding(top = dimensionResource(id = R.dimen.spacing_xs))
                            .semantics {
                                this.invisibleToUser() // Prevent TalkBack to focus this Text cause the value of the progress is already read on LinearProgressIndicator focus
                            },
                        text = String.format(stringResource(id = R.string.progress_linear_indicator_value), (progress * 100).toInt())
                    )
                }
            }
        }.orElse {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
    }
}

/**
 * An icon in an [OdsLinearProgressIndicator].
 * It is a non-clickable button.
 */
class OdsLinearProgressIndicatorIcon : OdsComponentIcon {

    /**
     * Creates an instance of [OdsLinearProgressIndicatorIcon].
     *
     * @param painter Painter of the icon.
     * @param contentDescription The content description associated to this [OdsLinearProgressIndicatorIcon].
     */
    constructor(painter: Painter, contentDescription: String) : super(painter as Any, contentDescription)

    /**
     * Creates an instance of [OdsLinearProgressIndicatorIcon].
     *
     * @param imageVector Image vector of the icon.
     * @param contentDescription The content description associated to this [OdsLinearProgressIndicatorIcon].
     */
    constructor(imageVector: ImageVector, contentDescription: String) : super(imageVector as Any, contentDescription)

    /**
     * Creates an instance of [OdsLinearProgressIndicatorIcon].
     *
     * @param bitmap Image bitmap of the icon.
     * @param contentDescription The content description associated to this [OdsLinearProgressIndicatorIcon].
     */
    constructor(bitmap: ImageBitmap, contentDescription: String) : super(bitmap as Any, contentDescription)

    @Composable
    override fun Content(modifier: Modifier) {
        super.Content(modifier = modifier.size(ButtonDefaults.IconSize))
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsLinearProgressIndicator(@PreviewParameter(OdsLinearProgressIndicatorPreviewParameterProvider::class) parameter: OdsLinearProgressIndicatorPreviewParameter) =
    Preview {
        with(parameter) {
            OdsLinearProgressIndicator(
                progress = progress,
                icon = iconRes?.let { OdsLinearProgressIndicatorIcon(painterResource(id = it), "") },
                label = label,
                showCurrentValue = showCurrentValue
            )
        }
    }

private data class OdsLinearProgressIndicatorPreviewParameter(
    val progress: Float?,
    val iconRes: Int?,
    val label: String?,
    val showCurrentValue: Boolean
)

private class OdsLinearProgressIndicatorPreviewParameterProvider :
    BasicPreviewParameterProvider<OdsLinearProgressIndicatorPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OdsLinearProgressIndicatorPreviewParameter>
    get() {
        val iconRes = android.R.drawable.ic_dialog_alert
        val shortLabel = "Downloading …"
        val longLabel = "Downloading file Applications/ODS/app/src/main/java/utilities/file_to_download.txt"

        return listOf(
            OdsLinearProgressIndicatorPreviewParameter(0.75f, iconRes, shortLabel, true),
            OdsLinearProgressIndicatorPreviewParameter(0.75f, iconRes, longLabel, false),
        )
    }