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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.DrawerDefaults
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.content.OdsCircularImageBuilder
import com.orange.ods.compose.component.divider.OdsDivider
import com.orange.ods.compose.component.list.OdsListItem
import com.orange.ods.compose.component.list.OdsListItemIconBuilder
import com.orange.ods.compose.component.content.OdsComponentBuilder
import com.orange.ods.compose.component.content.OdsImageBuilder
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.text.OdsTextBody2
import com.orange.ods.compose.theme.OdsTheme

private val DrawerHeaderMaxHeight = 167.dp
private const val SelectedItemOpacity = 20f / 255f

/**
 * Navigation drawers provide ergonomic access to destinations in an app.
 *
 * @param header Content descriptor of the drawer header.
 * @param items List of [OdsModalDrawerItem] displayed in a column inside the modal drawer.
 * @param modifier [Modifier] applied to this drawer.
 * @param state State of the modal drawer.
 * @param selectedItem Selected [OdsModalDrawerListItem] that appears in selected state.
 * @param content Content of the rest of the UI.
 */
@Composable
@OdsComposable
fun OdsModalDrawer(
    header: OdsModalDrawerHeader,
    items: List<OdsModalDrawerItem>,
    modifier: Modifier = Modifier,
    state: DrawerState = rememberDrawerState(DrawerValue.Closed),
    selectedItem: OdsModalDrawerListItem? = null,
    content: @Composable () -> Unit
) {
    ModalDrawer(
        drawerContent = {
            header.Content()
            OdsDivider()
            LazyColumn {
                items(items = items.filterIsInstance<OdsComponentBuilder<OdsModalDrawerItem.ExtraParameters>>()) { item ->
                    item.Content(OdsModalDrawerItem.ExtraParameters(item == selectedItem))
                }
            }
        },
        drawerState = state,
        modifier = modifier,
        drawerBackgroundColor = OdsTheme.colors.surface,
        drawerContentColor = contentColorFor(OdsTheme.colors.onSurface),
        scrimColor = DrawerDefaults.scrimColor,
        content = content
    )
}

/**
 * Represents an item in an [OdsModalDrawer] content.
 * It can be a clickable list item ([OdsModalDrawerListItem]), a section header ([OdsModalDrawerSectionHeader]) or
 * a divider ([OdsModalDrawerDivider]).
 * These items will be displayed vertically in the [OdsModalDrawer] after the header part.
 */
sealed interface OdsModalDrawerItem {
    data class ExtraParameters(val selected: Boolean) : OdsComponentBuilder.ExtraParameters()
}

/**
 * A section header in the [OdsModalDrawer] content.
 *
 * @property label Label of the section header.
 */
data class OdsModalDrawerSectionHeader(private val label: String) : OdsModalDrawerItem, OdsComponentBuilder<OdsModalDrawerItem.ExtraParameters>() {
    @Composable
    override fun Content(modifier: Modifier) {
        Column {
            OdsDivider()
            OdsTextBody2(
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_m), start = dimensionResource(id = R.dimen.spacing_m)),
                text = label
            )
        }
    }
}

/**
 * A divider in the [OdsModalDrawer] content.
 */
data object OdsModalDrawerDivider : OdsModalDrawerItem, OdsComponentBuilder<OdsModalDrawerItem.ExtraParameters>() {
    @Composable
    override fun Content(modifier: Modifier) = OdsDivider()
}

/**
 * A list item in the [OdsModalDrawer] content.
 *
 * @property text Text displayed in the modal drawer list item.
 * @property leadingIcon Leading icon displayed in the modal drawer list item.
 * @property onClick Callback invoked on an `OdsModalDrawerListItem` click. Provides the clicked `OdsModalDrawerListItem`.
 */
