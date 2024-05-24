/*
 * Software Name: Orange Design System
 * SPDX-FileCopyrightText: Copyright (c) Orange SA
 * SPDX-License-Identifier: MIT
 *
 * This software is distributed under the MIT license,
 * the text of which is available at https://opensource.org/license/MIT/
 * or see the "LICENSE" file for more details.
 *
 * Software description: Android library of reusable graphical components
 */

package com.orange.ods.compose.component.progressindicator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.orange.ods.compose.component.utilities.OdsPreview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.extension.orElse
import com.orange.ods.compose.text.OdsText
import com.orange.ods.compose.theme.OdsTheme

/**
 * Progress indicators express an unspecified wait time or display the length of a process.
 *
 * @see androidx.compose.material.LinearProgressIndicator
 *
 * @param modifier [Modifier] applied to the progress indicator.
 * @param progress Progress indicator value where 0.0 represents no progress and 1.0 represents full progress. Values outside of this range are coerced
 * into the range. If set to `null`, the progress indicator is indeterminate.
 * @param label Label displayed above the linear progress.
 * @param icon Icon displayed above the progress indicator.
 * @param showCurrentValue Controls the progress indicator current value visibility.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
@OdsComposable
fun OdsLinearProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float? = null,
    label: String? = null,
    icon: OdsLinearProgressIndicator.Icon? = null,
    showCurrentValue: Boolean = false
) {
    Column(
        modifier = modifier
            .padding(horizontal = OdsTheme.spacings.medium)
            .padding(top = OdsTheme.spacings.medium)
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = OdsTheme.spacings.extraSmall),
        ) {
            icon?.Content(modifier = Modifier.padding(end = ButtonDefaults.IconSpacing))

            if (label != null) {
                OdsText(
                    text = label,
                    style = OdsTheme.typography.bodySmall,
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
                    OdsText(
                        modifier = Modifier
                            .padding(top = OdsTheme.spacings.extraSmall)
                            .semantics {
                                this.invisibleToUser() // Prevent TalkBack to focus this Text cause the value of the progress is already read on LinearProgressIndicator focus
                            },
                        text = stringResource(id = R.string.ods_progressLinearIndicator_value, (progress * 100).toInt()),
                        style = OdsTheme.typography.bodySmall
                    )
                }
            }
        }.orElse {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
    }
}

/**
 * Contains classes to build an [com.orange.ods.compose.component.progressindicator.OdsLinearProgressIndicator].
 */
object OdsLinearProgressIndicator {

    /**
     * An icon in an [OdsLinearProgressIndicator].
     * It is a non-clickable button.
     */
    class Icon private constructor(
        val graphicsObject: Any,
        val contentDescription: String
    ) : OdsComponentIcon<Nothing>(Nothing::class.java, graphicsObject, contentDescription) {

        /**
         * Creates an instance of [OdsLinearProgressIndicator.Icon].
         *
         * @param painter Painter of the icon.
         * @param contentDescription The content description associated to this [OdsLinearProgressIndicator.Icon].
         */
        constructor(painter: Painter, contentDescription: String) : this(painter as Any, contentDescription)

        /**
         * Creates an instance of [OdsLinearProgressIndicator.Icon].
         *
         * @param imageVector Image vector of the icon.
         * @param contentDescription The content description associated to this [OdsLinearProgressIndicator.Icon].
         */
        constructor(imageVector: ImageVector, contentDescription: String) : this(imageVector as Any, contentDescription)

        /**
         * Creates an instance of [OdsLinearProgressIndicator.Icon].
         *
         * @param bitmap Image bitmap of the icon.
         * @param contentDescription The content description associated to this [OdsLinearProgressIndicator.Icon].
         */
        constructor(bitmap: ImageBitmap, contentDescription: String) : this(bitmap as Any, contentDescription)

        @Composable
        override fun Content(modifier: Modifier) {
            super.Content(modifier = modifier.size(ButtonDefaults.IconSize))
        }
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsLinearProgressIndicator(@PreviewParameter(OdsLinearProgressIndicatorPreviewParameterProvider::class) parameter: OdsLinearProgressIndicatorPreviewParameter) =
    OdsPreview {
        with(parameter) {
            OdsLinearProgressIndicator(
                progress = progress,
                icon = iconRes?.let { OdsLinearProgressIndicator.Icon(painterResource(id = it), "") },
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
            OdsLinearProgressIndicatorPreviewParameter(0.40f, null, longLabel, false),
        )
    }