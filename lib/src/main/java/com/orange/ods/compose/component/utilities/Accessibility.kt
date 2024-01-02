/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.compose.component.utilities

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.orange.ods.R

/**
 * Returns the selection state ready for vocalization according to the [selected] provided value.
 *
 * @param selected Set it to true if the element is selected, false otherwise.
 */
@Composable
internal fun selectionStateDescription(selected: Boolean) =
    if (selected) stringResource(id = R.string.ods_selected_stateA11y) else stringResource(id = R.string.ods_unselected_stateA11y)

/**
 * Returns the enabled/disabled state ready for vocalization according to the [enabled] provided value.
 *
 * @param enabled Set it to true if the element is enabled, false otherwise.
 */
@Composable
internal fun enabledStateDescription(enabled: Boolean) =
    if (!enabled) stringResource(id = R.string.ods_disabled_stateA11y) else ""