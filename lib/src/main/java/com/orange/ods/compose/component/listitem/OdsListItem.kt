/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.listitem

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.content.OdsComponentCircularImage
import com.orange.ods.compose.component.content.OdsComponentContent
import com.orange.ods.compose.component.content.OdsComponentIcon
import com.orange.ods.compose.component.content.OdsComponentImage
import com.orange.ods.compose.component.control.OdsCheckbox
import com.orange.ods.compose.component.control.OdsRadioButton
import com.orange.ods.compose.component.control.OdsSwitch
import com.orange.ods.compose.component.divider.OdsDivider
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.text.OdsTextCaption
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.extension.isNotNullOrBlank
import com.orange.ods.extension.orElse

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/09a804-lists/b/669743" target="_blank">ODS Lists</a>.
 *
 * Displays a list item with one or two lines of text: A mandatory primary text and an optional subtitle on one line.
 * It allows you to display a Checkbox, a Switch, a RadioButton, an Icon or a Caption text as trailing element. If this does not meet your
 * needs, you can use the other [OdsListItem] signature which accept any Composable as trailing.
 *
 * @param text The primary text of the list item.
 * @param modifier [Modifier] applied to the list item.
 * @param leadingIcon The leading supporting visual of the list item.
 * @param subtitle Text displayed on one line below the primary text of the list item.
 * @param overlineText The text displayed above the primary text.
 * @param trailing The trailing content to display at the end of the list item.
 * @param divider Whether or not a divider is displayed at the bottom of the list item.
 * @param onClick Will be called when the user clicks the list item. This parameter only has an effect if [trailing] is [OdsListItem.TrailingIcon] or `null`.
 */
@Composable
@OdsComposable
fun OdsListItem(
    text: String,
    modifier: Modifier = Modifier,
    leadingIcon: OdsListItem.LeadingIcon? = null,
    subtitle: String? = null,
    overlineText: String? = null,
    trailing: OdsListItem.Trailing? = null,
    divider: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    OdsListItem(
        text = text,
        textColor = OdsTheme.colors.onSurface,
        textStyle = OdsTheme.typography.subtitle1,
        modifier = modifier,
        leadingIcon = leadingIcon,
        secondaryText = subtitle,
        singleLineSecondaryText = true,
        overlineText = overlineText,
        trailing = trailing,
        divider = divider,
        onClick = onClick
    )
}

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/09a804-lists/b/669743" target="_blank">ODS Lists</a>.
 *
 * Displays a list item with two or three lines of text: A mandatory primary text and description which can be on one or two lines.
 * This composable allows you to display a Checkbox, a Switch, a RadioButton, an Icon or a Caption text as trailing element. If this does not meet your
 * needs, you can use the other [OdsListItem] signature which accept any Composable as trailing.
 *
 * @param text The primary text of the list item.
 * @param modifier [Modifier] applied to the list item.
 * @param leadingIcon The leading supporting visual of the list item.
 * @param description Text displayed on two lines max below the primary text of the list item.
 * @param overlineText The text displayed above the primary text.
 * @param trailing The trailing content to display at the end of the list item.
 * @param divider Whether or not a divider is displayed at the bottom of the list item.
 * @param onClick Will be called when the user clicks the list item. This parameter only has an effect if [trailing] is [OdsListItem.TrailingIcon] or `null`.
 */
@Composable
@OdsComposable
fun OdsListItem(
    text: String,
    description: String,
    modifier: Modifier = Modifier,
    leadingIcon: OdsListItem.LeadingIcon? = null,
    overlineText: String? = null,
    trailing: OdsListItem.Trailing? = null,
    divider: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    OdsListItem(
        text = text,
        textColor = OdsTheme.colors.onSurface,
        textStyle = OdsTheme.typography.subtitle1,
        modifier = modifier,
        leadingIcon = leadingIcon,
        secondaryText = description,
        singleLineSecondaryText = false,
        overlineText = overlineText,
        trailing = trailing,
        divider = divider,
        onClick = onClick
    )
}