data class OdsModalDrawerListItem(
    private val text: String,
    private val leadingIcon: Painter? = null,
    private val onClick: (OdsModalDrawerListItem) -> Unit
) : OdsModalDrawerItem, OdsComponentBuilder<OdsModalDrawerItem.ExtraParameters>() {

    @Composable
    override fun Content(modifier: Modifier) {
        val selected = extraParameters.selected
        CompositionLocalProvider(LocalRippleTheme provides OdsModalDrawerListItemRippleTheme) {
            OdsListItem(
                text = text,
                textColor = if (selected) OdsTheme.colors.primaryVariant else OdsTheme.colors.onSurface,
                textStyle = if (selected) OdsTheme.typography.subtitle2 else OdsTheme.typography.subtitle2.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .selectable(selected = selected, onClick = { onClick(this@OdsModalDrawerListItem) })
                    .let {
                        if (selected) it.background(OdsTheme.colors.primaryVariant.copy(alpha = SelectedItemOpacity)) else it
                    },
                icon = leadingIcon?.let {
                    OdsListItemIconBuilder(it, "", if (selected) OdsTheme.colors.primaryVariant else OdsTheme.colors.onSurface)
                }
            )
        }
    }
}

/**
 * The [OdsModalDrawer] header.
 *
 * @property title Title displayed in the header.
 * @property image Image displayed in the header. It should be an avatar image of type [OdsModalDrawerHeaderAvatar] or a background image
 * of type [OdsModalDrawerHeaderBackground].
 * @property subtitle Subtitle displayed below the [title] in the header.
 */
class OdsModalDrawerHeader(
    private var title: String,
    private val image: OdsModalDrawerHeaderImage? = null,
    private var subtitle: String? = null
) : OdsComponentBuilder<Nothing>() {

    @Composable
    override fun Content(modifier: Modifier) {

        when (image) {
            is OdsModalDrawerHeaderBackground ->
                Box(modifier = modifier.fillMaxWidth()) {
                    image.Content(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(DrawerHeaderMaxHeight)
                    )
                    Surface(
                        color = Color.Black.copy(alpha = 0.8f),
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .fillMaxWidth()
                    ) {
                        OdsHeaderText(title = title, subtitle = subtitle, color = Color.White)
                    }
                }

            is OdsModalDrawerHeaderAvatar ->
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(DrawerHeaderMaxHeight),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    image.Content(
                        modifier = Modifier.padding(
                            start = dimensionResource(id = R.dimen.spacing_m),
                            bottom = dimensionResource(id = R.dimen.spacing_m)
                        )
                    )
                    OdsHeaderText(title = title, subtitle = subtitle, color = OdsTheme.colors.onSurface)
                }

            else -> OdsHeaderText(title = title, subtitle = subtitle, color = OdsTheme.colors.onSurface)
        }
    }

}

/**
 * An image that can be added in the [OdsModalDrawerHeader].
 */
sealed interface OdsModalDrawerHeaderImage

/**
 * An avatar in [OdsModalDrawerHeader].
 */
class OdsModalDrawerHeaderAvatar : OdsModalDrawerHeaderImage, OdsCircularImageBuilder {

    /**
     * Creates an instance of [OdsModalDrawerHeaderAvatar].
     *
     * @param painter The painter to draw.
     * @param contentDescription The content description associated to this [OdsModalDrawerHeaderAvatar].
     */
    constructor(painter: Painter, contentDescription: String) : super(painter, contentDescription)

    /**
     * Creates an instance of [OdsModalDrawerHeaderAvatar].
     *
     * @param imageVector The image vector to draw.
     * @param contentDescription The content description associated to this [OdsModalDrawerHeaderAvatar].
     */
    constructor(imageVector: ImageVector, contentDescription: String) : super(imageVector, contentDescription)

    /**
     * Creates an instance of [OdsModalDrawerHeaderAvatar].
     *
     * @param bitmap The image bitmap to draw.
     * @param contentDescription The content description associated to this [OdsModalDrawerHeaderAvatar].
     */
    constructor(bitmap: ImageBitmap, contentDescription: String) : super(bitmap, contentDescription)
}

/**
 * A background image in the [OdsModalDrawerHeader].
 */
class OdsModalDrawerHeaderBackground : OdsModalDrawerHeaderImage, OdsImageBuilder<Nothing> {

    /**
     * Creates an instance of [OdsModalDrawerHeaderBackground].
     *
     * @param painter The painter to draw.
     * @param contentScale The rule to apply to scale the image in this [OdsModalDrawerHeaderBackground], [ContentScale.Crop] by default.
     */
    constructor(painter: Painter, contentScale: ContentScale = ContentScale.Crop) : super(painter, "", contentScale = contentScale)

