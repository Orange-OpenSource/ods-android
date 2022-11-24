/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.theme

/**
 * Contains all the allowed components customizations.
 * This class can be extended to override default appearances.
 */
open class OdsComponentCustomizations {

    companion object {
        enum class ChipStyle {
            Filled, Outlined
        }
    }

    /**
     * By default the chips are outlined. If your theme needs to use filled chips, set this parameter to `ChipStyle.Filled`.
     */
    open val chipStyle: ChipStyle = ChipStyle.Outlined
}