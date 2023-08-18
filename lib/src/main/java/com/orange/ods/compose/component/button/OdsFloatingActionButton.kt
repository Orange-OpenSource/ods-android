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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ButtonDefaults
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
import com.orange.ods.compose.component.content.OdsComponentIcon
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
 * @param icon The FAB icon.
 * @param onClick callback invoked when this FAB is clicked
 * @param modifier [Modifier] to be applied to this FAB.
 * @param mini If `true`, the size of the FAB will be 40dp, otherwise the default size will be used.
 */
@Composable
@OdsComposable
fun OdsFloatingActionButton(
    icon: OdsFloatingActionButtonIcon,
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
        icon.Content(modifier = Modifier.size(FabIconSize))
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
 * @param onClick callback invoked when this FAB is clicked
 * @param text The text displayed in the [OdsExtendedFloatingActionButton]
 * @param icon The FAB icon.
 * @param modifier [Modifier] to be applied to this FAB.
 */
@Composable
@OdsComposable
fun OdsExtendedFloatingActionButton(
    onClick: () -> Unit,
    text: String,
    icon: OdsFloatingActionButtonIcon,
    modifier: Modifier = Modifier,
) {
    ExtendedFloatingActionButton(
        onClick = onClick,
        text = { Text(text = text.uppercase(), style = OdsTheme.typography.button) },
        modifier = modifier,
        backgroundColor = OdsTheme.colors.component.floatingActionButton.background,
        contentColor = OdsTheme.colors.component.floatingActionButton.content,
        icon = { icon.Content(modifier = Modifier.size(FabIconSize)) }
    )
}

/**
 * A button icon in an [OdsFloatingActionButton].
 */
class OdsFloatingActionButtonIcon : OdsComponentIcon {

    /**
     * Creates an instance of [OdsFloatingActionButtonIcon].
     *
     * @param painter Painter of the icon.
     * @param contentDescription The content description associated to this [OdsFloatingActionButtonIcon].
     */
    constructor(painter: Painter, contentDescription: String) : super(painter as Any, contentDescription)

    /**
     * Creates an instance of [OdsFloatingActionButtonIcon].
     *
     * @param imageVector Image vector of the icon.
     * @param contentDescription The content description associated to this [OdsFloatingActionButtonIcon].
     */
    constructor(imageVector: ImageVector, contentDescription: String) : super(imageVector as Any, contentDescription)

    /**
     * Creates an instance of [OdsFloatingActionButtonIcon].
     *
     * @param bitmap Image bitmap of the icon.
     * @param contentDescription The content description associated to this [OdsFloatingActionButtonIcon].
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
private fun PreviewOdsFloatingActionButton(@PreviewParameter(OdsFloatingActionButtonPreviewParameterProvider::class) isMini: Boolean) =
    Preview {
        Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.spacing_s))) {
            OdsFloatingActionButton(
                onClick = {},
                mini = isMini,
                icon = OdsFloatingActionButtonIcon(painterResource(id = android.R.drawable.ic_input_add), "Add")
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
            icon = OdsFloatingActionButtonIcon(painterResource(id = android.R.drawable.ic_input_add), ""),
            text = "Add"
        )
    }
}
