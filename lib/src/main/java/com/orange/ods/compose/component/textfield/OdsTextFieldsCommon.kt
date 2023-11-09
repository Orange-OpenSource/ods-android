/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.textfield

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import com.orange.ods.R
import com.orange.ods.compose.component.button.OdsIconButton
import com.orange.ods.compose.component.button.OdsIconButtonIconBuilder
import com.orange.ods.compose.component.content.OdsComponentBuilder
import com.orange.ods.compose.component.content.OdsIconBuilder
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.text.OdsTextCaption
import com.orange.ods.compose.theme.OdsTheme

/**
 * A character counter to display below a text field.
 *
 * @property characterCount Text field current characters count.
 * @property maxCharacterCount Maximum number of characters to display in the counter. Note: the limitation behavior should be managed by yourself
 * in the `onValueChange` method of the text field.
 * @property enabled Controls the enable state of the counter. If set to `false` the text will be displayed in disabled color.
 */
class OdsTextFieldCharacterCounterBuilder(private val characterCount: Int, private val maxCharacterCount: Int, private val enabled: Boolean = true) :
    OdsComponentBuilder<Nothing>() {

    @Composable
    override fun Content(modifier: Modifier) {
        OdsTextCaption(
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.spacing_xs), end = dimensionResource(id = R.dimen.spacing_m)),
            text = "$characterCount/$maxCharacterCount",
            enabled = enabled
        )
    }
}

/**
 * A leading icon in an [OdsTextField].
 */
class OdsTextFieldLeadingIconBuilder : OdsIconBuilder<OdsTextFieldLeadingIconBuilder.ExtraParameters> {

    data class ExtraParameters(
        val enabled: Boolean
    ) : OdsComponentBuilder.ExtraParameters()

    /**
     * Creates an instance of [OdsTextFieldLeadingIconBuilder].
     *
     * @param painter Painter of the icon.
     * @param contentDescription The content description associated to this [OdsTextFieldLeadingIconBuilder].
     * @param onClick Callback invoked on icon click.
     */
    constructor(painter: Painter, contentDescription: String, onClick: (() -> Unit)? = null) : super(painter, contentDescription, onClick = onClick)

    /**
     * Creates an instance of [OdsTextFieldLeadingIconBuilder].
     *
     * @param imageVector Image vector of the icon.
     * @param contentDescription The content description associated to this [OdsTextFieldLeadingIconBuilder].
     * @param onClick Callback invoked on icon click.
     */
    constructor(imageVector: ImageVector, contentDescription: String, onClick: (() -> Unit)? = null) : super(imageVector, contentDescription, onClick = onClick)

    /**
     * Creates an instance of [OdsTextFieldLeadingIconBuilder].
     *
     * @param bitmap Image bitmap of the icon.
     * @param contentDescription The content description associated to this [OdsTextFieldLeadingIconBuilder].
     * @param onClick Callback invoked on icon click.
     */
    constructor(bitmap: ImageBitmap, contentDescription: String, onClick: (() -> Unit)? = null) : super(bitmap, contentDescription, onClick = onClick)

    @Composable
    override fun Content(modifier: Modifier) {
        enabled = extraParameters.enabled
        super.Content(modifier)
    }
}

sealed interface OdsTextFieldTrailingBuilder {
    data class ExtraParameters(
        val enabled: Boolean,
        val isTextFieldEmpty: Boolean
    ) : OdsComponentBuilder.ExtraParameters()
}

class OdsTextFieldTrailingTextBuilder(val text: String) : OdsTextFieldTrailingBuilder, OdsComponentBuilder<OdsTextFieldTrailingBuilder.ExtraParameters>() {
    @Composable
    override fun Content(modifier: Modifier) {
        Text(
            modifier = Modifier.padding(end = dimensionResource(id = R.dimen.spacing_s)),
            text = text,
            style = OdsTheme.typography.subtitle1,
            color = OdsTextFieldDefaults.trailingTextColor(extraParameters.isTextFieldEmpty, extraParameters.enabled)
        )
    }
}

class OdsTextFieldTrailingIconBuilder : OdsTextFieldTrailingBuilder, OdsIconBuilder<OdsTextFieldTrailingBuilder.ExtraParameters> {

    /**
     * Creates an instance of [OdsTextFieldTrailingIconBuilder].
     *
     * @param painter Painter of the icon.
     * @param contentDescription The content description associated to this [OdsTextFieldTrailingIconBuilder].
     * @param onClick Callback invoked on icon click.
     */
    constructor(painter: Painter, contentDescription: String, onClick: (() -> Unit)? = null) : super(painter, contentDescription, onClick = onClick)