@Composable
internal fun OdsListItem(
    text: String,
    textColor: Color,
    textStyle: TextStyle,
    modifier: Modifier = Modifier,
    leadingIcon: OdsListItem.LeadingIcon? = null,
    secondaryText: String? = null,
    singleLineSecondaryText: Boolean = true,
    overlineText: String? = null,
    trailing: OdsListItem.Trailing? = null,
    divider: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    val context = LocalContext.current
    val rootModifier = modifier.rootModifier(context, trailing, onClick)
    if (leadingIcon?.iconType == OdsListItem.LeadingIcon.Type.WideImage) {
        Row(modifier = rootModifier, verticalAlignment = Alignment.CenterVertically) {
            leadingIcon.Content()
            OdsListItemInternal(
                text = text,
                textColor = textColor,
                textStyle = textStyle,
                modifier = Modifier.weight(1f),
                leadingIcon = null,
                secondaryText = secondaryText,
                singleLineSecondaryText = singleLineSecondaryText,
                overlineText = overlineText,
                trailing = trailing
            )
        }
    } else {
        OdsListItemInternal(
            text = text,
            textColor = textColor,
            textStyle = textStyle,
            modifier = rootModifier,
            leadingIcon = leadingIcon,
            secondaryText = secondaryText,
            singleLineSecondaryText = singleLineSecondaryText,
            overlineText = overlineText,
            trailing = trailing
        )
    }

    if (divider) {
        OdsDivider(startIndent = getDividerStartIndent(leadingIcon?.iconType))
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun OdsListItemInternal(
    text: String,
    textColor: Color,
    textStyle: TextStyle,
    modifier: Modifier = Modifier,
    leadingIcon: OdsListItem.LeadingIcon? = null,
    secondaryText: String? = null,
    singleLineSecondaryText: Boolean = true,
    overlineText: String? = null,
    trailing: OdsListItem.Trailing? = null
) {
    val hasText = text.isNotBlank()
    val secondaryTextLinesNumber = if (singleLineSecondaryText || (overlineText != null && secondaryText != null)) 1 else 2

    ListItem(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth(),
        icon = leadingIcon?.let { { it.Content() } },
        secondaryText = if (secondaryText.isNotNullOrBlank()) {
            {
                Text(
                    text = secondaryText,
                    style = OdsTheme.typography.body2,
                    maxLines = secondaryTextLinesNumber,
                    overflow = TextOverflow.Ellipsis,
                    color = OdsTheme.colors.onSurface
                )
            }
        } else null,
        singleLineSecondaryText = singleLineSecondaryText,
        overlineText = if (overlineText.isNotNullOrBlank()) {
            { Text(text = overlineText, style = OdsTheme.typography.overline, color = OdsTheme.colors.onSurface.copy(alpha = 0.6f)) }
        } else null,
        trailing = (trailing as? OdsComponentContent<*>)?.let { { it.Content() } },
        text = {
            if (hasText) {
                Text(text = text, style = textStyle, color = textColor)
            }
        }
    )
}

private fun Modifier.rootModifier(context: Context, trailing: OdsListItem.Trailing?, onListItemClick: (() -> Unit)?): Modifier {
    return with(trailing) {
        when {
            this is OdsListItem.TrailingCheckbox && onCheckedChange != null -> toggleable(
                value = checked,
                role = Role.Checkbox,
                enabled = enabled,
                onValueChange = onCheckedChange
            ).semantics {
                stateDescription = if (checked) context.getString(R.string.state_checked) else context.getString(R.string.state_unchecked)
            }
            this is OdsListItem.TrailingRadioButton && onClick != null -> selectable(
                selected = selected,
                role = Role.RadioButton,
                enabled = enabled,
                onClick = onClick
            ).semantics {
                stateDescription = if (selected) context.getString(R.string.state_selected) else context.getString(R.string.state_unselected)
            }
            this is OdsListItem.TrailingSwitch && onCheckedChange != null -> toggleable(
                value = checked,
                role = Role.Switch,
                enabled = enabled,
                onValueChange = onCheckedChange
            ).semantics {
                stateDescription = if (checked) context.getString(R.string.state_on) else context.getString(R.string.state_off)
            }
            else -> onListItemClick?.let { clickable(onClick = it) }.orElse { this@rootModifier }
        }
    }
}

/**
 * Contains classes to build an [com.orange.ods.compose.component.listitem.OdsListItem].
 */
object OdsListItem {

    /**
     * A leading icon in an [OdsListItem].
     */
    class LeadingIcon private constructor(
        internal val iconType: Type,
        private val graphicsObject: Any,
        private val contentDescription: String,
        tint: Color?
    ) : OdsComponentContent<Nothing>() {

        /**
         * Creates an instance of [OdsListItem.LeadingIcon].
         *
         * @param type The type of icon.
         * @param painter Painter of the icon.
         * @param contentDescription The content description associated to this [OdsListItem.LeadingIcon].
         */
        constructor(type: Type, painter: Painter, contentDescription: String) : this(type, painter as Any, contentDescription, null)

        /**
         * Creates an instance of [OdsListItem.LeadingIcon].
         *
         * @param type The type of icon.
         * @param imageVector Image vector of the icon.
         * @param contentDescription The content description associated to this [OdsListItem.LeadingIcon].
         */
        constructor(type: Type, imageVector: ImageVector, contentDescription: String) : this(type, imageVector as Any, contentDescription, null)

        /**
         * Creates an instance of [OdsListItem.LeadingIcon].
         *
         * @param type The type of icon.
         * @param bitmap Image bitmap of the icon.
         * @param contentDescription The content description associated to this [OdsListItem.LeadingIcon].
         */
        constructor(type: Type, bitmap: ImageBitmap, contentDescription: String) : this(type, bitmap as Any, contentDescription, null)

        internal constructor (painter: Painter, contentDescription: String, tint: Color?) : this(
            Type.Icon,
            painter as Any,
            contentDescription,
            tint
        )

        /**
         * Represents the various types of icon that can be displayed in an [OdsListItem].
         */
        enum class Type {

            /** A standard icon. */
            Icon,

            /** An image cropped into a circle. */
            CircularImage,

            /** An image cropped into a square. */
            SquareImage,

            /** An image cropped into a rectangle. */
            WideImage
        }

        @Composable
        override fun Content(modifier: Modifier) {
            icon.Content(modifier = modifier)
        }

        private val icon = when (iconType) {
            Type.Icon -> getIcon(tint)
            Type.CircularImage -> getCircularImage()
            Type.SquareImage -> getSquareImage()
            Type.WideImage -> getWideImage()
        }

        private fun getIcon(tint: Color?): OdsComponentContent<Nothing> {
            return object : OdsComponentIcon<Nothing>(graphicsObject, contentDescription) {
                override val tint: Color?
                    @Composable
                    get() = tint.orElse { OdsTheme.colors.onSurface }

                @Composable
                override fun Content(modifier: Modifier) {
                    Column(modifier = modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                        super.Content(modifier = Modifier)
                    }
                }
            }
        }

        private fun getCircularImage(): OdsComponentContent<Nothing> {
            return object : OdsComponentCircularImage(graphicsObject, contentDescription) {}
        }

        private fun getSquareImage(): OdsComponentContent<Nothing> {
            return object : OdsComponentImage<Nothing>(graphicsObject, contentDescription, contentScale = ContentScale.Crop) {
                @Composable
                override fun Content(modifier: Modifier) {
                    super.Content(
                        modifier = modifier
                            .size(dimensionResource(id = R.dimen.list_square_image_size))
                            .clip(MaterialTheme.shapes.medium)
                    )
                }
            }
        }

        private fun getWideImage(): OdsComponentContent<Nothing> {
            return object : OdsComponentImage<Nothing>(graphicsObject, contentDescription, contentScale = ContentScale.Crop) {
                @Composable
                override fun Content(modifier: Modifier) {
                    super.Content(
                        modifier = modifier
                            .height(dimensionResource(id = R.dimen.list_wide_image_height))
                            .width(dimensionResource(id = R.dimen.list_wide_image_width))
                    )
                }
            }
        }
    }

    /**
     * A trailing content in an [OdsListItem].
     */
    sealed interface Trailing

    /**
     * A trailing checkbox in an [OdsListItem].
     *
     * @constructor Creates an instance of [OdsListItem.TrailingCheckbox].
     * @param checked Whether Checkbox is checked or unchecked.
     * @param onCheckedChange Callback to be invoked when checkbox is being clicked,
     * therefore the change of checked state in requested.  If null, then this is passive.
     * and relies entirely on a higher-level component to control the "checked" state.
     * @param enabled Whether the component is enabled or grayed out.
     */
    class TrailingCheckbox(
        internal val checked: Boolean,
        internal val onCheckedChange: ((Boolean) -> Unit)?,
        internal val enabled: Boolean = true
    ) : OdsComponentContent<Nothing>(), Trailing {

        @Composable
        override fun Content(modifier: Modifier) {
            OdsCheckbox(
                modifier = modifier,
                checked = checked,
                onCheckedChange = null,
                enabled = enabled
            ) // a11y: `onCheckedChange` is done on list item row click
        }
    }

    /**
     * A trailing switch in an [OdsListItem].
     *
     * @constructor Creates an instance of [OdsListItem.TrailingSwitch].
     * @param checked Whether or not this component is checked.
     * @param onCheckedChange Callback to be invoked when Switch is being clicked,
     * therefore the change of checked state is requested.  If null, then this is passive.
     * and relies entirely on a higher-level component to control the "checked" state.
     * @param enabled Whether the component is enabled or grayed out.
     */
    class TrailingSwitch(
        internal val checked: Boolean,
        internal val onCheckedChange: ((Boolean) -> Unit)?,
        internal val enabled: Boolean = true
    ) : OdsComponentContent<Nothing>(), Trailing {

        @Composable
        override fun Content(modifier: Modifier) {
            OdsSwitch(
                modifier = modifier,
                checked = checked,
                onCheckedChange = null,
                enabled = enabled
            ) // a11y: `onCheckedChange` is done on list item row click
        }
    }

    /**
     * A trailing radio button in an [OdsListItem].
     *
     * @constructor Creates an instance of [OdsListItem.TrailingRadioButton].
     * @param selected Whether this radio button is selected or not.
     * @param onClick Callback to be invoked when the radio button is clicked. If null, then this
     * radio button will not handle input events, and only act as a visual indicator of [selected] state.
     * @param enabled Controls the enabled state of the radio button. When `false`, this button will
     * not be selectable and appears disabled.
     */
    class TrailingRadioButton(
        internal val selected: Boolean,
        internal val onClick: (() -> Unit)?,
        internal val enabled: Boolean = true
    ) : OdsComponentContent<Nothing>(), Trailing {

        @Composable
        override fun Content(modifier: Modifier) {
            OdsRadioButton(modifier = modifier, selected = selected, onClick = null, enabled = enabled) // a11y: `onClick` is done on list item row click
        }
    }

    /**
     * A trailing icon in an [OdsListItem].
     */
    class TrailingIcon : OdsComponentIcon<Nothing>, Trailing {

        /**
         * Creates an instance of [OdsListItem.TrailingIcon].
         *
         * @param painter The painter to draw.
         * @param contentDescription The content description associated to this [OdsListItem.TrailingIcon].
         * @param onClick Will be called when the user clicks on the icon.
         */
        constructor(painter: Painter, contentDescription: String, onClick: (() -> Unit)?) : super(painter, contentDescription, onClick = onClick)

        /**
         * Creates an instance of [OdsListItem.TrailingIcon].
         *
         * @param imageVector The image vector to draw.
         * @param contentDescription The content description associated to this [OdsListItem.TrailingIcon].
         * @param onClick Will be called when the user clicks on the icon.
         */
        constructor(imageVector: ImageVector, contentDescription: String, onClick: (() -> Unit)?) : super(
            imageVector,
            contentDescription,
            onClick = onClick
        )

        /**
         * Creates an instance of [OdsListItem.TrailingIcon].
         *
         * @param bitmap The image bitmap to draw.
         * @param contentDescription The content description associated to this [OdsListItem.TrailingIcon].
         * @param onClick Will be called when the user clicks on the icon.
         */
        constructor(bitmap: ImageBitmap, contentDescription: String, onClick: (() -> Unit)?) : super(bitmap, contentDescription, onClick = onClick)

        override val tint: Color? // Despite warning, keep it optional as in parent class
            @Composable
            get() = OdsTheme.colors.onSurface

        @Composable
        override fun Content(modifier: Modifier) {
            super.Content(modifier = modifier.clip(RoundedCornerShape(12.dp)))
        }
    }

    /**
     * A trailing caption in an [OdsListItem].
     *
     * @constructor Creates an instance of [OdsListItem.TrailingCaption].
     * @param text The caption text.
     */
    class TrailingCaption(private val text: String) : OdsComponentContent<Nothing>(), Trailing {

        @Composable
        override fun Content(modifier: Modifier) {
            OdsTextCaption(modifier = modifier, text = text)
        }
    }

}

@Composable
private fun getDividerStartIndent(leadingIconType: OdsListItem.LeadingIcon.Type?): Dp {
    return when (leadingIconType) {
        OdsListItem.LeadingIcon.Type.Icon,
        OdsListItem.LeadingIcon.Type.CircularImage -> dimensionResource(id = R.dimen.avatar_size) + dimensionResource(id = R.dimen.spacing_m).times(2)
        OdsListItem.LeadingIcon.Type.SquareImage -> dimensionResource(id = R.dimen.list_square_image_size) + dimensionResource(id = R.dimen.spacing_m).times(2)
        OdsListItem.LeadingIcon.Type.WideImage -> dimensionResource(id = R.dimen.list_wide_image_width) + dimensionResource(id = R.dimen.spacing_m)
        null -> dimensionResource(id = R.dimen.spacing_m)
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsListItem(@PreviewParameter(OdsListItemPreviewParameterProvider::class) parameter: OdsListItemPreviewParameter) = Preview {
    with(parameter) {
        var trailingState by remember { mutableStateOf(false) }
        val leadingIcon = leadingIconType?.let { iconType ->
            val painter = when (iconType) {
                OdsListItem.LeadingIcon.Type.Icon -> rememberVectorPainter(image = Icons.Default.AccountBox)
                OdsListItem.LeadingIcon.Type.CircularImage,
                OdsListItem.LeadingIcon.Type.SquareImage,
                OdsListItem.LeadingIcon.Type.WideImage -> painterResource(id = R.drawable.placeholder_small)
            }
            OdsListItem.LeadingIcon(iconType, painter, "")
        }
        val trailing = when (trailingClass) {
            OdsListItem.TrailingCheckbox::class.java -> OdsListItem.TrailingCheckbox(trailingState, { trailingState = it })
            OdsListItem.TrailingSwitch::class.java -> OdsListItem.TrailingSwitch(trailingState, { trailingState = it })
            OdsListItem.TrailingRadioButton::class.java -> OdsListItem.TrailingRadioButton(trailingState, { trailingState = !trailingState })
            OdsListItem.TrailingIcon::class.java -> OdsListItem.TrailingIcon(painterResource(id = android.R.drawable.ic_dialog_info), "", null)
            OdsListItem.TrailingCaption::class.java -> OdsListItem.TrailingCaption(text = "caption")
            else -> null
        }

        if (singleLineSecondaryText || secondaryText == null) {
            OdsListItem(
                text = "Text",
                leadingIcon = leadingIcon,
                subtitle = secondaryText,
                trailing = trailing
            )
        } else {
            OdsListItem(
                text = "Text",
                leadingIcon = leadingIcon,
                description = secondaryText,
                trailing = trailing
            )
        }
    }
}

internal data class OdsListItemPreviewParameter(
    val secondaryText: String?,
    val singleLineSecondaryText: Boolean,
    val leadingIconType: OdsListItem.LeadingIcon.Type?,
    val trailingClass: Class<out OdsListItem.Trailing>?
)

private class OdsListItemPreviewParameterProvider : BasicPreviewParameterProvider<OdsListItemPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OdsListItemPreviewParameter>
    get() {
        val shortSecondaryText = "Secondary text"
        val longSecondaryText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor."

        return listOf(
            OdsListItemPreviewParameter(null, true, null, null),
            OdsListItemPreviewParameter(longSecondaryText, true, null, OdsListItem.TrailingCheckbox::class.java),
            OdsListItemPreviewParameter(shortSecondaryText, true, OdsListItem.LeadingIcon.Type.Icon, OdsListItem.TrailingIcon::class.java),
            OdsListItemPreviewParameter(longSecondaryText, false, OdsListItem.LeadingIcon.Type.SquareImage, OdsListItem.TrailingSwitch::class.java),
            OdsListItemPreviewParameter(longSecondaryText, false, OdsListItem.LeadingIcon.Type.WideImage, OdsListItem.TrailingCaption::class.java),
            OdsListItemPreviewParameter(shortSecondaryText, true, OdsListItem.LeadingIcon.Type.CircularImage, OdsListItem.TrailingRadioButton::class.java)
        )
    }
