/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.demo.ui.guidelines.color

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.orange.ods.compose.component.button.OdsButton
import com.orange.ods.compose.text.OdsTextBody1
import com.orange.ods.compose.text.OdsTextCaption
import com.orange.ods.compose.text.OdsTextH5
import com.orange.ods.compose.text.OdsTextH6
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.demo.R
import com.orange.ods.demo.ui.LocalMainTopAppBarManager
import com.orange.ods.demo.ui.LocalOdsDemoGuideline
import com.orange.ods.demo.ui.utilities.composable.Title
import com.orange.ods.demo.ui.utilities.getStringName
import com.orange.ods.theme.OdsColors
import com.orange.ods.theme.guideline.GuidelineColor
import com.orange.ods.theme.guideline.GuidelineColorType
import com.orange.ods.theme.guideline.toHexString
import com.orange.ods.theme.guideline.toRgbString
import kotlin.reflect.KProperty
import kotlin.reflect.full.extensionReceiverParameter
import kotlin.reflect.full.memberProperties

@Composable
fun GuidelineColorScreen() {
    LocalMainTopAppBarManager.current.updateTopAppBarTitle(R.string.guideline_color)

    val guidelineColors = LocalOdsDemoGuideline.current.guidelineColors

    val coreColors = guidelineColors.filter { it.type == GuidelineColorType.Core }
    val functionalColors = guidelineColors.filter { it.type == GuidelineColorType.Functional }
    val supportingColors = guidelineColors.filter { it.type == GuidelineColorType.Supporting }

    if (guidelineColors.isEmpty()) {
        OdsTextBody1(
            modifier = Modifier.padding(
                horizontal = dimensionResource(id = R.dimen.screen_horizontal_margin),
                vertical = dimensionResource(id = R.dimen.screen_vertical_margin)
            ),
            text = stringResource(id = R.string.guideline_colour_no_colours_defined)
        )
    } else {
        LazyColumn(
            contentPadding = PaddingValues(
                start = dimensionResource(id = R.dimen.spacing_m),
                end = dimensionResource(id = R.dimen.spacing_m),
                bottom = dimensionResource(id = R.dimen.screen_vertical_margin)
            ),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_m)),
        ) {
            if (coreColors.isNotEmpty()) {
                item {
                    Title(textRes = R.string.guideline_colour_core, modifier = Modifier.semantics { heading() })
                }
                items(coreColors.chunked(2)) { rowColors ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_m)),
                    ) {
                        BigColorItem(color = rowColors[0])
                        BigColorItem(color = rowColors[1])
                    }
                }
            }

            if (functionalColors.isNotEmpty()) {
                item {
                    Title(textRes = R.string.guideline_colour_functional, modifier = Modifier.semantics { heading() })
                }
                items(functionalColors.chunked(2)) { rowColors ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_m)),
                    ) {
                        BigColorItem(color = rowColors[0])
                        BigColorItem(color = rowColors[1])
                    }
                }
            }

            if (supportingColors.isNotEmpty()) {
                item {
                    Title(textRes = R.string.guideline_colour_supporting, modifier = Modifier.semantics { heading() })
                }
                items(supportingColors.chunked(3)) { rowColors ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_m)),
                    ) {
                        SmallColorItem(color = rowColors[0])
                        SmallColorItem(color = rowColors[1])
                        SmallColorItem(color = rowColors[2])
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun RowScope.SmallColorItem(color: GuidelineColor) {
    val openDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    val colorValue = color.getValue()
    Column(
        modifier = Modifier
            .weight(0.33f)
            .combinedClickable(
                onLongClick = { copyColorToClipboard(context, colorValue, clipboardManager) },
                onClick = { openDialog.value = true }
            )
    ) {
        Box(
            modifier = Modifier
                .background(color = colorValue)
                .fillMaxWidth()
                .aspectRatio(1f)
        )
        OdsTextH6(
            text = color.getName(),
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_xs))
        )
        OdsTextCaption(text = colorValue.toHexString())
    }
    if (openDialog.value) {
        DialogColor(color = color, openDialog)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun RowScope.BigColorItem(color: GuidelineColor) {
    val openDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    val colorValue = color.getValue()
    Column(
        modifier = Modifier
            .weight(0.5f)
            .combinedClickable(
                onLongClick = { copyColorToClipboard(context, colorValue, clipboardManager) },
                onClick = { openDialog.value = true }
            ),
    ) {
        val boxColorModifier = Modifier
            .background(color = colorValue)
            .fillMaxWidth()
            .aspectRatio(1f)
        Box(
            modifier = if (colorValue == OdsTheme.colors.background) {
                boxColorModifier.border(BorderStroke(1.dp, OdsTheme.colors.onBackground))
            } else {
                boxColorModifier
            }
        )
        OdsTextH6(
            text = color.getName(),
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_xs))
        )
        OdsTextBody1(text = color.callable.name)
        OdsTextCaption(
            modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_xs)),
            text = colorValue.toHexString()
        )
        OdsTextCaption(
            text = colorValue.toRgbString()
        )
    }
    if (openDialog.value) {
        DialogColor(color = color, openDialog)
    }
}

@Composable
private fun DialogColor(color: GuidelineColor, openDialog: MutableState<Boolean>) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    val colorValue = color.getValue()
    Dialog(
        onDismissRequest = { openDialog.value = false },
    ) {
        Column {
            Box(
                modifier = Modifier
                    .background(color = colorValue)
                    .fillMaxWidth()
                    .height(190.dp)
            )
            Column(
                modifier = Modifier
                    .background(color = OdsTheme.colors.background)
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(id = R.dimen.spacing_m), vertical = dimensionResource(id = R.dimen.spacing_s))
            ) {
                OdsTextH5(text = color.getName())
                OdsTextBody1(
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_xs)),
                    text = color.callable.name
                )
                OdsTextBody1(
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_s)),
                    text = colorValue.toHexString()
                )
                OdsTextBody1(
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_s)),
                    text = colorValue.toRgbString()
                )
                OdsTextBody1(
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_s)),
                    text = stringResource(
                        id = R.string.guideline_colour_xml,
                        context.getStringName(color.xmlResource)
                    )
                )
                OdsButton(
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_s)),
                    text = stringResource(id = R.string.guideline_colour_copy_to_clipboard_button_title),
                    onClick = { copyColorToClipboard(context, colorValue, clipboardManager) })
            }
        }
    }
}

private fun copyColorToClipboard(context: Context, color: Color, clipboardManager: ClipboardManager) {
    clipboardManager.setText(AnnotatedString(color.toHexString()))
    val text = String.format(context.getString(R.string.guideline_colour_copy_to_clipboard_toast))
    Toast
        .makeText(context, text, Toast.LENGTH_SHORT)
        .show()
}

@Composable
private fun GuidelineColor.getValue(): Color {
    val isColorsProperty = OdsColors::class.memberProperties.filterIsInstance<KProperty<Color>>().contains(callable)
    val isColorsExtensionProperty = callable.extensionReceiverParameter?.type?.classifier == Colors::class

    return if (isColorsProperty || isColorsExtensionProperty) callable.call(OdsTheme.colors) else callable.call()
}