    /**
     * Creates an instance of [OdsModalDrawerHeaderBackground].
     *
     * @param imageVector The image vector to draw.
     * @param contentScale The rule to apply to scale the image in this [OdsModalDrawerHeaderBackground], [ContentScale.Crop] by default.
     */
    constructor(imageVector: ImageVector, contentScale: ContentScale = ContentScale.Crop) : super(imageVector, "", contentScale = contentScale)

    /**
     * Creates an instance of [OdsModalDrawerHeaderBackground].
     *
     * @param bitmap The image bitmap to draw.
     * @param contentScale The rule to apply to scale the image in this [OdsModalDrawerHeaderBackground], [ContentScale.Crop] by default.
     */
    constructor(bitmap: ImageBitmap, contentScale: ContentScale = ContentScale.Crop) : super(bitmap, "", contentScale = contentScale)
}

private object OdsModalDrawerListItemRippleTheme : RippleTheme {

    @Composable
    override fun defaultColor() = OdsTheme.colors.primaryVariant

    @Composable
    override fun rippleAlpha() = RippleAlpha(SelectedItemOpacity, SelectedItemOpacity, SelectedItemOpacity, SelectedItemOpacity)
}

@Composable
private fun OdsHeaderText(title: String, subtitle: String?, color: Color) {
    Column(
        modifier = Modifier
            .padding(start = dimensionResource(id = R.dimen.spacing_m))
            .height(
                if (subtitle != null) dimensionResource(id = R.dimen.list_two_line_with_icon_item_height)
                else dimensionResource(id = R.dimen.list_single_line_item_height)
            ),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = title, color = color, style = OdsTheme.typography.h6)
        subtitle?.let { Text(text = it, color = color, style = OdsTheme.typography.body2) }
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsModalDrawer(@PreviewParameter(OdsModalDrawerPreviewParameterProvider::class) parameter: OdsModalDrawerPreviewParameter) =
    Preview {
        val listItemIcon = painterResource(id = R.drawable.ic_check)
        val items = if (parameter.hasItems) {
            listOf(
                OdsModalDrawerListItem("List item 1", listItemIcon) {},
                OdsModalDrawerDivider,
                OdsModalDrawerListItem("List item 2", listItemIcon) {},
                OdsModalDrawerSectionHeader("Section header"),
                OdsModalDrawerListItem("List item 3", listItemIcon) {}
            )
        } else {
            emptyList()
        }

        OdsModalDrawer(
            state = rememberDrawerState(DrawerValue.Open),
            content = {},
            header = OdsModalDrawerHeader(
                title = parameter.title,
                subtitle = parameter.subtitle,
                image = parameter.image?.let {
                    if (parameter.imageAsBackground) {
                        OdsModalDrawerHeaderBackground(painterResource(id = it))
                    } else {
                        OdsModalDrawerHeaderAvatar(painterResource(id = it), "")
                    }
                }
            ),
            items = items
        )
    }

private data class OdsModalDrawerPreviewParameter(
    val title: String,
    val subtitle: String?,
    val image: Int?,
    val imageAsBackground: Boolean = false,
    val hasItems: Boolean = true
)

private class OdsModalDrawerPreviewParameterProvider :
    BasicPreviewParameterProvider<OdsModalDrawerPreviewParameter>(*previewParameterValues.toTypedArray())

private val previewParameterValues: List<OdsModalDrawerPreviewParameter>
    get() {
        val title = "Headline 6"
        val image = R.drawable.placeholder
        val subtitle = "Body 2"

        return listOf(
            OdsModalDrawerPreviewParameter(title, subtitle, image, imageAsBackground = true),
            OdsModalDrawerPreviewParameter(title, null, image, imageAsBackground = true),
            OdsModalDrawerPreviewParameter(title, subtitle, image),
            OdsModalDrawerPreviewParameter(title, null, image),
            OdsModalDrawerPreviewParameter(title, null, image, hasItems = false),
            OdsModalDrawerPreviewParameter(title, subtitle, null)
        )
    }