    /**
     * Creates an instance of [OdsTextFieldTrailingIconBuilder].
     *
     * @param imageVector Image vector of the icon.
     * @param contentDescription The content description associated to this [OdsTextFieldTrailingIconBuilder].
     * @param onClick Callback invoked on icon click.
     */
    constructor(imageVector: ImageVector, contentDescription: String, onClick: (() -> Unit)? = null) : super(imageVector, contentDescription, onClick = onClick)

    /**
     * Creates an instance of [OdsTextFieldTrailingIconBuilder].
     *
     * @param bitmap Image bitmap of the icon.
     * @param contentDescription The content description associated to this [OdsTextFieldTrailingIconBuilder].
     * @param onClick Callback invoked on icon click.
     */
    constructor(bitmap: ImageBitmap, contentDescription: String, onClick: (() -> Unit)? = null) : super(bitmap, contentDescription, onClick = onClick)

    @Composable
    override fun Content(modifier: Modifier) {
        enabled = extraParameters.enabled
        super.Content(modifier)
    }
}

internal class OdsExposedDropdownMenuTrailingBuilder(val expanded: Boolean) : OdsTextFieldTrailingBuilder,
    OdsComponentBuilder<OdsTextFieldTrailingBuilder.ExtraParameters>() {
    @Composable
    override fun Content(modifier: Modifier) {
        val degrees = if (expanded && extraParameters.enabled) 180f else 0f
        Box(modifier = Modifier.rotate(degrees)) {
            OdsTextFieldIcon(
                painter = rememberVectorPainter(image = Icons.Filled.ArrowDropDown),
                contentDescription = null,
                onClick = if (extraParameters.enabled) {
                    {}
                } else {
                    null
                }
            )
        }
    }

}

@Composable
internal fun OdsTextFieldBottomRow(isError: Boolean, errorMessage: String?, characterCounter: OdsTextFieldCharacterCounterBuilder?) {
    Row {
        Box(modifier = Modifier.weight(1f)) {
            if (isError && errorMessage != null) {
                OdsTextFieldErrorText(message = errorMessage)
            }
        }
        characterCounter?.Content()
    }
}

@Composable
internal fun OdsTextFieldIcon(painter: Painter, contentDescription: String?, onClick: (() -> Unit)?) {
    OdsIconButton(
        icon = OdsIconButtonIconBuilder(
            painter = painter, contentDescription = contentDescription.orEmpty()
        ),
        enabled = onClick != null,
        onClick = onClick ?: {})
}

@Composable
private fun OdsTextFieldErrorText(message: String) {
    Text(
        modifier = Modifier.padding(
            start = dimensionResource(id = R.dimen.spacing_m),
            top = dimensionResource(id = R.dimen.spacing_xs)
        ),
        text = message,
        style = OdsTheme.typography.caption,
        color = OdsTheme.colors.error
    )
}

@Composable
internal fun trailingPreview(parameter: OdsTextFieldPreviewParameter): OdsTextFieldTrailingBuilder? = when (parameter.previewTrailingType) {
    OdsTextFieldTrailingTextBuilder::class.java -> OdsTextFieldTrailingTextBuilder(text = "units")
    OdsTextFieldTrailingIconBuilder::class.java -> OdsTextFieldTrailingIconBuilder(painter = painterResource(id = android.R.drawable.ic_input_add), "")
    OdsExposedDropdownMenuTrailingBuilder::class.java -> OdsExposedDropdownMenuTrailingBuilder(expanded = false)
    else -> null
}

internal data class OdsTextFieldPreviewParameter(
    val hasCounter: Boolean,
    val hasErrorMessage: Boolean,
    val isVeryLongErrorMessage: Boolean,
    val previewTrailingType: Class<out OdsTextFieldTrailingBuilder>?
)

internal class OdsTextFieldPreviewParameterProvider : BasicPreviewParameterProvider<OdsTextFieldPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OdsTextFieldPreviewParameter>
    get() {
        val booleanValues = listOf(true, false)
        val trailings = listOf(
            null,
            OdsTextFieldTrailingTextBuilder::class.java,
            OdsTextFieldTrailingIconBuilder::class.java,
            OdsExposedDropdownMenuTrailingBuilder::class.java
        )

        return booleanValues.flatMap { hasCounter ->
            booleanValues.flatMap { hasErrorMessage ->
                booleanValues.flatMap { isVeryLongErrorMessage ->
                    trailings.map { trailing ->
                        OdsTextFieldPreviewParameter(
                            hasCounter = hasCounter,
                            hasErrorMessage = hasErrorMessage,
                            isVeryLongErrorMessage = isVeryLongErrorMessage,
                            previewTrailingType = trailing
                        )
                    }
                }
            }
        }
    }
