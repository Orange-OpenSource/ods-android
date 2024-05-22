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

package com.orange.ods.app.ui.guidelines.color

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
import com.orange.ods.app.R
import com.orange.ods.app.ui.utilities.composable.Title
import com.orange.ods.app.ui.utilities.extension.CatalogEntry
import com.orange.ods.app.ui.utilities.extension.entries
import com.orange.ods.compose.component.button.OdsButton
import com.orange.ods.compose.text.OdsText
import com.orange.ods.compose.theme.OdsTheme
import com.orange.ods.theme.extension.toHexString
import com.orange.ods.theme.extension.toRgbString

@Composable
fun GuidelineColorScreen() {
    val guidelineColors = OdsTheme.colors

    val coreColors = guidelineColors.entries
    val functionalColors = guidelineColors.functional.entries

    LazyColumn(
        contentPadding = PaddingValues(
            start = OdsTheme.spacings.medium.dp,
            end = OdsTheme.spacings.medium.dp,
            top = dimensionResource(id = com.orange.ods.R.dimen.screen_vertical_margin),
            bottom = dimensionResource(id = com.orange.ods.R.dimen.screen_vertical_margin)
        ),
        verticalArrangement = Arrangement.spacedBy(OdsTheme.spacings.medium.dp),
    ) {
        if (coreColors.isNotEmpty()) {
            item {
                Title(textRes = R.string.guideline_colour_core, modifier = Modifier.semantics { heading() })
            }
            items(coreColors.chunked(2)) { rowColors ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(OdsTheme.spacings.medium.dp),
                ) {
                    BigColorItem(colorEntry = rowColors[0])
                    BigColorItem(colorEntry = rowColors[1])
                }
            }
        }

        if (functionalColors.isNotEmpty()) {
            item {
                Title(textRes = R.string.guideline_colour_functional, modifier = Modifier.semantics { heading() }, topPadding = true)
            }
            items(functionalColors.chunked(2)) { rowColors ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(OdsTheme.spacings.medium.dp),
                ) {
                    BigColorItem(colorEntry = rowColors[0])
                    BigColorItem(colorEntry = rowColors[1])
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun RowScope.SmallColorItem(colorEntry: CatalogEntry<Color>) {
    val openDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    val colorValue = colorEntry.value
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
        OdsText(
            text = colorEntry.description,
            modifier = Modifier.padding(top = OdsTheme.spacings.extraSmall.dp),
            style = OdsTheme.typography.titleLarge
        )
        OdsText(text = colorValue.toHexString(), style = OdsTheme.typography.bodySmall)
    }
    if (openDialog.value) {
        DialogColor(colorEntry = colorEntry, openDialog)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun RowScope.BigColorItem(colorEntry: CatalogEntry<Color>) {
    val openDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    Column(
        modifier = Modifier
            .weight(0.5f)
            .combinedClickable(
                onLongClick = { copyColorToClipboard(context, colorEntry.value, clipboardManager) },
                onClick = { openDialog.value = true }
            ),
    ) {
        val boxColorModifier = Modifier
            .background(color = colorEntry.value)
            .fillMaxWidth()
            .aspectRatio(1f)
        Box(
            modifier = if (colorEntry.value == OdsTheme.colors.background) {
                boxColorModifier.border(BorderStroke(1.dp, OdsTheme.colors.onBackground))
            } else {
                boxColorModifier
            }
        )
        OdsText(
            text = colorEntry.description,
            modifier = Modifier.padding(top = OdsTheme.spacings.extraSmall.dp),
            style = OdsTheme.typography.titleLarge
        )
        OdsText(text = colorEntry.name, style = OdsTheme.typography.bodyLarge)
        OdsText(
            modifier = Modifier.padding(top = OdsTheme.spacings.extraSmall.dp),
            text = colorEntry.value.toHexString(),
            style = OdsTheme.typography.bodySmall
        )
        OdsText(
            text = colorEntry.value.toRgbString(),
            style = OdsTheme.typography.bodySmall
        )
    }
    if (openDialog.value) {
        DialogColor(colorEntry = colorEntry, openDialog)
    }
}

@Composable
fun DialogColor(colorEntry: CatalogEntry<Color>, openDialog: MutableState<Boolean>) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current
    val colorValue = colorEntry.value
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
                    .padding(
                        horizontal = OdsTheme.spacings.medium.dp,
                        vertical = OdsTheme.spacings.small.dp
                    )
            ) {
                OdsText(text = colorEntry.description, style = OdsTheme.typography.headlineSmall)
                OdsText(
                    modifier = Modifier.padding(top = OdsTheme.spacings.extraSmall.dp),
                    text = colorEntry.name,
                    style = OdsTheme.typography.bodyLarge
                )
                OdsText(
                    modifier = Modifier.padding(top = OdsTheme.spacings.small.dp),
                    text = colorValue.toHexString(),
                    style = OdsTheme.typography.bodyLarge
                )
                OdsText(
                    modifier = Modifier.padding(top = OdsTheme.spacings.small.dp),
                    text = colorValue.toRgbString(),
                    style = OdsTheme.typography.bodyLarge
                )
                OdsButton(
                    modifier = Modifier.padding(top = OdsTheme.spacings.small.dp),
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
