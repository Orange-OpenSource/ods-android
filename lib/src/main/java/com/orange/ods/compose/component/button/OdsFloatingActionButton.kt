/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.orange.ods.R
import com.orange.ods.compose.component.OdsComposable
import com.orange.ods.compose.component.content.OdsIconBuilder
import com.orange.ods.compose.component.utilities.BasicPreviewParameterProvider
import com.orange.ods.compose.component.utilities.Preview
import com.orange.ods.compose.component.utilities.UiModePreviews
import com.orange.ods.compose.theme.OdsTheme

private val MiniFabSize = 40.dp
private val FabIconSize = 24.dp

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/577022-buttons-fab/b/101b2a" target="_blank">ODS Floating Action Buttons</a>.
 *
 * A floating action button (FAB) represents the primary action of a screen.
 *
 * This FAB is typically used with an [Icon]
 * @see [OdsExtendedFloatingActionButton] for an extended FAB that contains text and an optional icon.
 *
 * @see androidx.compose.material.FloatingActionButton for further information.
 *
 * @param icon Icon used into the FAB.
 * @param onClick Callback invoked on FAB click.
 * @param modifier [Modifier] applied to the FAB.
 * @param mini Controls the size of the FAB. If `true`, the size of the FAB will be 40dp, otherwise the default size will be used.
 */
@Composable
@OdsComposable
fun OdsFloatingActionButton(
    icon: OdsFloatingActionButtonIconBuilder,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    mini: Boolean = false,
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier.let { if (mini) it.size(MiniFabSize) else it },
        backgroundColor = OdsTheme.colors.component.floatingActionButton.background,
        contentColor = OdsTheme.colors.component.floatingActionButton.content
    ) {
        icon.Content()
    }
}

/**
 * <a href="https://system.design.orange.com/0c1af118d/p/577022-buttons-fab/b/101b2a" target="_blank">ODS Floating Action Buttons</a>.
 *
 * The extended FAB is wider than a regular FAB, and it includes a text label.
 *
 * This extended FAB contains text and an optional icon that will be placed at the start.
 * @see [OdsFloatingActionButton] for a FAB that just contains an icon.
 *
 * @see androidx.compose.material.ExtendedFloatingActionButton for further information.
 *
 * @param icon Icon used into the FAB.
 * @param onClick Callback invoked on FAB click.
 * @param text Text displayed into the FAB.
 * @param modifier [Modifier] applied to the FAB.
 */
@Composable
@OdsComposable
fun OdsExtendedFloatingActionButton(
    icon: OdsFloatingActionButtonIconBuilder,
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
) {
    ExtendedFloatingActionButton(
        onClick = onClick,
        text = { Text(text = text.uppercase(), style = OdsTheme.typography.button) },
        modifier = modifier,
        backgroundColor = OdsTheme.colors.component.floatingActionButton.background,
        contentColor = OdsTheme.colors.component.floatingActionButton.content,
        icon = { icon.Content() }
    )
}

/**
 * A button icon in an [OdsFloatingActionButton].
 */
class OdsFloatingActionButtonIconBuilder : OdsIconBuilder<Nothing> {

    /**
     * Creates an instance of [OdsFloatingActionButtonIconBuilder].
     *
     * @param painter Painter of the icon.
     * @param contentDescription The content description associated to this [OdsFloatingActionButtonIconBuilder].
     */
    constructor(painter: Painter, contentDescription: String) : super(painter, contentDescription)

    /**
     * Creates an instance of [OdsFloatingActionButtonIconBuilder].
     *
     * @param imageVector Image vector of the icon.
     * @param contentDescription The content description associated to this [OdsFloatingActionButtonIconBuilder].
     */
    constructor(imageVector: ImageVector, contentDescription: String) : super(imageVector, contentDescription)

    /**
     * Creates an instance of [OdsFloatingActionButtonIconBuilder].
     *
     * @param bitmap Image bitmap of the icon.
     * @param contentDescription The content description associated to this [OdsFloatingActionButtonIconBuilder].
     */
    constructor(bitmap: ImageBitmap, contentDescription: String) : super(bitmap, contentDescription)

    @Composable
    override fun Content(modifier: Modifier) {
        super.Content(modifier = modifier.size(FabIconSize))
    }
}

@UiModePreviews.Default
@Composable
private fun PreviewOdsFloatingActionButton(@PreviewParameter(OdsFloatingActionButtonPreviewParameterProvider::class) isMini: Boolean) =
    Preview {
        Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacing_s))) {
            OdsFloatingActionButton(
                onClick = {},
                mini = isMini,
                icon = OdsFloatingActionButtonIconBuilder(painterResource(id = android.R.drawable.ic_input_add), "Add")
            )
        }
    }

internal class OdsFloatingActionButtonPreviewParameterProvider : BasicPreviewParameterProvider<Boolean>(false, true)

@UiModePreviews.Button
@Composable
private fun PreviewOdsExtendedFloatingActionButton() = Preview {
    Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacing_s))) {
        OdsExtendedFloatingActionButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {},
            icon = OdsFloatingActionButtonIconBuilder(painterResource(id = android.R.drawable.ic_input_add), ""),
            text = "Add"
        )
    }
}
